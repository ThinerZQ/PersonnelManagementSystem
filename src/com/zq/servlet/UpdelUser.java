package com.zq.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zq.bean.User;
import com.zq.service.UserService;
import com.zq.service.impl.UserServiceImpl;

public class UpdelUser extends HttpServlet {

	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		
		String flag = req.getParameter("flag");
		if("goupdate".equals(flag)){
			goupdateui(req, resp);
		}else if("update".equals(flag)){
			
			User user = new User();
			
			String realname = req.getParameter("realname");
			String username = req.getParameter("username");
			String sex = req.getParameter("sex");
			String age = req.getParameter("age");
			String user_id = req.getParameter("user_id");
			
			user.setRealname(realname);
			user.setUsername(username);
			user.setSex(sex);
			user.setAge(Integer.parseInt(age));
			user.setId(Integer.parseInt(user_id));
			
			UserService userService = new UserServiceImpl();
			boolean b = userService.updateInfo(user);
			
			System.out.println(b);
			if(b){
				req.setAttribute("info", "更新成功");
				
			
			}else{
				req.setAttribute("info", "更新失败");
				
			}
			
			this.goupdateui(req, resp);
			
		}
	}


	public void goupdateui(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String user_id = req.getParameter("user_id");
		
		UserService userService = new UserServiceImpl();
		User user = userService.getUserByUserId(Integer.parseInt(user_id));
		
		req.setAttribute("selectUser", user);
		
		req.getRequestDispatcher("updel.jsp").forward(req, resp);
	}
	
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
	}
}
