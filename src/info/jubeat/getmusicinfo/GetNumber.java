package info.jubeat.getmusicinfo;

import info.jubeat.saucer.db.ConnectionMaker;
import info.jubeat.saucer.db.DBAdapter;
import info.jubeat.saucer.db.JConnectionMaker;
import info.jubeat.saucer.domain.MusicData;

import java.sql.SQLException;
import java.util.ArrayList;

public class GetNumber {
	private DBAdapter dba;
	private ArrayList<String> list;
	private ConnectionMaker connection;
	
	public GetNumber() {
		connection = new JConnectionMaker();
		dba = new DBAdapter(connection);
	}
	
	public ArrayList<String> getInfo() {
		try {
			list = dba.getAllInfo();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public void insertLevels(ArrayList<MusicData> list) {
		try {
			//dba.updateLevels(list);
			dba.updateLevelToInfo(list);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}