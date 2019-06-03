<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>后台管理界面-安全知识题库</title>
<!--bootstarp-css-->
<link href="../resource/css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
<link rel="stylesheet" href="../resource/css/jquery.cxdialog.css" type="text/css" media="all" />
<!--/bootstarp-css -->
<!--css-->
<link rel="stylesheet" href="../resource/css/style.css" type="text/css" media="all" />
<link rel="stylesheet" type="text/css" href="../resource/css/easyui.css">
<link rel="stylesheet" type="text/css" href="../resource/css/icon.css">
<link rel="stylesheet" type="text/css" href="../resource/css/demo.css">
<link rel="stylesheet" type="text/css" href="../resource/css/default_theme.css">
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
<script src="../resource/js/jquery.cxdialog.min.js"></script>
<script>
    $(function () {
      $("#slider2").responsiveSlides({
        auto: true,
        pager: true,
        speed: 300,
        namespace: "callbacks",
      });
    });
    
    var DATA;
    $(function(){
		$.ajax({
			cache: true,
			type: "GET",
			dataType: "json",
			url: "../rest/Manage/getQuestions/3",
			async: false,
			success: function(data) {
				DATA = data;
			}
		});
	});
  </script>
</head>
<body>

	<div style="margin:20px 0;"></div>
	
	<table id="dg" class="easyui-datagrid" title="选择题题库" style="width:100%;height:auto;"
			data-options="
			rownumbers:true,
			toolbar: '#tb',
			singleSelect:true,
			pagination:true,
			method:'get',
			onClickCell: onClickCell,
			onEndEdit: onEndEdit
			">
		<thead>
			<tr>
				<th data-options="field:'num_Id',width:80">题号</th>

				<th data-options="field:'content',width:500,editor:'textbox'">题目</th>

				<th data-options="field:'type',width:80,
					formatter:function(value,row){
							if(row.type == 3)
							{return '单选题';}
							else
							{return '多选题';}
						},
					editor:{
							type:'combobox',
							options:{
								valueField:'type',
								textField:'Typename',
								method:'get',
								panelHeight:'auto',
								url:'../data/CQ.json',
								required:true,
								editable:false
							}
							}">题型</th>
							
				<th data-options="field:'answer',width:80,editor:'textbox'">答案</th>
				
				<th data-options="field:'Option_one',width:500,editor:'textbox'">选项1</th>
				<th data-options="field:'Option_two',width:500,editor:'textbox'">选项2</th>
				<th data-options="field:'Option_three',width:500,editor:'textbox'">选项3</th>
				<th data-options="field:'Option_four',width:500,editor:'textbox'">选项4</th>
				<th data-options="field:'Option_five',width:500,editor:'textbox'">选项5</th>
				<th data-options="field:'Option_six',width:500,editor:'textbox'">选项6</th>
				
				<th data-options="field:'difficult',width:80,
					formatter:function(value,row){
							if (row.difficult == 0)
							{return '未定义';}
							else
							{return row.difficult;}
						},
					editor:{
							type:'combobox',
							options:{
								valueField:'difficult',
								textField:'difname',
								method:'get',
								panelHeight:'auto',
								url:'../data/difficult.json',
								required:true,
								editable:false
							}
							}">难度</th>
							
			</tr>
		</thead>
	</table>

	<div id="tb" style="height:auto">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="append()">新增</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeit()">删除</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="accept()">保存</a> 
		<input class="easyui-searchbox" data-options="searcher:doSearch" style="width:200px">
		<button id="bo" class="easyui-linkbutton" type="button" onclick="ExporterExcel();">下载为Excel</button>		
	</div>
	
	<script type="text/javascript">
		var editIndex = undefined;
		var inserted;
		var deleted;
		var updated;
		//分页
		(function($){
		function pagerFilter(data){
			if ($.isArray(data)){	// is array
				data = {
					total: data.length,
					rows: data
				}
			}
			var target = this;
			var dg = $(target);
			var state = dg.data('datagrid');
			var opts = dg.datagrid('options');
			if (!state.allRows){
				state.allRows = (data.rows);
			}
			if (!opts.remoteSort && opts.sortName){
				var names = opts.sortName.split(',');
				var orders = opts.sortOrder.split(',');
				state.allRows.sort(function(r1,r2){
					var r = 0;
					for(var i=0; i<names.length; i++){
						var sn = names[i];
						var so = orders[i];
						var col = $(target).datagrid('getColumnOption', sn);
						var sortFunc = col.sorter || function(a,b){
							return a==b ? 0 : (a>b?1:-1);
						};
						r = sortFunc(r1[sn], r2[sn]) * (so=='asc'?1:-1);
						if (r != 0){
							return r;
						}
					}
					return r;
				});
			}
			var start = (opts.pageNumber-1)*parseInt(opts.pageSize);
			var end = start + parseInt(opts.pageSize);
			data.rows = state.allRows.slice(start, end);
			return data;
		}

		var loadDataMethod = $.fn.datagrid.methods.loadData;
		var deleteRowMethod = $.fn.datagrid.methods.deleteRow;
		$.extend($.fn.datagrid.methods, {
			clientPaging: function(jq){
				return jq.each(function(){
					var dg = $(this);
		            var state = dg.data('datagrid');
		            var opts = state.options;
		            opts.loadFilter = pagerFilter;
		            var onBeforeLoad = opts.onBeforeLoad;
		            opts.onBeforeLoad = function(param){
		                state.allRows = null;
		                return onBeforeLoad.call(this, param);
		            }
		            var pager = dg.datagrid('getPager');
					pager.pagination({
						onSelectPage:function(pageNum, pageSize){
							opts.pageNumber = pageNum;
							opts.pageSize = pageSize;
							pager.pagination('refresh',{
								pageNumber:pageNum,
								pageSize:pageSize
							});
							dg.datagrid('loadData',state.allRows);
						}
					});
		            $(this).datagrid('loadData', state.data);
		            if (opts.url){
		            	$(this).datagrid('reload');
		            }
				});
			},
		    loadData: function(jq, data){
		        jq.each(function(){
		            $(this).data('datagrid').allRows = null;
		        });
		        return loadDataMethod.call($.fn.datagrid.methods, jq, data);
		    },
		    getAllRows: function(jq){
		    	return jq.data('datagrid').allRows;
		    }
		})
		})(jQuery);

		$(function(){
			var a = new Object();
			for(var n in DATA)
			{
				a[n] = DATA[n];
			}
			$('#dg').datagrid({data:a}).datagrid('clientPaging');
		});
		
	//END
		function endEditing(){
			if (editIndex == undefined){return true}
			if ($('#dg').datagrid('validateRow', editIndex)){
				$('#dg').datagrid('endEdit', editIndex);
				updated = $('#dg').datagrid('getChanges', "updated");
				editIndex = undefined;
				return true;
			} else {
				return false;
			}
		}
		function onClickCell(index, field){
			if (editIndex != index){
				if (endEditing()){
					$('#dg').datagrid('selectRow', index)
							.datagrid('beginEdit', index);
					var ed = $('#dg').datagrid('getEditor', {index:index,field:field});
					if (ed){
						($(ed.target).data('textbox') ? $(ed.target).textbox('textbox') : $(ed.target)).focus();
					}
					editIndex = index;
				} else {
					setTimeout(function(){
						$('#dg').datagrid('selectRow', editIndex);
					},0);
				}
			}
		}
		
		function onEndEdit(index, row){
			var ed = $(this).datagrid('getEditor', {
				index:index,
				field:'type'
			});
			var a = $(ed.target).combobox('getText');
			if(a == "单选题")
			{a = 3}	
			else
			{a = 4}	
			row.type = a;
			var ed = $(this).datagrid('getEditor', {
				index:index,
				field:'difficult'
			});
			var a = $(ed.target).combobox('getText');
			if(a == "未定义")
			{a = 0}		
			row.difficult = a;
		}
		function doSearch(value){
			$.ajax({
		        cache: true,
		        type: "POST",
		        dataType: "json",
		        url: "../rest/Manage/getSearch/3",
		        data:{"Key":value},
		        async: false,
		        error: function(errCode, errMsg) {
		            alert("Connection error: "+errCode);
		        },
		        success: function(data) {
		        	if(data==false || data=='false' ){
		            	alert("不存在该数据！");
		            } else {
		            	DATA = data;
	        			var a = new Object();
	        			for(var n in DATA)
	        			{
	        				a[n] = DATA[n];
	        			}
	        			$('#dg').datagrid({data:a}).datagrid('clientPaging');
		            }
		        }
		    });
			//查找数据传到后台
		}
		function append(){
			if (endEditing()){
				$('#dg').datagrid('appendRow',{status:'P'});
				editIndex = $('#dg').datagrid('getRows').length-1;
				$('#dg').datagrid('selectRow', editIndex)
						.datagrid('beginEdit', editIndex);
				inserted = $('#dg').datagrid('getChanges',"inserted");
			}
		}
		function removeit(){
			if (editIndex == undefined){return}
			$('#dg').datagrid('cancelEdit', editIndex)
					.datagrid('deleteRow', editIndex);
			deleted = $('#dg').datagrid('getChanges', "deleted");
		}
		function accept(){
			if (endEditing()){
				var $dg = $('#dg');
				var rows = $('#dg').datagrid('acceptChanges');
				 if (rows.length) {
					 var effectRow = new Object();
						
					 effectRow["deleted"] = JSON.stringify(deleted);
					 
					 effectRow["inserted"] = JSON.stringify(inserted);
					
					 effectRow["updated"] = JSON.stringify(updated);
					
				 }
			}
			$.post("../rest/Manage/getChange/3", effectRow, function (rsp) {
				if (rsp) {
					$('#dg').datagrid('acceptChanges');
					
					$.cxDialog.defaults.baseClass = 'ios';
					$.cxDialog({
						title: '提示',
						info: '修改成功！',
						ok: function(){
						
						}
					});
					
				}
			},"JSON").error(function () {
				$.messager.alert("提示", "提交错误了！");
			});
		}
		
		//导出Excel
		function ExporterExcel() {
            //获取Datagride的列
            var rows = DATA.rows;
					//$('#dg').datagrid('getData').rows;
            var columns = $("#dg").datagrid("options").columns[0];
            var oXL = new ActiveXObject("Excel.Application"); //创建AX对象excel
            var oWB = oXL.Workbooks.Add(); //获取workbook对象
            var oSheet = oWB.ActiveSheet; //激活当前sheet
            //设置工作薄名称
            oSheet.name = "保密考试选择题库";
            //设置表头
            for (var i = 0; i < columns.length; i++) {
                oSheet.Cells(1, i+1).value = columns[i].title;
            }
            //设置内容部分
            for (var i = 0; i < rows.length; i++) {
                //动态获取每一行每一列的数据值
                for (var j = 0; j < columns.length; j++) {
                    oSheet.Cells(i + 2, j+1).value = rows[i][columns[j].field];
                }
            }
            oXL.Visible = true; //设置excel可见属性
        }		
	</script>
</body>
</html>