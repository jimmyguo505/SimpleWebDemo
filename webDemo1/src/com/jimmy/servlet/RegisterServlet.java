package com.jimmy.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.jimmy.domin.User;
import com.jimmy.domin.UserRegisterCheck;
import com.jimmy.exception.UserExistException;
import com.jimmy.service.AppService;
import com.jimmy.service.Implements.ServiceImplements;

public class RegisterServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		//首先验证输入的信息格式
		UserRegisterCheck urc = new UserRegisterCheck();
		try {
			BeanUtils.populate(urc, request.getParameterMap());
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		
		if (!urc.check()) {  //
				request.setAttribute("urc", urc);
				request.getRequestDispatcher("/register.jsp").forward(request, response);
				return;
		}
		
		//获取表单数据
		User user = new User();
		try {
			BeanUtils.populate(user, request.getParameterMap());
			AppService service = new ServiceImplements();
			
			service.checkUserByName(user.getUsername());
			service.register(user);
			
			response.getWriter().print("注册成功，2秒跳转到主页");
			response.setHeader("refresh", "2;url='"+request.getContextPath()+"/index.jsp'");
		} catch (UserExistException e) {
			request.setAttribute("userExist", "用户名已存在");
			request.getRequestDispatcher("/register.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
