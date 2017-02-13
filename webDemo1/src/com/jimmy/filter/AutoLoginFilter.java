package com.jimmy.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jimmy.domin.User;
import com.jimmy.service.AppService;
import com.jimmy.service.Implements.ServiceImplements;

public class AutoLoginFilter implements Filter {

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// 1,转换2个对象
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		String uri = req.getRequestURI();
		String path = req.getContextPath();
		path = uri.substring(path.length());

		if (!("/login.jsp".equals(path) || "/servlet/LoginServlet".equals(path) || "/servlet/LogoutServlet".equals(path))) {
			// 如果用户从来都没有登陆过，我们才执行下面的自动登录过程
			User userCheck = (User) req.getSession().getAttribute("user");
			if (userCheck == null) {

				// 2,处理业务
				Cookie[] cookies = req.getCookies();
				String username = "";
				String password = "";
				for (int i = 0; cookies != null && i < cookies.length; i++) {
					if ("user".equals(cookies[i].getName())) {

						String[] split = cookies[i].getValue().split("&");// 拿到存有用户名密码的cookie
						username = split[0];
						password = split[1];
					}
				}

				// 登录操作
				User user = new User();
				user.setUsername(username);
				user.setPassword(password);

				// 如果登录成功，把用户信息保存到session
				AppService as = new ServiceImplements();
				User user2 = as.logIn(user);
				if (user2 != null) {
					req.getSession().setAttribute("user", user2);
				}
			}
		}
		// 3,放行
		chain.doFilter(request, response);
	}

	public void init(FilterConfig filterConfig) throws ServletException {

	}

}
