var conference = [];
var hrSpan = 50; // 1시간 마다 pixel 크기
var totalHeight = hrSpan * 9;

$(document).ready(function(){
	$("#registerInfo").hide();
});	

function leadingZeros(n, digits) {
	var zero = '';
	n = n.toString();

	if (n.length < digits) {
		for (var i = 0; i < digits - n.length; i++)
				zero += '0';
	}
	return zero + n;
}
   
/* 오늘의 날짜 계산를 반환한다. */
function getToday()	{
	var currentTime = new Date();
	var date = "";
	var year = currentTime.getFullYear();
	date += year;
	
	var month = currentTime.getMonth() + 1;
	if(month < 10)
		date += "-0" + month + "-";
	else
		date += "-" + month + "-";
    
	var day = currentTime.getDate();
	if(day < 10)
		date += "0"+day;
	else
		date += day;
       
	return date;
}


/* 분을 시간 문자열로 변환한다. Ex) 540 -> 09:00 */
function minToTime(time) {
	var hr = time/60 - time/60%1;
	var min = time%60;
	var str;
      
	if(hr<10)
		str = "0"+hr+":";
	else
		str = hr+":";
	if(min<10)
		str += "0"+min;
	else
		str += min;
	return str;
 }
   
/*  시간 문자열을 분으로 변경한다. Ex) 0900 -> 540 */
function timeToMin(time){
	var hr = parseInt(time.substring(0,2));
	var min = parseInt(time.substring(2,4));
	return hr*60 + min; 
}
   
/* 분을 디비에 저장하는 시간 문자열로 변경한다. Ex) 540 -> 0900 */
function minToStr(time){
	var hr = time/60 - time/60%1;
	var min = time%60;
	var str = "";
	
	if(hr<10)
		str += "0"+hr;
	else
		str += hr;
	if(min<10)
		str += "0"+min;
	else
		str += min;
	return str;
}
   
/* 프로젝트별 회의실 목록을 보여준다. */
function displayConf(value) {
    
	$.ajax({
		type : "post",
		url : "SelectBySite.do",
		data : {
			project : value
		},
		success : function(data) {
			
			var confNumber = data.length;
				
			var width = (document.getElementById('schedule').offsetWidth-70)/confNumber;
			
			var left = 70;
               
			$('#meetings').empty();
			$('#conference').empty();
			for (var i = 0; i < confNumber; i++) {
				$('#conference').append("<div class='conf' style='top:0px; left:"+left+"px; width:"+width+"px;'>"+data[i].confrn_nm+"</div>");
                $('#meetings').append("<div class='line' style='top:0px; left:"+left+"px;'></div>");
                left += width;
				
            }            
            displayTime();
           
            },
        error : function() {
        	
        	console.log("error");
        }
	});
}
      
/* 시간정보를 보여준다. Ex) 09:00 ~ 18:00 */
function displayTime(){
	var time = 540;
	var top = 0;
	
	$('#timeDiv').empty();
	for (var i = 0; i < 10; i++) {
		
		if(i!=10){
			$('#timeDiv').append("<div class='time' style='top:"+top+"px; '>"+minToTime(time)+"</div>");
        } 
		
		// 가로선긋기
		$('#meetings').append("<div class='line2' style='top:"+top+"px; height:1px; width:"+(document.getElementById('schedule').offsetWidth-70)+"px;'</div>"); // 시간별
        
		top += hrSpan;
		time += 60;
	}
          
	top = hrSpan/2; 
	for (var i = 0; i <= 8; i++) {
		// 가로선긋기(30분선)
		$('#meetings').append("<div class='line3' style='top:"+top+"px; height:1px; width:"+(document.getElementById('schedule').offsetWidth-70)+"px;'</div>"); // 시간별
		top += hrSpan;
	}
}

