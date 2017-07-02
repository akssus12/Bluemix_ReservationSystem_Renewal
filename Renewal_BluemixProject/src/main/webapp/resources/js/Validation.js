/**
 *  수정자   : 정연우
 *  수정일자 : 2017-04-20
 *  수정목적 : Javascript 코드 분리
 */


/* 회의실 예약시 Validation Check */
function ValidationCheck(){
   theForm = document.myForm;
   
   if (theForm.date.value == "") {
      alert("날짜를 선택하세요.");
      theForm.date.focus();
      return false;
   }
   else if (theForm.confer_nm.value == "") {
      alert("회의실을 선택하세요.");
      theForm.confer_nm.focus();
      return false;
   }
   else if (theForm.start_time.value >= theForm.end_time.value) {
      alert("유효하지 않은 시간입니다.");
      return false;
   }
   else if (theForm.title.value == "") {
      alert("회의제목을 입력하세요.");
      theForm.title.focus();
      return false;
   }
   else if (theForm.phone.value == "") {
      alert("전화번호를 입력하세요.");
      theForm.phone.focus();
      return false;
   }
   else if (theForm.name.value == "") {
      alert("이름을 입력하세요.");
      theForm.name.focus();
      return false;
   }
   else if (theForm.email.value == "") {
      alert("이메일을입력하세요.");
      theForm.email.focus();
      return false;
   }
   
   else if (theForm.title.value.length >= 20) {
      alert("회의제목을 20자 이내로 입력해주세요.");
      theForm.title.focus();
      return false;
   }
   else if (!parseInt(theForm.phone.value)) {
      alert("전화번호는 숫자만 입력해주세요");
      theForm.phone.focus();
      return false;
   }
   else if (!(theForm.phone.value.length == 11 || theForm.phone.value.length == 10)) {
	  alert("전화번호 자리수는 10~11개여야 합니다.");
      theForm.phone.focus();
      return false;
   }
   else if (theForm.name.value.length >= 15) {
      alert("이름을 확인해 주세요");
      theForm.name.focus();
      return false;
   }
   else if (!isEmail(theForm.email.value)) {
      alert("이메일을 확인해 주세요");
      theForm.email.focus();
      return false;
   }
   
   function isEmail(email) {
	   var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	   return regex.test(email);
	}
   
   return true;
}



/* 날짜 validation */
function getAdminMonth(projectname){
   
   $.ajax({
      type: "post",
      url : "GetAdminMonth.do",
      dataType : 'json',
      data: {
         project : projectname
      },

      success : function(data) {      
         
         var adminmonth = 0;
         var newdate; 
         var d = new Date();
         var date = leadingZeros(d.getFullYear(), 4) + '-' +
         leadingZeros(d.getMonth() + 1, 2) + '-' +
         leadingZeros(d.getDate(), 2);
         
         adminmonth = data.result[0].admin_month; // db에서 admin_month 가져옴
         // alert("이번달날짜:"+date);
         
         if((d.getMonth()+adminmonth+1)>12) // 이번달 + admin_month 가 12월을 초과하면
         {
            newdate = leadingZeros((d.getFullYear()+1), 4) + '-' +
            leadingZeros((d.getMonth()+adminmonth-12) + 1, 2) + '-' +
            leadingZeros(d.getDate(), 2) + ' '; // 유의해야할게 1월은 javascript에서 0으로
												// 표시 ㅎ...
         }
         else 
         {
            newdate = leadingZeros(d.getFullYear(), 4) + '-' +
            leadingZeros((d.getMonth()+adminmonth) + 1, 2) + '-' +
            leadingZeros(d.getDate(), 2) + ' ';
         }

         // alert("달력에서고른날짜"+document.myForm.date.value);
         if (document.myForm.date.value > newdate) {
            alert("관리자가 지정한 날짜보다 초과하였습니다. 날짜를 다시 선택해주세요.");
            $('input[name="date"]').val("");    
         }
         else if(document.myForm.date.value < date){
            $('input[name="date"]').val("");
            alert("오늘보다 이전 날짜는 예약이 되지 않습니다. 날짜를 다시 선택해주세요.");
         }

         else {
            $('input[name="confer_nm"]').val("");
         }

      },
      error : function() {
         console.log("error");
      }
   });
   return adminmonth; 
}