<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><s:if test="#attr.goods!=null && #attr.goods.goods_id!=0">编辑</s:if><s:else>新增</s:else>商品发布</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/store.css">
<script language="javascript" type="text/javascript" src=""></script>
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script charset="utf-8" src="admin/editor/kindeditor.js"></script>
<script type="text/javascript" src="js/goodsType.js"></script>
<script language="javascript" type="text/javascript"> 
	
</script>
<style type="text/css">
 body,td,div
 {
   font-size:12px;
 }
</style>
</head>
<body>
<jsp:include page="top.jsp"></jsp:include>
<div id="middle">
	 <jsp:include page="leftMenu.jsp"></jsp:include>
	 <!--  购物车 -->
	 <div id="product_info">
			<div class="title">个人中心  &gt;&gt;  <s:if test="#attr.goods!=null && #attr.goods.goods_id!=0">编辑</s:if><s:else>新增</s:else>商品发布</div>
			<div style="margin-top:5px">
				 <form id="infoSelf" name="infoSelf" action="page_saveGoods.action" method="post" style="width:100%;height:100%">    
				 <input type="hidden" name="paramsGoods.goods_id" id="paramsGoods.goods_id" value="<s:property value='#attr.goods.goods_id'/>"/>
				 <input type="hidden" name="paramsGoods.user_id" id="paramsGoods.user_id" value="<s:property value='#attr.userFront.user_id'/>"/>
				 <input type="hidden" name="paramsGoods.goods_flag" id="paramsGoods.goods_flag" value="1"/>
				 <input type="hidden" name="paramsGoods.goods_pics" id="paramsGoods.goods_pics" value="<s:property value='#attr.goods.goods_pics'/>"/>
				 <table class="ptable" style="margin-bottom:5px;">
			        <tr>
			          <td width="15%" align="right" style="padding-right:5px">商品名称：</td>
			          <td width="*">
			          	<s:textfield cssStyle="width:450px;" name="paramsGoods.goods_name" id="paramsGoods.goods_name" value="%{#attr.goods.goods_name}"/> 
			          </td>
			        </tr> 
			        <tr>
			          <td width="15%" align="right" style="padding-right:5px">商品类型：</td>
			          <td width="*">
			          	<select id="goods_type1" name="paramsGoods.goods_type1" class="selectstyle" style="width:120px;">
			          		<option value="">请选择</option>
					    </select> 
					    <span id="type2Select" style="display:none">
					    -
					    <select id="goods_type2" name="paramsGoods.goods_type2" class="selectstyle" style="width:120px;">
			          		<option value="">请选择</option>
					    </select>
					    </span>
			          </td>
			        </tr>
			        <tr>
					  <td align="right" style="padding-right:5px">商品图片：</td>
					  <td align="left" height="80">
					    <div id="pic" style="width:95%;height:80px;overflow:auto">
					    	<s:if test="#attr.goods!=null && #attr.goods.goods_id!=0">
					    	<s:iterator value="#attr.goodsPics" id="goodsPic">
					    	<div style='margin-left:5px;height:80px;width:60px;float:left;text-align:center'>
					    		<img src='images/goodss/<s:property value="#goodsPic.goods_pic"/>' width='60' height='60'/>
					    		<br/><a href='javascript:void(0)' id='delPic_<s:property value="#goodsPic.goods_pic.replace('.','P')"/>'>删除</a>
					    	</div>
					    	</s:iterator>
					    	</s:if>
					    </div>
	        			&nbsp;<span id="op" style="display:none"><img src="images/loading001.gif" /></span>
				      </td>
				    </tr>
				    <tr>
					  <td align="right" style="padding-right:5px">上传图片：</td>
				      <td align="left"> 
				          <iframe name="uploadPage" src="uploadImg.jsp" width="100%" height="50" marginheight="0" marginwidth="0" scrolling="no" frameborder="0"></iframe>            
				       </td>
				    </tr>
			        <tr>
			          <td width="15%" align="right" style="padding-right:5px">商品价格：</td>
			          <td width="*">
			          	<s:textfield cssStyle="width:450px;" name="paramsGoods.goods_price" id="paramsGoods.goods_price" value="%{#attr.goods.goods_price}"/> 
			          </td>
			        </tr>
			        <tr>
			          <td width="15%" align="right" style="padding-right:5px">可否小刀：</td>
			          <td width="*">
			          		<s:select list="#{'1':'不可小刀','2':'可小刀'}" name="paramsGoods.goods_sale" value="%{#attr.goods.goods_sale}"
			          				listKey="key" listValue="value" emptyOption="false" 
			          				cssClass="selectstyle" cssStyle="width:120px;">
			          		</s:select>
			          </td>
			        </tr>
			       <tr>
			          <td width="15%" align="right" style="padding-right:5px">卖家手机：</td>
			          <td width="*">
			          	<s:textfield cssStyle="width:450px;" name="paramsGoods.user_phone" id="paramsGoods.user_phone" value="%{#attr.goods.user_phone}"/> 
			          </td>
			        </tr>
			        <tr>
			          <td width="15%" align="right" style="padding-right:5px">卖家QQ：</td>
			          <td width="*">
			          	<s:textfield cssStyle="width:450px;" name="paramsGoods.user_qq" id="paramsGoods.user_qq" value="%{#attr.goods.user_qq}"/> 
			          </td>
			        </tr>
			         <tr>
			          <td width="15%" align="right" style="padding-right:5px">交易地点：</td>
			          <td width="*">
			          	<s:textfield cssStyle="width:450px;" name="paramsGoods.goods_address" id="paramsGoods.goods_address" value="%{#attr.goods.goods_address}"/> 
			          </td>
			        </tr>
			        <tr>
			          <td align="right" style="padding-right:5px">商品介绍：</td>
			          <td class="KEEdit">
			          	<textarea style="width:450px;height:150px" name="paramsGoods.goods_desc" id="noticeContent"><s:property value="#attr.goods.goods_desc" escape="false"/></textarea> 
			          </td>
			        </tr> 
			        <tr class="">
			          <td align="center" height="30" colspan="2">
			            <input type="button"  Class="btnstyle" value="返 回" onclick="window.history.back();"/>&nbsp;
			            <s:if test="#attr.goods!=null && #attr.goods.goods_id!=0">
			          	<input type="button" id="editBtn" Class="btnstyle" value="编 辑"/> 
			          	</s:if>
			          	<s:else>
			          	<input type="button" id="addBtn" Class="btnstyle" value="新 增" />
			          	</s:else>
			          </td>
			        </tr>
			     </table>
			     </form>
			</div>
		</div>
	 <!--  购物车 -->
