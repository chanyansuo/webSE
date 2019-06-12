/**
 用户名存储用
 */

function setUserName(value){
	sessionStoraage.setItem("Username",value);
}

function getUserName(){
	var value = sessionStoraage.getItem("Username");
	return value;
}

