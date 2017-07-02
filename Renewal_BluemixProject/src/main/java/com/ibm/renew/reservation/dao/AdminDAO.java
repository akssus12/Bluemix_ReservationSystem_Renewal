package com.ibm.renew.reservation.dao;

import org.springframework.stereotype.Repository;

import com.ibm.renew.common.dao.AbstractDAO;

@Repository("adminDAO")
public class AdminDAO extends AbstractDAO {
	public String CheckLogin(Object Params) {
		return (String)selectOne("adminlogin.checkAccount",Params);
	}
	
	
}