</div>
<jsp:include page="bottom.jsp"></jsp:include>
<script language="javascript" type="text/javascript">
	$(document).ready(function(){
		var goods_type1V = '<s:property value="#attr.goods.goods_type1"/>';
		var goods_type2V = '<s:property value="#attr.goods.goods_type2"/>';
		var goods_type1 = $("#goods_type1");
		var goods_type2 = $("#goods_type2");
		var type2Select = $("#type2Select");
		
		goods_type1.empty();
		for(var i=0;i<type1.length;i++){
			if(goods_type1V == type1[i]){
				goods_type1.append("<option selected='selected' value='"+type1[i]+"'>"+type1[i]+"</option>"); 
			}else{
				goods_type1.append("<option value='"+type1[i]+"'>"+type1[i]+"</option>"); 
			}
		}
		
		function loadType2(item){
			goods_type2.empty();
			var type1Index = goods_type1.get(0).selectedIndex;
			if(goods_type1.val()!=''){
				if(type2[type1Index].length>0){
					type2Select.show();
					for(var j=0;j<type2[type1Index].length;j++){
						if(item && item == type2[type1Index][j]){
							goods_type2.append("<option selected='selected' value='"+type2[type1Index][j]+"'>"+type2[type1Index][j]+"</option>"); 
						}else{
							goods_type2.append("<option value='"+type2[type1Index][j]+"'>"+type2[type1Index][j]+"</option>"); 
						}
					}
				}else{
					type2Select.hide();
					goods_type2.empty();
				}
			}else{
				type2Select.hide();
				goods_type2.empty();
			}
		}
		loadType2(goods_type2V);
		goods_type1.change(function(){
			loadType2();
		});
		
		
		var goods_pics = $("#paramsGoods\\.goods_pics");
		var goods_pics_temp = "";
		$("a[id^='delPic']").live("click",function(){
			var pic = $(this).attr("id").split("_")[1].replace("P","."); 
			var goods_picsV = goods_pics.val().split(",");
			for(var i=0;i<goods_picsV.length;i++){
				if(pic != goods_picsV[i]){
					if(goods_pics_temp==""){
						goods_pics_temp=goods_picsV[i];
					}else{
						goods_pics_temp+=","+goods_picsV[i];
					}
					
				}
			}
			goods_pics.val(goods_pics_temp);
			$(this).parent().remove();
		});
		
		var num=/^\d+(\.\d+)?$/;
		var num2=/^\d+$/;
		$("#addBtn").bind('click',function(){
			KE.sync('noticeContent');
			if($("#paramsGoods\\.goods_name").val()==''){
				alert('商品名称不能为空');
				return;
			}
			if($("#goods_type1").val()==''){
				alert('商品大类不能为空');
				return;
			}
			var type1Index = goods_type1.get(0).selectedIndex;
			if(type2[type1Index].length>0 && $("#goods_type2").val()==''){
				alert('商品小类不能为空');
				return;
			}
			if($("#paramsGoods\\.goods_pics").val()==''){
				alert('商品图片不能为空');
				return;
			}
			if(!num.exec($("#paramsGoods\\.goods_price").val())){
				alert('商品价格必须为数字');
				return;
			}
			if($("#paramsGoods\\.user_phone").val()=='' && $("#paramsGoods\\.user_qq").val()==''){
				alert('卖家手机和QQ至少有一个不能为空');
				return;
			}
			if($("#paramsGoods\\.goods_address").val()==''){
				alert('交易地点不能为空');
				return;
			} 
			if($("#noticeContent").val()==''){
				alert('商品介绍不能为空');
				return;
			}
			$("#paramsGoods\\.goods_id").val(0);
			var aQuery = $("#infoSelf").serialize();  
			$.post('page_addGoods.action',aQuery,
				function(responseObj) {
						if(responseObj.success) {	
							 alert('新增成功！请等待审核！');
							 window.location.href="page_listMyGoodss.action";
						}else  if(responseObj.err.msg){
							 alert('新增失败：'+responseObj.err.msg);
						}else{
							 alert('新增失败：服务器异常！');
						}	
			},'json');
		 });
		
		 $("#editBtn").bind('click',function(){
			KE.sync('noticeContent');
			if($("#paramsGoods\\.goods_name").val()==''){
				alert('商品名称不能为空');
				return;
			}
			if($("#goods_type1").val()==''){
				alert('商品大类不能为空');
				return;
			}
			var type1Index = goods_type1.get(0).selectedIndex;
			if(type2[type1Index].length>0 && $("#goods_type2").val()==''){
				alert('商品小类不能为空');
				return;
			}
			if($("#paramsGoods\\.goods_pics").val()==''){
				alert('商品图片不能为空');
				return;
			}
			if(!num.exec($("#paramsGoods\\.goods_price").val())){
				alert('商品价格必须为数字');
				return;
			}
			if($("#paramsGoods\\.user_phone").val()=='' && $("#paramsGoods\\.user_qq").val()==''){
				alert('卖家手机和QQ至少有一个不能为空');
				return;
			}
			if($("#paramsGoods\\.goods_address").val()==''){
				alert('交易地点不能为空');
				return;
			} 
			if($("#noticeContent").val()==''){
				alert('商品介绍不能为空');
				return;
			}
			var aQuery = $("#infoSelf").serialize();  
			$.post('page_saveGoods.action',aQuery,
				function(responseObj) {
						if(responseObj.success) {	
							 alert('编辑成功！');
							 window.location.href="page_listMyGoodss.action";
						}else  if(responseObj.err.msg){
							 alert('编辑失败：'+responseObj.err.msg);
						}else{
							 alert('编辑失败：服务器异常！');
						}	
			},'json');
		 });
		
	});	 
	
	KE.show({ 
	    id : 'noticeContent',
	    items:['plainpaste', '|', 'selectall', 'bold','italic'],
	    resizeMode:1
	    
	            
	});
</script>
</body>
</html>