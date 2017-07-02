/**
 * http://usejsdoc.org/
 */
var title = document.getElementById("title");
//title.addEventListener("focus", confCheck, true);

function confCheck() {
	theForm = document.myForm;
	if(theForm.confer_nm.value == "")
		alert("회의실을 선택하세요.");
}

function Reservation() {
	
	if(ValidationCheck() == false){   
		return false;
	}
         
	$.ajax({
		type : "post",
		url : "Reservation.do",
		dataType : 'json',
		data : {
			rsv_mem_pn : document.myForm.phone.value,
			rsv_mem_nm : document.myForm.name.value,
			rsv_mem_em : document.myForm.email.value,
			rsv_site : document.myForm.site.value,
            rsv_confer_nm : document.myForm.confer_nm.value,
			rsv_date : document.myForm.date.value,
			rsv_start_time : document.myForm.start_time.value,
			rsv_end_time : document.myForm.end_time.value,
			rsv_color : document.myForm.color.value,
			rsv_title : document.myForm.title.value,
			rsv_del_pw : document.myForm.del_pw.value
		},

		success : function(data) {
			var msg = "" + data.result.message;
			alert(msg);
			if(msg == "success") {
				alert("예약이 되었습니다.");
			} else {
				alert("선택하신 날짜,회의실,시간에 예약이 되어있거나 Off-Boarding된 회원이므로 예약이 불가능 합니다.");
			}
			                    
		},
		error : function() {
			console.log("error");
		}
	});
	document.myForm.action = "home.do?selectDate="+document.myForm.datepicker.value;
	document.myForm.submit();
}

function Modify(option) {
	if(ValidationCheck() == false){   
		return false;
	}
	if(PasswordCheck() == false){
		return false;
	}
         
	$.ajax({
		type : "post",
		url : "ModifyRsv.do",
		dataType : 'json',
		data : {
			rsv_seq : document.myForm.rsv_seq.value,
			phone : document.myForm.phone.value,
			name : document.myForm.name.value,
			email : document.myForm.email.value,
			site : document.myForm.site.value,
                 
			confer_nm : document.myForm.confer_nm.value,
			date : document.myForm.date.value,
			start_time : document.myForm.start_time.value,
			end_time : document.myForm.end_time.value,
			color : document.myForm.color.value,
			title : document.myForm.title.value,
			del_pw : document.myForm.del_pw.value,
                 
			repeat_seq : document.myForm.rsv_repeat_seq.value,
			option : option
		},

		success : function(data) {
			var msg = "" + data.result.message;
			if(msg == "sucess") {
				alert("수정되었습니다.");
			} else {
				alert("시간이 겹쳐서 수정이 불가합니다.");
			}
		},
		error : function() {
			console.log("error");
		}
	});
	document.myForm.action = "home.do?selectDate="+document.myForm.datepicker.value;
	document.myForm.submit();
}

function Delete(option) {
	if(PasswordCheck() == false){
		return false;
	}
    	 
	$.ajax({
		type : "post",
		url : "DeleteRsv.do",
		dataType : 'json',
		data : {
			rsv_seq : document.myForm.rsv_seq.value,
			repeat_seq : document.myForm.rsv_repeat_seq.value,
			option : option
		},

		success : function(data) {
			var msg = "" + data.result.message;
			if(msg == "sucess") {
				alert("삭제되었습니다.");
			}
		},
		error : function() {
			console.log("error");
		}
	});
	//setDate(document.myForm.date.value);
	document.myForm.action = "home.do?selectDate="+document.myForm.datepicker.value;
	document.myForm.submit();
}

function PasswordCheck(){
 	  theForm = document.myForm;
 	  var userPW = theForm.del_pw.value;
 	  var correctPW = theForm.rsv_correct_pw.value;
 	  	   	  
 	  var admin = $('#admin');

 	  if( admin == "yes" )
 		  return true;
 	  
 	  if(userPW == ""){
 		  alert("비밀번호를 입력하세요.");
 		  theForm.del_pw.focus();
 		  return false;
 	  }
 	  
 	  if(userPW != correctPW){
 			alert("비밀번호가 일치하지 않습니다.");
 		  theForm.del_pw.focus();
 		  return false;
 	  }
	  
	return true;
}
