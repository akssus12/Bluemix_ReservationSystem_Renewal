<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회의실 예약 시스템</title>

<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js" type="text/javascript"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="/resources/js/AutoComplete.js"></script>
<script src="/resources/js/ConferUtil.js"></script>
<script src="/resources/js/Validation.js"></script>
<script src="/resources/js/Reservation.js"></script>
<script src="/resources/js/bootstrap-datepicker.js"></script>

<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="/resources/css/RsvView.css">
<link rel="stylesheet" href="https://formden.com/static/cdn/font-awesome/4.4.0/css/font-awesome.min.css" />
<link rel="stylesheet" href="/resources/css/datepicker.css">

<style>
.bootstrap-iso .formden_header h2,.bootstrap-iso .formden_header p,.bootstrap-iso form
   {
   font-family: Arial, Helvetica, sans-serif;
   color: black
}
.bootstrap-iso form button,.bootstrap-iso form button:hover {
   color: white !important;
}
.asteriskField {
   color: red;
}
body {
    font-family: 'Jeju Gothic', serif !important; 
}

#conference {
   position: relative;
   width: 100%;
   height: 20px;
   margin-bottom: 5px;
   background-color: rgba(255, 255, 255, 0); /*마지막0: 투명도 */
}
.conf {
   position: absolute;
   text-align: center;
}
#timeDiv {
   /*border: 5px inset #48BAE4;*/
   position: relative;
   text-align: center;
   border-radius: 10px; /*모서리 약간 둥글게*/
   border-bottom-right-radius: 10px;
   border-top-right-radius: 10px;
   width: 70px;
}
.time {
   position: absolute;
   width: 70px;
   left: 0px;
}


#schedule {
   position: relative;
   width: 100%;
   height: 450px;
   margin-left: auto;
   margin-right: auto;
   margin-bottom: 30px;
   border: 1px;
   background-color: #F9F9F9;
   border-radius: 10px; /*모서리 약간 둥글게*/
   border-bottom-right-radius: 10px;
   border-top-right-radius: 10px;
}
.meeting {
   position: absolute;
   text-align: center;
   color:#ffffff; 
   background-color: #00599D;
   border: 1px solid #ffffff;
}
.meeting:hover {
   position: absolute;
   text-align: center;
   color:#000; 
   background-color: #9D99BB;
   border: 1px solid #ffffff;
}
.empty {
   position: absolute;
   text-align: center;
   background-color: #F9F9F9;
   border-radius: 10px;
}

.empty:hover {
   position: absolute;
   text-align: center;
   background-color: #C9C9C9;
   border-radius: 10px;
}
.line {
   position: absolute;
   height: 450px;
   width: 1px;
   border: 1px dotted #00599D;
}
.line2 {
   position: absolute;
   left: 70px;
   border: 1px dotted #00599D;
}

.line3 {
   position: absolute;
   left: 70px;
  border: 1px dotted #C9C9C9;
}
.align_right {
   
}
textarea:focus,input:focus,input[type]:focus,.uneditable-input:focus {
   border-color: rgba(102, 102, 102, 0.7);
   box-shadow: 0 1px 1px rgba(51, 51, 51, 0.3) inset, 0 0 8px
      rgba(51, 51, 51, 0.9);
   outline: 0 none;
}

</style>

<!-- <script>
function hide(){
	//alert("윤미는집가고싶옹ㅜㅜ");
	var datepickerforhide = request.getAttribute("datepicker");
	datepickerforhide.hide();
}
</script> -->
</head>

<body>

