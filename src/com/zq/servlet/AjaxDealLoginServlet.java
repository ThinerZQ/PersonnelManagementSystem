package com.zq.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import com.zq.bean.User;
import com.zq.service.AjaxService;
import com.zq.service.impl.AjaxServiceImpl;


public class AjaxDealLoginServlet extends HttpServlet {

	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = resp.getWriter();
		
		
		String department_id= req.getParameter("department_value");
		int department_id_int = Integer.parseInt(department_id);
		
		AjaxService ajaxService = new AjaxServiceImpl();
		List<User> list = ajaxService.getUserByDepartmentId(department_id_int);
		String notUse[] = new String[]{"username","password","email","sex","department_id"};
		JsonConfig config = new JsonConfig();
		config.setExcludes(notUse);
		JSONArray jsonArray = JSONArray.fromObject(list,config);
		out.println(jsonArray.toString());

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
	}

}
