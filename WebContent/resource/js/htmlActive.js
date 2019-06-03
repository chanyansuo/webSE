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
			   	Login.html
			   	*/