/* 날짜를 클릭할 때마다 이벤트가 발생하며 회의 스케줄을 보여준다. ! */ 
function displaySchedule(date){
	
	var project = document.getElementById('site').value;
	
	var meetingsList = [];
	var confersList = [];
	$.ajax({
		type : "post",
		async : true,
		url : "SelectRsvBySiteDate.do",
		dataType : 'json',
		data : {
			project : project,
			date : date
		},
		success : function(data) {
			if( data.length == 0 ) {
				alert("No result. Check again!");
			} else {
				confersList = data.confers;
				meetingsList = data.meetings;
			}
			
			var confNumber = data.confers.length;
			var width = (document.getElementById('schedule').offsetWidth - 70)/confNumber;
			var left = 70;
			var top, top2=0, height, j=0, bottom;
			var start, end, alphaT;
			var confName = "";
			var confIdx = 0;

			$('#meetings').empty();      

			conference = [];
			var check = [];
			for (var i = 0; i < confNumber; i++) {
				conference.push(confersList[i].confrn_nm);
				check.push(false);
			}

			for (var i = 0; i < 10; i++) {
				$('#meetings').append("<div class='line2' style='top: "+top2+"px; height:1px; left:0px; width:"+(document.getElementById('schedule').offsetWidth)+"px; </div>"); // 시간별
				top2 += hrSpan;
			}
               
			for (var i = 0; i < confNumber; i++) {
				var flag = false;
				bottom = 0;
                  
				if(j < meetingsList.length){
					confName = meetingsList[j].rsv_confer_nm;
					confIdx = conference.indexOf(confName);
					left = 70 + width * confIdx;
					check[conference.indexOf(confName)] = true;
				}  
                  
				while (j < meetingsList.length && meetingsList[j].rsv_confer_nm == confName) {
                     flag = true;
                     top = (timeToMin(meetingsList[j].rsv_start_time) - 540) / 30 * (hrSpan/2);
                     height = (timeToMin(meetingsList[j].rsv_end_time) - timeToMin(meetingsList[j].rsv_start_time)) / 30 * (hrSpan/2);
                    
                     // 예약되어있지 않는 공간
                     start = bottom/(hrSpan/2)*30+540;
                     end = top/(hrSpan/2)*30+540;
                     alphaT = 0;
                     for(var k = start; k < end; k += 30) {
                        $('#meetings').append("<div id='empty' class='empty'"
                           + "style='top:"+(bottom+alphaT)+"px; left:"+left+"px; width:"+width+"px; height:"+(hrSpan/2)+"px;'"
                           + "onClick='reserve("+confIdx+", "+start+", "+end+", "+k+");'></div>");
                        alphaT += (hrSpan/2);
                     }

                     bottom = top + height;

                     j++;
				}

				if(flag){
					// 예약되어있지 않는 공간
					start = bottom/(hrSpan/2)*30+540;
					end = 360/(hrSpan/2)*30+540;
					alphaT = 0;
					for(var k = start; k < end; k += 30) {
                        $('#meetings').append("<div id='empty' class='empty'"
                           + "style='top:"+(bottom+alphaT)+"px; left:"+left+"px; width:"+width+"px; height:"+(hrSpan/2)+"px;'"
                           + "onClick='reserve("+confIdx+", "+start+", "+end+", "+k+");'></div>");
                        alphaT += (hrSpan/2);
					}
                     
					$('#meetings').append("<div class='line' style='top:0px; left:"+left+"px;'></div>"); // 회의실별
																											// 세로라인
				}
			}
               
			for(var i = 0; i < confersList.length; i++) {
				if(check[i] == false) {
					
					left = 70 + width * i;
					top = 0;
					for(var k = 540; k<1080; k+=30){
						$('#meetings').append("<div id='empty' class='empty'"
                              + "style='top:"+top+"px; left:"+left+"px; width:"+width+"px; height:"+(hrSpan/2)+"px;'"
                              + "onClick='reserve("+i+", "+540+", "+1080+", "+k+");'></div>");
                        top += (hrSpan/2);
					}
                     // 회의실별 세로라인
                     $('#meetings').append("<div class='line' style='top:0px; left:"+left+"px;'></div>");
				}
			}
			
			$('#meetings').append("<div class='line' style='top:0px; left:"+(left+width)+"px;'></div>");
            displayTime();
               
            // 예약된 공간
            for(var j = 0; j < meetingsList.length; j++) {
            	confName = meetingsList[j].rsv_confer_nm;
            	confIdx = conference.indexOf(confName);
            	left = 70 + width * confIdx;
                   
            	top = (timeToMin(meetingsList[j].rsv_start_time) - 540) / 30 * (hrSpan/2);
            	height = (timeToMin(meetingsList[j].rsv_end_time) - timeToMin(meetingsList[j].rsv_start_time)) / 30 * (hrSpan/2);

            	var color = meetingsList[j].rsv_color;
            	if(height <= (hrSpan/2))// 30분짜리 예약일때만 (글씨가 작아지므로)
            		$('#meetings').append("<div id='reserved' class='meeting'"
                            + "style='background-color:"+color+"; top:"+top+"px; left:"+left+"px; width:"+width+"px; height:"+height+"px;'"
                            + "onClick='reserveInfo("+meetingsList[j].rsv_seq+");'>"+meetingsList[j].rsv_title+"</div>");

            	else
                   $('#meetings').append("<div id='reserved' class='meeting'"
                         + "style='background-color:"+color+"; top:"+top+"px; left:"+left+"px; width:"+width+"px; height:"+height+"px; padding-top:"+(height-15)/2+"px'"
                         + "onClick='reserveInfo("+meetingsList[j].rsv_seq+");'>"+meetingsList[j].rsv_title+"</div>");
               }
            },
            error : function() {
               console.log("error");
            }
	});
}
      