<%-- <%String selectDate = (String)request.getAttribute("selectDate");%> --%>

	<c:if test="${message}">
	<script>
	  alert("관리자 모드입니다.");        
	</script>
	</c:if>
   <!-- navigation bar -->
   <%@ include file="header.jsp"%>   

   <form method="post" name="myForm" action="Reservation.do">
      <div class="container">
         <!-- 사이트 선택 -->
         <div class="row" >
         	<div class="col-md-6 col-sm-9 col-xs-12">
         	<%-- <%if(session.getAttribute("project").equals("master")){ %>
         	   <select class="form-control" id="site" name="site" onchange="masterDisplay(this.value);">
         	   <option value="">선택하세요</option>
         	   <%ArrayList<String> proj = (ArrayList) request.getAttribute("proj");
           		    if (proj != null) {
            	    	 for (int i = 0; i < proj.size(); i++) {
                	     String name = proj.get(i);%>
               		<option value="<%=name%>"><%=name%></option> <%}%>
            	</select>
            <%}}else{
               String project = (String)session.getAttribute("project");%>
               <h2 style="margin-left: 10%; font-family: 'Jeju Gothic', serif;">${project} 회의실</h2>
               <input type="hidden" id="site" name="site" value=${project}>
               <script>displayConf('<%=project%>');</script>
            <%}%> --%>
               <%String project = (String)session.getAttribute("project");%>
               <h2 style="margin-left: 10%; font-family: 'Jeju Gothic', serif;">${project} 회의실</h2>
               <input type="hidden" id="site" name="site" value=${project}>
               <script>displayConf('<%=project%>');</script>
         	</div>
      	</div>

      <!-- 달력 -->
      <%//@ include file="calendar/calendar.jsp"%>
       
         <div class="form-inline" align="right">
         	날짜<input type="text" name="datepicker" id="datepicker" class="form-control" value="${selectDate}" 
         	onchange="hide()">
         	<script>
         		
         		$('#datepicker').datepicker({
           	   		dateFormat : 'yyyy-mm-dd',
            		onSelect: function(selected,evnt) {
            		                    
                	}
            	});
            	$('#datepicker').datepicker('hide');
            
            	
         	</script>
         </div>
         
         <br>
          
          
         <div class="row">
            <!-- 회의실tab & 예약현황 -->
            <div class="col-md-12 col-sm-12 col-xs-12">
               <div>
                    <!-- 회의실탭 -->
                    <div class="col-md-6 col-sm-6 col-xs-12" id="conference"></div>
               
                  <div id="schedule" class="col-md-12 col-sm-12 col-xs-12">
                     <div id="timeDiv"></div>
                     <div id="meetings"></div>
                  </div>
               </div>
            </div> 
         </div>
         <div class="row">
            <!-- 회의실 예약 입력창 -->
            <div class="search-container" style="font-family: 'Nanum Gothic', serif;">
               <div class="row" id="resv_container">
                  <div class="col-md-12 col-sm-12 col-xs-12"></div>
                  <div>
                     <div class="well well-lg col-md-12 col-sm-12 col-xs-12" role="register">
                        <section class="register-form">
                           
                        <div class="row">
                           <div class="col-md-3">
                              <div class="form-group">
                                 <label class="control-label" for="date"> 날짜 </label>
                                 <div class="input-group">
                                    <div class="input-group-addon">
                                       <i class="fa fa-calendar"></i>
                                    </div>
                                    <input type="text" readonly class="form-control" id="date"
                                       name="date">
                                 </div>
                              </div>
                           </div>
                           <div class="col-md-3">
                              <div class="form-group">
                                 <label class="control-label " for="start_time"> 시작시간
                                 </label>
                                 <div class="input-group">
                                    <div class="input-group-addon">
                                       <i class="fa fa-clock-o"> </i>
                                    </div>
                                    <select class="form-control" name="start_time"
                                       id="start_time">
                                       <option value="">선택하세요</option>
                                    </select>
                                 </div>
                              </div>
                           </div>
                           <div class="col-md-3">
                              <div class="form-group">
                                 <label class="control-label" for="end_time"> 끝시간 </label>
                                 <div class="input-group">
                                    <div class="input-group-addon">
                                       <i class="fa fa-clock-o"> </i>
                                    </div>
                                    <select class="form-control" name="end_time" id="end_time">
                                       <option value="">선택하세요</option>
                                    </select>
                                 </div>
                              </div>
                           </div>
                           <div class="col-md-3">
                              <div class="form-group ">
                                 <label class="control-label" for="color"> 색깔
                                 </label>
                                 <div class="input-group">
                                    <div class="input-group-addon">
                                       <i class="fa fa-clock-o"> </i>
                                    </div>
                                    <select class="form-control" name="color"
                                       id="color">
                                       <option value="#00599D">파랑</option>
                                       <option value="#001D59">남색</option>
                                       <option value="#3399ff">하늘</option>
                                       <option value="#33cc33">초록</option>
                                       <option value="#fe9a2e">주황</option>
                                       <option value="#ff1a1a">빨강</option>
                                    </select>
                                 </div>
                              </div>
                           </div>
                        </div>
                        <div class="row">
                           <div class="col-md-4">
                              <div class="form-group ">
                                 <label class="control-label" for="confer_nm"> 회의실 </label>
                                 <div class="input-group">
                                    <div class="input-group-addon">
                                       <i class="fa fa-building"> </i>
                                    </div>
                                    <input type="text" readonly class="form-control" id="confer_nm"
                                       name="confer_nm">
                                 </div>
                              </div>
                           </div>
                           <div class="col-md-4">
                              <div class="form-group ">
                                 <label class="control-label" for="title"> 회의제목 </label>
                                 <div class="input-group">
                                    <div class="input-group-addon">
                                       <i class="fa fa-commenting"> </i>
                                    </div>
                                    <input type="text" class="form-control" id="title"
                                       name="title">
                                 </div>
                              </div>
                           </div>
                           <div class="col-md-4">
                              <div class="form-group ">
                                 <label class="control-label" for="del_pw"> 비밀번호 </label>
                                 <div class="input-group">
                                    <div class="input-group-addon">
                                       <i class="fa fa-lock"> </i>
                                    </div>
                                    <input type="password" class="form-control" id="del_pw"
                                       name="del_pw" data-toggle="tooltip" data-trigger="manual"
                                       data-title="Caps lock is on">
                                 </div>
                              </div>
                           </div>
                           
                           <div class="col-md-4">
                              <div class="form-group ">
                                 <label class="control-label" for="phone"> 전화번호 </label>
                                 <div class="input-group">
                                    <div class="input-group-addon">
                                       <i class="fa fa-phone"> </i>
                                    </div>
                                    <input type="text" id="phone" class="form-control"
                                       name="phone" value="0104995">
                                 </div>
                              </div>
                           </div>
                           <div class="col-md-4">
                              <div class="form-group ">
                                 <label class="control-label" for="name"> 이름 </label>
                                 <div class="input-group">
                                    <div class="input-group-addon">
                                       <i class="fa fa-smile-o"> </i>
                                    </div>
                                    <input class="form-control" id="name" name="name"
                                       placeholder="ex)홍길동" type="text" />
                                 </div>
                              </div>
                           </div>
                           <div class="col-md-4">
                              <div class="form-group ">
                                 <label class="control-label" for="email"> 이메일 </label>
                                 <div class="input-group">
                                    <div class="input-group-addon">
                                       <i class="fa fa-envelope-o"></i>
                                    </div>
                                    <input type="text" class="form-control" id="email"
                                       name="email">
                                 </div>
                              </div>
                           </div>
                        </div>
                        <!-- 예약 버튼 -->
		                  <div id="register" align="right">
		                     <button type="button" class="btn btn-primary" onClick="Reservation();">예약</button>
		                  </div>
		                  <!-- 수정 및 삭제 -->
		                  <div id="registerInfo" align="right">
		                     <button type="button" class="btn btn-primary" onClick="Modify(0);">수정</button>
		                     <button type="button" class="btn btn-primary" onClick="Delete(0);">삭제</button>
		                  </div>
                        </section>
                        <div style="background-color: #3399ff"></div>
                        <input type="hidden" id="rsv_seq" name="rsv_seq">
                        <input type="hidden" id="rsv_repeat_seq" name="rsv_repeat_seq">
                        <input type="hidden" id="rsv_correct_pw" name="rsv_correct_pw">
                        <input type="hidden" id="admin" name="admin" value="<%=(String)session.getAttribute("admin")%>">
                        <input type="hidden" id="selectDate" value="${selectDate}" />
                     </div>
                  </div>
               </div>
            </div>
         </div>
   		</div>
   </form>
   <div style="margin-top: 30px"></div>
	<script>
	/* 오늘날짜로 달력 설정 */
    var selectDate = $('#selectDate').val();
	if( selectDate == "" ) {
		var date = getToday();
		setDate(date);
	} else {
		setDate(selectDate);
	}      
	</script>
	<!-- footer -->
   <%@ include file="footer.jsp"%>  
</body>
</html>