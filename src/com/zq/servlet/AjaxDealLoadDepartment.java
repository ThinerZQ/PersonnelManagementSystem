package com.zq.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.zq.bean.Department;
import com.zq.service.AjaxService;
import com.zq.service.impl.AjaxServiceImpl;

public class AjaxDealLoadDepartment extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = resp.getWriter();
		
		AjaxService ajaxService = new AjaxServiceImpl();
		List<Department> list = ajaxService.getDepartment();
		
		JSONArray jsonArray = JSONArray.fromObject(list);
		out.println(jsonArray.toString());

	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
	}
}
