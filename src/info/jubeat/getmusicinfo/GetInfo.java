package info.jubeat.getmusicinfo;

import info.jubeat.saucer.HtmlDown;
import info.jubeat.saucer.domain.MusicData;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class GetInfo {
	public static void main(String... args) {
		GetNumber info = new GetNumber();
		ArrayList<String> list = info.getInfo();

		BufferedImage image = null;
		String host = "http://p.eagate.573.jp/game/jubeat/saucer/p/images/img.html?img_key=jacket";
		String minfo = "http://p.eagate.573.jp/game/jubeat/saucer/p/playdata/music_detail.html?mid=";
		ArrayList<MusicData> mdList = new ArrayList<MusicData>();
		int count = 0;
		String url = "http://p.eagate.573.jp/game/jubeat/saucer/p/playdata/music.html?page=";
		HtmlDown page = new HtmlDown("http://p.eagate.573.jp/game/jubeat/saucer/p/playdata/music.html");
		page.login();
		
		for(int i=1; i<6; i++) {
			page.setUrl(url+i);
			Document doc = Jsoup.parse(page.getData());

			Element table = doc.select("table[id=play_music_table]").first();
			Iterator<Element> it = table.select("td").iterator();
			it.next(); // colspan=5 line

			// for(int i=0; i<11; i++) {
			while (it.hasNext()) {
				String mid = it.next().child(0).attr("src");
				mid = mid.substring(mid.indexOf('=') + 7, mid.length());
				System.out.println(mid);
				it.next(); // name
				it.next();
				it.next();
				it.next(); // score

				if (!list.contains(mid)) {
					try {
						String jacketUrl = host + mid;
						String levelUrl = minfo + mid;
						count++;
						image = ImageIO.read(new URL(jacketUrl));
						downImage(image, mid); // for exception.
						page.setUrl(levelUrl);
						String mhtml = page.getData();

						/* get level */
						GetLevel level = new GetLevel(mhtml);

						MusicData md = new MusicData();
						md.info = mid;
						md.bscl = level.getBscLevel();
						md.advl = level.getAdvLevel();
						md.extl = level.getExtLevel();
						md.name = level.getName();
						mdList.add(md);
						/* */
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			info.insertLevels(mdList);
		}
		System.out.println("total " + count + " update complete.");
		System.out.println("musicinfo parsing complete.");
	}
	
	public static void downImage(BufferedImage img, String num) {
		try {
			ImageIO.write(img, "gif", new File("WebContent/res/jacket/" + num + ".gif"));
			System.out.println("image down complete : " + num);
		} catch (Exception e) {
			System.out.println("삭제곡입니다. : " + num);
		}
	}
}