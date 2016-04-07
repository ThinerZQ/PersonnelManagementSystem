
window.onload=function() {
	$("#database").change(function(){
		document.getElementById("table").length=1;
		if(this.value=="softwareassignment"){
			
			var $option1 = $("<option value='users'>users</option>");
			var $option2 = $("<option value='department'>department</option>");
			var $option3 = $("<option value='role'>role</option>");
			var $option4= $("<option value='root'>root</option>");
			$("#table").append($option1);
			$("#table").append($option2);
			$("#table").append($option3);
			$("#table").append($option4);
		}
	});
	$("#ok").click(function(){
		if($("#database").val()=="0"){
			alert("请选择数据库");
			return false;
		}
	});
};