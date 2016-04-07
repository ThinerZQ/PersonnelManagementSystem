package com.zq.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zq.service.AjaxService;
import com.zq.service.impl.AjaxServiceImpl;

public class AjaxDealEmailServlet extends HttpServlet {

	
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = resp.getWriter();
		
		String email = req.getParameter("email").trim();
		AjaxService ajaxService = new AjaxServiceImpl();
		boolean b = ajaxService.checkEmailRepeat(email);
		System.out.println(b);
		if(b){
			out.println("{'check':'yes'}");
		}else{
			out.println("{'check':'no'}");
		}
		
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
	}

}
