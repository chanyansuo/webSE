<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="java.*,java.sql.*,java.sql.Statement"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>个人信息页</title>
<!--bootstarp-css-->
<link href="../resource/css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
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
				<p>保密考试信息</p>
				<div class="info2">
					<div id="border" class="border">
						
					</div>
				</div>
			</div>
			<div class="container">
				<p>安全考试信息</p>
				<div class="info2">
					<div id="border2" class="border">
						
					</div>
				</div>
			</div>
		</div>
	</div>
	
</div>
<!-- strip end here-->
</body>
</html>
<script language="javascript" type="text/javascript">

	//全局变量
	var DATA,              //全部数据
		TestSCORE1 = "保密考试成绩：",              //保密考试成绩
		TestTIME1 = "保密考试时间",                //保密考试时间
		TestSCORE2 = "安全考试成绩：",              //安全考试成绩
		TestTIME2 = "安全考试时间",                //安全考试时间
		a = "<p class=\"infor-p1\">",
		b = "</p>",
		TestScore = new Array(),           //考试成绩数据
		TestTime = new Array(),            //考试时间数据
		Length,
		y="",
		z="",
		j;              //计数器
	
	function GetData(){
		$.ajax({
   	 		cache: true,
   		 	type: "GET",
    		dataType: "json",
    		url: "../rest/user/getUserInfo",   //后台连接代码
    		async: false,
    		error: function(request) {
    	    	alert("获取个人信息失败");
    		},
    		success: function(data) {
    			UserInfo = data.Data;
    			ED1 = data.ExamDate;
    			FS1 = data.FinalScore;
    			ED2 = data.SafeExamDate;        //发送安全考试数据  20190701mxy
    			FS2 = data.SafeScore;           //发送安全考试数据  20190701mxy
	    	}
		});
	}
		
	function Valuation(){
		if ( ED1 != "" && typeof(ED1) != "undefined"){
			y = "";
			if( FS1 != 0 ){
				if(FS1 >=80){
					var fs1 = "通过";
				}
				else{
					var fs1 = "未通过";
				}
				y += a + "成绩：" + FS1 + b;
			}
			else{
				var fs1 = "未出成绩";
				y += a + "成绩：暂无" + b;
			}
			
			y += a + "通过情况：" + fs1 + b;
			
			y += a + "考试时间：" + ED1 + b;
			var x = "../rest/Manage/download";
			//y += "<iframe name=\"study\" src=\"../PDF/web/viewer.html?file=../../doc/" + window.sessionStorage.getItem("login_user") + ".pdf\" style=\"width: 90%;height: 500px;\"></iframe>";
			y += "<a id=\"download\" class=\"download\" href=\"" + x +"\">打开试卷</a>";
			y += a + "  " + b;
		}
		else{
			y = a + "对不起，暂无考试数据！" + b;
		}
		if ( ED2 != "" && typeof(ED2) != "undefined"){
			z = "";
			if( FS2 != 0 ){
				if(FS2 >=80){
					var fs2 = "通过";
				}
				else{
					var fs2 = "未通过";
				}
				z += a + "成绩：" + FS2 + b;
			}
			else{
				var fs2 = "未出成绩";
				z += a + "成绩：暂无" + b;
			}
			
			z += a + "通过情况：" + fs2 + b;
			//z += a + "   " + b;
			z += a + "考试时间：" + ED2 + b;
			var x = "../rest/Manage/SafetyEXdownload";
			//y += "<iframe name=\"study\" src=\"../PDF/web/viewer.html?file=../../doc/" + window.sessionStorage.getItem("login_user") + ".pdf\" style=\"width: 90%;height: 500px;\"></iframe>";
			z += "<a id=\"download\" class=\"download\" href=\"" + x +"\">打开试卷</a>";
			z += a + "  " + b;
		}
		else{
			z = a + "对不起，暂无考试数据！" + b;
		}
	}

	function show(){
		$("#border").html(y);
	}
	
	function show2(){
		$("#border2").html(z);
	}
	
	function DownLoad(){
		var y = "D:/doc/"+window.sessionStorage.getItem("login_user")+".pdf";
		window.location.href = y;
	}
	
	GetData();
	Valuation();
	show();
	show2();
	
	
</script>		