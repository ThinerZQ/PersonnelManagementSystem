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
			System.out.println("�Ӳ�����ȡ������password�� "+password);
			
			String department =req.getParameter("department");
			int	i_department= Integer.parseInt(department);
			System.out.println("�Ӳ�����ȡ������departmentid�� "+i_department);
			
			String user = req.getParameter("realname");
			int user_id = Integer.parseInt(user);
			System.out.println("�Ӳ�����ȡ������userid�� "+user_id);
			
			String checkinfo_parm = req.getParameter("checkinfo");
			System.out.println("�Ӳ�����ȡ������checkinfo�� "+checkinfo_parm);
			
			String password_tran =Encryption.getPassword(password);
			System.out.println("���ܺ�����룺"+password_tran);
			
			
			String checkinfo_right = (String) req.getSession().getAttribute("rand");
			System.out.println("�ڴ��е�checkinfo: "+checkinfo_right);
			
			boolean b=false;
			
			
			User user1 = null;
			
			
			//��֤�û��������룬����֤��
			if(checkinfo_right.equals(checkinfo_parm)){
				
				System.out.println("�û�ID��"+user_id+"��½����֤����ȷ");
				//��֤�û��������룬����
				b = userService.login(i_department,user_id,password_tran);
				//��֤�ɹ�
				if(b){
					//����Ҫ��½���û��ĸ�����Ϣ
					user1 = userService.getUserByUserId(user_id);
					//��ȡ��ת��һ��ҳ��Ҫ��ʾ����Ϣѧ���û��б���Ĭ����ʾ����һҳ������ʵ����������
					userInfoList = userService.getUsersInfo(pageNow,"realname");
					//�õ�������Ϣ
					departmentInfoList = userService.getDepartment();
					//�õ�һ���ж��ٸ��û�
					pageCount = userService.getPageCount();
					//�жϣ����ҵõ��ж���ҳ
					//���õ���ҳ�����ó�һ��list������jstl�������ҳ��
					for(int i=0;i<pageCount;i++){
						pageCountList.add(i+1);
					}
					//��Ҫ׼����ֵ����request��
					req.setAttribute("pageCountList", pageCountList);
					req.setAttribute("userInfoList", userInfoList);
					req.setAttribute("departmentInfoList", departmentInfoList);
					req.getSession().setAttribute("user", user1);
				}else{
					//������֤����
					req.setAttribute("loginErrorPassword", "�������");
				}
				
			}else{
				//��֤����֤����
				req.setAttribute("loginErrorCheckInfo", "��֤�����");
			}
			//���ͨ����֤��ת����main.jspҳ��
			if(b){
				req.getRequestDispatcher("main.jsp").forward(req, resp);
			}else{
				//����ת������½ҳ��
				req.getRequestDispatcher("login.jsp").forward(req, resp);
			}
		}
		
		
	}
	
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
	}

}
