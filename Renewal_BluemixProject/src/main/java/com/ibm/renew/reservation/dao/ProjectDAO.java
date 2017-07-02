package com.ibm.renew.reservation.dao;

import org.springframework.stereotype.Repository;

import com.ibm.renew.common.dao.AbstractDAO;

@Repository("projectDAO")
public class ProjectDAO extends AbstractDAO {
	public String selectProjectName(String id) {
		return (String)selectOne("project.selectProjectName",id);
	}
}
