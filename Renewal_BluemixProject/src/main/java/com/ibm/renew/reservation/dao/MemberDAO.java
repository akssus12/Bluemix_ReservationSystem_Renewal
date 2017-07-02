package com.ibm.renew.reservation.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ibm.renew.common.dao.AbstractDAO;
import com.ibm.renew.reservation.vo.MemberVO;

@Repository("memberDAO")
public class MemberDAO extends AbstractDAO {
	public String isCheckBlocked(String phone) {
		
		return (String)selectOne("member.isCheckBlocked",phone);
		
	}
	
	public String isCheckID(String phone) {
		return (String)selectOne("member.isCheckID",phone);
	}
	
	public void joinMember(MemberVO memberVO) {
		insert("member.joinMember",memberVO);
	}
	
	public void updateMember(String phone) {
		update("member.updateMember",phone);
	}
	
	public List<MemberVO> autoSearchPhoneNb(String phone) {
		return selectList("member.autoSearchPhoneNb", phone);
	}
	
	public List<MemberVO> autoFillElements(String id) {
		return selectList("member.autoFillElements", id);
	}
}
