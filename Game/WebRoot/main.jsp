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
		trace(300);
	});
	function trace(obj) {
		for ( var i = 0; i < obj; i++) {
			var num = i + 1;
			var tableid = "table" + num;
			var buttonid = "button" + num;
			var pid = "p" + num;
			var insertHtml = "<div class='table' id='"+tableid+"'><p>0/3</p><input type='submit' value='坐下' onclick='setin()'"
					+ num + "</div>";
			$("#readytable > div:last-child").after(insertHtml);
		}
	};

	function setin() {
		alert(11111);
		$.ajax({
			//这里的需要Struts.xml的<action/>的name属性一致。
			url : "setin",
			//提交类型
			type : "POST",
			//提交数据给Action传入数据 
			data : {},
			//返回的数据类型
			dataType : "json",
			//成功是调用的方法
			success : callback
		});
		function callback(data) {
			alert(data);
		}
	}
	/* 	var int = self.setInterval(function() {
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
	 } */

	//websocket
	var webSocket = new WebSocket('ws://127.0.0.1:8080/Game/websocket');

	webSocket.onerror = function(event) {
		onError(event);
	};

	webSocket.onopen = function(event) {
		onOpen(event);
	};

	webSocket.onmessage = function(event) {
		onMessage(event);
	};

	function onMessage(event) {
		addmsg(event.data);
	}

	function onOpen(event) {
		//document.getElementById('messages').innerHTML = '连接建立中。。。。。。';
		addmsg("连接建立中。。。。。。");
	}
	function addmsg(message) {
		var insertHtml = "<p class='message'>" + message + "</p>";
		$("#messages > p:last-child").after(insertHtml);
		$("#messages").scrollTop($("#talk")[0].scrollHeight);
	}
	function onError(event) {
		alert(event.data);
	}
	function start() {
		var result1 = $("#inmsg").val();
		webSocket.send(result1);
		return false;
	}
</script>

</head>

<body>
	<div id="readytable">
		<div></div>
	</div>
	<div id="talk">
		<p class="message">聊天内容：</p>
		<div id="messages" class="messages" style="bottom: 40px;">
			<p></p>
		</div>
		<div style="bottom: 5px;left: 5px;">
			<input type="text" id="inmsg" style="width: 120px;"> <input
				type="submit" value="发送" onclick="start()" />
		</div>
	</div>
</body>

</html>
