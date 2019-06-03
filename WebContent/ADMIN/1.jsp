<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>实验专用</title>
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
<script type="text/javascript" src="../resource/js/easyui-lang-zh_CN.js"></script>
</head>
<body>
<h2>Basic FileBox</h2>
	<p>The filebox component represents a file field of the forms.</p>
	<div style="margin:20px 0;"></div>
	<div class="easyui-panel" title="Upload File" style="width:100%;max-width:400px;padding:30px 60px;">
		<div style="margin-bottom:20px">
			<input class="easyui-textbox" label="Name:" labelPosition="top" style="width:100%">
		</div>
		<div style="margin-bottom:20px">
			<input id="file" class="easyui-filebox" label="File1:" labelPosition="top" data-options="prompt:'Choose a file...'" style="width:100%">
		</div>
		<div>
			<a href="#" class="easyui-linkbutton" style="width:100%" onclick="submit();">Upload</a>
		</div>
	</div>
</body>
</html>
<script language="javascript" type="text/javascript">
function submit(){
	var filePath = $("#file").filebox("getValue");
	if(filePath != ""){
		var sheet_id=1; 
	    var row_start=2; 
	    var tempStr='';
	    try{
	        var oXL = new ActiveXObject("Excel.application"); //创建Excel.Application对象
	    }
	    catch(err){
	        alert(err);
	    }
	    var oWB = oXL.Workbooks.open(filePath);
	    oWB.worksheets(sheet_id).select();
	    var oSheet = oXL.Worksheets(sheet_id);
	    var colcount=oXL.Worksheets(sheet_id).UsedRange.Cells.Rows.Count ;
		var Data=new Object();
		Data.rows = Array();
		Data.rows.length = colcount;
	    for(var i=row_start;i<=colcount;i++){
	    	Data.rows[i-2] = Object();
	    	Data.rows[i-2].name=oSheet.Cells(i,1).value;
	    	
	    	//if (typeof(oSheet.Cells(i,8).value)=='date'){ //处理第8列部分单元格内容是日期格式时的读取问题
	        //    d= new Date(oSheet.Cells(i,8).value);
	        //    temp_time=d.getFullYear()+"-"+(d.getMonth() + 1)+"-"+d.getDate();
	        //}
	        //else
	        //    temp_time=$.trim(oSheet.Cells(i,7).value.toString());
	        //tempStr+=($.trim(oSheet.Cells(i,2).value)+" "+$.trim(oSheet.Cells(i,4).value)+" "+$.trim(oSheet.Cells(i,6).value.toString())+" "+temp_time+"\n");
	        //读取第2、4、6、8列内容
	    }
	    return tempStr; //返回
	    oXL.Quit();
	    CollectGarbage();
	}
	else{
		alert("请选择文件！")
	}
}	
	

</script>
