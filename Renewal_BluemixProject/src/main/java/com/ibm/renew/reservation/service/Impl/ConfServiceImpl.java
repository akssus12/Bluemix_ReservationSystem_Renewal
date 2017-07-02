package com.ibm.renew.reservation.service.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ibm.renew.reservation.dao.AdminDAO;
import com.ibm.renew.reservation.dao.ConfDAO;
import com.ibm.renew.reservation.service.ConfService;
import com.ibm.renew.reservation.vo.ConfVO;

@Service("confService")
public class ConfServiceImpl implements ConfService {
	@Resource(name="confDAO")
	private ConfDAO confDAO;
	
	public List<ConfVO> fetchRoomByProject(String project) throws Exception {
		return confDAO.fetchRoomByProject(project);
	}
	
	
}
