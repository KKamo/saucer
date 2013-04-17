package info.jubeat.getmusicinfo;

import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class GetLevel {
	private Document doc;
	private Element table;
	Iterator<Element> it;
	private String name;
	
	public GetLevel(String html) {
		doc = Jsoup.parse(html);
		name = doc.select("div[id=contents_caption_text").first().text();
		System.out.println("name: " + name);
		table = doc.select("div[id=seq_container]").first();
		it = table.select("table").iterator();
	}
	
//	public int getaBscLevel(String html) { return Integer.parseInt(Jsoup.parse(html).select("div[id=seq_container]").first().select("table").iterator().next().child(0).child(0).child(1).html().substring(8)); }
	
	public String getName() {
		return name;
	}
	
	public int getBscLevel() {
		String lev = it.next().child(0).child(0).child(1).html();
		return Integer.parseInt(lev.substring(8));
	}
	
	public int getAdvLevel() {
		String lev = it.next().child(0).child(0).child(1).html();
		return Integer.parseInt(lev.substring(8));
	}
	
	public int getExtLevel() {
		String lev = it.next().child(0).child(0).child(1).html();
		return Integer.parseInt(lev.substring(8));
	}
}
