
window.onload=function(){
	
		
	 $("#username").blur(function(){
		
	     	if(this.value.length<2||this.value.length>10){
						$("#username_info").hide();
						$("#username_info").show();
										
				}else{
					$("#username_info").hide();	
				}
			});
			
		 $("#password").blur(function(){
	     	if(this.value == ""){
					$("#password_info").hide();
				}else if(this.value.length<6){
					$("#password_info").hide();
					$("#password_info").show();
				}
			});
		 $("#age").blur(function(){
			 	var r = /^\d+$/;
			 	if(r.test(this.value)==true){
			 		if(this.value>120 ||this.value<10){
			 			$("#age_info").hide();
						$("#age_info").show();
			 		}else{
			 			$("#age_info").hide();
			 		}
			 		
			 	}else{
			 		$("#age_info").hide();
					$("#age_info").show();
			 	}
			 	/*
		     	if(this.value == ""){
		     			$("#age_info").hide();
		     			alert(1);
						$("#age_info").show();
					}else if(this.value>120 ||this.value<10 ){
						$("#age_info").hide();
						$("#age_info").show();
						alert(2);
					}else{
						$("#age_info").hide();
					}
					*/
			});
		 $("#update").click(function(){
			 var hidden = $("a:hidden");
			 alert(typeof(hidden.length));
			 alert(hidden.length);
			 alert(hidden.length!=3);
			 if(hidden.length!=3){
				 alert("请填写正确的信息");
				 return false;
			 }
			 
		 });
				
};
	 
	
