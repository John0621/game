<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'index.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/main.css">
<script type="text/javascript" src="js/jquery/jquery-3.1.1.min.js"></script>

<script>
	$(document).ready(function() {
		setTimout(test(), 200);
		$("#btn1").click(function() {
			$("#test1").text("Hello world!");
		});
		$("#btn2").click(function() {
			$("#test2").html("<b>Hello world!</b>");
		});
		$("#btn3").click(function() {
			$("#test3").val("Dolly Duck");
		});
	});
	var int = self.setInterval(function() {
		changetest1();
	}, 1000);
	function changetest1() {
		var a = random(100, 500);
		var insertHtml = "<p>" + a + "</p>";
		$("#talk > p:last-child").after(insertHtml);
		$("#talk").scrollTop($("#talk")[0].scrollHeight);
	}
	//获取范围内的随机数  
	function random(min, max) {
		return Math.floor(min + Math.random() * (max - min));
	}
</script>
</head>

<body>
	<div id="readytable">
		<div class="roletable"></div>
		<div class="roletable"></div>
		<div class="roletable"></div>
	</div>
	<div id="talk">
		<p>聊天内容：</p>
		<p></p>
	</div>
</body>
</html>
