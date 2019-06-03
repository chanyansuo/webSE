<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>后台管理界面-保密知识题库</title>
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
			url: "../rest/Manage/getAllRecord",
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
	
	<table id="dg" class="easyui-datagrid" title="试卷模板库" style="width:100%;height:auto;"
			data-options="
			rownumbers:true,
			toolbar: '#tb',
			singleSelect:true,
			pagination:true,
			method:'get',
			onClickCell: onClickCell
			">
		<thead>
			<tr>
				<th data-options="field:'Exam_id',width:100">试卷样式号</th>
				
				<th data-options="field:'Exam_date',width:220,
				editor:{
					type:'datetimebox',
					options:{
						showSeconds:false,
						required:true
							}
					}">考试时间</th>
					
				<th data-options="field:'Exam_time',width:120,
				editor:{
					type:'numberbox',
					options:{
						min:0,
						precision:0,
						required:true
							}
					}">考试时长(分钟)</th>
					
				<th data-options="field:'Exam_syllabus',width:120,
				formatter:function(value,row){
							return row.Exam_syllabus;
						},
					editor:{
							type:'combobox',
							options:{
								valueField:'Exam_syllabus',
								textField:'Exam_syllabus',
								method:'get',
								panelHeight:'auto',
								url:'../data/Exam_syllabus.json',
								required:true
							}
							}">考试范围</th>

				<th data-options="field:'Completion_num1',width:120,
					editor:{
					type:'numberbox',
					options:{
						min:0,
						precision:0
							}
					}">填空题（一空）</th>
					
				<th data-options="field:'Completion_num2',width:120,
					editor:{
					type:'numberbox',
					options:{
						min:0,
						precision:0
							}
					}">填空题（二空）</th>
				
				<th data-options="field:'Completion_num3',width:120,
					editor:{
					type:'numberbox',
					options:{
						min:0,
						precision:0
							}
					}">填空题（三空）</th>
					
				<th data-options="field:'Completion_num4',width:120,
					editor:{
					type:'numberbox',
					options:{
						min:0,
						precision:0
							}
					}">填空题（四空）</th>
				
				<th data-options="field:'Completion_num5',width:120,
					editor:{
					type:'numberbox',
					options:{
						min:0,
						precision:0
							}
					}">填空题（五空）</th>
				
				<th data-options="field:'Completion_Score',width:100,
					editor:{
					type:'numberbox',
					options:{
						min:0,
						precision:1
							}
					}">每空分值</th>
				
				<th data-options="field:'Judgment_num',width:100,
					editor:{
					type:'numberbox',
					options:{
						min:0,
						precision:0
							}
					}">判断题</th>
				
				<th data-options="field:'Judgment_Score',width:100,
					editor:{
					type:'numberbox',
					options:{
						min:0,
						precision:1
							}
					}">每题分值</th>
					
				<th data-options="field:'Choice_num',width:100,
					editor:{
					type:'numberbox',
					options:{
						min:0,
						precision:0
							}
					}">单选题</th>
				
				<th data-options="field:'Choice_Score',width:100,
					editor:{
					type:'numberbox',
					options:{
						min:0,
						precision:1
							}
					}">每题分值</th>
					
				<th data-options="field:'MulChoice_num',width:100,
					editor:{
					type:'numberbox',
					options:{
						min:0,
						precision:0
							}
					}">多选题</th>
				
				<th data-options="field:'MulChoice_Score',width:100,
					editor:{
					type:'numberbox',
					options:{
						min:0,
						precision:1
							}
					}">每题分值</th>

				<th data-options="field:'QA_num',width:100,
					editor:{
					type:'numberbox',
					options:{
						min:0,
						precision:0
							}
					}">简答题</th>
				
				<th data-options="field:'QA_Score',width:100,
					editor:{
					type:'numberbox',
					options:{
						min:0,
						precision:1
							}
					}">每题分值</th>
					
				<th data-options="field:'Attendance_expect',width:100,
					editor:{
					type:'numberbox',
					options:{
						min:0,
						precision:0
						}
					}">总分</th>				
							
			</tr>
		</thead>
	</table>

	<div id="tb" style="height:auto">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="append()">新增</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeit()">删除</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="accept()">保存</a> 
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
// 			自改
			editIndex = undefined;
// 			end
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
				if(score(editIndex)){
				
				$('#dg').datagrid('endEdit', editIndex);
				updated = $('#dg').datagrid('getChanges', "updated");
				editIndex = undefined;
				return true;
				}
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
						($(ed.target).data('numbertbox') ? $(ed.target).numberbox('numberbox') : $(ed.target)).focus();
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
			field:'Exam_syllabus'
			});
			row.Exam = $(ed.target).combobox('getText');
		}
		
		//自动计算
		function score(index){
			
			var C1Nub = $('#dg').datagrid('getEditor',{index:index,field:'Choice_num'});
			var C1score = $('#dg').datagrid('getEditor',{index:index,field:'Choice_Score'});
			var C2Nub = $('#dg').datagrid('getEditor',{index:index,field:'MulChoice_num'});
			var C2score = $('#dg').datagrid('getEditor',{index:index,field:'MulChoice_Score'});
			var TFNub = $('#dg').datagrid('getEditor',{index:index,field:'Judgment_num'});
			var TFscore = $('#dg').datagrid('getEditor',{index:index,field:'Judgment_Score'});
			var GF1Nub = $('#dg').datagrid('getEditor',{index:index,field:'Completion_num1'});
			var GF2Nub = $('#dg').datagrid('getEditor',{index:index,field:'Completion_num2'});
			var GF3Nub = $('#dg').datagrid('getEditor',{index:index,field:'Completion_num3'});
			var GF4Nub = $('#dg').datagrid('getEditor',{index:index,field:'Completion_num4'});
			var GF5Nub = $('#dg').datagrid('getEditor',{index:index,field:'Completion_num5'});
			var GFscore = $('#dg').datagrid('getEditor',{index:index,field:'Completion_Score'});
			var AQNub = $('#dg').datagrid('getEditor',{index:index,field:'QA_num'});
			var AQscore = $('#dg').datagrid('getEditor',{index:index,field:'QA_Score'});
			
			var scoreEditor = $('#dg').datagrid('getEditor',{index:index,field:'Attendance_expect'});
			
			var score = C1Nub.target.val()*C1score.target.val();
			score = score + C2Nub.target.val()*C2score.target.val(); 
			score = score + TFNub.target.val()*TFscore.target.val();
			score = score + GF1Nub.target.val()*GFscore.target.val() + GF2Nub.target.val()*GFscore.target.val()*2 + GF3Nub.target.val()*GFscore.target.val()*3 + GF4Nub.target.val()*GFscore.target.val()*4 + GF5Nub.target.val()*GFscore.target.val()*5;
			score = score + AQNub.target.val()*AQscore.target.val();
			if (score !=100)
			{
				$.messager.alert("提示", "请确认总分！目前总分不为100！");
				return false;
			}else{
				$(scoreEditor.target).numberbox('setValue',score)
				
				return true;
			}
			
			
		}
		
		//自动计算end
		
		
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
			$.post("../rest/Manage/getRecordChange", effectRow, function (rsp) {
				if (rsp) {
					$dg.datagrid('acceptChanges');
					
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
	</script>
</body>
</html>