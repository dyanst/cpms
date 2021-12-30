package com.wang.servlet.user;

import com.wang.pojo.User;
import com.wang.service.user.UserService;
import com.wang.service.user.UserServiceImpl;
import com.wang.util.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Servlet:控制层，调用业务层代码

        System.out.println("进入LoginServlet--->start.....");
        //获取用户名和密码
        String userCode = req.getParameter("userCode");
        String userPassword = req.getParameter("userPassword");
        //和数据库的进行对比，调用业务层
        UserService userService = new UserServiceImpl();
        User user = userService.login(userCode, userPassword);//这里把登录的人查出来了
        if (user!=null){//可以登录
            //将用户信息保存在session
            req.getSession().setAttribute(Constants.USER_SESSION,user);
            //跳转到登录页面
            resp.sendRedirect("jsp/frame.jsp");

        }else {
            req.setAttribute("error","用户名和密码错误！！！");
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
