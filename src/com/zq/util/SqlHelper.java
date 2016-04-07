/*
package com.zq.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SqlHelper {

	private static Connection ct =null;
	
	
	//连接数据库
	public static Connection getConnection() throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		ct=DriverManager.getConnection("jdbc:mysql://localhost:3306/softwareassignment","root","193746");
		return ct;
	}
	//关闭连接
	public  static void closeResource(Connection ct,PreparedStatement ps,ResultSet rs){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(ps!=null){
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(ct!=null){
			try {
				ct.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}


*/

package com.zq.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SqlHelper {

	public static Connection ct =null;
	public static String driver="com.mysql.jdbc.Driver";
	public static String url="jdbc:mysql://localhost:3306/";
	public static String database ="softwareassignment";
	public static String username ="root";
	public static String password ="193746";
	
	//连接数据库
	public static Connection getConnection() throws Exception{
		Class.forName(driver);
		ct=DriverManager.getConnection(url+database+"?useUnicode=true&characterEncoding=gbk",username,password);
		return ct;
	}
	//关闭连接
	public  static void closeResource(Connection ct,PreparedStatement ps,ResultSet rs){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(ps!=null){
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(ct!=null){
			try {
				ct.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
