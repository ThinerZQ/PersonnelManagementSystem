package com.zq.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zq.service.UserService;
import com.zq.service.impl.UserServiceImpl;

public class DelUser extends HttpServlet {

	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		
		
		String flag = req.getParameter("flag");
		if("del".equals(flag)){
			String user_id = req.getParameter("user_id");
			System.out.println("删除Id  = "+user_id);
			UserService userService = new UserServiceImpl();
			boolean b = userService.delUser(Integer.parseInt(user_id));
			if(b){
				resp.sendRedirect("manager.jsp");
			}else{
				req.setAttribute("info", "删除失败");
				req.getRequestDispatcher("updelUser?flag=goupdate?user_id="+user_id).forward(req,resp);
			}
		}
		
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
	}

}
