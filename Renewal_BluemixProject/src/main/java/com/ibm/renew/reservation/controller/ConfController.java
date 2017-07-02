package com.ibm.renew.reservation.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.ibm.renew.reservation.service.ConfService;
import com.ibm.renew.reservation.service.MemberService;
import com.ibm.renew.reservation.service.RsvService;
import com.ibm.renew.reservation.vo.ConfVO;
import com.ibm.renew.reservation.vo.MemberVO;
import com.ibm.renew.reservation.vo.RsvVO;

/*********************************************************
 * @author yeonwoo.jung
 * @date   2017.04.15
 * @Info   회의실 정보와 관련된 Controller
 * 
 * 수정자		  수정날짜		수정이력
 * 정연우		2017.04.15		최초 생성 
**********************************************************/

@Controller
public class ConfController {
	Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private ConfService confService;
	
	@Autowired
    private RsvService rsvService;
	
	@Autowired
	private MemberService memberService;
		
	@RequestMapping(value={"/SelectBySite.do"}, method=RequestMethod.POST)
    public @ResponseBody void fetchSiteByProject(@RequestParam("project") String project,HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
		
		Gson gson = new Gson();
		List<ConfVO> roomList = confService.fetchRoomByProject(project);
		String roomListToJSON = gson.toJson(roomList);
		
		PrintWriter out = response.getWriter();
		out.write(roomListToJSON);
		out.flush();
		out.close();
    	
    }
	
	@RequestMapping(value={"/SelectRsvBySiteDate.do"}, method=RequestMethod.POST)
	@ResponseBody
	public void fetchScheduleByProject(@RequestParam("project") String project,
    		@RequestParam("date") String date,HttpServletResponse response) throws Exception {
						
		response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
		
        JSONObject object = new JSONObject();
        JsonArray scheduleJSONArr = new JsonArray();
        JsonArray roomJSONArr = new JsonArray();
        Gson gson = new GsonBuilder().create();
                
        HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("project", project);
		map.put("date", date);
				
		List<RsvVO> scheduleList = rsvService.fetchScheduleByProject(map); // 회의실별 스케쥴을 가져온다.
		List<ConfVO> roomList = confService.fetchRoomByProject(project);
		
		scheduleJSONArr = gson.toJsonTree(scheduleList).getAsJsonArray();
		roomJSONArr = gson.toJsonTree(roomList).getAsJsonArray();
						
		object.put("meetings", scheduleJSONArr);
		object.put("confers", roomJSONArr);
				
		PrintWriter out = response.getWriter();
		out.print(object);		
    }	
	
	@RequestMapping(value={"/autoSearchPhoneNb.do"}, method=RequestMethod.POST)
    public @ResponseBody void autoSearchPhoneNb(@RequestParam("phone") String phone,HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
		log.info("phone : "+phone);
        
		Gson gson = new Gson();
		List<MemberVO> autoInput = memberService.autoSearchPhoneNb(phone);
		String autoInputToJSON = gson.toJson(autoInput);
		log.info("autoInput : "+autoInputToJSON);
		
		PrintWriter out = response.getWriter();
		out.write(autoInputToJSON);
		out.flush();
		out.close();
    	
    }
		
	@RequestMapping(value={"/autoFillElements.do"}, method=RequestMethod.POST)
    public @ResponseBody void autoFillElements(@RequestParam("id") String id,HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
		
		Gson gson = new Gson();
		List<MemberVO> autoFillList = memberService.autoFillElements(id);
		String autoFillListToJSON = gson.toJson(autoFillList);
		
		PrintWriter out = response.getWriter();
		out.write(autoFillListToJSON);
		out.flush();
		out.close();
    	
    }
}
