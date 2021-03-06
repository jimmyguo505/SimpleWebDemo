package com.jimmy.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.jimmy.domin.User;
import com.jimmy.service.AppService;
import com.jimmy.service.Implements.ServiceImplements;

public class LoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		User user = new User();
		try {
			BeanUtils.populate(user, request.getParameterMap());
			AppService service = new ServiceImplements();
			User user2 = service.logIn(user);
			
			if(user2 != null){
				//保存用户名和密码到cookie
				String autoLogin = request.getParameter("autoLogin");
				Cookie cookie = new Cookie("user", user2.getUsername()+"&"+user2.getPassword());
				cookie.setPath("/");
				if (autoLogin!=null) {
					cookie.setMaxAge(60*60*24);
				} else {
					cookie.setMaxAge(0);
				}
				response.addCookie(cookie); //把cookie保存到客户端
				
				request.getSession().setAttribute("user", user2); //将user对象作为session域，供其他servlet使用。
				response.getWriter().print("登陆成功，2秒后跳转到主页");
				response.setHeader("refresh", "2;url='"+request.getContextPath()+"/index.jsp'");
				
			}
			else {
				response.getWriter().print("登录失败，请重新登录，2秒调转到登陆页面");
				response.setHeader("refresh", "2;url='"+request.getContextPath()+"/login.jsp'");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
