/**
前端动作函数 
 */

/**
Login.html
*/

function searchLogin(){
	var v = document.getElementsByName("loginChoice");
	if(v[0].checked){
		doLoginAdmin();
	}
	else{
		doLogin();
	}
}
		
var UserInfo; 
function GetPersonData(){
	$.ajax({
	 		cache: true,
		 	type: "GET",
		dataType: "json",
		url: "../rest/user/getUserInfo",   //后台连接代码
		async: false,
		error: function(request) {
	    	alert("获取个人信息失败");
		},
		success: function(data) {
			UserInfo = data.Data;
    	}
	});
}

function doLogin(){
	var name = document.getElementById("accountControl").value;
	var pwd = document.getElementById("passwordControl").value;
	$.ajax({
		cache: true,
		type: "POST",
		dataType: "json",
		url: "/webSE/rest/user/login",
		data:{"userid":name, "password":pwd},
		async: false,
		error: function(errCode, errMsg) {
			alert("Connection error: "+errCode);
		},
		success: function(data) {
			switch (data){
				case 0:
					show1();
					break;
			   	case 1:
			   	    document.getElementById("Login").style.display="none";
				   	window.sessionStorage.setItem('login_user', name);
				   	GetPersonData();
				   	window.sessionStorage.setItem('login_name', UserInfo.Name);
				   	setTimeout(function(){
				   	     window.location.href = '/webSE/USER/index_Logined.html';
				   	},1);            	
				   	break;
			   	case 2:
			   	    window.sessionStorage.setItem('login_user', name);
				   	setTimeout(function(){
				   		window.location = '/webSE/ADMIN/admin_index.html';
				   	},1);
				   	break;
				}
		}
	});
}
			   	
function show1(){
	$.cxDialog.defaults.baseClass = 'ios';
    $.cxDialog({
    	title: '提示',
       	info: '用户名、密码不正确！',
    	ok: function(){
       							
       	}
    });
}
			   	
function show2(){
	alert('用户名、密码不正确！');
}
			   	
/**
index_Logined.html
*/

function getUserId(){
	var value = sessionStorage.getItem("login_user");
	return value;
}

function showUserId( ElementId ){
	var value = getUserId();
	document.getElementById( ElementId ).innerText = value;
}



function getUserName(){
	var value = sessionStorage.getItem("login_name");
	return value;
}

function showUserName( ElementId ){
	var value = getUserName();
	document.getElementById( ElementId ).innerText = value;
}

function fun(){
	$.cxDialog.defaults.baseClass = 'ios';
	$.cxDialog({
		title: '信息：',
		info: '请问是否退出考试系统？',
		ok: function(){
			$.ajax({
	   	 		cache: true,
	   		 	type: "GET",
	    		dataType: "json",
	    		url: "../rest/user/logout",   //后台连接代码
	    		async: false,
	    		success: function(data) {
	    			
		    	}
			});
			location.href=("Login.html");
		}
		,
		okText: '确认退出',
		no: function(){
		}
		,
		noText: '暂不退出'
	});
}	



/**
BaomiStudy.html
*/

var DATA;
var choiced;

function no(){
	$.cxDialog.defaults.baseClass = 'ios';
	$.cxDialog({
		title: '提示',
		info: '暂未开启！',
		ok: function(){
		}
	});
}

function test(){
// 	$.cxDialog.defaults.baseClass = 'ios';
// 	$.cxDialog({
// 		title: '信息：',
// 		info: '考试中途不得离开网页!是否开始考试？',
// 		ok: function(){
// 			window.open("Test.jsp",'newwindow','fullscreen=1','menubar=0');
// 		}
// 		,
// 		okText: '开始',
// 		no: function(){
// 		}
// 		,
// 		noText: '取消'
// 	});
// 	window.open("Test.jsp",'blank','fullscreen=1','menubar=0');
	if(DATA.result == 1){
		document.getElementById("ExamClass").style.display="none";
		var choice = document.getElementById("loginChoice");
		choice.checked = false;
		$("#submit").attr("disabled","disabled");
		window.open("Test.jsp","blank");
	}
	else{
// 		alert(DATA.result);
		$.cxDialog.defaults.baseClass = 'ios';
		$.cxDialog({
			title: '提示',
			info: DATA.result,
			ok: function(){
				document.getElementById("ExamClass").style.display="none";
				var choice = document.getElementById("loginChoice");
				choice.checked = false;
				$("#submit").attr("disabled","disabled");
			}
		});
	}
	
}

$("#submit").attr("disabled","disabled");

	var oBox = document.getElementById('loginChoice');
   	oBox.onclick = function(){
   		if(this.checked){
   			$("#submit").removeAttr("disabled");
   		}
   		else{
   			$("#submit").attr("disabled","disabled");
   		}
   	}
	
	function fun1(value){
		$.ajax({
			cache: true,
			type: "GET",
			dataType: "json",
			contentType: "application/x-www-form-urlencoded; charset=utf-8", 
			url: "../rest/Exam/checkQA",
			async: false,
			error: function(errCode, errMsg) {
				alert("Connection error: "+errCode);
			},
			success:function(data){
				if(data.isExam == false || data.isExam == "false"){
					$.cxDialog.defaults.baseClass = 'ios';
       				$.cxDialog({
       					title: '提示',
       					info: data.result,
       					ok: function(){
       						document.getElementById("ExamClass").style.display="none";
       						var choice = document.getElementById("loginChoice");
       						choice.checked = false;
       						$("#submit").attr("disabled","disabled");	
       					}
       				});
// 					alert(data.result);
				}
				else{
					document.getElementById("ExamClass").style.display="block";
					DATA = data;
				}
			}
		});
    }		
	
	function ExChoice(){
		document.getElementById("ExerciseClass").style.display="block";
		var radio = document.getElementsByName("ExChoice");
		var checked = false;  //是否被选中判定
		var j;
		for(j=0;j<radio.length;j++)
		{
			checked = checked||radio[j].checked;
		}
		if (!checked)
		{
			$("#submit2").attr("disabled","disabled");	
		}
		else{
			$("#submit2").removeAttr("disabled");
		}
    }	
		
	function check(obj){
		var radio = document.getElementsByName("ExChoice");
		var checked = false;  //是否被选中判定
		var j;
		for(j=0;j<radio.length;j++)
		{
			checked = checked||radio[j].checked;
		}
		if (!checked)
		{
			$("#submit2").attr("disabled","disabled");	
		}
		else{
			for(j=0;j<radio.length;j++)
			{
				if(radio[j].checked){
					choiced = radio[j].value;
				}
			}
			$("#submit2").removeAttr("disabled");
			return choiced;
		}
	}




