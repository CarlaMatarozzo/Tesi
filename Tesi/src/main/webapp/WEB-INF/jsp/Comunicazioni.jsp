<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>

<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta http-equiv="X-UA-Compatible" content="ie=edge">

<!-- bootstrap -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>

<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" href="css/bandiDaCorreggere.css" type="text/css">

<title>Bandi di Concorso</title>
<link rel="icon" href="../image/Logo.png" type="image/png">
</head>
<style>
table {
	border-collapse: collapse;
	width: 95%;
	margin:auto;
}

tr {
	border: 1px solid black;
	padding: 8px;
	text-align: left;
}


</style>
<body>
	<jsp:include page="Navbar.jsp"></jsp:include>
	<div class="container-fluid"></div>
	<h2>Comunicazioni</h2>
	<table>
		<c:forEach items="${arraynotifiche}" var="arraynotifiche"
			varStatus="status">
			<tr>
				<td>${arraynotifiche.messaggio}</td>
				<td><c:if
						test="${arraynotifiche.messaggio.startsWith('Richiesta registrazione: ')}">
						<c:set var="inputString" value="${arraynotifiche.messaggio}" />
						<c:set var="trimmedString"
							value="${fn:trim(fn:substringAfter(inputString, 'Richiesta registrazione: '))}" />
						<c:set var="parametriDoc" value="${fn:split(trimmedString, ' ')}" />
						<form id="myForm" action="aggiungiDocente" method="get">
							<input type="hidden" id="codFiscale" name="codFiscale"
								value="${parametriDoc[2]}"> <a
								href="/aggiungiDocente?codFiscale=?${parametriDoc[2]}}">
								<button type="submit">Registra Docente</button>
							</a>
						</form>
					</c:if></td>
			</tr>
			<tr></tr>
		</c:forEach>

	</table>
</body>
</html>