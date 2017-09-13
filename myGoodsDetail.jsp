<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>发布商品详情</title>
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
			<div class="title">个人中心  &gt;&gt;  发布商品详情</div>
			<div style="margin-top:5px">
				 <table class="ptable" style="margin-bottom:5px;">
			        <tr>
			          <td width="40%" align="center">
			          	<img src="images/goodss/<s:property value='#attr.goods.goods_pic'/>" width="100" /> 
			          </td>
			          <td width="60%" style="line-height:22px;">
			          	商品名称：<s:property value="#attr.goods.goods_name"/>
			          	<br/>商品类型：<s:property value="#attr.goods.goods_type1"/>-<s:property value="#attr.goods.goods_type2"/>
			          	<br/>商品价格：￥<s:property value="#attr.goods.goods_price"/> 元
			          	<br/>可否小刀：<s:property value="#attr.goods.goods_saleDesc"/>
			          	<br/>浏览次数：<s:property value="#attr.goods.goods_click"/> 
			          	<br/>卖家手机：<s:property value="#attr.goods.user_phone"/>
			          	<br/>卖家QQ：<s:property value="#attr.goods.user_qq"/>
			          	<br/>交易地点：<s:property value="#attr.goods.goods_address"/>
			          	<br/>添加时间：<s:property value="#attr.goods.goods_date.substring(0,19)"/>
			          	<br/>商品描述：<s:property value="#attr.goods.goods_desc" escape="false"/>
			          </td>
			        </tr> 
			        <tr class="">
			          <td align="center" height="30" colspan="2">
			            <input type="button" Class="btnstyle" value="返 回" onclick="window.history.back();"/>
			          </td>
			        </tr>
			     </table>
			</div>
		</div>
	 <!--  购物车 -->
</div>
<jsp:include page="bottom.jsp"></jsp:include>
</body>
</html>