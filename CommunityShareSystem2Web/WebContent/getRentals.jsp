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
		out.print("<span class='label label-default'>"+mensagem+"</span>");
	
	Map<String,List<EMedium>> shelvesRentals = (Map<String,List<EMedium>>)request.getAttribute("mapa");
	out.print("<p>Documents per Shelf</p>");
	
	if(shelvesRentals.isEmpty())
		out.print("<ul><li>There are no shelves to be listed.</li></ul>");
	else {

		for(Map.Entry<String,List<EMedium>> entry : shelvesRentals.entrySet()) {
			
			String key = entry.getKey();
			out.print("<ul><li>" + entry.getKey() + "</li></ul>");
			
			List<EMedium> value = entry.getValue();
			
			if(!value.isEmpty()) {
				out.print("<table class='table-bordered'>");
				out.print("<thead>");
				out.print("<tr>");
				out.print("<th align=\"center\"> Rental ID </th>");
				out.print("<th align=\"center\"> Lendable ID </th>");
				out.print("<th align=\"center\"> Titulo </th>");
				out.print("<th align=\"center\"> Tipo </th>");
				out.print("</tr>");
				out.print("</thead>");
			}
			out.print("<tbody>");
			for(EMedium rental : value) {
				out.print("<tr>");
				out.print("<td> " + rental.getID() + "</td>");
				out.print("<td> " + rental.getLendable().getID() + "</td>");
				out.print("<td> " + rental.getTitle() + "</td>");
				out.print("<td> " + rental.getType() + "</td>");
				out.print("</tr>");
			}
			out.print("</tbody>");
			out.print("</table>");	
		}
	}
	%>
</body>
</html>