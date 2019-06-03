<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>后台管理界面-安全知识题库</title>
<!--bootstarp-css-->
<link href="../resource/css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
<!--/bootstarp-css -->
<!--css-->
<link rel="stylesheet" href="../resource/css/style.css" type="text/css" media="all" />
<link rel="stylesheet" type="text/css" href="../resource/css/easyui.css">
<link rel="stylesheet" type="text/css" href="../resource/css/icon.css">
<link rel="stylesheet" type="text/css" href="../resource/css/demo.css">
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
<script type="text/javascript" src="../resource/js/jquery.easyui.min.js"></script>
<script>
    $(function () {
      $("#slider2").responsiveSlides({
        auto: true,
        pager: true,
        speed: 300,
        namespace: "callbacks",
      });
    });
  </script>
</head>
<body>
<p id="1">111</p>
<p id="2">111</p>
<p id="3">111</p>
<p id="4">111</p>
<p id="5">111</p>
<p id="c6">111</p>
<p id="c7">111</p>
<script type="text/javascript">
var DATA;
var DATA1;
$.ajax({
	cache: true,
	type: "GET",
	dataType: "json",
	url: "../data/PAPERdata.json",
	async: true,
	success: function(data) {
		
		DATA = JSON.stringify(data);
		document.getElementById("1").innerHTML = DATA;
	
		document.getElementById("2").innerHTML = data.total;
		
		document.getElementById("3").innerHTML = data.rows[0].Paperid;
		
		document.getElementById("4").innerHTML = data.rows.length;
		
		var j = 7;
		document.getElementById("c"+j).style.visibility = "hidden";
		document.getElementById("c"+j).style.visibility = "visible";
		
		var j = 6;
		document.getElementById("c"+j).style.display = "none";
		//document.getElementById("c"+j).style.display = "inline";
	}
});
</script>


</body>
</html>