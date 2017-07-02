package com.ibm.renew.reservation.service.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ibm.renew.reservation.dao.AdminDAO;
import com.ibm.renew.reservation.dao.RsvDAO;
import com.ibm.renew.reservation.service.RsvService;
import com.ibm.renew.reservation.vo.HistoryVO;
import com.ibm.renew.reservation.vo.RsvVO;

@Service("rsvService")
public class RsvServiceImpl implements RsvService {
	@Resource(name="rsvDAO")
	private RsvDAO rsvDAO;
			
	public List<RsvVO> fetchScheduleByProject(HashMap<String,Object> map) throws Exception {
		return rsvDAO.fetchScheduleByProject(map);
	}
	
	public String checkRsvTime(RsvVO rsvVO) throws Exception {
		return rsvDAO.checkRsvTime(rsvVO);
	}
	
	public void doReservation(RsvVO rsvVO) throws Exception {
		rsvDAO.doReservation(rsvVO);
	}
	
	public void doRecordHistory(HistoryVO historyVO) throws Exception {
		rsvDAO.doRecordHistory(historyVO);
	}
	
	public List<RsvVO> fetchRsvInfo(int seq) throws Exception {
		return rsvDAO.fetchRsvInfo(seq);
	}
}
