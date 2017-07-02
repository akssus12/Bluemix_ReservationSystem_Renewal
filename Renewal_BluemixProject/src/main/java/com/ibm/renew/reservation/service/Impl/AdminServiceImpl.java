package com.ibm.renew.reservation.service.Impl;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ibm.renew.reservation.dao.AdminDAO;
import com.ibm.renew.reservation.service.AdminService;

@Service("adminService")
public class AdminServiceImpl implements AdminService {
	@Resource(name="adminDAO")
	private AdminDAO adminDAO;
	
	public String checkLogin(HashMap<String,Object> map) throws Exception {
		return adminDAO.CheckLogin(map);
	}
	
	
}
