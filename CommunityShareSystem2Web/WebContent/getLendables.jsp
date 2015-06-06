<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="helper" class="ui.helpers.EMediumHelper" scope="request"/>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<title>Resposta</title>
</head>
<body>

<ul><li>${helper.message}</li></ul>

<c:if test="${helper.hasEMediums}">
	<div class="panel panel-default">
	<div class="panel panel-heading"> Biblioteca </div>
	<div class="panel panel-body"> 
	<table>
		<tr>
			<td align="center"> Lendable ID </td>
			<td align="center"> Titulo </td>
			<td align="center"> Tipo </td>
		</tr>
	
	<c:forEach var="x" items="${helper.EMediums}">
		<tr>
		<td>${x.ID}</td>
		<td>${x.title}</td>
		<td>${x.type}</td>
		</tr>
	</c:forEach>
	</table>
	</div>
	</div>
</c:if>

</body>
</html>