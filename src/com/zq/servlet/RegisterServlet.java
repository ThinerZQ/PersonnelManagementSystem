package com.zq.servlet;

import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.zq.bean.User;
import com.zq.service.UserService;
import com.zq.service.impl.UserServiceImpl;
import com.zq.util.Encryption;


public class RegisterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("---------------------用户请求添加用户---------------------");
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		//得到参数
		String department = req.getParameter("department").trim();
		String username = req.getParameter("username").trim();
		String realname = req.getParameter("realname").trim();
		String password_old = req.getParameter("password").trim();
		String email = req.getParameter("email");
		
		//加密
		String password = Encryption.getPassword(password_old);
		//部门号转化成整数
		int department_id = Integer.parseInt(department);
		
		//调用业务逻辑，处理数据
		UserService userService = new UserServiceImpl();
		//得到返回结果
		int result = userService.register(department_id, username, realname, password, email);
		//判断，输出
		if(result==-1){
			//添加失败
			System.out.println("给客户端的添加用户处理结果是 ："+"fail");
			out.println("fail");
		}else{
			//添加成功
			System.out.println("给客户端的添加用户处理结果是 ："+"success");
			out.println("success");
			
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
	}

}
