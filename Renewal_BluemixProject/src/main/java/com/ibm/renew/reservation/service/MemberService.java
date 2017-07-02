package com.ibm.renew.reservation.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibm.renew.reservation.vo.MemberVO;

public interface MemberService {
	public String isCheckBlocked(String phone) throws Exception;
	
	public String isCheckID(String phone) throws Exception;
	
	public void joinMember(MemberVO memberVO) throws Exception;
	
	public void updateMember(String phone) throws Exception;
	
	public List<MemberVO> autoSearchPhoneNb(String phone) throws Exception;
	
	public List<MemberVO> autoFillElements(String id) throws Exception;
}
