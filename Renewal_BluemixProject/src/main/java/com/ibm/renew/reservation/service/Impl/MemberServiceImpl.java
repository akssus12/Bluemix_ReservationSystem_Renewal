package com.ibm.renew.reservation.service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ibm.renew.reservation.dao.AdminDAO;
import com.ibm.renew.reservation.dao.MemberDAO;
import com.ibm.renew.reservation.service.MemberService;
import com.ibm.renew.reservation.vo.MemberVO;

@Service("memberService")
public class MemberServiceImpl implements MemberService {
	@Resource(name="memberDAO")
	private MemberDAO memberDAO;
	
	public String isCheckBlocked(String phone) throws Exception {
		
		return memberDAO.isCheckBlocked(phone);
	}
	
	public String isCheckID(String phone) throws Exception {
		return memberDAO.isCheckID(phone);
	}
	
	public void joinMember(MemberVO memberVO) throws Exception {
		memberDAO.joinMember(memberVO);
	}
	
	public void updateMember(String phone) throws Exception {
		memberDAO.updateMember(phone);
	}
	
	public List<MemberVO> autoSearchPhoneNb(String phone) throws Exception {
		return memberDAO.autoSearchPhoneNb(phone);
	}
	
	public List<MemberVO> autoFillElements(String id) throws Exception {
		return memberDAO.autoFillElements(id);
	}
}
