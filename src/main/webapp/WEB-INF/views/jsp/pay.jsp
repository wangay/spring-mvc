<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>pay</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	
	document.local.href = "https://blockchain.info/payment_request?address=${blockChainAddress}";
	-->

    <script src="resources/core/js/jquery.min.js"></script>
    <script src="resources/core/js/pay.js"></script>
  </head>
  
  <body>
  pay now:${blockChainAddress}<br>
  <input type="text" id="inputId" value="${blockChainAddress}">
  </body>
</html>
