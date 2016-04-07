package com.zq.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Backup extends HttpServlet {

	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		
		String database = req.getParameter("database");
		String[] tables = req.getParameterValues("table");
		
		String sql="mysqldump -u root -p193746 --databases ";
		if(tables.length==0 ||tables==null){
			sql+=database;
		}else{
			sql+=database;
			for(String a:tables){
				sql+=" "+a;
			}
		}
		sql+=(" >c:\\file\\backup"+new Date().toLocaleString())+".sql";
		
	
		
		
		try {
			Process process;
			process = Runtime.getRuntime().exec("C:\\Windows\\System32\\cmd.exe");
			
			OutputStream os = process.getOutputStream();
			os.write(sql.getBytes());
			process.waitFor();
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		
		
		
		System.out.println(sql);
		
		
		
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
	}

}
