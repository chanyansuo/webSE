<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html XMLNS:IE>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script language="javascript" type="text/javascript">
window.moveTo(0,0);
</script>
</head>
<body onLoad = "javascript:window.resizeTo(screen.availWidth,screen.availHeight);window.moveTo(0,0)">
<script language="javascript" type="text/javascript">
var docElm = document.documentElement;
if (docElm.requestfullscreen){
	docElm.requestfullscreen();
}
else if(docElm.mozRequestFullScreen){
	docElm.mozRequestFullScreen();
}
else if(docElm.webkitRequestFullScreen){
	docElm.webkitRequestFullScreen();
}
else if(docElm.msRequestFullScreen){
	docElm.msRequestFullScreen();
}
</script>
</body>
</html>