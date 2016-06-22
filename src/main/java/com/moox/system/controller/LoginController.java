package com.moox.system.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * wrj
 * 登录
 * Created by Administrator on 2016-06-21.
 */
@WebServlet(name = "LoginServlet",urlPatterns = "/LoginServlet")
public class LoginController extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO 用于模拟检测用户登录
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        if (name.equals("test01".trim()) && "123456".equals(password)) {
            req.getSession().setAttribute("userName", "test01");
        }
        resp.sendRedirect("main.jsp");
    }
}
