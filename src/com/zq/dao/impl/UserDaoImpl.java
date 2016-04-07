package com.zq.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zq.bean.Department;
import com.zq.bean.User;
import com.zq.dao.UserDao;
import com.zq.util.Encryption;
import com.zq.util.SqlHelper;

public class UserDaoImpl implements UserDao {

	Connection ct =null;
	PreparedStatement ps =null;
	ResultSet rs =null;
	
	
	
	private int pageCount =0;
	private int pageSize=10;
	private int rowCount=0;
	private static int rowCount_current=0;
	
	//得到部门信息
	public List<Department> getDepartment() {
	
		List<Department> departmentList =new ArrayList<Department>();
		try{
			ct = SqlHelper.getConnection();
			String sql = "select distinct * from department";
			ps=ct.prepareStatement(sql);
			rs=ps.executeQuery();
			
			//封装部门信息到list
			while(rs.next()){
				Department department = new Department();
				department.setId(rs.getInt("id"));
				department.setName(rs.getString("name"));
				departmentList.add(department);
			}
		
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			SqlHelper.closeResource(ct, ps, rs);
		}
		
		return departmentList;
	}
	//登陆验证，
	public boolean login(int department_id, int user_id, String password) {

		boolean b=false;
		try{
			ct=SqlHelper.getConnection();
			ps = ct.prepareStatement("select password from users where id=? and department_id=?");
			ps.setInt(1, user_id);
			ps.setInt(2, department_id);
			rs = ps.executeQuery();
			while(rs.next()){
				System.out.println("找到密码用户密码，");
				String sql_password=rs.getString("password");
				
				if(sql_password.equals(password)){
					System.out.println("登陆成功");
					b=true;
				}
			
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			SqlHelper.closeResource(ct, ps, rs);
		}
		return b;

	}

	//得到用户信息通过ID号
	public User getUserByUserId(int user_id) {
		User user = null;
		int department_id=0;
		try{
			ct=SqlHelper.getConnection();
			ps = ct.prepareStatement("select * from users where id=?");
			ps.setInt(1, user_id);
			
			rs = ps.executeQuery();
			//封装用户信息，到user中
			while(rs.next()){
				user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setRealname(rs.getString("realname"));
				//还原密码
				String password = rs.getString("password");
				
				user.setPassword(Encryption.getPassword(password));
				
				System.out.println("password="+password);
				System.out.println("Encryption.getpassword = "+Encryption.getPassword(password));
				user.setEmail(rs.getString("email"));
				user.setRegisterDate(rs.getDate("registerdate"));
				user.setSex(rs.getString("sex"));
				user.setDepartment_id(rs.getInt("department_id"));
				user.setAge(rs.getInt("age"));
				user.setPhoto(rs.getString("photo"));
				department_id=rs.getInt("department_id");
				
				
				
				
				System.out.println("得到当前用户的信息："+rs.getString("realname"));
			}
			ps = ct.prepareStatement("select name from department where id=?");
			ps.setInt(1, department_id);
			rs = ps.executeQuery();
			while(rs.next()){
				user.setDepartmentName(rs.getString("name"));
				System.out.println("部门名称："+ rs.getString("name"));
			}
			ps = ct.prepareStatement("select * from role where id=(select role_id from users where id=?)");
			ps.setInt(1, user_id);
			rs=ps.executeQuery();
			while(rs.next()){
				user.setRole_id(rs.getInt("id"));
				user.setRolename(rs.getString("rolename"));
				
				System.out.println("角色名称："+rs.getString("rolename"));
			}
			ps=ct.prepareStatement("select * from root where role_id="+user.getRole_id());
			rs = ps.executeQuery();
			String rootname[] = new String[100];
			while(rs.next()){
				int i=0;
				rootname[i]=rs.getString("rootname");
				i++;	
				System.out.println("权限名称："+rs.getString("rootname"));
			}
			user.setRootname(rootname);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			SqlHelper.closeResource(ct, ps, rs);
		}
		return user;
	}

	//用户注册
	public int register(int department_id, String username, String realname,
			String password, String email) {
		int result=-1;
		try{
			ct = SqlHelper.getConnection();
			ps = ct.prepareStatement("insert into users (username,realname,password,email,registerdate,department_id) values(?,?,?,?,?,?)");
			ps.setString(1, username);
			ps.setString(2, realname);
			ps.setString(3, Encryption.getPassword(password));
			ps.setString(4, email);
			ps.setDate(5, new Date(new java.util.Date().getTime()));
			ps.setInt(6, department_id);
			
			result  = ps.executeUpdate();
			System.out.println("用户注册完毕");
			System.out.println("-------注册的新用户为-------------");
			System.out.println("用户名:"+username);
			System.out.println("真实姓名："+realname);
			System.out.println("密码："+password);
			System.out.println("电子邮件："+email);
			System.out.println("-----------------------------");
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			SqlHelper.closeResource(ct, ps, rs);
		}
		return result;
	}

	//通过邮箱得到用户信息
	public User getUserByEmail(String email) {
		User user = null;
		try{
			ct=SqlHelper.getConnection();
			ps = ct.prepareStatement("select * from users where email=?");
			ps.setString(1, email);
			
			rs = ps.executeQuery();
			while(rs.next()){
				user = new User();
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
				user.setRegisterDate(rs.getDate("registerdate"));
				user.setPhoto(rs.getString("photo"));
				System.out.println("得到当前用户注册的所有信息");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			SqlHelper.closeResource(ct, ps, rs);
		}
		return user;
	}

	//得到所有的用户信息
	public List<User> getUsersInfo(int pageNow,String sortway) {
		
		List<User> userInfoList =new ArrayList<User>();
		
		try{
			ct = SqlHelper.getConnection();
			String sql = "select u.id as u_id,username,realname,password,email,age,sex,registerdate,department_id, name from users u inner join department d on u.department_id =d.id ";
			sql+="order by ";
			sql+=sortway;
			sql+=" limit ?,?";
		
			ps=ct.prepareStatement(sql);
			
			System.out.println(sql);
			
			ps.setInt(1, (pageNow-1)*pageSize);
			ps.setInt(2, pageSize);
			rs=ps.executeQuery();
			System.out.println("---------------------查询到的所有的用户信息如下：----------------------");
			while(rs.next()){
				
				User user =  new User();
				user.setId(rs.getInt("u_id"));
				user.setUsername(rs.getString("username"));
				user.setRealname(rs.getString("realname"));
				//还原密码
				String password = rs.getString("password");
				user.setPassword(Encryption.getPassword(password));
				user.setEmail(rs.getString("email"));
				user.setSex(rs.getString("sex"));
				user.setDepartment_id(rs.getInt("department_id"));
				user.setAge(rs.getInt("age"));
				user.setRegisterDate(rs.getDate("registerdate"));
				user.setDepartmentName(rs.getString("name"));
				
				userInfoList.add(user);
				
				
				System.out.println("**********************************************************");
				System.out.println("用户ID： "+rs.getInt("u_id"));
				System.out.println("用户名： "+rs.getString("username"));
				System.out.println("真实姓名： "+rs.getString("realname"));
				System.out.println("密码： "+Encryption.getPassword(password));
				System.out.println("邮件： "+rs.getString("email"));
				System.out.println("年龄： "+rs.getString("age"));
				System.out.println("性别： "+rs.getString("sex"));
				System.out.println("注册时间： "+rs.getDate("registerdate"));
				System.out.println("部门ID： "+rs.getInt("department_id"));
				System.out.println("部门名字： "+rs.getString("name"));
				
			}
		
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			SqlHelper.closeResource(ct, ps, rs);
		}
		
		return userInfoList;
	}
//得到数据表中一共那个有多少用户
public int getRows() {
	
		try{
			ct=SqlHelper.getConnection();
			ps = ct.prepareStatement("select count(*) from users");
			
			 rs= ps.executeQuery();
			 while(rs.next()){
				 this.rowCount = rs.getInt(1);
			 }
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			SqlHelper.closeResource(ct, ps, rs);
		}
		System.out.println("当users表中共有："+rowCount+" 条记录");
		return rowCount;
		
}
//得到一共有多少页
public int getPageCount() {
	
	
		this.rowCount = this.getRows();
		
		if(rowCount%pageSize==0){
			this.pageCount = rowCount/pageSize;
		}else{
			this.pageCount = rowCount/pageSize+1;
		}
		System.out.println("每页显示： "+pageSize+" 个用户，一共有 "+pageCount+" 页");
	
	return pageCount;
}
//得到要打印输出地结果集
public List<ResultSet> getResultSetList() {
		
		List<ResultSet> list = new ArrayList<ResultSet>();
		try{
			ct=SqlHelper.getConnection();
			ps = ct.prepareStatement("SELECT u.id,username,realname,PASSWORD,email,age,sex,registerdate,NAME FROM users AS u INNER JOIN department AS d ON u.department_id=d.id");
			
			rs = ps.executeQuery();
			list.add(rs);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
		}
	return list;	
	}
//给用户设置头像
public int savePhoto(String user_id, String photo) {
		int result=-1;
		try{
			
			ct=SqlHelper.getConnection();
			String sql ="update users set photo = ? where id=?";
			System.out.println(sql + user_id+  photo);
			ps = ct.prepareStatement(sql);
			ps.setString(1, photo);
			ps.setString(2, user_id);
			result= ps.executeUpdate();
			System.out.println("用户id为 :"+user_id+ "  设置头像成功 ，写入数据库成功");
		
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			SqlHelper.closeResource(ct, ps, rs);
		}
		return result;
		
	}
//根据查询条件得到用户信息
	public List<User> getSelectUserInfo(String username, String sex,
			String email, String department) {
		
		List<User> userList = new ArrayList<User>();
		try{
			
			ct=SqlHelper.getConnection();
			String sql ="select u.id as u_id,username,realname,password,email,age,sex,registerdate,department_id, name from users u inner join department d on u.department_id =d.id";
			sql+=" where ";
			sql+=" realname like '%";
			sql+=username+"%' ";
			sql+=" and  email like '%";
			sql+=email+"%' ";
			sql+=" and department_id like '%";
			sql+=department+"%' ";
			if(!"".equals(sex)){
				sql+=" and sex like '%";
				sql+=sex+"%' ";
			}
			
			
			/*
			if(!"".equals(username)){
				sql+=" realname like '%";
				sql+=username+"%' ";
			}
			if(!"".equals(email)){
				sql+=" email like '%";
				sql+=email+"%' ";
			}
			if(!"".equals(department)){
				sql+=" department_id like '%";
				sql+=department+"%' ";
			}
			if(!"".equals(sex)){
				sql+=" sex like '%";
				sql+=sex+"%' ";
			}
			*/
			
			System.out.println("输入条件查询的语句为："+sql);
			ps=ct.prepareStatement(sql);
			
			rs = ps.executeQuery();
			System.out.println("---------------------根据查询条件得到的的所有的用户信息如下：----------------------");
			
			while(rs.next()){
				
				User user = new User();
				user.setId(rs.getInt("u_id"));
				user.setUsername(rs.getString("username"));
				user.setRealname(rs.getString("realname"));
				//还原密码
				String password = rs.getString("password");
				user.setPassword(Encryption.getPassword(password));
				
				user.setEmail(rs.getString("email"));
				user.setSex(rs.getString("sex"));
				user.setDepartment_id(rs.getInt("department_id"));
				user.setAge(rs.getInt("age"));
				user.setRegisterDate(rs.getDate("registerdate"));
				user.setDepartmentName(rs.getString("name"));
				
				//放回当前插查询的中一共有多少条记录
				rowCount_current++;
				//封装
				userList.add(user);
				
				System.out.println("￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥￥");
				System.out.println("用户ID： "+rs.getInt("u_id"));
				System.out.println("用户名： "+rs.getString("username"));
				System.out.println("真实姓名： "+rs.getString("realname"));
				System.out.println("密码： "+Encryption.getPassword(password));
				System.out.println("邮件： "+rs.getString("email"));
				System.out.println("年龄： "+rs.getString("age"));
				System.out.println("性别： "+rs.getString("sex"));
				System.out.println("注册时间： "+rs.getDate("registerdate"));
				System.out.println("部门ID： "+rs.getInt("department_id"));
				System.out.println("部门名字： "+rs.getString("name"));
				
				
			}
		
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			SqlHelper.closeResource(ct, ps, rs);
		}
		
		return userList;
		
	}
	
	//得到数据库中所有用户信息
	public Map<String,List> getAllUserInfo() {
		List<User> userInfoList =new ArrayList<User>();
		Map<String,List> allInfoMap = new HashMap<String,List>();
		List<Department> departmentInfoList = new ArrayList<Department>();
		List<String> aboutNumInfoList = new ArrayList<String>();
		int count=0;
		
		try{
			ct = SqlHelper.getConnection();
			String sql = "select u.id as u_id,username,realname,password,email,age,sex,registerdate,department_id, name from users u inner join department d on u.department_id =d.id order by name,u_id";
	
			ps=ct.prepareStatement(sql);
			
			rs=ps.executeQuery();
			System.out.println("---------------------要输出到excel的 所有的用户信息如下：----------------------");
			System.out.println("---员工信息---");
			while(rs.next()){
				
				User user =  new User();
				user.setId(rs.getInt("u_id"));
				user.setUsername(rs.getString("username"));
				user.setRealname(rs.getString("realname"));
				//还原密码
				String password = rs.getString("password");
				user.setPassword(Encryption.getPassword(password));
				
				user.setEmail(rs.getString("email"));
				user.setSex(rs.getString("sex"));
				user.setDepartment_id(rs.getInt("department_id"));
				user.setAge(rs.getInt("age"));
				user.setRegisterDate(rs.getDate("registerdate"));
				user.setDepartmentName(rs.getString("name"));
				
				userInfoList.add(user);
			
				System.out.println("**********************************************************");
				System.out.println("用户ID： "+rs.getInt("u_id"));
				System.out.println("用户名： "+rs.getString("username"));
				System.out.println("真实姓名： "+rs.getString("realname"));
				System.out.println("密码： "+Encryption.getPassword(password));
				System.out.println("邮件： "+rs.getString("email"));
				System.out.println("年龄： "+rs.getString("age"));
				System.out.println("性别： "+rs.getString("sex"));
				System.out.println("注册时间： "+rs.getDate("registerdate"));
				System.out.println("部门ID： "+rs.getInt("department_id"));
				System.out.println("部门名字： "+rs.getString("name"));
				
			}
			String sql1 = "SELECT d.id department_id,d.name  AS department_name, COUNT(*) AS department_people,GROUP_CONCAT(realname) AS people FROM users u INNER JOIN department d ON u.department_id =d.id GROUP BY d.name ORDER BY NAME,u.ID";
			
			ps=ct.prepareStatement(sql1);
			rs = ps.executeQuery();
			
			
			System.out.println("---部门信息---");
			while(rs.next()){
				
				Department department =  new Department();
				
				department.setId(rs.getInt("department_id"));
				department.setName(rs.getString("department_name"));
				department.setMember_num(rs.getInt("department_people"));
				department.setMember(rs.getString("people"));
				
				departmentInfoList.add(department);
				
				System.out.println("------------------------------------------------------");
				System.out.println("部门Id： "+rs.getInt("department_id"));
				System.out.println("部门名： "+rs.getString("department_name"));
				System.out.println("部门成员个数： "+rs.getInt("department_people"));

			}
			String sql2 = "select count(*) as num from users";
			
			ps=ct.prepareStatement(sql2);
			rs = ps.executeQuery();
			
			
			System.out.println("---关于一共有多少人的信息---");
			while(rs.next()){
				
				count = rs.getInt("num");
				aboutNumInfoList.add(count+"");
				
				System.out.println("##############################################################");
				System.out.println("一共有  "+count+ "人");
				
			}
			
			
			allInfoMap.put("userInfoList", userInfoList);
			allInfoMap.put("departmentinfoList", departmentInfoList);
			allInfoMap.put("aboutNumInfoList", aboutNumInfoList);
		
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			SqlHelper.closeResource(ct, ps, rs);
		}
		
		return allInfoMap;
	}
	//更新用户信息
	public boolean updateUserInfo(User user) {
		boolean b=false;
		try{
			
			ct=SqlHelper.getConnection();
			String sql ="update users set username=?,age=?,sex=?";
			System.out.println(""+user.getSex());
			if(user.getPassword()!=""){
				sql+=",password =?";
			}
			sql+=" where id=?";
					
			System.out.println("修改个人信息的语句如下："+sql);
			ps = ct.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setInt(2, user.getAge());
			ps.setString(3, user.getSex());
			if(user.getPassword()!=""){
				ps.setString(4, Encryption.getPassword(user.getPassword()));
				ps.setInt(5, user.getId());
			}else{
				ps.setInt(4, user.getId());
			}
				
			
			int  i = ps.executeUpdate();
			if(i==1){
				b=true;
				System.out.println("---------------修改我的信息成功-------------");
			}
		
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			SqlHelper.closeResource(ct, ps, rs);
		}
		return b;
	}
	@Override
	public int getCurrentPageCount() {
		return rowCount_current;
	}
	
	public boolean updateInfo(User user) {
		boolean b=false;
		try{
			
			ct=SqlHelper.getConnection();
			String sql ="update users set realname=?,username=?,age=?,sex=?";
			
			
			sql+=" where id=?";
					
			System.out.println("修改用户信息的语句如下："+sql);
			ps = ct.prepareStatement(sql);
			ps.setString(1, user.getRealname());
			ps.setString(2, user.getUsername());
			ps.setInt(3, user.getAge());
			ps.setString(4, user.getSex());
			ps.setInt(5, user.getId());
				
			
			int  i = ps.executeUpdate();
			if(i==1){
				b=true;
				System.out.println("---------------修改用户的信息成功-------------");
			}
		
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			SqlHelper.closeResource(ct, ps, rs);
		}
		return b;
	}
	
	public boolean delUser(int user_id) {
		boolean b=false;
		try{
			
			ct=SqlHelper.getConnection();
			String sql ="delete from users where id=?";
		
			System.out.println("删除个人信息的语句如下："+sql);
			System.out.println("删除Id ====="+user_id);
			ps=ct.prepareStatement(sql);
			ps.setInt(1, user_id);
			
				
			
			int  i = ps.executeUpdate();
			if(i==1){
				b=true;
				System.out.println("---------------删除用户的信息成功-------------");
			}
		
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			SqlHelper.closeResource(ct, ps, rs);
		}
		return b;
	}

}
