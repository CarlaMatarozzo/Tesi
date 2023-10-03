<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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

<script src="./js/assistenza.js"></script>
<link rel="stylesheet" href="css/bandiDaCorreggere.css" type="text/css">

<title>Bandi di Concorso</title>
<link rel="icon" href="../image/Logo.png" type="image/png">
</head>
<body>
	<jsp:include page="Navbar.jsp"></jsp:include>
	
	<div class="form2">
	<h1 style="text-align: center;">Bandi
		Da Correggere</h1>
	<div class="table-container">
		<table>
			<thead>
				<tr style="background: white;">
					<td id="td1">Codice Bando</td>
					<td id="td1">Titolo</td>
					<td id="td1">Numero Richieste</td>
					<td id="td1">Stato Bando</td>
					<td id="td1">
					</th>
				</tr>
			</thead>
			<tbody style="text-align: center;">
				<c:forEach items="${bandiDaCorreggere}" var="bandiDaCorreggere"
					varStatus="status">
					<tr>
						<td id="td1">${bandiDaCorreggere.codice}</td>
						<td id="td1">${bandiDaCorreggere.titolo}</td>
						<td id="td1">${numRichieste[status.index]}</td>
						<c:choose>
							<c:when
								test="${fn:contains(bandiCorretti,bandiDaCorreggere.codice) and bandiScaduti.contains(bandiDaCorreggere.codice)}">
								<td id="td1">Corretto</td>
								<c:set var="trovato" value="false" />
								<c:forEach var="element" items="${bandiCorretti}"
									varStatus="loop">
									<c:if test="${element eq bandiDaCorreggere.codice}">
										<c:set var="indice" value="${loop.index}" />
										<c:set var="trovato" value="true" />
									</c:if>
								</c:forEach>
								<c:set var="pdfGrad" value="${pdf[indice]}" />
								<td><a id="downloadGrad" href="#"
									download="Graduatoria.pdf">Scarica graduatoria</a> <script>
										// La tua stringa Base64 contenente i dati del PDF
										var base64PDFData = '${pdfgrad}'; // Inserisci qui i dati Base64

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
							</c:when>
							<c:when
								test="${!bandiScaduti.contains(bandiDaCorreggere.codice)}">

								<td id="td1">In corso</td>
								<td>Attendi per correggere</td>
							</c:when>

							<c:otherwise>
								<td id="td1">Scaduto</td>
								<td><a
									href="/correzioneBando?codiceBando=${bandiDaCorreggere.codice}">
									<button type="button"  class="btn btn-xs btn-danger btn-nuova-bozza"
								style="width: 60%; border-color: transparent; background-color: #33CC66">Correggi bando</button>
								</a></td>
							</c:otherwise>
						</c:choose>
					</tr>
					<!-- Aggiungi altre righe qui -->
				</c:forEach>
			</tbody>
		</table>
	</div>
	</div>
</body>
</html>