/* 예약되어 있는 시간표를 클릭시 상세 정보를 보여준다. */
function reserveInfo(seq){
	$("#register").hide();
	$("#registerInfo").show();
	
	$.ajax({
		type: "post",
		url : "SelectRsvInfo.do",
		dataType : 'json',
		data: {
			seq : seq
		},

		success : function(data) {
			
			$('#name').empty();
			$('#phone').empty();
			$('#email').empty();
			$('#date').empty();
			$('#confer_nm').empty();
			$('#title').empty();
			$('#rsv_seq').empty();
			$('#rsv_correct_pw').empty();
			$('#rsv_repeat_seq').empty();

			for(var i=0; i<data.length; i++) {
				
				$('input[name="name"]').val(data[i].rsv_mem_nm);
				$('input[name="phone"]').val(data[i].rsv_mem_pn);
				$('input[name="email"]').val(data[i].rsv_mem_em);
				$('input[name="date"]').val(data[i].rsv_date);
				$('input[name="confer_nm"]').val(data[i].rsv_confer_nm);
				$('input[name="title"]').val(data[i].rsv_title);
				$('input[name="rsv_seq"]').val(""+seq);
				$('input[name="rsv_correct_pw"]').val(data[i].rsv_del_pw);
				$('input[name="rsv_repeat_seq"]').val(data[i].rsv_repeat_seq);
				$("#color").val(data[i].rsv_color).attr("selected", "selected");
                  
				if(data[i].rsv_repeat_seq == 0){
					$('#adminRsvButton').empty();
					$("#adminRsvButton").append("<button type='button' class='btn btn-primary' onClick='Modify(0);'>수정</button>");
					$("#adminRsvButton").append("<button type='button' class='btn btn-primary' onClick='Delete(0);'>삭제</button>");
				} else {
					$('#adminRsvButton').empty();
					$("#adminRsvButton").append("<button type='button' class='btn btn-primary' onClick='Modify(0);'>수정</button>");
					$("#adminRsvButton").append("<button type='button' class='btn btn-primary' onClick='Delete(0);'>삭제</button>");
					$("#adminRsvButton").append("<button type='button' class='btn btn-primary' onClick='Modify(1);'>전체수정</button>");
					$("#adminRsvButton").append("<button type='button' class='btn btn-primary' onClick='Delete(1);'>전체삭제</button>");
				}	
			}

			timeSelectListAll(data[0].rsv_start_time, data[0].rsv_end_time);
		},
		error : function() {
			console.log("error");
		}
	});
}

/* 빈 회의 시간표를 클릭시 예약 할 수 있도록 해준다. */
function reserve(conf_idx, start, end, selectT){
    
	$("#registerInfo").hide();
	$("#register").show();

	timeSelectList(start, end, selectT);
	
	$('#name').empty();
	$('#phone').empty();
	$('#email').empty();
	$('#confer_nm').empty();
	$('#title').empty();
	$('#del_pw').empty();

	$('input[name="name"]').val("");
	$('input[name="phone"]').val("");
	$('input[name="email"]').val("");
	$('input[name="confer_nm"]').val(conference[conf_idx]);
	$('input[name="title"]').val("");
	$('input[name="del_pw"]').val("");
	$("#color").val("#00599D").attr("selected", "selected");
}

function timeSelectList(start, end, selectT){
	
	$('#start_time').empty();
	
	for(var i=start; i<end; i+=30) {
		$('#start_time').append("<option value='"+minToStr(i)+"'>"+minToTime(i)+"</option>");
	}
	$("#start_time").val(minToStr(selectT)).attr("selected", "selected");

	$('#end_time').empty();
	for(var i=start+30; i<=end; i+=30) {
		$('#end_time').append("<option value='"+minToStr(i)+"'>"+minToTime(i)+"</option>");
	}
	
	var endTime;
	if(selectT + 60 <= 1080)
		endTime = selectT + 60;
	else
		endTime = 1080;
         
	$("#end_time").val(minToStr(endTime)).attr("selected", "selected");
}

function timeSelectListAll(start, end){
	
	$('#start_time').empty();
	for(var i=540; i<1080; i+=30) {
		$('#start_time').append("<option value='"+minToStr(i)+"'>"+minToTime(i)+"</option>");
	}
	$("#start_time").val(start).attr("selected", "selected");
	$('#end_time').empty();

	for(var i=570; i<=1080; i+=30) {
		$('#end_time').append("<option value='"+minToStr(i)+"'>"+minToTime(i)+"</option>");
	}
	$("#end_time").val(end).attr("selected", "selected");
}

/* 오늘 날짜로 달력을 설정한다. */      
function setDate(date) {
	  document.myForm.date.value = date;
      document.myForm.datepicker.value = date;
            
      displaySchedule(date);
}



 