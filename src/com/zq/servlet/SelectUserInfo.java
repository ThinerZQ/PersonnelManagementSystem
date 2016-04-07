package com.zq.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zq.bean.User;
import com.zq.service.UserService;
import com.zq.service.impl.UserServiceImpl;

public class SelectUserInfo extends HttpServlet {

	
	private static final long serialVersionUID = 1L;
	private static List<User> userList1 = null;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		
		
		
		UserService userService = new UserServiceImpl();
		
		System.out.println("----------------正在进行查询用户操作---------------");
		//得到参数
		String username = req.getParameter("username1");
		String sex = req.getParameter("sex1");
		String email = req.getParameter("email1");
		String department  = req.getParameter("department1");
		String flag = req.getParameter("flag");
		
		//定义变量
		int pageCount=0;
		int rowCount=0;
		int pageSize=10;
		int pageNow=1;
		List pageCountList = new ArrayList();
		List<User> userList= null;
		
		if("select".equals(flag)){
			
			
			if("0".equals(sex)||sex==null){
				sex="";
			}
			if("0".equals(department)||department==null){
				department="";
			}
			
			userList = userService.getSelectUserInfo(username, sex, email, department);
			
			userList1 = userList;
			
			if(userList1.size()>10){
				userList = userList1.subList(0,9);
			}else{
				userList = userList1.subList(0,userList1.size());
			}
			
			rowCount = userList1.size();
			if(rowCount%pageSize==0){
				pageCount = rowCount/pageSize;
			}else{
				pageCount=rowCount/pageSize+1;
			}
			for(int i=1;i<=pageCount;i++){
				pageCountList.add(i);
			}
			
			
		}else if("fenye".equals(flag)){
			
			pageNow = Integer.parseInt(req.getParameter("pageNow"));
			rowCount = userList1.size();
			if(rowCount%pageSize==0){
				pageCount = rowCount/pageSize;
			}else{
				pageCount=rowCount/pageSize+1;
			}
			for(int i=1;i<=pageCount;i++){
				pageCountList.add(i);
			}
			
			if(pageCount==pageNow){
				userList = userList1.subList((pageNow-1)*pageSize,(pageNow-1)*pageSize+(userList1.size()%pageSize) );
			}else{
				userList = userList1.subList((pageNow-1)*pageSize,(pageNow-1)*pageSize+pageSize );
			}
			
			
		}
		req.setAttribute("pageCountList", pageCountList);
		req.setAttribute("selectUser", userList);
		
		req.getRequestDispatcher("manager.jsp").forward(req, resp);
		
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
	}

}
