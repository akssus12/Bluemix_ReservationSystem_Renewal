package com.ibm.renew.reservation.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ibm.renew.reservation.service.AdminService;
import com.ibm.renew.reservation.service.ProjectService;

/*********************************************************
 * @author yeonwoo.jung
 * @date   2017.02.02
 * @Info   회의실 예약 사이트 접속 및 계정관리 관련 Controller
 * 
 * 수정자		  수정날짜		수정이력
 * 정연우		2017.02.02		최초 생성 
**********************************************************/

@Controller
public class AdminController {
	Logger log = Logger.getLogger(this.getClass());
	
	@Autowired	
	private AdminService adminService;
	
	@Autowired	
	private ProjectService projectService;
		
	@RequestMapping(value = "/AdminLogout.do")
    public String Logout(HttpServletRequest req, HttpServletResponse res, HttpSession session) {
        session.invalidate();
        return "AdminLogin";
    }
	
	@RequestMapping(value = "/home.do",method={RequestMethod.GET,RequestMethod.POST})
    public ModelAndView checkAuth(HttpServletRequest req, HttpServletResponse res, HttpSession session,
    		@RequestParam(value="key",defaultValue="") String key, @RequestParam(value="selectDate",defaultValue="") String selectDate) throws Exception {
		ModelAndView mv = new ModelAndView();
			
		String project = projectService.selectProjectName(key);
				
		if(session.getAttribute("admin") == null) {
			if(key == null || project.equals("")) {
		        mv.setViewName("AdminLogin");
			} else {
				session.setMaxInactiveInterval(10*60*60);
				session.setAttribute("admin", "no");
		        session.setAttribute("project", project);
			}
		}
		
		mv.addObject("selectDate", selectDate);
		mv.setViewName("RsvView");
				
		return mv;		
    }
	
	
	@RequestMapping(value = "/AdminLogin.do", method=RequestMethod.POST)
    public ModelAndView AdminLogin(@RequestParam("id") String id, @RequestParam("pw") String pw, HttpServletRequest req,
    		HttpServletResponse res, HttpSession session) throws Exception {
              
		ModelAndView mv = new ModelAndView();
        HashMap<String,Object> adminMap = new HashMap<String,Object>();
        adminMap.put("id", id);
        adminMap.put("pw", pw);
              
        String checkResult = adminService.checkLogin(adminMap);
        String project = projectService.selectProjectName(id);
        
        log.info("checkResult : "+checkResult);
        if(checkResult != null) {
        	session.setMaxInactiveInterval(10*60*60);
        	session.setAttribute("admin", "yes");
        	session.setAttribute("project", project);
        	mv.addObject("message",true);
        	mv.setViewName("RsvView");
        } else if(checkResult == null) {
        	mv.addObject("message",true);
        	mv.setViewName("AdminLogin");
        } 
        
        return mv;
    }
}
