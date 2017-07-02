package com.ibm.renew.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ibm.renew.reservation.service.MemberService;

/*********************************************************
 * @author yeonwoo.jung
 * @date   2017.03.10
 * @Info   회의실 예약자 및 차단관리 관련 Controller
 * 
 * 수정자		  수정날짜		수정이력
 * 정연우		2017.03.10		최초 생성 
**********************************************************/

@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;
}
