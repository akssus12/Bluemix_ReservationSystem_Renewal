package com.ibm.renew.reservation.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibm.renew.reservation.vo.HistoryVO;
import com.ibm.renew.reservation.vo.RsvVO;

public interface RsvService {
	
	public List<RsvVO> fetchScheduleByProject(HashMap<String,Object> map) throws Exception;
	
	public List<RsvVO> fetchRsvInfo(int seq) throws Exception;
	
	public String checkRsvTime(RsvVO rsvVO) throws Exception;
	
	public void doReservation(RsvVO rsvVO) throws Exception;
	
	public void doRecordHistory(HistoryVO historyVO) throws Exception;
	
	
}
