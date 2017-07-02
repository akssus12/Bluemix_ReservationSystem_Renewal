package com.ibm.renew.reservation.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.ibm.renew.reservation.service.AdminService;
import com.ibm.renew.reservation.service.MemberService;
import com.ibm.renew.reservation.service.ProjectService;
import com.ibm.renew.reservation.service.RsvService;
import com.ibm.renew.reservation.vo.HistoryVO;
import com.ibm.renew.reservation.vo.MemberVO;
import com.ibm.renew.reservation.vo.RsvVO;
import org.apache.commons.lang3.*;

/*********************************************************
 * @author yeonwoo.jung
 * @date   2017.03.01
 * @Info   회의실 예약 관련 Controller
 * 
 * 수정자		  수정날짜		수정이력
 * 정연우		2017.03.01		최초 생성 
**********************************************************/

@Controller
public class RsvController {
	Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
    private RsvService rsvService;    
	
	@Autowired	
	private AdminService adminService;
	
	@Autowired	
	private ProjectService projectService;
	
	@Autowired
	private MemberService memberService;
	
	MemberVO memberVO = new MemberVO();
    HistoryVO historyVO = new HistoryVO();
		
	@RequestMapping(value={"/Reservation.do"}, method=RequestMethod.POST)
    public @ResponseBody void doReservation(RsvVO rsvVO,HttpServletRequest request,HttpServletResponse response,
    		@RequestParam(value="rsv_mem_nm") String mem_nm,@RequestParam(value="rsv_mem_pn") String mem_pn,
    		@RequestParam(value="rsv_mem_em") String mem_em,@RequestParam(value="rsv_site") String mem_site) throws Exception {
		
		response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        
        String message = "";
                
        if( StringUtils.isNotEmpty(mem_nm) || StringUtils.isNotEmpty(mem_pn) || StringUtils.isNotEmpty(mem_em) || StringUtils.isNotEmpty(mem_site) ) {
        	memberVO.setMem_nm(mem_nm);
        	memberVO.setMem_pn(mem_pn);
        	memberVO.setMem_em(mem_em);
        	memberVO.setMem_site(mem_site);        	
        }
                     
        /* 멤버 가입 및 예약 Transaction 처리 */
        if( memberService.isCheckBlocked(mem_pn) != null ) {
        	message = "fail"; 
        } else {
        	if ( rsvService.checkRsvTime(rsvVO) != null ) {
        		message = "fail"; 
        	} else {
        		if ( memberService.isCheckID(mem_pn) != null ) {
        			memberService.updateMember(mem_pn);
        		} else {
        			memberService.joinMember(memberVO);
        		}
        		
        		rsvService.doReservation(rsvVO);
        		rsvService.doRecordHistory(returnHistoryVO(rsvVO));
        	    message = "success";   		
        	}
        }         
               
        JSONObject convertMessage = new JSONObject();
        convertMessage.put("message", message);
		
		JSONObject convertObject = new JSONObject();
		convertObject.put("result", convertObject);
				
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(convertMessage);
		response.setCharacterEncoding("UTF-8");    	
    }
			
	public HistoryVO returnHistoryVO(RsvVO rsvVO) {
				
		historyVO.setHst_rsv_date(rsvVO.getRsv_date());
		historyVO.setHst_rsv_confer_nm(rsvVO.getRsv_confer_nm());
		historyVO.setHst_rsv_del_pw(rsvVO.getRsv_del_pw());
		historyVO.setHst_rsv_start_time(rsvVO.getRsv_start_time());
		historyVO.setHst_rsv_end_time(rsvVO.getRsv_end_time());
		historyVO.setHst_rsv_mem_em(rsvVO.getRsv_mem_em());
		historyVO.setHst_rsv_mem_nm(rsvVO.getRsv_mem_nm());
		historyVO.setHst_rsv_mem_pn(rsvVO.getRsv_mem_pn());
		historyVO.setHst_rsv_site(rsvVO.getRsv_site());
		historyVO.setHst_rsv_title(rsvVO.getRsv_title());
		historyVO.setHst_state("reserve");
		
		return historyVO;
		
	}
	
	@RequestMapping(value={"/SelectRsvInfo.do"}, method=RequestMethod.POST)
	public @ResponseBody void fetchRsvInfo(@RequestParam("seq") int seq, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
       		
		Gson gson = new Gson();
		List<RsvVO> rsvInfo = rsvService.fetchRsvInfo(seq);
		String rsvInfoToJSON = gson.toJson(rsvInfo);
						
		PrintWriter out = response.getWriter();
		out.write(rsvInfoToJSON);
		out.flush();
		out.close();			
	}
}
