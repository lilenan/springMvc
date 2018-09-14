<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="msgFrompPush">
	</div>
	<script type="text/javascript" src="assets/js/jquery-1.7.1.js"></script>
	<script type="text/javascript">
		if(!!window.EventSource){ //EventSource对象只有新式的浏览器才有，是SSE的客户端
			var source=new EventSource('push');
		s='';
		source.addEventListener('message',function(e){ //添加SSE客户端监听，在此获得服务器端推送的消息
			s+=e.data+"<br/>";
			$("#msgFrompPush").html(s);
		});
		
		source.addEventListener('open',function(e){
			console.log("连接打开");
		},false);
		
		source.addEventListener('error',function(e){
			if(e.readyState==EventSource.CLOSED){
				console.log("连接关闭");
			}else{
				console.log(e.readyState);
			}
		},false);
		}else{
			console.log("你的浏览器不支持SSE");
		}
	</script>
</body>
</html>