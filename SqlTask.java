package mgms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import mgms.SqlList;

public class SqlTask {

	private static SqlList sqlList = new SqlList();
	
	/**
	 * 
	 * @author 下山
	 * 引数 ・・・ 処理番号(0:データ削除, 1:データ更新), id, name, money を入力
	 * @see deposit
	 * 
	 */
	public static void main(String[] args) {
		
		//DB接続用定数
        String DATABASE_NAME = "mydb";
        String PROPATIES = "?characterEncoding=UTF-8&serverTimezone=JST";
        String URL = "jdbc:mySQL://localhost/" + DATABASE_NAME+PROPATIES;
        //DB接続用・ユーザ定数
        String USER = "tstg";
        String PASS = "";

        try {
            //MySQL に接続する
            Class.forName("com.mysql.cj.jdbc.Driver");
//            Class.forName("com.mysql.jdbc.Driver");
            //データベースに接続
            Connection conn = DriverManager.getConnection(URL, USER, PASS);
            // データベースに対する処理
            System.out.println("データベースに接続に成功");

            /***** 送信するSQL *****/
//            // DELETE
//            PreparedStatement delete = conn.prepareStatement("DELETE FROM mydb.account WHERE ID = ?");
//            delete.setInt(1, 2);
//            int del = delete.executeUpdate();
            
//            // INSERT
//            PreparedStatement insert = conn.prepareStatement("INSERT INTO mydb.account VALUES(?, ?, ?)");
//            insert.setInt(1, 2);
//            insert.setString(2, "佐藤");
//            insert.setInt(3, 5000);
//            int ins = insert.executeUpdate();
            
//            // UPDATE
//            PreparedStatement update = conn.prepareStatement("UPDATE mydb.account SET MONEY=? WHERE ID = ?");
//            update.setInt(1, 10000);
//            update.setInt(2, 2);
//            int upd = update.executeUpdate();
            
//          PreparedStatement delete = conn.prepareStatement("DELETE FROM mydb.account WHERE ID = ?");
//          delete.setInt(1, 3);
//          int del = delete.executeUpdate();
            
            System.out.println("処理番号を入力してください。(0:データ削除, 1:データ更新)(※必須)");
            List<String> input = new ArrayList<String>(); 
            Scanner sc1 = new Scanner(System.in);
            input.add(sc1.nextLine());
            System.out.println("IDを入力してください。(※必須)"); 
            Scanner sc2 = new Scanner(System.in);
            input.add(sc2.nextLine());
            System.out.println("名前を入力してください。(新規登録以外は不要)");
            Scanner sc3 = new Scanner(System.in);
            input.add(sc3.nextLine());
            System.out.println("金額を入力してください。(データ削除時は不要)");
            Scanner sc4 = new Scanner(System.in);
            input.add(sc4.nextLine());
            System.out.println("入力した情報は=" + input);
            
            
            // SELECT
            PreparedStatement pstmt = sqlList.Select(conn);
//            PreparedStatement pstmt = Select(conn);
            ResultSet res = pstmt.executeQuery();
            
        	List<String> id = new ArrayList<String>();
        	List<String> name = new ArrayList<String>();
        	List<String> money = new ArrayList<String>();
            while(res.next()) {            	
            	id.add(res.getString("ID"));
            	name.add(res.getString("NAME"));
            	money.add(res.getString("MONEY"));
            }
        	System.out.print(id);
        	System.out.print(name);
        	System.out.print(money);
        	System.out.println();
            
//        	String sqlProcess = args[0];
//        	String sqlId = args[1];
//        	String sqlName = args[2];
//        	String sqlMoney = args[3];
        	
        	String sqlProcess = input.get(0);
        	String sqlId = input.get(1);
        	String sqlName = input.get(2);
        	String sqlMoney = input.get(3);
        	
        	if(sqlProcess.equals("0")) {
        		pstmt = sqlList.DeleteStatus(conn, Integer.parseInt(sqlId));
//        		pstmt = DeleteStatus(conn, Integer.parseInt(sqlId));
        		System.out.println("削除完了");
        	}else {
        		if(!id.contains(sqlId)) {
            		System.out.println(sqlId);
            		pstmt = sqlList.UpdateInsert(conn, Integer.parseInt(sqlId), sqlName, Integer.parseInt(sqlMoney));
//            		pstmt = UpdateInsert(conn, Integer.parseInt(sqlId), sqlName, Integer.parseInt(sqlMoney));
            		System.out.println("新規登録");
            	}else {
            		pstmt = sqlList.UpdateStatus(conn, Integer.parseInt(sqlId), Integer.parseInt(sqlMoney));
//            		pstmt = UpdateStatus(conn, Integer.parseInt(sqlId), Integer.parseInt(sqlMoney));
            		System.out.println("更新完了");
            	}
        	}
            
            // 結果
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next()) {
            	System.out.print(rs.getString("ID"));
            	System.out.print(" ");
            	System.out.print(rs.getString("NAME"));
            	System.out.print(" ");
            	System.out.print(rs.getString("MONEY"));
            	System.out.println();
            }
            rs.close();
            pstmt.close();
            
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

	}
//
//	/**
//	 * 検索
//	 */
//	private static PreparedStatement Select(Connection conn) {
//		PreparedStatement ps = null;
//		try {
//			ps = conn.prepareStatement("SELECT * FROM mydb.account");
//		} catch(SQLException e) {
//			e.printStackTrace();
//		}
//		return ps;
//	}
//	
//	/** 
//	 * 登録
//	 */
//	private static PreparedStatement UpdateInsert(Connection conn, int id, String name, int money) {
//		PreparedStatement ps = null;
//		try {
//			PreparedStatement insert = conn.prepareStatement("INSERT INTO mydb.account VALUES(?, ?, ?)");
//	        insert.setInt(1, id);
//	        insert.setString(2, name);
//	        insert.setInt(3, money);
//	        int ins = insert.executeUpdate();
//	        ps = conn.prepareStatement("SELECT * FROM mydb.account");
//		} catch(SQLException e) {
//			e.printStackTrace();
//		}
//		return ps;
//	}
//	
//	/**
//	 * 更新
//	 * */
//	private static PreparedStatement UpdateStatus(Connection conn, int id, int money) {
//		PreparedStatement ps = null;
//		try {
//			PreparedStatement update = conn.prepareStatement("UPDATE mydb.account SET MONEY=? WHERE ID=?");
//			update.setInt(1, money);
//			update.setInt(2, id);
//	        int ins = update.executeUpdate();
//	        ps = conn.prepareStatement("SELECT * FROM mydb.account");
//		} catch(SQLException e) {
//			e.printStackTrace();
//		}
//		return ps;
//	}
//	
//	/**
//	 * 削除
//	 * */
//	private static PreparedStatement DeleteStatus(Connection conn, int id) {
//		PreparedStatement ps = null;
//		try {
//			PreparedStatement delete = conn.prepareStatement("DELETE FROM mydb.account WHERE ID=?");
//			delete.setInt(1, id);
//	        int ins = delete.executeUpdate();
//	        ps = conn.prepareStatement("SELECT * FROM mydb.account");
//		} catch(SQLException e) {
//			e.printStackTrace();
//		}
//		return ps;
//	}
//	
}
