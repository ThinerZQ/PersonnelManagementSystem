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
import com.zq.util.Encryption;

public class LoginServlet extends HttpServlet {

	
	
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		
		List<User> userInfoList  = new ArrayList<User>();
		List<Department> departmentInfoList = new ArrayList<Department>();
		List pageCountList = new ArrayList();
		UserService userService = new UserServiceImpl();
		
		String flag = req.getParameter("flag");
		
		
		
		int pageCount=0;
		int pageSize=10;
		int pageNow =1;
		int rowCount=0;
		
		
		if("login".equals(flag)){
			
		
			String password = req.getParameter("password");
			System.out.println("从参数中取出来的password： "+password);
			
			String department =req.getParameter("department");
			int	i_department= Integer.parseInt(department);
			System.out.println("从参数中取出来的departmentid： "+i_department);
			
			String user = req.getParameter("realname");
			int user_id = Integer.parseInt(user);
			System.out.println("从参数中取出来的userid： "+user_id);
			
			String checkinfo_parm = req.getParameter("checkinfo");
			System.out.println("从参数中取出来的checkinfo： "+checkinfo_parm);
			
			String password_tran =Encryption.getPassword(password);
			System.out.println("加密后的密码："+password_tran);
			
			
			String checkinfo_right = (String) req.getSession().getAttribute("rand");
			System.out.println("内纯中的checkinfo: "+checkinfo_right);
			
			boolean b=false;
			
			
			User user1 = null;
			
			
			//验证用户名，密码，和验证码
			if(checkinfo_right.equals(checkinfo_parm)){
				
				System.out.println("用户ID："+user_id+"登陆，验证码正确");
				//验证用户名，密码，部门
				b = userService.login(i_department,user_id,password_tran);
				//验证成功
				if(b){
					//加载要登陆的用户的个人信息
					user1 = userService.getUserByUserId(user_id);
					//获取跳转下一个页面要显示的信息学，用户列表，，默认显示，第一页，按照实际姓名排序
					userInfoList = userService.getUsersInfo(pageNow,"realname");
					//得到部门信息
					departmentInfoList = userService.getDepartment();
					//得到一共有多少个用户
					pageCount = userService.getPageCount();
					//判断，并且得到有多少页
					//将得到的页数设置成一个list，便于jstl迭代输出页数
					for(int i=0;i<pageCount;i++){
						pageCountList.add(i+1);
					}
					//把要准备的值放入request中
					req.setAttribute("pageCountList", pageCountList);
					req.setAttribute("userInfoList", userInfoList);
					req.setAttribute("departmentInfoList", departmentInfoList);
					req.getSession().setAttribute("user", user1);
				}else{
					//密码验证出错
					req.setAttribute("loginErrorPassword", "密码错误");
				}
				
			}else{
				//验证码验证出错
				req.setAttribute("loginErrorCheckInfo", "验证码错误");
			}
			//如果通过验证，转发到main.jsp页面
			if(b){
				req.getRequestDispatcher("main.jsp").forward(req, resp);
			}else{
				//错误，转发到登陆页面
				req.getRequestDispatcher("login.jsp").forward(req, resp);
			}
		}
		
		
	}
	
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
	}

}
