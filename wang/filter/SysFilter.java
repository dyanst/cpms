package com.wang.filter;

import com.wang.pojo.User;
import com.wang.util.Constants;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class SysFilter implements Filter{
	public void init(FilterConfig filterConfig) throws ServletException{
		
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		//System.out.println("SysFilter doFilter()===========");
		HttpServletRequest request =  (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)resp;
		
		//过滤器，从session中获取用户
		User user = (User)request.getSession().getAttribute(Constants.USER_SESSION);
		//User user = (User)request.getSession().getAttribute("userSession");
		if(user == null){//已经被移除或者注销了，或者未登录
			response.sendRedirect("/smbms/error.jsp");
			//System.out.println("未登录");
		}else {
			chain.doFilter(req, resp);
			//System.out.println("过滤器");
		}
	}

	public void destroy() {

		
	}
	
}
