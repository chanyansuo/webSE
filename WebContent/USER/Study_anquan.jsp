﻿<%@ page language="java" contentType="text/html; charset=utf-8"
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
	<div id="firstpane" class="menu_list">
    <p id="1" class="menu_head current">舰船分所保密管理制度</p>
    <div style="display:block" class=menu_body >
		<a>安全考试系统题库</a>
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
</div>
<div class="vertical_val">
	<div class="dd2">
	<div>
		<div class="container">
			<div class="img-bg2">
				<div class="study">
					<div class="title1">
						<iframe name="study" src="../PDF/web/viewer.html?file=../../resource/word/anquan/1001.pdf" class="PDFiframe"></iframe>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</div>
</div>
<!-- strip end here-->

<script type="text/javascript">
	var loc = location.href;
	var n1 = loc.length;
	var n2 = loc.indexOf("?");
	var id;
	if(n2 != -1){
		id = loc.substr(n2+1,n1-n2);
	}
	if(parseInt(id)>=100 && parseInt(id)<104){
		$("#2").addClass("current").next("div.menu_body").slideToggle(300).siblings("div.menu_body").slideUp("slow");
		$("#2").siblings().removeClass("current");
	}
	else if(parseInt(id)>=200 && parseInt(id)<204){
		$("#3").addClass("current").next("div.menu_body").slideToggle(300).siblings("div.menu_body").slideUp("slow");
		$("#3").siblings().removeClass("current");
	}
	else if(parseInt(id)>=1 && parseInt(id)<28){
		$("#1").addClass("current").next("div.menu_body").slideToggle(300).siblings("div.menu_body").slideUp("slow");
		$("#1").siblings().removeClass("current");
	}
</script>

</body>
</html>