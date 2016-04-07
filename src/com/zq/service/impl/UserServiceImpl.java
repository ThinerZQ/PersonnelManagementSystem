package com.zq.service.impl;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import com.zq.bean.Department;
import com.zq.bean.User;
import com.zq.dao.UserDao;
import com.zq.dao.impl.UserDaoImpl;
import com.zq.service.UserService;

public class UserServiceImpl implements UserService {

	UserDao userdao = null;
	public List<Department> getDepartment() {
		userdao = new UserDaoImpl();
		return userdao.getDepartment();
	}
	
	public boolean login(int department_id, int username_id, String password) {
		userdao  = new UserDaoImpl();
		return userdao.login(department_id, username_id, password);
	}

	
	public User getUserByUserId(int user_id) {
		userdao = new UserDaoImpl();
		return userdao.getUserByUserId(user_id);
	}

	
	public int register(int department_id, String username, String realname,
			String password, String email) {
		userdao = new UserDaoImpl();
		return userdao.register(department_id, username, realname, password, email);
	}

	
	public User getUserByEmail(String email) {
		userdao = new UserDaoImpl();
		return userdao.getUserByEmail(email);
	}

	
	public List<User> getUsersInfo(int pageNow,String sortway) {
		userdao = new UserDaoImpl();
		return userdao.getUsersInfo(pageNow,sortway);
	}
	public int getPageCount(){
		userdao = new UserDaoImpl();
		return userdao.getPageCount();
	}

	
	public List<ResultSet> getResultSetList() {
		userdao = new UserDaoImpl();
		return userdao.getResultSetList();
	}

	
	public int savePhoto(String user_id, String photo) {
	
		userdao = new UserDaoImpl();
		return userdao.savePhoto(user_id, photo);
	}

	@Override
	public List<User> getSelectUserInfo(String username, String sex,
			String email, String department) {
		userdao = new UserDaoImpl();
		return userdao.getSelectUserInfo(username, sex, email, department);
	}

	@Override
	public Map<String,List> getAllUserInfo() {
		userdao = new UserDaoImpl();
		return userdao.getAllUserInfo();
	}

	@Override
	public boolean updateUserInfo(User user) {
		userdao = new UserDaoImpl();
		return userdao.updateUserInfo(user);
	}

	@Override
	public int getCurrentPageCount() {
		userdao = new UserDaoImpl();
		return userdao.getCurrentPageCount();
	}

	@Override
	public boolean updateInfo(User user) {
		userdao = new UserDaoImpl();
		return userdao.updateInfo(user);
	}

	@Override
	public boolean delUser(int user_id) {
		userdao = new UserDaoImpl();
		return userdao.delUser(user_id);
	}

	

}
