<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>我的商品求购</title>
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
			<div class="title">个人中心  &gt;&gt;  我的商品求购</div>
			<div style="margin-top:5px">
				 <form id="infoSelf" name="infoSelf" action="page_listMyGoodsBuys.action" method="post" style="width:100%;height:100%">
				 <input type="hidden" name="pageNo" id="pageNo2" value="${pageNo}"/>    
				 <table class="ptable" style="margin-bottom:5px;">
				 	<tr>
					     <td colspan="9" align="right">
					     	商品名称：
      						<input type="text" id="paramsGoods.goods_name" name="paramsGoods.goods_name" value="${paramsGoods.goods_name}" class="inputstyle" Style="width:100px;"/>&nbsp;
					     	<input type="button" value="搜索" onclick="serch();" class="btnstyle"/>&nbsp;
					     	<input type="button" value="新增" onclick="window.location.href='page_addGoodsBuyShow.action';" class="btnstyle"/>
					     </td>
					</tr>
					<tr class="head_text">
					     <td width="" align="center">商品名称</td>
					     <td width="" align="center">期望价格</td>
					     <td width="" align="center">交易地点</td>
					     <td width="" align="center">发布时间</td>
					     <td width="" align="center">操作</td>
					</tr>
					<s:if test="#attr.goodss!=null && #attr.goodss.size()>0">
					   <s:iterator value="#attr.goodss" id="goods" status="status">
					   <tr> 
					     <td width="" align="center" title='<s:property value="#goods.goods_name"/>'>
					     	<s:a href="page_queryMyGoodsBuy.action?paramsGoods.goods_id=%{#goods.goods_id}">
					     	<s:property value="#goods.goods_name.length()>12?#goods.goods_name.substring(0,11)+'..':#goods.goods_name"/>
					     	</s:a>
					     </td>
					     <td width="" align="center"><s:property value="#goods.goods_price"/></td>
					     <td width="" align="center"><s:property value="#goods.goods_address"/></td>
					     <td width="" align="center"><s:property value="#goods.goods_date.substring(0,19)"/></td>
					     <td width="" align="center">
					       <s:a href="page_editGoodsBuy.action?paramsGoods.goods_id=%{#goods.goods_id}">编辑</s:a>&nbsp;
					       <s:a id="delGoodss_%{#goods.goods_id}" href="javascript:void(0)">删除</s:a>&nbsp;
					     </td>
					   </tr> 
					   </s:iterator>
					</s:if>
				    <s:else>
				    <tr>
				      <td height="60" colspan="9" align="center"><b>&lt;不存在商品求购信息&gt;</b></td>
				    </tr>
				    </s:else>
				 </table>
				 </form>
			</div>
			<div class="pages">
				<jsp:include page="page.jsp"></jsp:include>
			</div>
		</div>
	 <!--  购物车 -->
</div>
<jsp:include page="bottom.jsp"></jsp:include>
<script language="javascript" type="text/javascript">
	function serch()
	{
	   document.infoSelf.action="page_listMyGoodsBuys.action";
	   document.infoSelf.submit();
	}
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
	  document.infoSelf.action="page_listMyGoodsBuys.action";
	  document.infoSelf.submit();
	}
	function ChangePage(pagenum)
	{
		document.getElementById("pageNo2").value=pagenum;
		document.infoSelf.action="page_listMyGoodsBuys.action";
		document.infoSelf.submit();
	}
	$(document).ready(function(){
		$("a[id^='delGoodss']").bind('click',function(){
		    if(confirm('确认删除吗!?'))
		    {
		    	var goods_id=$(this).attr('id').split('_')[1];
		    	var aQuery = {
						'paramsGoods.ids':goods_id
				};  
				$.post('page_delGoodss.action',aQuery,
					function(responseObj) {
							if(responseObj.success) {	
								 alert('删除成功！');
								 window.location.reload();
							}else  if(responseObj.err.msg){
								 alert('删除失败：'+responseObj.err.msg);
							}else{
								 alert('删除失败：服务器异常！');
							}	
				},'json');
		    }
		 });
		
	});
</script>
</body>
</html>