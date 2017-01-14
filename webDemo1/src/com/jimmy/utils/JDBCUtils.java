package com.jimmy.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class JDBCUtils {
	//数据库连接用到的字段我们从配置文件中读取
	private static String driverClass;
	private static String url;
	private static String username;
	private static String password;
	//下面的静态代码块就是读取配置文件中的数据
	static{
		ResourceBundle rb = ResourceBundle.getBundle("mysqlInfo");
		driverClass = rb.getString("driverClass");
		url = rb.getString("url");
		username = rb.getString("username");
		password = rb.getString("password");
	}
	//取得连接函数
	public static Connection getConnection() throws Exception {
		Class.forName(driverClass);
		Connection conn = DriverManager.getConnection(url,username, password);
		return conn;
	}
	//关闭资源函数
	public static void closeRes(ResultSet rst, Statement stmt, Connection conn){
		if (rst != null) {				
			try {
				rst.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			rst = null;
		}
		if (stmt != null) {				
			try {
				stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			stmt = null;
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			conn = null;
		}
	}
}
