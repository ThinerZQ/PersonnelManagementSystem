package com.zq.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import com.zq.bean.Department;
import com.zq.bean.User;
import com.zq.dao.AjaxDao;
import com.zq.util.Encryption;
import com.zq.util.SqlHelper;

public class AjaxDaoImpl implements AjaxDao {

	Connection ct =null;
	PreparedStatement ps =null;
	ResultSet rs =null;
	public List<User> getUserByDepartmentId(Integer department_id) {
	
		List<User> list = new ArrayList<User>();
		try{
			ct=SqlHelper.getConnection();
			ps=ct.prepareStatement("select id,realname from users where department_id=?");
			ps.setInt(1, department_id);
			rs = ps.executeQuery();
			while(rs.next()){
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setRealname(rs.getString("realname"));
				
				list.add(user);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			SqlHelper.closeResource(ct, ps, rs);
		}
		return list;
	}

	@Override
	public List<Department> getDepartment() {
	
		List<Department> list = new ArrayList<Department>();
		try{
			ct=SqlHelper.getConnection();
			ps=ct.prepareStatement("select * from department");
			rs = ps.executeQuery();
			while(rs.next()){
				Department department = new Department();
				department.setId(rs.getInt("id"));
				department.setName(rs.getString("name"));
				
				list.add(department);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			SqlHelper.closeResource(ct, ps, rs);
		}
		return list;
	}

	
	public boolean checkEmailRepeat(String email) {
		System.out.println("开始检查邮箱有没有重复");
		boolean b=false;
		int i=0;
		try{
			ct=SqlHelper.getConnection();
			ps=ct.prepareStatement("select * from users where email=?");
			ps.setString(1, email);
			rs = ps.executeQuery();
			
			while(rs.next()){
				i++;
			}
			if(i>0){
				b=false;
				System.out.println("检测完成，邮箱不唯一");
			}else{
				b=true;
				System.out.println("检测完成，邮箱唯一");
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			SqlHelper.closeResource(ct, ps, rs);
		}
		return b;
	}

	
	public String loadMaxUserId() {
		System.out.println("开始加载员工最大ID");
		String num="0";
		try{
			ct=SqlHelper.getConnection();
			ps=ct.prepareStatement("select count(id) as num from users");
			
			rs = ps.executeQuery();
			
			while(rs.next()){
				num=rs.getString("num");
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			SqlHelper.closeResource(ct, ps, rs);
		}
		return num;
	}

	
	public List<User> loadUserInformation(String where, Object[] params) {
		
		List<User> userlist = new ArrayList<User>();
		
		StringBuilder sql = new StringBuilder("select * from users ");
		
		if(null != where && !where.trim().equals("")) {
			
			where = where.substring(0, where.length()-4);
			sql.append("where " +where);
			System.out.println(sql.toString());
		}
		try{
			ct=SqlHelper.getConnection();
			ps=ct.prepareStatement(sql.toString());
			
			if(null != params && params.length > 0) {
				for(int i = 0; i < params.length; i++) {
					ps.setObject((i+1), params[i]);
				}
			}
			rs = ps.executeQuery();
			while(rs.next()){
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setRealname(rs.getString("realname"));
				//还原密码
				String password = rs.getString("password");
				
				user.setPassword(Encryption.getPassword(password));
				user.setEmail(rs.getString("email"));
				user.setSex(rs.getString("sex"));
				user.setDepartment_id(rs.getInt("department_id"));
				user.setAge(rs.getInt("age"));
				user.setRegisterDate((java.util.Date)rs.getObject("registerdate"));
				user.setPhoto(rs.getString("photo"));
				userlist.add(user);
				
				System.out.println("**********************************************************");
				System.out.println("用户ID： "+rs.getInt("id"));
				System.out.println("用户名： "+rs.getString("username"));
				System.out.println("真实姓名： "+rs.getString("realname"));
				System.out.println("密码： "+Encryption.getPassword(password));
				System.out.println("邮件： "+rs.getString("email"));
				System.out.println("年龄： "+rs.getString("age"));
				System.out.println("性别： "+rs.getString("sex"));
				System.out.println("注册时间： "+rs.getDate("registerdate"));
				System.out.println("部门ID： "+rs.getInt("department_id"));
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			SqlHelper.closeResource(ct, ps, rs);
		}
		return userlist;
		
	}

}
