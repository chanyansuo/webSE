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
				<p>个人信息</p>
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
	var UserInfo,              //全部个人信息数据
		ED1,   //考试日期
		FS1,    //考试成绩
		ED2,   //考试日期
		FS2,    //考试成绩
		NAME = "姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：",              //姓名
		ID = "工&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：",               //工号
		SEX = "性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：",              //性别
		UserPOSITION = "身&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;份：",           //身份
		DEPARTMENT = "部&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;门：",             //部门
		TestCASE_1 = "保密考试情况：",            //考试情况
		TestTIME_1 = "保密考试时间：",   	 //考试时间
		TestCASE_2 = "安全考试情况：",            //考试情况
		TestTIME_2 = "安全考试时间：",   	 //考试时间
		KeepTIME = "在线时间：",        //在线时间
		a = "<p class=\"infor-p\">",
		b = "</p>",
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
		NAME += UserInfo.Name;
		ID += UserInfo.Employee_id;
		SEX += UserInfo.Sex;
		if(UserInfo.User_type == 1){
			UserPOSITION += "普通用户";
		}
		else{
			UserPOSITION += "管理员";
		}
		DEPARTMENT += UserInfo.Department;
		if( typeof(FS1) != "undefined" ){
			if(FS1 >=80){
				var fs1 = "通过";
			}
			else{
				var fs1 = "未通过";
			}
		}
		else{
			var fs1 = "暂无数据";
		}
		TestCASE_1 += fs1;
		if ( ED1 != "" && typeof(ED1) != "undefined"){
			var ed1 = ED1;
		}
		else{
			var ed1 = "暂无数据"
		}
		TestTIME_1 += ed1;
		if( typeof(FS2) != "undefined" ){
			if(FS2 >=80){
				var fs2 = "通过";
			}
			else{
				var fs2 = "未通过";
			}
		}
		else{
			var fs2 = "暂无数据";
		}
		TestCASE_2 += fs2;
		if ( ED2 != "" && typeof(ED2) != "undefined"){
			var ed2 = ED2;
		}
		else{
			var ed2 = "暂无数据"
		}
		TestTIME_2 += ed2;
// 		KeepTIME += UserInfo.name;
	}

	function show(){
		var y = "";
		y += a + NAME + b;
		y += a + ID + b;
		y += a + SEX + b;
		y += a + UserPOSITION + b;
		y += a + DEPARTMENT + b;
//		y += a + TestCASE_1 + b;
		y += a + TestTIME_1 + b;
//		y += a + TestCASE_2 + b;
		y += a + TestTIME_2 + b;
// 		y += a + KeepTIME + b;
		$("#border").html(y);
	}
	
	GetData();
	Valuation();
	show();
	
	
</script>		