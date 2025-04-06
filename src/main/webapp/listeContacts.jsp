<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, model.Contact" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
    List<Contact> contacts = (List<Contact>)request.getAttribute("contact");
%>
<table>
    <tr>
        <th>ID</th>
        <th>Nom</th>
        <th>Prénom</th>
        <th>Téléphone</th>
        <th>Actions</th>
    </tr>
    <%
        for (Contact c : contacts) {
    %>
    <tr>
        <td><%= c.getId() %></td>
        <td><%= c.getNom() %></td>
        <td><%= c.getPrenom() %></td>
        <td><%= c.getTelephone() %></td>
        <td>
            <form action="ContactServlet" method="post">
                <input type="hidden" name="action" value="supprimer">
                <input type="hidden" name="id" value="<%= c.getId() %>">
                <input type="submit" value="Supprimer">
            </form>
        </td>
    </tr>
    <% } %>
</table>
</body>
</html>
