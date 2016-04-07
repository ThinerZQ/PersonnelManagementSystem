package com.zq.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zq.bean.User;
import com.zq.service.UserService;
import com.zq.service.impl.UserServiceImpl;

public class SendMail extends HttpServlet {

	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		
		String flag = req.getParameter("flag");
		if("gosendmail".equals(flag)){
			
			String user_id = req.getParameter("user_id");
			
			
			UserService userService = new UserServiceImpl();
			User user = userService.getUserByUserId(Integer.parseInt(user_id));
			
			
			req.setAttribute("sendUser", user);
			
			req.getRequestDispatcher("sendmail.jsp").forward(req, resp);
			
		}else if("send".equals(flag)){
			
			String sendUser = 	req.getParameter("senduser");
			String toUser = req.getParameter("touser");
			String msg = req.getParameter("massage");
			String subject = req.getParameter("subject");
			
			com.zq.util.SendMail sendMail = new com.zq.util.SendMail();
			
			sendMail.sendMail(sendUser,toUser,subject,msg);
			
			resp.sendRedirect("send_success.jsp");
		}
		
		
		
		
		
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
	}

}
