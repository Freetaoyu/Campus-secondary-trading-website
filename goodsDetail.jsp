<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>产品详情</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/main.css">
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript">
$(document).ready(function(){
	//搜索
 	$("#big_searchBtn").bind("click",function(){
 		$("#info").attr("action","page_index.action").removeAttr("target").submit();
 	});
	
	
    $(".psmall").hover(function(){
			$("#pbig").attr("src",$(this).attr("src"));
	});
    
   	var addReplyPics = ['avatar1.png','avatar2.png','avatar3.png','avatar4.png','avatar5.png'];
   	function getReplyPic(){
   		var picIndex = Math.floor(Math.random()*5);
   		return addReplyPics[picIndex];
   	}	
	$("#paramsSblog\\.sblog_pic").val(getReplyPic());
   	$("#addReplyPic").attr("src","images/"+$("#paramsSblog\\.sblog_pic").val());
   	
   	
   	var user_id="${userFront.user_id}";
   	$("#addReply").bind("click",function(){
   		if(user_id==''){
			alert("请先登录");
			return;
		}
		if($("#paramsSblog\\.sblog_content").val()==''){
			alert("评论内容不能为空");
			return;
		}
		 
		var aQuery = $("#infoReply").serialize();  
		$.post('page_addSblog.action',aQuery,
			function(responseObj) {
					if(responseObj.success) {	
						 $(".replyTitle").after(
								 '<div class="replyCon">'+
								 '<div class="replyCon1"><img src="images/'+$("#paramsSblog\\.sblog_pic").val()+'" /></div>'+
								 '<div class="replyCon2">'+
										'<span style="color:#00947e;">${userFront.nick_name}</span> '+ 
										'<span style="color:#666;">'+calender()+'</span>'+ 
										'<br/>'+$("#paramsSblog\\.sblog_content").val()+
								  '</div>'+
								  '</div>'
						 );
						 $("#paramsSblog\\.sblog_pic").val(getReplyPic());
						 $("#addReplyPic").attr("src","images/"+$("#paramsSblog\\.sblog_pic").val());
						 $("#paramsSblog\\.sblog_content").val("");
					}else  if(responseObj.err.msg){
						 alert('失败：'+responseObj.err.msg);
					}else{
						 alert('失败：服务器异常！');
					}	
		 },'json');
	});
   	
   	var goods_user_id="<s:property value='#attr.goods.user_id'/>";
   	$("#addBtn").bind("click",function(){
   		if(user_id==''){
			alert("请先登录");
			return;
		}
   		if(goods_user_id==user_id){
   			alert("您不能购买自己发布的商品");
			return;
   		}
   		$("#goodsField").show();
	});

   	var user_phone = $("#paramsOrders\\.user_phone");
   	var user_address = $("#paramsOrders\\.user_address");
   	$("#addOrders").bind("click",function(){
   		if(user_phone==''){
			alert("联系电话不能为空");
			return;
		}
   		if(user_address==''){
			alert("收货地址不能为空");
			return;
		}
		 
		var aQuery = $("#goods_info").serialize();  
		$.post('page_addOrders.action',aQuery,
			function(responseObj) {
				    $("#goodsField").hide();
					if(responseObj.success) {	
						 alert('订单提交成功！');
						 window.location.href="page_listMyOrderss2.action";
					}else  if(responseObj.err.msg){
						 alert('失败：'+responseObj.err.msg);
					}else{
						 alert('失败：服务器异常！');
					}	
		 },'json');
	});
   	
   	$("#cancelOrders").bind("click",function(){
   		$("#goodsField").hide();
   	});
   	
   	//商品收藏
   	var goods_id="<s:property value='#attr.goods.goods_id'/>";
   	$("#collect").bind('click',function(){
		 if(user_id==''){
			 alert('请先登录');
			 return;
		 }
		 var aQuery = {
				 'paramsCollect.user_id':user_id,
				 'paramsCollect.goods_id':goods_id
		 };//$("#info").serialize();  
		 $.post('page_addCollect.action',aQuery,
			function(responseObj) {
					if(responseObj.success) {	
						 alert('收藏成功！');
					}else  if(responseObj.err.msg){
						 alert('收藏失败：'+responseObj.err.msg);
					}else{
						 alert('收藏失败：服务器异常！');
					}	
		 },'json');
	 });
}); 
</script>
<style type="text/css">
 body,td,div
 {
   font-size:12px;
 }
 #addBtn,#collect{
	width:120px;
	height:40px;  
	font-size:16px;
	font-weight:bold;
	margin-top:-20px;
	background-color: #ffb300;
	BORDER:1px solid #ffa800;
}
#addBtn:hover,#collect:hover{
	 background-color: #00947e; 
	 BORDER:1px solid #4bbfa5;
}
</style>
</head>
<body>
<jsp:include page="top.jsp"></jsp:include>
 
 <div id="mainArea">
	<div class="mainArea2">
		<div id="picShow">			
			<div id="picShow1">			
					<img id="pbig" src="images/goodss/<s:property value='#attr.goods.goods_pic'/>" />
			</div>	 
			<div id="picShow2">		
					<s:iterator value="#attr.goodsPics" id="goodsPic">
					<img class="psmall" src="images/goodss/<s:property value='#goodsPic.goods_pic'/>" />
					</s:iterator>	
			</div>
		</div>	 

		<div id="textShow">			  
				<span style="color:#00947e;font-size:20px;font-weight:bold"><s:property value='#attr.goods.goods_name'/></span>
				<br/><span style="color:#EB5055;font-size:14px">￥<s:property value='#attr.goods.goods_price'/></span>&nbsp;&nbsp;&nbsp;&nbsp;<s:property value='#attr.goods.goods_saleDesc'/>
				<br/>------------------浏览了<s:property value='#attr.goods.goods_click'/>次-------------------
				<br/><input type="button"  value="交易地点" class="btnstyle"/>&nbsp;&nbsp;<s:property value='#attr.goods.goods_address'/>
				<br/>　　<input type="button"  value="卖家" class="btnstyle"/>&nbsp;&nbsp;<s:property value='#attr.goods.nick_name'/>
				<br/><input type="button"  value="联系电话" class="btnstyle"/>&nbsp;&nbsp;<s:property value='#attr.goods.user_phone'/>
				<br/>　　<input type="button"  value="QQ" class="btnstyle"/>&nbsp;&nbsp;<s:property value='#attr.goods.user_qq'/>
				<br/><input type="button"  value="发布时间" class="btnstyle"/>&nbsp;&nbsp;<s:property value='#attr.goods.goods_date.substring(0,19)'/>
				<br/><br/><span>
					<input type="button" id="addBtn" class="btnstyle" value="在线购买"/>&nbsp;&nbsp;
					<input type="button" id="collect" class="btnstyle" value="收 藏"/>
				</span>
		</div>

		<div id="goodsField" style="display:none;">
			 <form id="goods_info" name="goods_info" method="post">
			 <input type="hidden" name="paramsOrders.user_id" value="${userFront.user_id}"/>
			 <input type="hidden" name="paramsOrders.real_name" value="${userFront.real_name}"/>
			 <input type="hidden" name="paramsOrders.goods_id" value="<s:property value='#attr.goods.goods_id'/>"/>
			 <input type="hidden" name="paramsOrders.goods_name" value="<s:property value='#attr.goods.goods_name'/>"/>
			 <input type="hidden" name="paramsOrders.goods_price" value="<s:property value='#attr.goods.goods_price'/>"/>
			 <table class="regTable">
				<tr>
					<td colspan="2"><input type="button" value="订单信息>>" class="btnstyle"/></td>
				</tr>
				<tr>
					<td align="right" width="20%"><span style="color:red">*</span> 联系电话：</td>
					<td align="left" width="80%">
						<input type="text" name="paramsOrders.user_phone" id="user_phone" value="${userFront.user_phone}" style="width:200px;" class="inputstyle"/>
					</td>
				</tr>
				<tr>
					<td align="right" width="20%"><span style="color:red">*</span> 收货地址：</td>
					<td align="left" width="80%">
						<input type="text" name="paramsOrders.user_address" id="user_address" value="" style="width:200px;" class="inputstyle"/>
					</td>
				</tr>
				<tr>
					<td align="center" colspan="2">
						　<input type="button" id="addOrders" class="btnstyle" value="提交订单"/>&nbsp;
						  <input type="button" id="cancelOrders" class="btnstyle" value="取 消"/>
					</td>
				</tr>
		 	 </table>
		 	 </form>
				
		</div>	
	</div>
	
	<div class="mainArea2" style="background-color: white;margin-left:5px;width:985px">
		<div id="headShow">
			  <img src="images/avatar3.png" />
		</div>	
		<div id="contentShow">
			  <s:property value='#attr.goods.goods_desc' escape="false"/>
			  <br/>(联系我的时候，请说明是在校园二手街看见的噢！)
		</div>	 
	</div>

	<form id="infoReply" name="infoReply" action="page_queryGoods.action" method="post" style="width:100%;height:100%">
	<input type="hidden" id="pageNo2" name="pageNo" value="${pageNo}"/>
	<input type="hidden" id="paramsGoods.goods_id" name="paramsGoods.goods_id" value="${paramsGoods.goods_id}"/>
	<input type="hidden" id="paramsSblog.goods_id" name="paramsSblog.goods_id" value="${paramsGoods.goods_id}"/>
	<input type="hidden" id="paramsSblog.user_id" name="paramsSblog.user_id" value="${userFront.user_id}"/>
	<input type="hidden" id="paramsSblog.sblog_pic" name="paramsSblog.sblog_pic" value=""/>
	<div class="mainArea2">
		<div class="replyTitle">
			<input type="button"  value="用户评论" class="btnstyle" style="height:40px;width:120px;font-size:16px"/>
		</div> 
		
		<s:if test="#attr.sblogs!=null && #attr.sblogs.size()>0">
		<s:iterator value="#attr.sblogs" id="sblog">
		<div class="replyCon">
			  <div class="replyCon1"><img src="images/<s:property value='#sblog.sblog_pic'/>" /></div>	
			  <div class="replyCon2">
					<span style="color:#00947e;"><s:property value='#sblog.nick_name'/></span> 
					<span style="color:#666;"><s:property value='#sblog.sblog_date.substring(0,19)'/></span> 
					<br/><s:property value='#sblog.sblog_content'/>
			  </div>	
		</div>	
		</s:iterator>
		</s:if>
		<div class="replyCon">
			  <div class="replyCon1"><img id="addReplyPic" src="images/avatar4.png" /></div>	
			  <div class="replyCon3">
					 <input type="text" name="paramsSblog.sblog_content"  id="paramsSblog.sblog_content" class="inputstyle" style="width:550px;height:45px;"/>
					 <input type="button" id="addReply"  value="评 论" class="btnstyle" style="width:55px;height:50px;font-weight:bold"/>
			  </div>	
		</div>
		
		<!--  评论分页 -->
		<jsp:include page="page.jsp"></jsp:include>
		<!--   评论分页 -->

	</div>
	</form>

</div>
 
<jsp:include page="bottom.jsp"></jsp:include>
<script language="javascript" type="text/javascript">
	function GoPage()
	{
	  var pagenum=document.getElementById("goPage").value;
	  var patten=/^\d+$/;
	  if(!patten.exec(pagenum))
	  {
	    alert("页码必须为大于0的数字");
	    return false;
	  }
	  document.getElementById("pageNo2").value=pagenum;
	  document.infoReply.submit();
	}
	function ChangePage(pagenum)
	{
		 document.getElementById("pageNo2").value=pagenum;
		 document.infoReply.submit();
	}
</script>
</body>
</html>