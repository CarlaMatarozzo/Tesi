<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>

<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- bootstrap -->

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>

	

<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

<link rel="stylesheet" href="css/index.css" type="text/css">

<title>Bandi di Concorso</title>
<link rel="icon" href="../image/Logo.png" type="image/png">
</head>
<body>
	<jsp:include page="Navbar.jsp"></jsp:include>
	<h1 style="text-align: center;">Bandi in corso</h1>
	<div class="row catalogo_bandi_home">
		<c:forEach items="${bandi}" var="bandi" varStatus="status">
			<!-- Questa è la card -->
			<div class="col-lg-4">
				<div class="card-wrapper card-space"
					style="margin-bottom: 10px !important;">
					<div class="card card-bg card-big"
						style="box-shadow: 0px 0px 10px 2px #5f5f5f;">
						<c:if
							test="${codicefiscale!=null and codicefiscale!='ADMIN' and docente==false}">
							<c:if test="${bandipreferiti[status.index]==0}">
								<form id="formAggiunta" method="post" action="AggiungiPreferiti">
									<input type="hidden" id="codicebando" name="codicebando"
										style="color: black;" value="${bandi.codice}">
									<div style="text-align: right;">
										<button type="submit"
											aria-label="Clicca qui per eseguire l'azione"
											style="float: right; background: transparent; border: none;">
											Aggiungi ai preferiti
											<svg xmlns="http://www.w3.org/2000/svg" width="20"
												height="20" fill="currentColor" class="bi bi-bookmark"
												viewBox="0 0 16 16"> <path
													d="M2 2a2 2 0 0 1 2-2h8a2 2 0 0 1 2 2v13.5a.5.5 0 0 1-.777.416L8 13.101l-5.223 2.815A.5.5 0 0 1 2 
												15.5V2zm2-1a1 1 0 0 0-1 1v12.566l4.723-2.482a.5.5 0 0 1 .554 0L13 14.566V2a1 1 0 0 0-1-1H4z" /> </svg>
										</button>
									</div>

								</form>
							</c:if>
							<c:if test="${bandipreferiti[status.index]==1}">

								<form id="formRimozione" method="post" action="RimuoviPreferiti">
									<input type="hidden" id="codicebando" name="codicebando"
										value="${bandi.codice}">
									<div style="text-align: right;">
										<button type="submit"
											style="float: right; background: transparent; border: none;">
											Rimuovi dai preferiti
											<svg style="color: rgb(249, 235, 83);"
												xmlns="http://www.w3.org/2000/svg" width="20" height="20"
												fill="currentColor" class="bi bi-bookmark-fill"
												viewBox="0 0 16 16"> <path
													d="M2 2v13.5a.5.5 0 0 0 .74.439L8 13.069l5.26 2.87A.5.5 0 0 0 14 15.5V2a2 2 0 0 0-2-2H4a2 2 0 0 0-2 2z"
													fill="#f9eb53"></path> </svg>
										</button>
									</div>
								</form>
							</c:if>
						</c:if>
						<input type="hidden" id="hiddenInput" value="hiddenInput">
						<div class="card-body">
							<div class="etichetta mt-1">
								<span> <c:choose>
										<c:when test="${!bandiScaduti.contains(bandi.codice)}">
											<small class="badge badge-success">Aperto</small>
											<em><span style="color: black;">Il bando chiude
													il</span> <strong style="color: black;">${bandi.datascadenza}</strong></em>
										</c:when>
										<c:otherwise>
											<small class="badge badge-success">Chiuso</small>
											<em><span style="color: black;">Il bando &#233
													scaduto il</span> <strong style="color: black;">${bandi.datascadenza}</strong></em>
										</c:otherwise>
									</c:choose>
								</span>
							</div>

							<div class="wrapper-image">
								<c:if test="${bandi.img != null}">
									<img id="imgbando" src=${bandi.img}>
								</c:if>
								<c:if test="${bandi.img == null}">
									<img id="imgbando" src="image\notImage.png">
								</c:if>
							</div>



							<a class="text-decoration-none" target="_blank">
								<h4 class="card-title" style="color: black;">${bandi.titolo}</h4>
							</a>
							<a id="downloadLink" href="#" download="BandoIta.pdf"
								style="color: black;">Scarica il PDF</a>
							<c:set var="myVariable" value='${bandi.pdfIta}' />
							<p></p>
							<c:if test="${bandi.pdfInglese!=null}">
								<a id="downloadLinkIng" href="#" download="BandoIng.pdf"
									style="color: black;">Download PDF </a>
								<c:set var="myVariable2" value='${bandi.pdfInglese}' />
							</c:if>
							<script>
								// La tua stringa Base64 contenente i dati del PDF
								var base64PDFData = '${myVariable}'; // Inserisci qui i dati Base64
								// Decodifica la stringa Base64 in un Blob
								var binaryData = atob(base64PDFData.split(',')[1]);
								var arrayBuffer = new ArrayBuffer(
										binaryData.length);
								var uint8Array = new Uint8Array(arrayBuffer);
								for (var i = 0; i < binaryData.length; i++) {
									uint8Array[i] = binaryData.charCodeAt(i);
								}
								var blob = new Blob([ uint8Array ], {
									type : 'application/pdf'
								});

								// Crea il link di download dinamico
								var downloadLink = document
										.getElementById('downloadLink');
								downloadLink.href = URL.createObjectURL(blob);
							</script>
							<script>
								// La tua stringa Base64 contenente i dati del PDF
								var base64PDFDataIng = '${myVariable2}'; // Inserisci qui i dati Base64
								// Decodifica la stringa Base64 in un Blob
								var binaryData2 = atob(base64PDFDataIng
										.split(',')[1]);
								var arrayBuffer2 = new ArrayBuffer(
										binaryData2.length);
								var uint8Array2 = new Uint8Array(arrayBuffer2);
								for (var i = 0; i < binaryData2.length; i++) {
									uint8Array2[i] = binaryData2.charCodeAt(i);
								}
								var blob2 = new Blob([ uint8Array2 ], {
									type : 'application/pdf'
								});

								// Crea il link di download dinamico
								var downloadLinkIng = document
										.getElementById('downloadLinkIng');
								downloadLinkIng.href = URL
										.createObjectURL(blob);
							</script>
							<div class="it-card-footer">
								<!-- FAI DOMANDA -->
								<span class="card-signature"> <!-- Bandi SIAGE --> <c:if
										test="${codicefiscale != null and codicefiscale!='ADMIN' and docente==false}">
										<c:choose>
											<c:when
												test="${!bandiScaduti.contains(bandi.codice) and !codiceBandiCompilati.contains(bandi.codice)}">
												<a href="/compilaBando?codiceBando=${bandi.codice}">
													<div style="text-align: right;">
														<button type="button" class="btn btn-xs btn-danger btn-nuova-bozza"
															style="width: 30%; border-color: transparent; background-color: #33CC66; color: black;">Fai
															domanda</button>
													</div>
												</a>
											</c:when>
											<c:when
												test="${!bandiScaduti.contains(bandi.codice) and codiceBandiCompilati.contains(bandi.codice)}">
												<div style="text-align: right;">
													<form method="post" action="RimuoviDomanda">
														<input type="hidden" id="codbando" name="codbando"
															value='${bandi.codice}'>
														<div style="text-align: right;">
															<button class="btn btn-xs btn-danger btn-nuova-bozza"
																type="submit" id="btnRimuoviCandidatura">Rimuovi Candidatura</button>
														</div>
													</form>
												</div>
											</c:when>
										</c:choose>
									</c:if>
								</span> <span class="card-signature"> <c:if
										test="${codicefiscale=='ADMIN'}">
										<form id="formRimozioneBando" method="post"
											action="RimuoviBando">
											<input type="hidden" id="codicebando" name="codicebando"
												value="${bandi.codice}">
											<div style="text-align: right;">
												<button class="btn btn-xs btn-danger btn-nuova-bozza"
													type="submit">Rimuovi Bando</button>
											</div>
										</form>
									</c:if>
								</span>
							</div>
						</div>

					</div>
				</div>
			</div>

		</c:forEach>
	</div>
</body>
<script src="./js/faidomanda.js"></script>

</html>