package com.wgz.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtils {

//	public static String className = "oracle.jdbc.driver.OracleDriver";
//	public static String url = "jdbc:oracle:thin:@10.200.250.25:1521:URRP";
//	public static String user = "urrpapp";
//	public static String password = "urrpapp";
	
	public static String className = "com.mysql.jdbc.Driver";
	public static String url = "jdbc:mysql://127.0.0.1:3306/slzx?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT";
	public static String user = "slzx";
	public static String password = "slzx";
	
	public static Connection getConnection(){
		try {
			Class.forName(className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static String getDBMD5(String str){
		
		Connection con = DBUtils.getConnection();
		String password = "";
		if(con!=null){
			
			try {
				Statement sta = con.createStatement();
				
				StringBuffer sql = new StringBuffer();
				sql.append(" select md5('");
				sql.append(str);
				sql.append("') as md5 from dual");
				
				ResultSet rs = sta.executeQuery(sql.toString());
				if(rs!=null){
					while (rs.next()) {
						password = rs.getString("MD5");
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return password;
	}

	
	public static void main(String[] args) {
		System.out.println(getDBMD5("123"));
	}
}
