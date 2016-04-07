
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


window.onload=function(){
	
	 $.post("ajaxDealLoadDepartment",function(data,textStatus){
			var dataObj = eval("("+data+")");
			for(var i=0;i<dataObj.length;i++){
				//alert(dataObj[i].pid+"   "+dataObj[i].pname);
				$option = $("<option></option>");
				$option.attr("value",dataObj[i].id);
				$option.text(dataObj[i].name);
				
				$("#department").append($option);
			}
		});
	
	
	
	
	//获取部门信息
	document.getElementById("department").onchange=function(){
		var department_value=this.value;
		//得到ajax对象
		var xmlReq = ajaxFunction();
		
		//xml是异步的，后面所有服务器端的改变，都会通知反应到readstate，
		//接受服务器端的响应
		//xmlHttprequest对象中有一个readystate属性，标志ajax请求状态״̬
		xmlReq.onreadystatechange = function(){
			//alert(xmlReq.readyState);
			//alert(xmlReq.status);ص�״̬
			if(xmlReq.readyState==4){
				if(xmlReq.status==200 || xmlReq.status==304){
				
					var data = xmlReq.responseText;
					var dataObj = eval("("+data+")");
					
					var realnameElement = document.getElementById("realname");
					realnameElement.length=1;
					//动态创建option追加到后面
					for(var i=0;i<dataObj.length;i++){
						var optionElement = document.createElement("option");
						optionElement.value=dataObj[i].id;
						var optionTextElement = document.createTextNode(dataObj[i].realname);
						optionElement.appendChild(optionTextElement);
						realnameElement.appendChild(optionElement);
					}
				}
			}
		};
		//打开连接
		xmlReq.open("post","./ajaxDealLoginServlet?timeStamp="+new Date().getTime(),true);
		//使用post方法需要这句话
		xmlReq.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		//发送数据
		xmlReq.send("department_value="+department_value);
	
	};
	//验证
	document.getElementById("ok").onclick=function(){
		
		var departmewnt_value = document.getElementById("department").value;
		
		var realname_value = document.getElementById("realname").value;
		var password_value= document.getElementById("password").value;
		var checkinfo_value=document.getElementById("checkinfo").value;
		
		if(departmewnt_value=="0"){
			alert("请选择你的姓名");
			return false;
			
		}else if(realname_value=="0"){
				alert("请选择你的名字");
				return false;
				
		}else if(password_value==""){
			alert("请输入密码");
			return false;
		}else if(checkinfo_value.length!=4){
			alert("请填写有效地验证信息");
			return false;
		}
	};
};
