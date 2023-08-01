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

<title>TESI!</title>

</head>
<style>
table {
	border-collapse: collapse;
	width: 100%;
}

tr {
	border: 1px solid black;
	padding: 8px;
	text-align: left;
}

img {
	width: 100 px;
	height: 80px;
}

#imgBando {
	width: 180px !important;
}
</style>
<body>
	<jsp:include page="Navbar.jsp"></jsp:include>
	<div class="container-fluid"></div>
	<h2>I Miei Bandi</h2>
	<table>
		<c:forEach items="${bandiCompilati}" var="bandiCompilati"
			varStatus="status">
			<tr>
				<td id="imgBando"><c:if test="${bandiCompilati.img != null}">
						<img id="imgbandiCompilati" src=${bandiCompilati.img}>
					</c:if> <c:if test="${bandiCompilati.img == null}">
						<img id="imgbandiCompilati" src="image\notImage.png">
					</c:if>
				<td>
				<td>Codice Bando: ${bandiCompilati.codice}</td>
				<td>${bandiCompilati.titolo}</td>
				<c:if test="${!fn:contains(bandiScaduti, bandiCompilati.codice)}">
				<form id="formRimozioneIscrizione" method="post"
					action="RimuoviIscrizione">
					<input type="hidden" id="codicebando" name="codicebando"
						value="${bandiCompilati.codice}">
				<td>
					<button type="button" data-toggle="modal"
						data-target="#rimuoviModal"
						style="float: right; color: red !important">Rimuovi
						Iscrizione al Bando</button>
				</td>
				</form>
				</c:if>
				<c:if test="${fn:contains(bandiScaduti, bandiCompilati.codice)}">
				<td>Attendi la graduatoria</td>
				</c:if>
			</tr>
		</c:forEach>
	</table>

</body>
</html>