$("document").ready(function(){
	$("#del").click(function(){
		if(window.confirm("你确认删除吗")){
			return true;
		}else{
			return false;
		};
	});
	
	
});