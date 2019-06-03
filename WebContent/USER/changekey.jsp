<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="java.*,java.sql.*,java.sql.Statement"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>保密知识学习</title>
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
			 <div id="secondpane" class="menu_list">
				<p class="menu_head current">用户信息</p>
				<div style="display:block" class=menu_body >
					<a href=?101>个人信息</a>
					<a href=?102>考试成绩</a>
				</div>
				<p class="menu_head">安全管理</p>
				<div style="display:none" class=menu_body >
					<a href=?203>修改密码</a>
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
			$(this).addClass("current").next("div.menu_body").slideDown(500).siblings("div.menu_body").slideUp("slow");
			$(this).siblings().removeClass("current");
		});

	});
	</script>
	<div class="vertical_val">
		<div class="dd2">
			<div class="container">
				<p>修改密码</p>
				<div class="info2">
					<div>
					<div class="info1-left">
						<p>原密码：<input type="password" class="text" name="Uemail"></p>
					</div>
					<div class="info1-right">
						<p>&nbsp;</p>
					</div>
					</div>
					<div>
					<div class="info1-left">
						<p>新密码：<input type="password" class="text" name="Uemail"></p>
					</div>
					<div class="info1-right">
						<p>重复密码：<input type="password" class="text" name="Uemail"></p>
					</div>
					</div>
					<div>
					<input type="submit" value="确认" >
					<input type="reset" value="清除" >
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- strip end here-->
</body>
</html>