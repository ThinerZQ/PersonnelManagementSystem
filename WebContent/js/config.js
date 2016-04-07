function xmlFunction(filename){
	/*
	var xmlDoc =null;
	try{
		xmlDoc=new ActiveXObject("Microsoft.XMLDOM");
	}catch(e){
		try{
			xmlDoc = document.implementation.createDocument("","",null);
		}catch(e){
			
		}
	}
	xmlDoc.async="false";
	xmlDoc.load("database.xml");
	return xmlDoc;
	*/
	
/*	
	
	try //Internet Explorer
	  {
	  xmlDoc=new ActiveXObject("Microsoft.XMLDOM");
	  }
	catch(e)
	  {
	  try //Firefox, Mozilla, Opera, etc.
	    {
	    xmlDoc=document.implementation.createDocument("","",null);
	    }
	  catch(e) {
		  alert(e.message);
		}
	  }
	try 
	  {
	  xmlDoc.async=false;
	  xmlDoc.load("database.xml");
	  return(xmlDoc);
	  }
	catch(e) {
		alert(e.message);
	}
	return(null);
	*/
	
	 var xmlDoc;
	 try{
	      xmlDoc=new ActiveXObject("Microsoft.XMLDOM");
	 } catch(e){
	      try{
	        var oXmlHttp = new XMLHttpRequest() ;
	        oXmlHttp.open( "GET", "database.xml", false ) ;
	        oXmlHttp.send(null) ; 
	        return oXmlHttp.responseXML;
	     }catch(e){
	    	 alert(e.message);
	         return;
	     }
	 }
	 xmlDoc.async=false;
	 xmlDoc.load(filename);
	 return xmlDoc;
}
window.onload=function(){
	//得到xmlrequest对象
	/********************************/
	  var xmlDoc = xmlFunction("database.xml");
	  
	  
		var databaseproduct = xmlDoc.getElementsByTagName("database-product");
		
		
		for(var i=0;i<databaseproduct.length;i++){
			var $optionElement = $("<option></option>");
			var optionTextElement = document.createTextNode(databaseproduct[i].attributes.getNamedItem("name").nodeValue);
			//alert(databaseproduct[i].attributes.getNamedItem("name").nodeValue);
			$optionElement.attr("value",databaseproduct[i].attributes.getNamedItem("name").nodeValue);
			$optionElement.append(optionTextElement);
			$("#database").append($optionElement);
		}
		//alert(databaseproduct.length);
		
		$("#database").change(function(){
			
			document.getElementById("databasename").length=1;
			document.getElementById("databaseusername").length=1;
			
			
			
			
			var databaseValue =this.value;
			//alert("数据库的类型："+databaseValue);
			var current_database=null;
			for(var i=0;i<databaseproduct.length;i++){
				//alert(databaseproduct[i].attributes.getNamedItem("name").nodeValue);
				try{
						if(databaseproduct[i].getAttribute("name")==databaseValue){
							current_database=databaseproduct[i];
						}
					
				}catch(e){
					if(databaseproduct[i].attributes.getNamedItem("name").nodeValue==databaseValue){
					//alert(e.message);
					current_database=databaseproduct[i];
					//alert(current_database);
					}
				}
				//alert("123123");
			}
			var databasename = current_database.getElementsByTagName("database-name");
			//alert("所选类型的数据库有: "+databasename.length);
			var databaseurl = current_database.getElementsByTagName("url-pattern");
			var databasedriver = current_database.getElementsByTagName("driver-name");
			var databaseuser = current_database.getElementsByTagName("user");
			for(var i=0;i<databasename.length;i++){
				
				//alert("所选数据库名字是： "+databasename[i].childNodes[0].nodeValue );
				var $optionElement = $("<option></option>");
				
				var optionTextElement = document.createTextNode(databasename[i].childNodes[0].nodeValue);
				//alert("所选数据库名字是： "+databasename[i].childNodes[0].nodeValue );
				$optionElement.attr("value",databasename[i].childNodes[0].nodeValue);
				$optionElement.append(optionTextElement);
				$("#databasename").append($optionElement);
			}
			$("#driver").attr("value",databasedriver[0].childNodes[0].nodeValue);
			$("#url").attr("value",databaseurl[0].childNodes[0].nodeValue);
			//alert("数据库下面有："+databaseuser.length+"  个用户");
			for(var i=0;i<databaseuser.length;i++){
				//var databaseusername = databaseuser[i].firstChild;
				//alert(databaseusername);
				//var databaseusername1 =databas("username");
				//alert(databaseusername1);
				var $optionElement = $("<option></option>");
				var optionTextElement;
				try{
					//alert(databaseuser[i].getAttribute("name"));
					optionTextElement = document.createTextNode(databaseuser[i].getAttribute("name"));
					$optionElement.attr("value",databaseuser[i].getAttribute("name"));
					
				}catch(e){
					//alert(e.message);
					 optionTextElement = document.createTextNode(databaseuser[i].attributes.getNamedItem("name").nodeValue);
					$optionElement.attr("value",databaseuser[i].attributes.getNamedItem("name").nodeValue);
				}
				$optionElement.append(optionTextElement);
				$("#databaseusername").append($optionElement);
			}
		});
		
		//验证
		$("#connect").click(function(){
			if($("#database").val()==""){
				alert("请选择你要使用何种数据库产品");
				return false;
			}else if($("#databasename").val()==""){
				alert("请选择所使用的数据库名称");
				return false;
			}else if($("#databaseusername").val()==""){
				alert("请选择用户名");
				return false;
			}else if($("#databasepassword").val()==""){
				alert("请输入密码");
				return false;
			}
		});
};

