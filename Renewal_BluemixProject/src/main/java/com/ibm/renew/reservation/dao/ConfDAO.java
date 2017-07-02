package com.ibm.renew.reservation.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ibm.renew.common.dao.AbstractDAO;
import com.ibm.renew.reservation.vo.ConfVO;

@Repository("confDAO")
public class ConfDAO extends AbstractDAO {
	public List<ConfVO> fetchRoomByProject(String project) {
		return selectList("conference.fetchRoomByProject",project);
	}
		
}
