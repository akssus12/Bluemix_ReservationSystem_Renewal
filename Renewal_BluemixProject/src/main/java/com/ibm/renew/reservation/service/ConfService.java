package com.ibm.renew.reservation.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibm.renew.reservation.vo.ConfVO;

public interface ConfService {
	public List<ConfVO> fetchRoomByProject(String project) throws Exception; 
	
		
	
}
