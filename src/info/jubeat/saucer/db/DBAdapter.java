package info.jubeat.saucer.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import info.jubeat.saucer.domain.*;

public class DBAdapter {
	private ConnectionMaker connectionMaker;
	
	public DBAdapter(ConnectionMaker c) {
		this.connectionMaker = c;
	}
	
	public Player getUser() throws ClassNotFoundException, SQLException {
		Connection c = this.connectionMaker.makeConnection();
		PreparedStatement ps = c.prepareStatement(
				"select * from user");
		
		ResultSet rs = ps.executeQuery();
		rs.next();
		Player p = new Player();
		p.uid = rs.getString("uid");
		p.id = rs.getString("id");
		p.title = rs.getString("title");
		p.groups = rs.getString("groups");
		p.jimage = rs.getString("jimage");
		p.jubility = rs.getString("jubility");
		p.plma = rs.getString("plma");
		p.jubilityName = rs.getString("jubilityname");
		p.marker = rs.getString("marker");
		p.background = rs.getString("background");
		p.lastTime = rs.getString("lasttime");
		p.lastLocation = rs.getString("lastlocation");
		p.playTunes = rs.getString("playtune");
		p.fullCombo = rs.getString("fullcombo");
		p.excellent = rs.getString("excellent");
		
		rs.close();
		ps.close();
		c.close();
		
		return p;
	}
	
