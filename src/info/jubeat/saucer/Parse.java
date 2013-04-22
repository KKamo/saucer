package info.jubeat.saucer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import info.jubeat.saucer.db.ConnectionMaker;
import info.jubeat.saucer.db.DBAdapter;
import info.jubeat.saucer.db.JConnectionMaker;
import info.jubeat.saucer.domain.MusicData;
import info.jubeat.saucer.domain.Player;

public class Parse {
	private String url;
	
	private ConnectionMaker connection;
	private DBAdapter dba;
	private ArrayList<MusicData> rs;
	private HtmlDown html;
	
	private static String nextPageHeader = "http://p.eagate.573.jp/game/jubeat/saucer/p/playdata/";

	public Parse() {
		connection = new JConnectionMaker();
		dba = new DBAdapter(connection);
	}

	public Parse(String murl) {
		connection = new JConnectionMaker();
		dba = new DBAdapter(connection);
		url = murl;
		
		this.html = new HtmlDown();
	}
	
	public Parse login() {
		html.login().setUrl(url);
		return this;
	}
	
	public Player getPlayer() {
		Player p = null;
		try { p = dba.getUser(); }
		catch (ClassNotFoundException e) { e.printStackTrace(); }
		catch (SQLException e) { e.printStackTrace(); }
		
		return p;
	}
	
	public ArrayList<MusicData> getAllMusic(String uid) {
		this.rs = null;
	//	try { this.rs = dba.getAll(); }
		try { this.rs = dba.getAllMusicData(uid); }
		catch (ClassNotFoundException e) { e.printStackTrace(); }
		catch (SQLException e) {e.printStackTrace(); }
		return rs;
	}
	
	public void parsing() {
		getPlayerData();
		getMusicData();
		html.close();
		println("parsing complete.");
	}

	private Player getPlayerData() {
		Document doc = Jsoup.parse(getPage(url));
		url = nextPageHeader + "music.html";
		
		Player player = playerParsing(doc);
		
		try {
			dba.insertAndUpdateUser(player);
		//	dba.deleteUser(player.uid);
		//	dba.addUser(player);
		} catch (ClassNotFoundException e) { e.printStackTrace();
		} catch (SQLException e) { e.printStackTrace(); }

		printUser(player);
		
		return player;
	}
	
	private void getMusicData() {
		while(url != null) {
			Document doc = getDocument();
			contentParsing(doc);
		}
	}
	
	private Document getDocument() {
		Document doc = Jsoup.parse(getPage(url));
		println("parsing : " + url);
		
		url = getNextURL(doc);
		
		return doc;
	}
	
	private String getPage(String pageUrl) {
		html.setUrl(pageUrl);
		String data = html.getData();
		
		return data;
	}
	
	private int isFullCombo(Element e) {
		if (e.child(0).attr("class").equals("fc1"))
			return 1;
		else
			return 0;
	}
	
	private MusicData nextMsuic(Iterator<Element> it) {
		MusicData md = new MusicData();
		Element e;
		md.info = it.next().child(0).attr("src");
		md.info = md.info.substring(md.info.indexOf('=') + 7, md.info.length());
		md.name = it.next().text();

		e = it.next();
		md.bsc = e.text();
		md.bscf = isFullCombo(e);

		e = it.next();
		md.adv = e.text();
		md.advf = isFullCombo(e);

		e = it.next();
		md.ext = e.text();
		md.extf = isFullCombo(e);
		
		return md;
	}
	
	private Parse contentParsing(Document doc) {
		Element table = doc.select("table[id=play_music_table]").first();
		String uid = getPlayer().uid;

		Iterator<Element> it = table.select("td").iterator();
		it.next();

		try {
			MusicData md;

			while (it.hasNext()) {
				md = nextMsuic(it);
				/* *
				if (dba.isExistMusic(md.info) != null)
					dba.update(md);
				else
					dba.add(md);
					/* */
				dba.insertAndUpdate(md, uid);
			}
		} catch (ClassNotFoundException ce) {
			// TODO: handle exception
			ce.printStackTrace();
		} catch (SQLException se) {
			// TODO: handle exception
			se.printStackTrace();
		}

		return this;
	}
	
	private Player playerParsing(Document doc) {
		Player player = new Player();
		Elements divs = doc.select(".top");

		String temp = "";
		for (Element e : divs)
			temp = e.text();

		String[] temp2 = temp.split(" ");
		player.jubilityName = temp2[0];
		player.jubility = temp2[1];

		divs = doc.select(".middle");
		for (Element e : divs)
			temp = e.text();

		temp2 = temp.split(" ");
		player.plma = temp2[2].substring(0, 5);

		/* */
		Element e2 = doc.getElementById("pname");
		temp2 = e2.text().split(" ");
		player.id = temp2[1];
		player.title = temp2[0];

		divs = doc.select(".bottom");
		for (Element e : divs)
			temp = e.text();
		
		player.lastTime = temp.substring(8, 25);
		player.lastLocation = temp.substring(25);

		e2 = doc.getElementById("play_detail");
		temp2 = e2.text().split(" ");
		player.playTunes = temp2[1].substring(0, temp2[1].length() - 4);
		player.fullCombo = temp2[4].substring(0, temp2[4].length() - 1);
		player.excellent = temp2[6].substring(0, temp2[6].length() - 1);
		/* */

		e2 = doc.select("div[id=playdata_info_left]").first();
		temp = e2.child(0).attr("src");
		player.jimage = temp.substring(temp.indexOf("=") + 1, temp.length());

		e2 = doc.select("div[id=rival_id]").first();
		player.uid = e2.text();

		e2 = doc.select("div[id=active_group]").first();
		player.groups = e2.text();

		e2 = doc.select("div[id=marker]").first();
		temp = e2.child(1).child(0).attr("src");
		player.marker = temp.substring(temp.indexOf("=") + 1, temp.length());

		e2 = doc.select("div[id=background]").first();
		temp = e2.child(1).child(0).attr("src");
		player.background = temp.substring(temp.indexOf("=") + 1, temp.length());

		return player;
	}

	private void printUser(Player p) {
		println("----------------- player info -----------------");
		println(p.id + " / " + p.jubility + "(" + p.plma + ")" + " / " + p.jubilityName);
		println(p.lastTime + " / " + p.lastLocation);
		println("play tunes : " + p.playTunes + " / " + "full combo : " + p.fullCombo + " / " + "excellent : " + p.excellent);
	}
	
	private String getNextURL(Document doc) {
		String nexturl = null;
		Element next = doc.select("li[class=next]").first();

		if(!next.html().equalsIgnoreCase("next"))
			if(next.child(0).hasAttr("href"))
				nexturl = nextPageHeader + next.child(0).attr("href").replace("&amp;", "&");

		return nexturl;
	}
	
	public void println(String str) {
		System.out.println(str);
	}
	
	public void print(String str) {
		System.out.print(str);
	}
}
