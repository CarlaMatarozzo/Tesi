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

<link rel="stylesheet" href="css/bandiDaCorreggere.css" type="text/css">

<title>TESI!</title>
</head>
<body style="background: #ffffb3;">
	<jsp:include page="Navbar.jsp"></jsp:include>
	<h1 style="text-align: center;">Partecipazioni Bando
		${codiceBandoDaCorreggere}</h1>
	<div class="table-container">
		<table>
			<thead>
				<tr>
					<th>Codice fiscale</th>
					<c:set var="documentiBandoSize"
						value="${fn:length(documentiBando)}"></c:set>
					<c:forEach items="${documentiBando}" var="documentiBando"
						varStatus="status">
						<th>${documentiBando.titolodocumento}</th>
					</c:forEach>
					<td>Punteggio</td>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${dati}" var="dati" varStatus="status">
					<form method="post" action="#" id="correggiBando-${status.index}">
						<input type="hidden" id="codicebando-${status.index}" name="codicebando-${status.index}"
							value="${codiceBandoDaCorreggere}">

						<c:if test="${!fn:startsWith(dati,'data:')}">
							<th id="cf-${status.index}" name="cf-${status.index}">${dati}</th>

						</c:if>
						<c:if test="${fn:startsWith(dati,'data:')}">

							<th><a id="downloadLink" href="#" download="Documento.pdf">
									Scarica documento</a></th>
							<script>
								var base = '${dati}';
								var binaryData2 = atob(base64.split(',')[1]);
								var arrayBuffer2 = new ArrayBuffer(
										binaryData2.length);
								var uint8Array2 = new Uint8Array(arrayBuffer2);
								for (var i = 0; i < binaryData2.length; i++) {
									uint8Array2[i] = binaryData2.charCodeAt(i);
								}
								var blob2 = new Blob([ uint8Array2 ], {
									type : 'application/pdf'
								});

								var downloadLinkIng = document
										.getElementById('downloadLink');
								downloadLinkIng.href = URL
										.createObjectURL(blob);
							</script>

						</c:if>
						<c:if test="${dati==' '}">
							<th><input class="input" type="number"
								class="form-control" id="punteggio-${status.index}" name="punteggio-${status.index}"
								style="color: black; width: 40px;" required min="1" max="100"></th>
							<th>
								<button type="submit" id="btnCorrezione-${status.index}"
									class="btn btn-xs btn-primary btn-nuova-bozza">Invia
									punteggio</button>
							</th>
							<tr></tr>
						</c:if>
						</form>
				</c:forEach>

			</tbody>
		</table>
	</div>

</body>


<script src="./js/correggiBando.js"></script>
</html>