package com.ibm.renew.reservation.service.Impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ibm.renew.reservation.dao.AdminDAO;
import com.ibm.renew.reservation.dao.ProjectDAO;
import com.ibm.renew.reservation.service.ProjectService;

@Service("projectService")
public class ProjectServiceImpl implements ProjectService{
	@Resource(name="projectDAO")
	private ProjectDAO projectDAO;
	
	public String selectProjectName(String id) throws Exception {
		return projectDAO.selectProjectName(id);
	}
}
