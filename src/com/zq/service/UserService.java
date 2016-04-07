package com.zq.service;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import com.zq.bean.Department;
import com.zq.bean.User;

public interface UserService {

	public List<Department> getDepartment();
	public boolean login(int department_id,int username_id,String password);
	public User getUserByUserId(int user_id);
	public int register(int department_id,String username,String realname,String password,String email);
	public User getUserByEmail(String email);
	//得到所有用户的一个集合
	public List<User> getUsersInfo(int pageNow,String sortway);
	
	
	public int getPageCount();
	public List<ResultSet> getResultSetList();
	public int savePhoto(String user_id,String photo);
	//查找用户信息
	public List<User> getSelectUserInfo(String username,String sex ,String email,String department);
	//得到所有用户信息
	public Map<String,List> getAllUserInfo();
	
	//更新用户信息
	public boolean updateUserInfo(User user);
	//得到当前正在查询的的数据共有多少条
	public int getCurrentPageCount();
	//更新用户信息
	public boolean updateInfo(User user);
	//删除用户
	public boolean delUser(int user_id);
	
	
	
}
