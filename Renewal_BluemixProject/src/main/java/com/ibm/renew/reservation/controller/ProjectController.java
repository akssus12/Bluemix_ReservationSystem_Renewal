package com.ibm.renew.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ibm.renew.reservation.service.ProjectService;

/*********************************************************
 * @author yeonwoo.jung
 * @date   2017.02.26
 * @Info   프로젝트 관리 관련 Controller(사용 미정)
 * 
 * 수정자		  수정날짜		수정이력
 * 정연우		2017.02.26		최초 생성 
**********************************************************/


@Controller
public class ProjectController {
	@Autowired
	private ProjectService projectService;
}
