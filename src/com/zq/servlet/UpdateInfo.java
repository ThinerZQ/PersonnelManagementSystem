package com.zq.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zq.bean.User;
import com.zq.service.UserService;
import com.zq.service.impl.UserServiceImpl;

public class UpdateInfo extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		
		System.out.println("--------------------开始修改我的个人信息------------------------");
		//获取参数
		String user_id = req.getParameter("user_id");
		String username = req.getParameter("username");
		String realname = req.getParameter("realname");
		String password = req.getParameter("password");
		String age = req.getParameter("age");
		
		String sex = req.getParameter("sex");
		
		
		//构造一个User，传递过去
		User user = new User();
		user.setId(Integer.parseInt(user_id));
		user.setUsername(username);
		user.setRealname(realname);
		user.setPassword(password);
		user.setAge(Integer.parseInt(age));
		user.setSex(sex);
		
		
		UserService userService = new UserServiceImpl();
		boolean b = userService.updateUserInfo(user);
		//判断是否修改成功
		if(b){
			req.setAttribute("updateInfo", "修改成功");
			//将改变的值写入session中
			User user1 = (User) req.getSession().getAttribute("user");
			user1.setId(Integer.parseInt(user_id));
			user1.setUsername(username);
			user1.setRealname(realname);
			user1.setPassword(password);
			user1.setAge(Integer.parseInt(age));
			user1.setSex(sex);
			req.getSession().setAttribute("user", user1);
		}else{
			req.setAttribute("updateInfo", "修改失败");
		}
		
		
		//转发到下一个页面
		req.getRequestDispatcher("personalinfo.jsp").forward(req, resp);
		
		
		
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
	}

	
	
}
