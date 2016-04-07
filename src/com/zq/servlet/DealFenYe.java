package com.zq.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.zq.bean.Department;
import com.zq.bean.User;
import com.zq.service.UserService;
import com.zq.service.impl.UserServiceImpl;


//这个是处理分页的servlet
public class DealFenYe extends HttpServlet {

	
	private static final long serialVersionUID = 1L;
	
	private static String  sortway_fixed="realname";
	private static String sortway_fixed1="realname";
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		//设置字符集
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		
		//定义一些变量
		List<User> userInfoList  = new ArrayList<User>();
		List<Department> departmentInfoList = new ArrayList<Department>();
		List pageCountList = new ArrayList();
		int pageCount=0;
		
		//实例化要用到的对象
		UserService userService = new UserServiceImpl();
		
		//得到参数
		String flag = req.getParameter("flag");
		String sortway = req.getParameter("sortway");
		String mark = req.getParameter("mark");
		
		if("fenye".equals(flag)){
			String pageNow = req.getParameter("pageNow");
			
			int pageNow_int = Integer.parseInt(pageNow);
			//一个标记符，判断通过何种方式排序
			if(mark==null){
				if(pageNow_int==1){
					sortway_fixed=sortway;
				}
			}
			//得到有多少页
			pageCount = userService.getPageCount();
			for(int i=0;i<pageCount;i++){
				pageCountList.add(i+1);
			}
			//得到用户列表
			userInfoList = userService.getUsersInfo(pageNow_int,sortway_fixed);
			
			//得到部门信息
			departmentInfoList = userService.getDepartment();
			//为下一页面准备信息
			req.setAttribute("userInfoList", userInfoList);
			req.setAttribute("pageCountList", pageCountList);
			req.setAttribute("departmentInfoList", departmentInfoList);
			//转发到main.jsp页面
			req.getRequestDispatcher("/main.jsp").forward(req, resp);
		}else if("fenye1".equals(flag)){
			String pageNow = req.getParameter("pageNow");
			
			int pageNow_int = Integer.parseInt(pageNow);
			//一个标记符，判断通过何种方式排序
			if(mark==null){
				if(pageNow_int==1){
					sortway_fixed1=sortway;
				}
			}
			//得到有多少页
			pageCount = userService.getCurrentPageCount();
			for(int i=0;i<pageCount;i++){
				pageCountList.add(i+1);
			}
			//得到用户列表
			userInfoList = userService.getUsersInfo(pageNow_int,sortway_fixed1);
			
			//得到部门信息
			departmentInfoList = userService.getDepartment();
			//为下一页面准备信息
			req.setAttribute("userInfoList", userInfoList);
			req.setAttribute("pageCountList", pageCountList);
			req.setAttribute("departmentInfoList", departmentInfoList);
			//转发到main.jsp页面
			req.getRequestDispatcher("/manager.jsp").forward(req, resp);
		}
		
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
	}

}
