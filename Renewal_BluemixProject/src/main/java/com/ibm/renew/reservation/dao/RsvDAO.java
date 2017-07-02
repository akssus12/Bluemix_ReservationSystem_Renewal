package com.ibm.renew.reservation.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ibm.renew.common.dao.AbstractDAO;
import com.ibm.renew.reservation.vo.HistoryVO;
import com.ibm.renew.reservation.vo.RsvVO;

@Repository("rsvDAO")
public class RsvDAO extends AbstractDAO {
	public List<RsvVO> fetchScheduleByProject(HashMap<String,Object> map) {
		return selectList("reservation.fetchScheduleByProjectDate",map);
	}
	
	public String checkRsvTime(RsvVO rsvVO) {
		return (String)selectOne("reservation.checkRsvTime",rsvVO);
	}
	
	public void doReservation(RsvVO rsvVO) {
		insert("reservation.doReservation",rsvVO);
	}
	
	public void doRecordHistory(HistoryVO historyVO) {
		insert("reservation.doRecordHistory",historyVO);
	}
	
	public List<RsvVO> fetchRsvInfo(int seq) {
		return selectList("reservation.fetchRsvInfo", seq);
	}
}
