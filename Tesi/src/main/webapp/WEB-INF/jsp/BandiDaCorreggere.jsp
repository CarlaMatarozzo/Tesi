<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

<script src="./js/assistenza.js"></script>
<link rel="stylesheet" href="css/bandiDaCorreggere.css" type="text/css">

<title>TESI!</title>
</head>
<body style="background: #ffffb3;">
	<jsp:include page="Navbar.jsp"></jsp:include>

	<h1 style="text-align: center;">Bandi Da Correggere</h1>
	<div class="table-container">
		<table>
			<thead>
				<tr>
					<th>Codice Bando</th>
					<th>Titolo</th>
					<th>Numero Richieste</th>
					<th>Stato Bando</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${bandiDaCorreggere}" var="bandiDaCorreggere"
					varStatus="status">
					<tr>
						<td>${bandiDaCorreggere.codice}</td>
						<td>${bandiDaCorreggere.titolo}</td>
						<td>${numRichieste[status.index]}</td>
						<c:choose>
							<c:when
								test="${!bandiScaduti.contains(bandiDaCorreggere.codice)}">

								<td>In corso</td>
								<td>Attendi per correggere</td>
							</c:when>

							<c:otherwise>
								<td>Scaduto</td>
								<td><a
									href="/correzioneBando?codiceBando=${bandiDaCorreggere.codice}">
										<button class="btn btn-xs btn-primary btn-nuova-bozza"
											type="button">Correggi bando</button>
								</a></td>
							</c:otherwise>
						</c:choose>
					</tr>
					<!-- Aggiungi altre righe qui -->
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>