<%@ page import="model.EMediumAttribute"%>
<%@ page import="model.EMedium"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
	<% 
	String mensagem = (String)request.getAttribute("mensagem");
	if(mensagem != null)
		out.print("<ul><li>" + mensagem + "</li></ul>");
	
	Map<String,List<EMedium>> shelvesRentals = (Map<String,List<EMedium>>)request.getAttribute("mapa");
	out.print("<p>Documents per Shelf</p>");
	
	if(shelvesRentals.isEmpty())
		out.print("<ul><li>There are no shelves to be listed.</li></ul>");
	else {
		out.print("<div class='panel panel-default'>");
		out.print("<div class='panel-heading'>Documents per Shelf</div>");
		for(Map.Entry<String,List<EMedium>> entry : shelvesRentals.entrySet()) {
			
			String key = entry.getKey();
			out.print("<ul><li>" + entry.getKey() + "</li></ul>");
			
			List<EMedium> value = entry.getValue();
			
			if(!value.isEmpty()) {
			
				out.print("<table>");
				out.print("<tr>");
				out.print("<td align=\"center\"> Rental ID </td>");
				out.print("<td align=\"center\"> Lendable ID </td>");
				out.print("<td align=\"center\"> Titulo </td>");
				out.print("<td align=\"center\"> Tipo </td>");
				out.print("</tr>");
			}
			
			for(EMedium rental : value) {
				out.print("<tr>");
				out.print("<td> " + rental.getID() + "</td>");
				out.print("<td> " + rental.getLendable().getID() + "</td>");
				out.print("<td> " + rental.getTitle() + "</td>");
				out.print("<td> " + rental.getType() + "</td>");
				out.print("</tr>");
			}
			out.print("</table>");
			out.print("</div>");
		}
	}
	%>
</body>
</html>