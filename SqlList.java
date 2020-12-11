package mgms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlList {

	/**
	 * åüçı
	 */
	public PreparedStatement Select(Connection conn) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("SELECT * FROM mydb.account");
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return ps;
	}
	
	/** 
	 * ìoò^
	 */
	public PreparedStatement UpdateInsert(Connection conn, int id, String name, int money) {
		PreparedStatement ps = null;
		try {
			PreparedStatement insert = conn.prepareStatement("INSERT INTO mydb.account VALUES(?, ?, ?)");
	        insert.setInt(1, id);
	        insert.setString(2, name);
	        insert.setInt(3, money);
	        insert.executeUpdate();
	        ps = conn.prepareStatement("SELECT * FROM mydb.account");
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return ps;
	}
	
	/**
	 * çXêV
	 * */
	public PreparedStatement UpdateStatus(Connection conn, int id, int money) {
		PreparedStatement ps = null;
		try {
			PreparedStatement update = conn.prepareStatement("UPDATE mydb.account SET MONEY=? WHERE ID=?");
			update.setInt(1, money);
			update.setInt(2, id);
	        update.executeUpdate();
	        ps = conn.prepareStatement("SELECT * FROM mydb.account");
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return ps;
	}
	
	/**
	 * çÌèú
	 * */
	public PreparedStatement DeleteStatus(Connection conn, int id) {
		PreparedStatement ps = null;
		try {
			PreparedStatement delete = conn.prepareStatement("DELETE FROM mydb.account WHERE ID=?");
			delete.setInt(1, id);
	        delete.executeUpdate();
	        ps = conn.prepareStatement("SELECT * FROM mydb.account");
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return ps;
	}
	
}
