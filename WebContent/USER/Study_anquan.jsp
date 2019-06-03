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
	 <div id="firstpane" class="menu_list">
    <p id="1" class="menu_head current">舰船分所保密管理制度</p>
    <div style="display:block" class=menu_body >
		<a href=?01>涉密信息系统安全保密管理制度</a>
		<a href=?02>涉密信息系统应急计划与响应策略</a>
		<a href=?03>保密守则</a>	
		<a href=?04>保密责任制</a>
		<a href=?05>要害部门保密管理规定</a>
		<a href=?06>“三员”工作职责</a>
		<a href=?07>外出试验、调试、护航人员管理规定</a>
		<a href=?08>外来人员保密管理规定</a>
		<a href=?09>台式计算机使用保密管理规定</a>
		<a href=?10>便携式计算机保密管理规定</a>
		<a href=?11>办公自动化设备保密管理规定</a>
		<a href=?12>涉密计算机维修与报废管理规定</a>
		<a href=?13>涉密设备保密管理规定</a>
		<a href=?14>涉密设备维修保密管理规定</a>
		<a href=?15>涉密载体保密管理规定</a>
		<a href=?16>三合一装置保密管理规定</a>
		<a href=?17>打印输出保密管理规定</a>
		<a href=?18>机要室安全保密管理规定</a>
		<a href=?19>军品总装调试车间安全保密管理规定</a>
		<a href=?20>涉密机房安全保密管理规定</a>	
		<a href=?21>网络服务室管理规定</a>
		<a href=?22>参加陆上联调试验保密工作方案</a>
		<a href=?23>打印输出保密管理规定</a>
		<a href=?24>涉密会议保密管理规定</a>
		<a href=?25>终端安全登录使用要求</a>
		<a href=?26>保密奖惩管理办法</a>
		<a href=?27>光盘刻录与传递的管理规定</a>
    </div>
    <p id="2" class="menu_head">六七八学习</p>
    <div style="display:none" class=menu_body >
	    <a href=?101>国防科技工业安全保密“六条规定”</a>
	    <a href=?102>军工单位七类人员安全保密管理措施</a>
	    <a href=?103>国防科技工业安全保密八个集中管理</a>
    </div>
    <p id="3" class="menu_head">保密知识题库与答案</p>
    <div style="display:none" class=menu_body >
	    <a href=?201>保密基本知识试题</a>
	    <a href=?202>武器装备保密资格审查认证</a>
	    <a href=?203>武器装备科研生产单位保密资格标准</a>
	    <a href=?204>武器装备科研生产单位保密资格标准-新</a>
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
					<% 
						String a;
					
						if (request.getQueryString()==null)
						{
							a = "01";
						}
						else
						{
							a = request.getQueryString();
						}

						int x = Integer.parseInt(a);
						int y;
						x = x+1;
						y = x-2;
						String b = String.valueOf(x);
						String c = String.valueOf(y);
						if (b.length()<2){
							b = "0" + b;
						}
						if (c.length()<2){
							c = "0" + c;
						}
						
					%>
						<iframe name="study" src="../PDF/web/viewer.html?file=../../resource/word/BaomiSystem/<% out.print(a); %>.pdf" class="PDFiframe"></iframe>
					</div>
				</div>
				<div >
					<div class="navigation">
					<%
					if (y>0 && y<28){
					%>
						<div class="nav"><a href=?<% out.print(b); %> class="buttom">下一篇</a></div>
					<%
					}
					if (y>0 && y<28){
					%>
						<div class="nav"><a href=?<% out.print(c); %> class="buttom">上一篇</a></div>
					<%
					}
					if (y>200 && y<204){
						%>
							<div class="nav"><a href=?<% out.print(b); %> class="buttom">下一篇</a></div>
						<%
						}
					if (y>200 && y<204){
						%>
							<div class="nav"><a href=?<% out.print(c); %> class="buttom">上一篇</a></div>
						<%
						}
					if (y>100 && y<104){
						%>
							<div class="nav"><a href=?<% out.print(b); %> class="buttom">下一篇</a></div>
						<%
						}
					if (y>100 && y<104){
						%>
							<div class="nav"><a href=?<% out.print(c); %> class="buttom">上一篇</a></div>
						<%
						}
					%>				 	
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