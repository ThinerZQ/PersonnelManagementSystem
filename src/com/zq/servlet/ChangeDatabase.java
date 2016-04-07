package com.zq.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zq.util.SqlHelper;

public class ChangeDatabase extends HttpServlet {

	
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8"); 
		
		String database_product = req.getParameter("database");
		String database_name = req.getParameter("databasename");
		String url=req.getParameter("url");
		String driver = req.getParameter("driver");
		String username = req.getParameter("databaseusername");
		String password = req.getParameter("databasepassword");
		System.out.println(database_product+"   "+driver+"  "+url+database_name+username+password);
		
		SqlHelper.driver=driver;
		SqlHelper.url=url;
		SqlHelper.database=database_name;
		SqlHelper.username=username;
		SqlHelper.password =password;
		req.getRequestDispatcher("login.jsp").forward(req,resp);
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
	}

}
