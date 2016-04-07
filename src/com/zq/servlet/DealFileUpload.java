package com.zq.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.zq.bean.User;
import com.zq.service.UserService;
import com.zq.service.impl.UserServiceImpl;
import com.zq.util.FileUpload;

public class DealFileUpload extends HttpServlet {

	
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		request.setCharacterEncoding("utf-8");	//设置编码
		
		System.out.println("------------------------开始上传文件---------------------");
		User user = (User) request.getSession().getAttribute("user");
		
		String filename = user.getId()+"";
		System.out.println("ID为 "+user.getId()+" 的用户正在上传文件");
		//实例化，上传文件类
		FileUpload fileupload = new FileUpload();
		//调用函数，处理
		fileupload.dealFileUpload(request, filename);
		
		//转发到下一个页面
		//response.sendRedirect("personalinfo.jsp");
		request.getRequestDispatcher("personalinfo.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
	}

}
