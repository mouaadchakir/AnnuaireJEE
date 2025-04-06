<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ajout d'un contact</title>
</head>
<body>
	<form action="ContactServlet" method="post">
		<input type="hidden" name="action" value="ajouter">
		<table>
			<tr>
				<td>Id :</td>
				<td><input type="text" name="id" required></td>
			</tr>
			<tr>
				<td>Nom :</td>
				<td><input type="text" name="nom" required></td>
			</tr>
			<tr>
				<td>Prénom :</td>
				<td><input type="text" name="prenom" required></td>
			</tr>
			<tr>
				<td>Téléphone :</td>
				<td><input type="text" name="telephone" required></td>
			</tr>
		</table>
		<input type="submit" value="Ajouter">
	</form>
</body>
</html>