	public void addUser(Player p) throws ClassNotFoundException, SQLException {
		Connection c = this.connectionMaker.makeConnection();
		
		PreparedStatement ps = c.prepareStatement(
				"insert into user(uid, id, title, groups, jimage, jubility, plma, jubilityname, marker, background, lasttime, lastlocation, playtune, fullcombo, excellent) " +
				"values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		
		ps.setString(1, p.uid);
		ps.setString(2, p.id);
		ps.setString(3, p.title);
		ps.setString(4, p.groups);
		ps.setString(5, p.jimage);
		ps.setString(6, p.jubility);
		ps.setString(7, p.plma);
		ps.setString(8, p.jubilityName);
		ps.setString(9, p.marker);
		ps.setString(10, p.background);
		ps.setString(11, p.lastTime);
		ps.setString(12, p.lastLocation);
		ps.setString(13, p.playTunes);
		ps.setString(14, p.fullCombo);
		ps.setString(15, p.excellent);
		
		ps.executeUpdate();
		
		ps.close();
		c.close();
	}
	
	public boolean isExestUser(String fid) throws ClassNotFoundException, SQLException {
		Connection c = connectionMaker.makeConnection();
		
		PreparedStatement ps = c.prepareStatement(
				"select fid from user where fid = ?");
		ps.setString(1, fid);
		boolean res = ps.execute();
		
		ps.close();
		c.close();
		
		return res;
	}
	
	public void deleteUser(String fid) throws ClassNotFoundException, SQLException {
		Connection c = this.connectionMaker.makeConnection();
		
		PreparedStatement ps = c.prepareStatement(
				"delete from user where uid = ?");
		ps.setString(1, fid);
		
		ps.executeUpdate();
		
		ps.close();
		c.close();
	}
	
	public void update(MusicData md) throws ClassNotFoundException, SQLException {
		Connection c = connectionMaker.makeConnection();
		
		PreparedStatement ps = c.prepareStatement(
				"update jubeat " +
				"set bscf = ?, bsc = ?, advf = ?, adv = ?, extf = ?, ext = ? " + 
				"where info=?");
		
		ps.setInt(1, md.bscf);
		ps.setString(2, md.bsc);
		ps.setInt(3, md.advf);
		ps.setString(4, md.adv);
		ps.setInt(5, md.extf);
		ps.setString(6, md.ext);
		ps.setString(7, md.info);
		
		ps.executeUpdate();
		
		ps.close();
		c.close();
	}
	
	public String isExistMusic(String info) throws ClassNotFoundException, SQLException {
		Connection c = connectionMaker.makeConnection();
		
		PreparedStatement ps = c.prepareStatement(
				"select name from jubeat where info = ?");
		ps.setString(1, info);
		
		ResultSet rs = ps.executeQuery();
		rs.next();
		
		String res = null;
		if(rs.isLast())
			res = rs.getString("name");
		
		rs.close();
		ps.close();
		c.close();
		
		return res;
	}
	
	public void add(MusicData md) throws ClassNotFoundException, SQLException {
		Connection c = this.connectionMaker.makeConnection();
		
		PreparedStatement ps = c.prepareStatement(
				"insert into jubeat(name, bscf, bsc, advf, adv, extf, ext, info) values(?, ?, ?, ?, ?, ?, ?, ?)");
		
		ps.setString(1, md.name);
		ps.setInt(2, md.bscf);
		ps.setString(3, md.bsc);
		ps.setInt(4, md.advf);
		ps.setString(5, md.adv);
		ps.setInt(6, md.extf);
		ps.setString(7, md.ext);
		ps.setString(8, md.info);
		
		ps.executeUpdate();
		
		ps.close();
		c.close();
	}
	
	public MusicData get(String info)  throws ClassNotFoundException, SQLException {
		Connection c = this.connectionMaker.makeConnection();
		
		PreparedStatement ps = c.prepareStatement(
				"select * from jubeat where id = ?");
		ps.setString(1, info);

		ResultSet rs = ps.executeQuery();
		rs.next();
		MusicData md = new MusicData();
		md.name	= rs.getString("name");
		md.bscl = rs.getInt("bscl");
		md.bscf = rs.getInt("bscf");
		md.bsc	= rs.getString("bsc");
		md.advl = rs.getInt("advl");
		md.advf = rs.getInt("advf");
		md.adv	= rs.getString("adv");
		md.extl = rs.getInt("extl");
		md.extf = rs.getInt("extf");
		md.ext	= rs.getString("ext");
		md.info = rs.getString("info");
		
		rs.close();
		ps.close();
		c.close();

		return md;
	}
	
	public ArrayList<MusicData> getAll() throws ClassNotFoundException, SQLException {
		Connection c = this.connectionMaker.makeConnection();
		
		PreparedStatement ps = c.prepareStatement(
				"select * from jubeat order by extl desc, name asc");
		
		ResultSet rs = ps.executeQuery();
		
		ArrayList<MusicData> list = ResultSetToArrayList(rs);
		
		rs.close();
		ps.close();
		c.close();
		
		return list;
	}
	
	public ArrayList<String> getAllInfo() throws ClassNotFoundException, SQLException {
		Connection c = this.connectionMaker.makeConnection();
		
		PreparedStatement ps = c.prepareStatement(
				"select info from jubeat");
		
		ResultSet rs = ps.executeQuery();
		
		ArrayList<String> list = new ArrayList<String>();
		
		while(rs.next())
			list.add(rs.getString("info"));
		
		return list;
	}
	
	private ArrayList<MusicData> ResultSetToArrayList(ResultSet rs) throws SQLException {
		ArrayList<MusicData> list = new ArrayList<MusicData>();
		
		while(rs.next()) {
			MusicData md = new MusicData();
			md.name	= rs.getString("mname");
			md.bscl = rs.getInt("bscl");
			md.bscf = rs.getInt("bscf");
			md.bsc	= rs.getString("bsc");
			md.advl = rs.getInt("advl");
			md.advf = rs.getInt("advf");
			md.adv	= rs.getString("adv");
			md.extl = rs.getInt("extl");
			md.extf = rs.getInt("extf");
			md.ext	= rs.getString("ext");
			md.info = rs.getString("mid");
			
			list.add(md);
		}
		return list;
	}
	
	public void updateLevels(ArrayList<MusicData> list) throws ClassNotFoundException, SQLException {
		Connection c = this.connectionMaker.makeConnection();
		
		for(MusicData md : list) {
			System.out.println(md.info + " : bsc " + md.bscl + " / adv " + md.advl + " / ext " + md.extl);
			PreparedStatement ps = c.prepareStatement(
				//	"INSERT INTO jubeat(bscl, advl, extl, info) values(?, ?, ?, ?)"
					"UPDATE jubeat SET bscl = ?, advl = ?, extl = ? WHERE info = ?"
				//	"update jubeat set bscl = " + md.bscl + ", advl = " + md.advl + ", extl = " + md.extl + " where info = " + md.info
					);
			/* */
			ps.setInt(1, md.bscl);
			ps.setInt(2, md.advl);
			ps.setInt(3, md.extl);
			ps.setString(4, md.info);
			/* */
			ps.execute();
			ps.close();
		}
		
		c.close();
	}
	
	public void updateLevelToInfo(ArrayList<MusicData> list) throws ClassNotFoundException, SQLException {
		Connection c = this.connectionMaker.makeConnection();
		
		for(MusicData md : list) {
			System.out.println(md.info + " : bsc " + md.bscl + " / adv " + md.advl + " / ext " + md.extl);
			PreparedStatement ps = c.prepareStatement(
					"INSERT INTO musicinfo(mname, bscl, advl, extl, mid) VALUES(?, ?, ?, ?, ?) "
					 + "ON DUPLICATE KEY UPDATE mname = ?, bscl = ?, advl = ?, extl = ?"
					);
			// insert
			ps.setString(1, md.name);
			ps.setInt(2, md.bscl);
			ps.setInt(3, md.advl);
			ps.setInt(4, md.extl);
			ps.setString(5, md.info);
			
			// update 
			ps.setString(6, md.name);
			ps.setInt(7, md.bscl);
			ps.setInt(8, md.advl);
			ps.setInt(9, md.extl);
			
			ps.execute();
			ps.close();
		}
		
		c.close();
	}
	
	public void insertAndUpdate(MusicData md, String uid) throws ClassNotFoundException, SQLException {
		Connection c = connectionMaker.makeConnection();
		
		PreparedStatement ps = c.prepareStatement(
				"INSERT INTO score(mid, uid, bsc, bscf, adv, advf, ext, extf) VALUES(?, ?, ?, ?, ?, ?, ?, ?) "
				+ "ON DUPLICATE KEY UPDATE bsc = ?, bscf = ?, adv = ?, advf = ?, ext = ?, extf = ?"
				);
		
		// insert
		ps.setString(1, md.info);
		ps.setString(2, uid);
		ps.setString(3, md.bsc);
		ps.setInt(4, md.bscf);
		ps.setString(5, md.adv);
		ps.setInt(6, md.advf);
		ps.setString(7, md.ext);
		ps.setInt(8, md.extf);
		
		// update
		ps.setString(9, md.bsc);
		ps.setInt(10, md.bscf);
		ps.setString(11, md.adv);
		ps.setInt(12, md.advf);
		ps.setString(13, md.ext);
		ps.setInt(14, md.extf);
		
		ps.executeUpdate();
		
		ps.close();
		c.close();
	}
	
	public ArrayList<MusicData> getAllMusicData(String uid) throws ClassNotFoundException, SQLException {
		Connection c = this.connectionMaker.makeConnection();
		
		PreparedStatement ps = c.prepareStatement(
			//	"select * from jubeat order by extl desc, name asc"
				"select m.mid, m.mname, m.bscl, s.bsc, s.bscf, m.advl, s.adv, s.advf, m.extl, s.ext, s.extf "
				+ "from musicinfo m, score s "
				+ "where m.mid = s.mid and s.uid = ? "
				+ "order by extl desc, mname asc "
				);
		ps.setString(1, uid);
		
		ResultSet rs = ps.executeQuery();
		
		ArrayList<MusicData> list = ResultSetToArrayList(rs);
		
		rs.close();
		ps.close();
		c.close();
		
		return list;
	}
}
