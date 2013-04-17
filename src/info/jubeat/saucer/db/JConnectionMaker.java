package info.jubeat.saucer.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JConnectionMaker implements ConnectionMaker {
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost/info?characterEncoding=UTF-8";
	private static final String user = "root";
	private static final String pass = "3120";
	
	public Connection makeConnection() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Class.forName(JDBC_DRIVER);
		Connection c = DriverManager.getConnection(DB_URL, user, pass);
		
		return c;
	}
}
