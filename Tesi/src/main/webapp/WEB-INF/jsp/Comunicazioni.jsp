<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

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
	margin: auto;
	border-color: transparent !important;
}

tr {
	padding: 8px;
	text-align: left;
}
</style>
<body>
	<jsp:include page="Navbar.jsp"></jsp:include>
	<div class="form2">
		<h2 style="text-align: center;">Comunicazioni</h2>
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
							<c:set var="paramLower"
								value="${fn:toLowerCase(parametriDoc[2])}" />
							<c:set var="cfDocentiLower" value="${fn:toLowerCase(cfDocenti)}" />

							<c:if
								test="${!fn:contains(cfDocentiLower, paramLower)}">
								<form id="myForm" action="aggiungiDocente" method="get">
									<input type="hidden" id="codFiscale" name="codFiscale"
										value="${parametriDoc[2]}"> <a
										href="/aggiungiDocente?codFiscale=?${parametriDoc[2]}}">
										<button type="submit" class=" btn btn-block mybtn x-tfm "
											style="background: #33CC66 !important; margin-botton: 10px;">Registra
											Docente</button>
									</a>
								</form>
							</c:if>
						</c:if></td>
				</tr>
			</c:forEach>

		</table>
	</div>
</body>
</html>