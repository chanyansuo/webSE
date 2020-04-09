<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>主页</title>
<!--bootstarp-css-->
<link href="../resource/css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
<!--/bootstarp-css -->
<!--css-->
<link rel="stylesheet" href="../resource/css/style.css" type="text/css" media="all" />
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
<!-- 代码 开始 -->
 <div id="firstpane" class="menu_list">
    <p class="menu_head current">综合管理</p>
    <div style="display:block" class=menu_body >
      <a href="admin_User.jsp">用户管理</a>
      <a href="admin_anquan_paper.jsp">试卷规则</a>
      <a href="admin_anquan_ExamPeople.jsp">参考人员</a>
      <a href="admin_anquan_score.jsp">成绩统计</a>
<!--       <a href="admin_UserTime.jsp">用户学时信息</a> -->
    </div>
    <p class="menu_head">保密学习</p>
    <div style="display:none" class=menu_body >
    	<a href="admin_baomi_GFQ.jsp">填空题题库</a>
      	<a href="admin_baomi_TFQ.jsp">判断题题库</a>
      	<a href="admin_baomi_ChoiceQ.jsp">选择题题库</a>
      	<a href="admin_baomi_AQ.jsp">简答题题库</a>
    </div>
    <p class="menu_head">安全知识</p>
    <div style="display:none" class=menu_body >
      	<a href="admin_anquan_TFQ.jsp">判断题题库</a>
      	<a href="admin_anquan_ChoiceQ.jsp">选择题题库</a>
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

<!-- 代码 结束 -->

</body>
</html>