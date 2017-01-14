package com.jimmy.dao.Implments;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.jimmy.dao.AppDao;
import com.jimmy.domin.User;
import com.jimmy.utils.JDBCUtils;

public class DaoImplements implements AppDao{
	/**
	 * 实现接口方法,实现插入数据库的方法
	 */
	public void insertUser(User user) {
		 Connection conn = null;
		 PreparedStatement ps = null;
		 
		 try {
			conn = JDBCUtils.getConnection();
			ps = conn.prepareStatement("INSERT INTO USER(username,PASSWORD,email,birthday) VALUES(?,?,?,?)");
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getEmail());
			ps.setString(4, user.getBirthday());
			
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			JDBCUtils.closeRes(null, ps, conn);
		}
	}
	
	/**
	 * 实现接口方法，实现返回用户信息的方法
	 */
	public User findUser(User user) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rst = null;
		User user2 = null;
		try {
			conn = JDBCUtils.getConnection();
			ps = conn.prepareStatement("SELECT * FROM USER WHERE username=? AND PASSWORD=?");
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			
			rst = ps.executeQuery();
			if (rst.next()) {
				user2 = new User();
				user2.setId(rst.getInt(1));
				user2.setUsername(rst.getString(2));
				user2.setPassword(rst.getString(3));
				user2.setEmail(rst.getString(4));
				user2.setBirthday(rst.getString(5));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			JDBCUtils.closeRes(rst, ps, conn);
		}
		return user2;
		
	}

	/**
	 * 实现接口方法，检测用户名是否已经存在
	 */
	public boolean findUserByName(String name) {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rst = null;
		
		try {
			conn = JDBCUtils.getConnection();
			ps = conn.prepareStatement("SELECT * FROM USER WHERE username=?");
			ps.setString(1, name);
			rst = ps.executeQuery();
			if (rst.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			JDBCUtils.closeRes(rst, ps, conn);
		}
		
		return false;
	}

}
