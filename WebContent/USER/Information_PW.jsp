<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="java.*,java.sql.*,java.sql.Statement"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>个人信息页</title>
<!--bootstarp-css-->
<link href="../resource/css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
<link rel="stylesheet" href="../resource/css/jquery.cxdialog.css" type="text/css" media="all" />
<!--/bootstarp-css -->
<!--css-->
<link rel="stylesheet" href="../resource/css/style.css" type="text/css" media="all" />
<link rel="stylesheet" href="../resource/css/style2.css" type="text/css" media="all" />
<link rel=stylesheet type=text/css href="../resource/css/zzsc.css">
<!--/css-->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Petcare Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<link href="../resource/css/hover.css" rel="stylesheet" media="all">
<script src="../resource/js/jquery.min.js"></script>
<script src="../resource/js/modernizr.custom.js"></script>
<script src="../resource/js/responsiveslides.min.js"></script>
<script src="../resource/js/jquery.cxdialog.min.js"></script>
</head>
<body>
<!--header-->
<div class="header">
	<div class="header-info">
		<div class="container">
			<jsp:include page="navigation.jsp"></jsp:include>
		</div>
	</div>
</div>
<!--/header-->
<!--login strip start here-->
<div  class="dd3">
	<div class="vertical_banner">
		<div class="banner">
			 <div id="firstpane" class="menu_list">
				<p class="menu_head current">用户信息</p>
				<div style="display:block" class=menu_body >
					<a href="Information_Person.jsp">个人信息</a>
					<a href="Information_Score.jsp">考试信息</a>
				</div>
				<p class="menu_head">安全管理</p>
				<div style="display:none" class=menu_body >
					<a href="Information_PW.jsp">修改密码</a>
				</div>
			</div>
		</div>
	</div>
	<script type=text/javascript>
	$(document).ready(function(){
		$("#firstpane .menu_body:eq(0)").show();
		$("#firstpane p.menu_head").click(function(){
			$(this).addClass("current").next("div.menu_body").slideToggle(300).siblings("div.menu_body").slideUp("slow");
			$(this).siblings().removeClass("current");
		});
		$("#secondpane .menu_body:eq(0)").show();
		$("#secondpane p.menu_head").mouseover(function(){
			$(this).addClass("current").next("div.menu_body").slideDown(1000).siblings("div.menu_body").slideUp("slow");
			$(this).siblings().removeClass("current");
		});

	});
	</script>
	<div class="vertical_val">
		<div class="dd2">
			<div class="container">
				<p>修改密码</p>
				<div class="info2">
					<div class="border">
						<p class="infor-Opass">原&nbsp;&nbsp;密&nbsp;码：<input id="oldPassword" type="password"></p>
						<p class="infor-Npass">新&nbsp;&nbsp;密&nbsp;码：<input id="newPassword1" type="password" onblur="check(1);" onkeyup="check(1);"></p>
						<div id="error1" class="error"></div>
						<p class="infor-Npass">确认密码：<input id="newPassword2" type="password" onblur="check(2);" onkeyup="check(2);"></p>
						<div id="error2" class="error"></div>
						<input id="submit" type="button" value="确认" onClick="submit();">
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- strip end here-->
</body>
</html>
<style>
.new2 {border:1px solid #f33 !important; }
</style>

<script language="javascript" type="text/javascript">
	$("#submit").attr("disabled","disabled");
	var flag = new Array();
	flag[0] = false;
	flag[1] = false;


	function length(value){
		var newPW = new Array();
		newPW[value-1] = document.getElementById("newPassword"+value).value;
		if(newPW[value-1].length < 6){
			$("#newPassword"+value).attr("class","new2");
			flag[value-1] = false;
			$("#error"+value).html("<p>请使用6~12位密码</p>");
		}
		else{
			$("#newPassword"+value).removeAttr("class");
			flag[value-1] = true;
			$("#error"+value).html("");
		}
	}

	function enableSubmit(value1,value2){
		if(value1 && value2){
			$("#submit").removeAttr("disabled");
		}
		else{
			$("#submit").attr("disabled","disabled");
		}
	}

	function validate(value){
		var newPW = new Array();
		newPW[0] = document.getElementById("newPassword1").value;
		newPW[1] = document.getElementById("newPassword2").value;
		if(newPW[0] != "" && newPW[1] != ""){
			if(newPW[0] === newPW[1]){
				$("#newPassword"+value).removeAttr("class");
				flag[value-1] = true;
				$("#error"+value).html("");
			}
			else{
				$("#newPassword"+value).attr("class","new2");
				$("#error"+value).html("<p>两次密码输入不一致</p>");
				flag[value-1] = false;
			}
		}	
	}

	function check(value){
		if(value == 1){
			length(1);
			validate(2);
		}
		if(value == 2){
			length(2);
			validate(2);
		}
		enableSubmit(flag[0],flag[1]);
	}

	function submit(){
		var oldPW = document.getElementById("oldPassword").value;
		var newPW = document.getElementById("newPassword1").value;
		$.ajax({
   	 		cache: true,
   		 	type: "POST",
    		dataType: "json",
    		url: "../rest/user/changePwd",   //后台连接代码
    		data:{"OldPwd":oldPW,"NewPwd":newPW},
    		async: false,
    		error: function(errCode, errMsg) {
	            alert("Connection error: "+errCode);
    		},
    		success: function(data) {
    			if(data==false || data=='false' ){
    				$.cxDialog.defaults.baseClass = 'ios';
    				$.cxDialog({
    					title: '提示',
    					info: '原始密码错误！',
    					ok: function(){
    					}
    				});
    			}
    			else{
    				$.cxDialog.defaults.baseClass = 'ios';
    				$.cxDialog({
    					title: '提示',
    					info: '密码修改成功！',
    					ok: function(){
    					
    					}
    				});
    			}
	    	}
		});
		document.getElementById("oldPassword").value = "";
		document.getElementById("newPassword1").value = "";
		document.getElementById("newPassword2").value = "";
		
	}

</script>