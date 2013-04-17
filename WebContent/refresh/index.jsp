<%@ page
	import="info.jubeat.saucer.Parse"
	language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head><%
	Parse parse = new Parse("http://p.eagate.573.jp/game/jubeat/saucer/p/playdata/index.html");
	parse.login().parsing();
%></head>
</html>