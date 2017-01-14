package com.jimmy.domin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;


public class UserRegisterCheck {
	private int id;
	private String username;
	private String password;
	private String repassword;
	private String email;
	private String birthday;
	Map<String, String> msg = new HashMap<String, String>();
	
	public boolean check() {
		
		//用户名非空，3-8位字母
		if("".equals(username)){
			msg.put("username", "*用户名不能为空");
		}else if (!username.matches("\\w{3,8}")) {
			msg.put("username", "*请输入3-8位用户名");
		}
			
		//密码非空，3-8位数字
		if ("".equals(password)) {
			msg.put("password", "*密码不能为空");
		}else if (!password.matches("\\d{3,8}")) {
			msg.put("password", "*密码应为3-8位数字");			
		}
		
		//确认密码，与密码保持一致
		if (!repassword.equals(password)) {
			msg.put("repassword", "*密码不一致");
		}
		
		//邮箱，非空，且符合格式
		if ("".equals(email)) {
			msg.put("email", "*邮箱不能为空");
		}else if (!email.matches("\\b^['_a-z0-9-\\+]+(\\.['_a-z0-9-\\+]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*\\.([a-z]{2}|aero|arpa|asia|biz|com|coop|edu|gov|info|int|jobs|mil|mobi|museum|name|nato|net|org|pro|tel|travel|xxx)$\\b")) {
			msg.put("email", "*邮箱格式不正确");
		}
		
		//生日，非空，且符合格式
		if ("".equals(birthday)) {
			msg.put("birthday", "*生日不能为空");
		}else  {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				sdf.parse(birthday);
			} catch (ParseException e) {
				msg.put("birthday", "*生日格式不正确,正确格式:yyyy-MM-dd");
			}
		}
		
		return msg.isEmpty();
	}
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRepassword() {
		return repassword;
	}
	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public Map<String, String> getMsg() {
		return msg;
	}
	public void setMsg(Map<String, String> msg) {
		this.msg = msg;
	}
	
	
	
}
