package com.zq.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zq.bean.User;
import com.zq.service.UserService;
import com.zq.service.impl.UserServiceImpl;
import com.zq.util.ExcelBean;

public class DealExcel extends HttpServlet {

	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
			System.out.println("用户开始下载");
			UserService userService = new UserServiceImpl();
			try{
				String fname = "person_info";
				OutputStream os = resp.getOutputStream();
				//清空输出流
				resp.reset();
				//设置http响应头
				resp.setHeader("Content-disposition", "attachment;filename="+fname+".xlsx");
				//定义输出类型
				resp.setContentType("application/msexcel");
				//得到要输出地信息
				Map<String,List> allInfoMap  = userService.getAllUserInfo();
				//实例化处理输出到excel的对象
				ExcelBean excelbean = new ExcelBean();
				//调用对象方法，进行输出
				excelbean.createFixationSheet(allInfoMap, os);
			
			
			}catch(Exception e){
				e.printStackTrace();
			}
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
	}

	
}
