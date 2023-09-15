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
	width: 95%;
	margin:auto;
	position: center;
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
<body style="background: #ffffb3;">
	<jsp:include page="Navbar.jsp"></jsp:include>
	<div class="container-fluid"></div>
	<h1 style="text-align: center; text-shadow: 2px 2px 4px black;">I miei bandi</h1>
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
					<c:set var="vuoto" value=" "/>
					<c:choose>
						<c:when test="${pdfPartecipanti[status.index] eq vuoto}">
							<td>Attendi la graduatoria</td>
						</c:when>
						<c:otherwise>
							<td><a id="downloadGrad" href="#" download="Graduatoria.pdf">Scarica
									graduatoria</a> <script>
										// La tua stringa Base64 contenente i dati del PDF
										var base64PDFData = '${pdfPartecipante[status.index]}'; // Inserisci qui i dati Base64

										var arrayBuffer = new ArrayBuffer(
												base64PDFData.length);
										var uint8Array = new Uint8Array(
												arrayBuffer);
										for (var i = 0; i < base64PDFData.length; i++) {
											uint8Array[i] = base64PDFData
													.charCodeAt(i);
										}
										var blob = new Blob([ uint8Array ], {
											type : 'application/pdf'
										});

										// Crea il link di download dinamico
										var downloadLink = document
												.getElementById('downloadGrad');
										downloadLink.href = URL
												.createObjectURL(blob);
									</script>
						</c:otherwise>
					</c:choose>
				</c:if>
			</tr>
		</c:forEach>
	</table>

</body>
</html>