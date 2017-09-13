$(document).ready(function(){
	var postStr = {
		'params.user_name':'',
		'params.user_pass':''
	};
	var selfCenterBtn = $("#selfCenterBtn");
	var loginOutBtn = $("#loginOutBtn");
	var loginInBtnShow = $("#loginInBtnShow");
	var loginInBtn = $("#loginInBtn");
	var regBtnShow = $("#regBtnShow");
	var regBtn = $("#regBtn");
	var loginField = $("#loginField");
	var regField = $("#regField");
	var user_name = $("#user_name");
	var user_pass = $("#user_pass");
	
	
	loginInBtnShow.bind('click',function(){
		$("#loginField").toggle();
	});
	
	regBtnShow.bind('click',function(){
		$("#regField").toggle();
		$("#infoReg")[0].reset();
	});
	
	$("#closeloginField").bind("click",function(){
		$("#loginField").hide();
	});
	$("#closeregField").bind("click",function(){
		$("#regField").hide();
		$("#infoReg")[0].reset();
	});
	
	var name=/^[a-z][a-z0-9_]{3,15}$/;
    var pass=/^[a-zA-Z0-9]{3,15}$/;
    var num= /^\d+$/;
	regBtn.bind('click',function(){
		var user_name = $("#params\\.user_name");
		var user_pass = $("#params\\.user_pass");
		var user_rpass = $("#user_rpass");
		var real_name = $("#params\\.real_name");
		var nick_name = $("#params\\.nick_name");
		
		if(user_name.val()==''||user_pass.val()==''){
			alert("用户名或密码不能为空");
			return false;
		}
		if(!name.exec(user_name.val())){
			window.alert("用户名必须为字母开头 、字母数字4-16位！");
			return false;
		}
		if(!pass.exec(user_pass.val())){
			window.alert("密码必须为字母数字4-16位！");
			return false;
		}
		if(user_pass.val()!=user_rpass.val()){
			alert("两次输入密码不一致");
			return false;
		}
		if(real_name.val()==''){
			alert("姓名不能为空");
			return false;
		}
		if(nick_name.val()==''){
			alert("昵称不能为空");
			return false;
		}
		
		var aQuery = $("#infoReg").serialize();  
		$.post('LoginRegSystem.action',aQuery,
			function(responseObj) {
					if(responseObj.success) {	
						 alert('注册成功！');
						 window.location.href="page_index.action";
					}else  if(responseObj.err.msg){
						 alert('失败：'+responseObj.err.msg);
					}else{
						 alert('失败：服务器异常！');
					}	
		 },'json');
	});
	
	loginOutBtn.bind('click',function(){
		$.post('LoginOutSystem.action',null,
			function(responseObj) {
					if(responseObj.success) {	
						 alert('退出成功！');
						 window.location.href="page_index.action";
					}else  if(responseObj.err.msg){
						 alert('退出异常：'+responseObj.err.msg);
					}else{
						 alert('退出异常：服务器异常！');
					}	
		 },'json');
	});
	
	loginInBtn.bind('click',function(){
		if(user_name.val()==''||user_pass.val()==''){
			alert("用户名和密码不能为空！")
			return;
		}
		postStr['params.user_name'] = user_name.val();
		postStr['params.user_pass'] = user_pass.val();
		
		$.post('LoginInSystem.action',postStr,
			function(responseObj) {
					if(responseObj.success) {	
						 alert('登录成功！');
						 window.location.reload();
					}else  if(responseObj.err.msg){
						 alert('登录异常：'+responseObj.err.msg);
					}else{
						 alert('登录异常：服务器异常！');
					}	
		 },'json');
	});
	
	selfCenterBtn.bind('click',function(){
		 window.location.href="page_myInfo.action";
	});
	
});

function calender(){
	 var time=new Date();
	 var year=time.getFullYear();
	 var month=toPair(time.getMonth()+1);
	 var day=toPair(time.getDate());
	 var hour=toPair(time.getHours());
	 var minute=toPair(time.getMinutes());
	 var second=toPair(time.getSeconds());
	 return year+"-"+month+"-"+day+" "+hour+":"+minute+":"+second;
}
function toPair(str){
	if(Number(str)<10){
		return "0"+str;
	}else{
		return str;
	}
}

