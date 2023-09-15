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
	src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/2.4.0/jspdf.umd.min.js"></script>

<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

<link rel="stylesheet" href="css/bandiDaCorreggere.css" type="text/css">

<title>TESI!</title>
</head>
<body style="background: #ffffb3;">
	<jsp:include page="Navbar.jsp"></jsp:include>
	<div class="title-container">
		<h1 style="text-align: center; text-shadow: 2px 2px 4px black;">Partecipazioni Bando ${codiceBandoDaCorreggere}</h1>
		<c:if test="${tuttiCorretti ==true }">
			<form method="post" action="#" id="generaPDF">
				<div class="button-container">
					<input type="hidden" id="cb" value="${codiceBandoDaCorreggere}">

					<button type="submit" id="btnGraduatoria" style="margin-right:20px;"
						class="btn btn-xs btn-primary btn-nuova-bozza">Genera
						graduatoria</button>
				</div>

			</form>
		</c:if>
	</div>
	<div>
		<table>
			<tr>
				<td id="td1">Codice fiscale</td>
				<c:set var="documentiBandoSize" value="${fn:length(documentiBando)}"></c:set>
				<c:forEach items="${documentiBando}" var="documentiBando"
					varStatus="status">
					<td id="td1">${documentiBando.titolodocumento}</td>
				</c:forEach>
				<td id="td1">Punteggio</td>
				<td id="td1"></td>
			</tr>
			<c:forEach items="${dati}" var="dato" varStatus="status">

				<form method="post" action="#" id="correggiBando-${status.index}">
					<input type="hidden" id="sizeX" value="${sizeX}">
					<c:if test="${!fn:startsWith(dato, 'data:') and dato.matches('^[A-Za-z].*')}">
						<td id="td1">${dato}</td>
					</c:if>
				<input type="hidden" id="codfisc-${status.index}" value="${dato}">

				<input type="hidden" id="codicebando-${status.index}"
					value="${codiceBandoDaCorreggere}">

				<c:if test="${fn:startsWith(dato,'data:')}">
					<td id="td1"><a id="downloadLink-${status.index}" href="#"
						download="Documento.pdf">Scarica documento</a></td>
					<script>
						var base64 = '${dato}';
						var binaryData2 = atob(base64.split(',')[1]);
						var arrayBuffer2 = new ArrayBuffer(binaryData2.length);
						var uint8Array2 = new Uint8Array(arrayBuffer2);
						for (var i = 0; i < binaryData2.length; i++) {
							uint8Array2[i] = binaryData2.charCodeAt(i);
						}
						var blob2 = new Blob([ uint8Array2 ], {
							type : 'application/pdf'
						});

						var downloadLinkIng = document
								.getElementById('downloadLink-${status.index}');
						downloadLinkIng.href = URL.createObjectURL(blob2);
					</script>
				</c:if>

				<c:set var="corretto" value="0" />
				<c:if test="${dato==' '}">
					<c:forEach var="graduatoria" items="${partecipazioniCorrette}"
						varStatus="status2">
						<c:if test="${status2.index ==0}">
							<c:choose>
								<c:when
									test="${fn:contains(cfCorrette, dati[status.index-sizeX-1])}">
									<td id="td1"><input class="input" type="number"
										class="form-control" id="punteggio-${status.index}"
										name="punteggio" placeholder="${graduatoria.punteggio}"
										style="color: black; width: 40px;" required min="1" max="100"></td>
									<td id="td1">
										<button type="submit" id="btnCorrezione"
											class="btn btn-xs btn-primary btn-nuova-bozza">Modifica
											punteggio</button>
									</td>
								</c:when>
								<c:otherwise>

									<c:set var="corretto" value="${corretto+1}" />
									<td id="td1"><input class="input" type="number"
										class="form-control" id="punteggio-${status.index}"
										name="punteggio" placeholder="0"
										style="color: black; widtd: 40px;" required min="1" max="100"></td>
									<td id="td1">
										<button type="submit" id="btnCorrezione"
											class="btn btn-xs btn-primary btn-nuova-bozza">Invia
											punteggio</button>
									</td>
								</c:otherwise>

							</c:choose>
							<tr></tr>

						</c:if>

					</c:forEach>
				</c:if>



				</form>
			</c:forEach>

		</table>
	</div>
	<div class="modal fade" id="graduatoriaModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="myform modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<div class="myform modal-body">
					<p>La graduatoria è stata inviata via email a tutti i
						partecipanti!</p>
				</div>
			</div>
		</div>
	</div>

</body>


<script src="./js/correggiBando.js"></script>
<script src="./js/email.js"></script>
</html>