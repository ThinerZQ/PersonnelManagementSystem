package com.zq.service.impl;

import java.util.List;

import com.zq.bean.Department;
import com.zq.bean.User;
import com.zq.dao.AjaxDao;
import com.zq.dao.impl.AjaxDaoImpl;
import com.zq.service.AjaxService;

public class AjaxServiceImpl implements AjaxService{

	AjaxDao ajaxDao = null;
	public List<User> getUserByDepartmentId(Integer department_id) {
		ajaxDao = new AjaxDaoImpl();
		return ajaxDao.getUserByDepartmentId(department_id);
	}
	
	public List<Department> getDepartment() {
		ajaxDao = new AjaxDaoImpl();
		return ajaxDao.getDepartment();
	}

	
	public boolean checkEmailRepeat(String email) {
		ajaxDao = new AjaxDaoImpl();
		return ajaxDao.checkEmailRepeat(email);
	}

	@Override
	public String loadMaxUserId() {
		ajaxDao = new AjaxDaoImpl();
		return ajaxDao.loadMaxUserId();
	}

	@Override
	public List<User> loadUserInformation(String where,Object[] object) {
		ajaxDao = new AjaxDaoImpl();
		return ajaxDao.loadUserInformation(where,object);
	}

}
