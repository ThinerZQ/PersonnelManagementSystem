package com.zq.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.zq.bean.User;
import com.zq.dao.AjaxDao;
import com.zq.dao.impl.AjaxDaoImpl;

public class AjaxDealSelectInfo extends HttpServlet {

	
	
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = resp.getWriter();
		//获取可能的参数
		String user_id_con = req.getParameter("user_id_con");
		String user_id_value = req.getParameter("user_id_value");
		String realname_value = req.getParameter("realname_value");
		String username_value = req.getParameter("username_value");
		String email_value = req.getParameter("email_value");
		String age_con = req.getParameter("age_con");
		String age_value = req.getParameter("age_value");
		String sex_con = req.getParameter("sex_con");
		String registerdate_con = req.getParameter("registerdate_con");
		String registerdate_value = req.getParameter("registerdate_value");
		
		//定义变量
		StringBuilder sb = new StringBuilder();
		
		List<Object> params = new ArrayList<Object>();
		
		
		//对各个参数值判断是否为空
		if(null!=user_id_con&&!"".equals(user_id_con)){
			sb.append(" id");
			if(user_id_con.equals("mt")){
				sb.append(">? and");
			}else if(user_id_con.equals("lt")){
				sb.append("<? and");
			}else{
				sb.append("=? and");
			}
			params.add(user_id_value );
		}
		if(null!=realname_value&&!"".equals(realname_value)){
				sb.append(" realname like ? and ");
				params.add(realname_value);
		}
				
		if(null!=username_value&&!"".equals(username_value)){
				sb.append(" username like ? and ");
				params.add(username_value);
		}
					
		if(null!=email_value&&!"".equals(email_value)){
				sb.append(" email like ? and");
				params.add(email_value);
		}
						
		if(null!=age_con&&!"".equals(age_con)){
			sb.append(" age");
			if(age_con.equals("mt")){
				sb.append(">? and ");
			}else if(age_con.equals("lt")){
				sb.append("<? and ");
			}else{
				sb.append("=? and ");
			}
			params.add(age_value);
		}
		
		if(null!=sex_con&&!"".equals(sex_con)){
			sb.append(" sex = ? and ");
			params.add(sex_con);
		}
		if(null!=registerdate_con&&!"".equals(registerdate_con)){
			sb.append(" registerdate");
			if(registerdate_con.equals("mt")){
				sb.append(">? and ");
			}else if(registerdate_con.equals("lt")){
				sb.append("<? and ");
			}else{
				sb.append("=? and ");
			}
			params.add(registerdate_value);
		} 
		
		//封装成对象数组
		Object object[] =params.toArray();
		System.out.println(sb.toString());
		for(int i=0;i<object.length;i++){
			System.out.println(object[i]);
		}
		
		//调用后代业务逻辑
		AjaxDao ajaxdao = new AjaxDaoImpl();
		List<User> list =  ajaxdao.loadUserInformation(sb.toString(), object);
		
		
		for(User li :list){
			System.out.println(li.getEmail());
		}
		//将后台的传来的数据以json的格式返回
		JSONArray jsonArray = JSONArray.fromObject(list);
		
		out.println(jsonArray.toString());
		
			
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
	}

	
}
