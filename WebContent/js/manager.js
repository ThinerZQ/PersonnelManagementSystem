function ajaxFunction(){
	var xmlHttp=null;
	try{
		xmlHttp=new XMLHttpRequest();
	}catch(e){
		try{
		xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");	
		}catch(e){
			try{
				xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
			}catch(e){
				
			}
		}
	}
	return xmlHttp;
}

$("document").ready(function() {
	
	
	
	 $("#selconditon").change(function(){
		 	//alert(this.value);	
			switch(this.value){
				
				case "user_id":
								if(document.getElementById("user_id_con")==null){
									var $div=$("<div id='div_1'></div>");
									var $user_id = $("<span>员工号</span>");
									var $user_id_con = $("<select id='user_id_con' name='user_id_con'><option value='mt'>大于</option><option value='lt'>小于</option>"+		                                                             "小于</option>"+
														"<option value='eq'>等于</option>"+
														"</select>");
									var $user_id_value =$("<select id='user_id_value' name='user_id_value'><option value='0'>请选择员工号</option></select>");
									
									$div.append($user_id);
									$div.append($user_id_con);
									$div.append($user_id_value);
									var $button =$("<input type='button' value='删除' id='button1'>");
									$div.append($button);
									$("#select_div").append($div);
									
									$.post("ajaxDealLoadUserMaxId",function(data,textStatus){
										for(var i=1;i<=data;i++){
											$option = $("<option></option>");
											
											$option.attr("value",i);
											$option.text(i);
											$("#user_id_value").append($option);
										}
									});
									
								}else{
									alert("已经选择此项,不要重复选择");
								}
								
							
								break;
				case "realname":
								if(document.getElementById("realname_value")==null){
									var $div=$("<div id='div_2'></div>");
									var $realname = $("<span>员工姓名</span>");
									var $realname_value=$("<input type='text' name='realname_value' id='realname_value'/>");
									$div.append($realname);
									$div.append($realname_value);
									$("#select_div").append($div);
									var $button =$("<input type='button' value='删除' id='button2'>");
									$div.append($button);
								}else{
									alert("已经选择此项,不要重复选择");
								}
								
								
								break;
				case "username":
								if(document.getElementById("username_value")==null){
									var $div=$("<div id='div_3'></div>");
									var $username = $("<span>用户名</span>");
									var $username_value=$("<input type='text' name='username_value' id='username_value'/>");
									$div.append($username);
									$div.append($username_value);
									$("#select_div").append($div);
									var $button =$("<input type='button' value='删除' id='button3'>");
									$div.append($button);
								}else{
									alert("已经选择此项,不要重复选择");
								}
								
								break;
				case "email":
								if(document.getElementById("email_value")==null){
									var $div=$("<div id='div_4'></div>");
									var $email = $("<span>电子邮箱</span>");
									var $email_value=$("<input type='text' name='email_value' id='email_value'/>");
									$div.append($email);
									$div.append($email_value);
									$("#select_div").append($div);
									var $button =$("<input type='button' value='删除' id='button4'>");
									$div.append($button);
								}else{
									alert("已经选择此项,不要重复选择");
								}
								
								break;
				case "age":
								if(document.getElementById("age_con")==null){
									var $div=$("<div id='div_5'></div>");
									var $age = $("<span>年龄</span>");
									var $age_con = $("<select id='age_con' name='age_con'><option value='mt'>大于</option><option value='lt'>小于</option>"+		                                                             "小于</option>"+
														"<option value='eq'>等于</option>"+
														"</select>");
									var $age_value =$("<select id='age_value' name='age_value'></select>");
									
									
									
									$div.append($age);
									$div.append($age_con);
									$div.append($age_value);
									$("#select_div").append($div);
									for(var i=1;i<=130;i++){
										$("#age_value").append($("<option value="+i+">"+i+"</option>"));
									}
									var $button =$("<input type='button' value='删除' id='button5'>");
									$div.append($button);
								}else{
									alert("已经选择此项,不要重复选择");
								}
								
								break;
				case "sex":
								if(document.getElementById("sex_con")==null){
									var $div=$("<div id='div_6'></div>");
									var $sex = $("<span>性别</span>");
									var $sex_con = $("<select id='sex_con' name='sex_con'><option value='男'>男</option><option value='女'>"+		                                                             "女</option></select>");
									$div.append($sex);
									$div.append($sex_con);
									$("#select_div").append($div);
									var $button =$("<input type='button' value='删除' id='button6'>");
									$div.append($button);
								}else{
									alert("已经选择此项,不要重复选择");
								}
							
								break;
				case "registerdate":
								if(document.getElementById("registerdate_con")==null){
									var $div=$("<div id='div_7'></div>");	
									var $registerdate = $("<span>注册时间</span>");
									var $registerdate_value = $("<input type='text' name='registerdate_value' id='registerdate_value'>");
									var $registerdate_con = $("<select id='registerdate_con' name='registerdate_con'><option value='mt'>大于</option><option value='lt'>小于</option>"+		                                                             "小于</option>"+
											"<option value='eq'>等于</option>"+
											"</select>");
									$div.append($registerdate);
									$div.append($registerdate_con);
									$div.append($registerdate_value);
									$("#select_div").append($div);
									var $button =$("<input type='button' value='删除' id='button7'>");
									$div.append($button);
								}else{
									alert("已经选择此项,不要重复选择");
								}
								break;
								/*
				case "department":
								var $div=$("<div id='div_8'></div>");
								var $department = $("<span>部门</span>");
								var $department_value =$("<select id='department_value' name='department_value'><option value='0'>请选择部门信息</option></select>");
								$div.append($department);
								$div.append($department_value);
								$("#select_div").append($div);
								
								 $.post("ajaxDealLoadDepartment",function(data,textStatus){
										var dataObj = eval("("+data+")");
										for(var i=0;i<dataObj.length;i++){
											//alert(dataObj[i].pid+"   "+dataObj[i].pname);
											$option = $("<option></option>");
											$option.attr("value",dataObj[i].id);
											$option.text(dataObj[i].name);
											
											$("#department_value").append($option);
										}
									});
								
								break;		
								*/	
			}
			
			/*
			var len =$("#select_div div").length;
			alert(len);
			
			if(len>=2){
				
				for(var i=0;i<len-1;i++){
					alert($("#select_div div:eq("+i+")"));
					$("#select_div div:eq("+i+")").append("<input type='radio' value='or' name=panduan"+i+">or<input type='radio' value='and' name=panduan"+i+"/>and");
				}
			}
			
			*/
		});
	 	
	 	$("#select_input").click(function(){
	 		$("#information").empty();
	 		$tr = $("<tr></tr>");
	 		if(document.getElementById("user_id_value")!=null){
	 			var $user_td = $("<td style='width=100px;'>员工号</td>");
	 			$tr.append($user_td);
	 		}
	 		if(document.getElementById("realname_value")!=null){
	 			var $realname_td = $("<td style='width=100px;'>真实姓名</td>");
	 			$tr.append($realname_td);
	 		}
	 		if(document.getElementById("username_value")!=null){
	 			var $username_td = $("<td style='width=100px;'>用户名</td>");
	 			$tr.append($username_td);
	 		}
	 		if(document.getElementById("email_value")!=null){
	 			var $email_td = $("<td style='width=100px;'>电子邮件</td>");
	 			$tr.append($email_td);
	 		}
	 		if(document.getElementById("age_value")!=null){
	 			var $age_td = $("<td style='width=100px;'>年龄</td>");
	 			$tr.append($age_td);
	 		}
	 		if(document.getElementById("sex_con")!=null){
	 			var $sex_td = $("<td style='width=100px;'>性别</td>");
	 			$tr.append($sex_td);
	 		}
	 		if(document.getElementById("registerdate_con")!=null){
	 			var $registerdate_td = $("<td style='width=100px;'>注册时间</td>");
	 			$tr.append($registerdate_td);
	 		}
	 		$("#information").append($tr);
	 		/*
	 		if(document.getElementById("department_value")!=null){
	 			var $department_td = $("<td>部门名称</td>");
	 			$("#information:first-child").append($department_td);
	 		}*/
	 		
	 		
	 		
	 		 $.post("ajaxDealSelectInfo",$("#select_div").serialize(),function(data,textStatus){
					var dataObj = eval("("+data+")");
					for(var i=0;i<dataObj.length;i++){
						var $m=$("<tr></tr>");
						var $tr = $("<a href=updelUser?flag=goupdate&user_id="+dataObj[i].id+"> </a>");
						$m.append($tr);
						if(document.getElementById("user_id_value")!=null){
				 			var $user_td = $("<td style='width=100px;'>"+dataObj[i].id+"</td>");
				 			$tr.append($user_td);
				 			
				 		}
				 		if(document.getElementById("realname_value")!=null){
				 			var $realname_td = $("<td style='width=100px;'>"+dataObj[i].realname+"</td>");
				 			$tr.append($realname_td);
				 		}
				 		if(document.getElementById("username_value")!=null){
				 			var $username_td = $("<td style='width=100px;'>"+dataObj[i].username+"</td>");
				 			$tr.append($username_td);
				 		}
				 		if(document.getElementById("email_value")!=null){
				 			var $email_td = $("<td style='width=100px;'>"+dataObj[i].email+"</td>");
				 			$tr.append($email_td);
				 			
				 		}
				 		if(document.getElementById("age_value")!=null){
				 			var $age_td = $("<td style='width=100px;'>"+dataObj[i].age+"</td>");
				 			$tr.append($age_td);
				 		}
				 		if(document.getElementById("sex_con")!=null){
				 			var $sex_td = $("<td style='width=100px;'>"+dataObj[i].sex+"</td>");
				 			$tr.append($sex_td);
				 		}
				 		if(document.getElementById("registerdate_con")!=null){
				 			var $registerdate_td = $("<td style='width=100px;'>"+dataObj[i].registerdate+"</td>");
				 			$tr.append($registerdate_td);
				 		}
				 		/*
				 		if(document.getElementById("department_value")!=null){
				 			var $department_td = $("<td>部门名称</td>");
				 			$tr.append($department_td);
				 		}*/
				 		$("#information").append($m);
					}
				});
	 	});
	 	if(document.getElementById("button1")!=null){
	 		alert(11123);
	 		document.getElementById("button1").onclick=function(){
		 		alert(111);
		 		//$("#div_1").remove();
		 	};
	 	}
	 	
	 
	
	//加载部门信息 
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
		if($("#form1 img").length!=6){
			alert("请完善你的信息再点击注册 ");
			return false;
		}else{
			//alert("进入到ajax添加");
			//ajax实现添加用户
			$.post("registerServlet",$("#form1").serialize(),function(data,textStatus){
				//alert("返回的数据是："+data);
				var flag="success";
				if(data == flag){
					alert("添加失败，重新试试");
				}else{
					alert("恭喜你，添加成功");
				}
				
			});
		}
		
	});
    
    //select被点击
   
    $("#select").toggle(function(){
    		$("#shwoselectinfo").slideDown(1000);
    	},function(){
    		$("#shwoselectinfo").slideUp(1000);
    	});
   //add被点击
    $("#add").toggle(function(){
		$("#showaddinfo").slideDown(1000);
	},function(){
		$("#showaddinfo").slideUp(1000);
	});
    
});