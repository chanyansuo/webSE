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
				<p>考试信息</p>
				<div class="info2">
					<div id="border" class="border">
						
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
		TestSCORE = "考试成绩：",              //考试成绩
		TestTIME = "考试时间",                //考试时间
		a = "<p class=\"infor-p1\">",
		b = "</p>",
		TestScore = new Array(),           //考试成绩数据
		TestTime = new Array(),            //考试时间数据
		Length,
		y="",
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
    			ED = data.ExamDate;
    			FS = data.FinalScore;
	    	}
		});
	}
		
	function Valuation(){
		if ( ED != "" && typeof(ED) != "undefined"){
			y = "";
			if( FS != "" && typeof(FS) != "undefined" ){
				if(FS >=80){
					var fs = "通过";
				}
				else{
					var fs = "未通过";
				}
			}
			else{
				var fs = "未出成绩";
			}
			y += a + "成绩:" + FS + b;
			y += a + "通过情况：" + fs + b;
			y += a + "考试时间：" + ED + b;
			var x = "../rest/Manage/download";
			//y += "<iframe name=\"study\" src=\"../PDF/web/viewer.html?file=../../doc/" + window.sessionStorage.getItem("login_user") + ".pdf\" style=\"width: 90%;height: 500px;\"></iframe>";
			y += "<a id=\"download\" class=\"download\" href=\"" + x +"\">打开试卷</a>";
		}
		else{
			y = a + "对不起，暂无考试数据！" + b;
		}		
	}

	function show(){
		$("#border").html(y);
	}
	
	function DownLoad(){
		var y = "D:/doc/"+window.sessionStorage.getItem("login_user")+".pdf";
		window.location.href = y;
	}
	
	GetData();
	Valuation();
	show();
	
	
</script>		