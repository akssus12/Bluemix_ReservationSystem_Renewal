
/* 이름을 입력하면 자동으로 나머지 필드(폰번호,이메일,사이트)를 자동으로 입력 */
function AutoFillElements(value)
{      
	
    $.ajax({
           type: "post",
           url : "autoFillElements.do",
           dataType : 'json',
           data: {
              id : value
           },
          
           success : function(data) {
              $('#name').empty();
              $('#email').empty();
                             
              for(var i=0; i<data.length; i++) {
                 alert(data[i].mem_nm);
                 $('input[name="name"]').val(data[i].mem_nm);
                 $('input[name="email"]').val(data[i].mem_em);
                 
            }
                         
           },
           error : function() {
              console.log("error");
           }
   });
}

/* 번호를 입력하면 과거 입력으로 캐시에 남아있는 데이터를 보여준다. */
$(document).ready(function() {
   $(function() {
      $("#phone").autocomplete(
            {
         source : function(request, response) {
            $.ajax({
               
               url : "autoSearchPhoneNb.do",
               type : "POST",
               minLength: 1,
               data : {
                  phone : request.term
               },
               dataType : "json",
               
               success : function(data) {
            	  result = [] 
            	  for(var i=0;i<data.length;i++) {
            		  result.push(data[i].mem_pn);      		  
            	  }
            	  response(result)   
               },
               
               
            });
         },
            select : function(event,ui) {
             
               AutoFillElements(ui.item.value);
               
            },
            focus: function( event, ui ) {
               return false; 
            }               
      });
   });
});

