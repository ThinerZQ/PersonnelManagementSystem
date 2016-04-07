$("document").ready(function() {
        $.post("ajaxDealRegisterServlet",function(data,textStatus){
			var dataObj = eval("("+data+")");
			for(var i=0;i<dataObj.length;i++){
				//alert(dataObj[i].pid+"   "+dataObj[i].pname);
				$option = $("<option></option>");
				$option.attr("value",dataObj[i].id);
				$option.text(dataObj[i].name);
				
				$("#department").append($option);
			}
		});
		$("#department").change(function(){
			if($("#department").val()==0){
			$("#department_info").children("img").remove();
			$("#department_info").text("请选择你所属的部门");
		}else{
			$("#department_info").children("img").remove();
			$("#department_info").text("");	
			var $img = $("<img></img>");
			$img.attr("src","images/accept.png");
			$("#department_info").append($img);
		}
		});
       
        $("#username").blur(function(){
        	if(this.value.length<2||this.value.length>10){
					$("#username_info").children("img").remove();
					$("#username_info").text("用户名的长度必须在2-10字符之间");
									
			}else{
				$("#username_info").children("img").remove();
				$("#username_info").text("");	
				var $img = $("<img></img>");
				$img.attr("src","images/accept.png");
				$("#username_info").append($img);
			}
		});
		 $("#realname").blur(function(){
        	if(this.value.length<2||this.value.length>4){
					$("#realname_info").children("img").remove();
					$("#realname_info").text("姓名的长度必须在4-8字符之间");
									
			}else{
				$("#realname_info").children("img").remove();
				$("#realname_info").text("");	
				var $img = $("<img></img>");
				$img.attr("src","images/accept.png");
				$("#realname_info").append($img);
			}
		});
		 $("#password").blur(function(){
        	if(this.value.length<6){
					$("#password_info").children("img").remove();
					$("#password_info").text("密码必须是6位以上");
									
			}else{
				$("#password_info").children("img").remove();
				$("#password_info").text("");	
				var $img = $("<img></img>");
				$img.attr("src","images/accept.png");
				$("#password_info").append($img);
			}
		});
		 $("#check_password").blur(function(){
			 
        	if(this.value!= $("#password").val()){
					$("#check_password_info").children("img").remove();
					$("#check_password_info").text("两次输入的密码不匹配，请重新输入");
									
			}else{
				$("#check_password_info").children("img").remove();
				$("#check_password_info").text("");	
				var $img = $("<img></img>");
				$img.attr("src","images/accept.png");
				$("#check_password_info").append($img);
			}
		});
		 $("#email").blur(function(){
			 var patten = new RegExp(/^[\w-]+(\.[\w-]+)*@([\w-]+\.)+[a-zA-Z]+$/);
				
        	if(patten.test(this.value)){
					$.post("ajaxDealEmailServlet",{email:this.value},function(data,textStatus){
							var dataObj = eval("("+data+")");
							var check = dataObj.check;
							if("yes"==check){
								$("#email_info").children("img").remove();
								$("#email_info").text("");	
								var $img = $("<img></img>");
								$img.attr("src","images/accept.png");
								$("#email_info").append($img);
							}else{
								$("#email_info").children("img").remove();
								$("#email_info").text("该邮件已经被注册，请重新填写");
							}
						});			
			}else{
				
				$("#email_info").children("img").remove();
				$("#email_info").text("请输入正确的邮件格式");
			}
		});
        $("#ok").click(function(){
			if($("img").length!=6){
				return false;
			}
			
		});
        
        
        
});