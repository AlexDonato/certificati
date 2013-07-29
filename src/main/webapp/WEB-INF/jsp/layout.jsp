<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html lang="it">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
			<title>
				<tiles:insertAttribute name="title" ignore="true" />
			</title>

			<link href="${pageContext.request.contextPath}/css/elledia-theme/jquery-ui-1.10.3.custom.css" rel="stylesheet">
			<link href="${pageContext.request.contextPath}/css/elledia.css" rel="stylesheet">
			
			<meta charset="utf-8">

			<script src="${pageContext.request.contextPath}/js/jquery-1.9.1.js"></script>
			<script src="${pageContext.request.contextPath}/js/jquery-ui-1.10.3.custom.js"></script>


<script>
	$(function() {
		$("#menu").menu();
	});
</script>

</head>
<body>
	<div id="wrap">
		<div id="header"><tiles:insertAttribute name="header" /></div>
		<div id="nav"><tiles:insertAttribute name="menu" /></div>
		<div id="sidebar"></div>
		<div id="main"><tiles:insertAttribute name="body" /></div>
		<div id="footer"><tiles:insertAttribute name="footer" /></div>
	</div>
</body>
</html>