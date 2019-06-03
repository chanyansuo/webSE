<%@page import="javax.websocket.Session"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>主页</title>
<!--bootstarp-css-->
<link href="../resource/css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
<link rel="stylesheet" href="../resource/css/jquery.cxdialog.css" type="text/css" media="all" />
<!--/bootstarp-css -->
<!--css-->
<link rel="stylesheet" href="../resource/css/style.css" type="text/css" media="all" />
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
			<div class="logo">
				<img src="../resource/images/logo.png" alt="" />
			</div>
			<div class="menu">
				<span class="menu-info"> </span>
					<ul class="cl-effect-21">
						<li><a href=# onclick="fun()">退出</a></li>
			 		<script language="javascript" type="text/javascript">
						function fun(){
							if(confirm("是否退出系统？")){
			    				alert("已退出!");
			    				$.ajax({
			    			        cache: true,
			    			        type: "GET",
			    			        url: "../rest/user/logout",
			    			        async: true,
			    			    });
			    				location.href=("../USER/Login.jsp");
			    			}
			   		 	}	
					</script>
					</ul>
			</div>
<!--script-nav -->	
			<script>
					$("span.menu-info").click(function(){
						$("ul.cl-effect-21").slideToggle("slow" , function(){
						});
					});
					
			</script>
<!-- /script-nav -->
			<div class="clearfix"> </div>
</body>
</html>
<script language="javascript" type="text/javascript">
function no(){
	alert("暂未开启！");
}
</script>