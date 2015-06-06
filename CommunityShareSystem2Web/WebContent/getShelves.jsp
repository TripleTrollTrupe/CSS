<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<title>getShelves</title>
</head>
<body>
	<%
		String mensagem = (String)request.getAttribute("mensagem");
		if(mensagem != null)
			out.print("<span class='label label-default'>"+mensagem+"</span>");
		
		List<String> shelves = (List<String>)request.getAttribute("resultado");
		out.print("<div class="+"'panel panel-default'>");
		out.print("<div class="+"'panel panel-body'>");
		out.print("<p>Shelves</p>");
		out.print("<div table="+"'table'>");
		out.print("<ul class="+"'list-group'>");
		if(!shelves.isEmpty()) {
			for(String shelf : shelves)
				out.print("<li class='list-group-item'>" + shelf + "</li>");
		out.print("</ul>");
		out.print("</div> </div>");
		}
	%>

</body>
</html>