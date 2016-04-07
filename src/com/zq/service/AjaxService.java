package com.zq.service;

import java.util.List;

import com.zq.bean.Department;
import com.zq.bean.User;

public interface AjaxService {

	public List<User> getUserByDepartmentId(Integer department_id);
	public List<Department> getDepartment();
	public boolean checkEmailRepeat(String email);
	public String loadMaxUserId();
	
	public List<User> loadUserInformation(String where, Object[] object);
}
