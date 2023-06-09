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

<!-- bootstrap -->

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>

<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

<link rel="stylesheet" href="css/index.css" type="text/css">
<title>TESI!</title>
</head>
<body>
	<jsp:include page="Navbar.jsp"></jsp:include>
	<h1 style="text-align: center;">Preferiti</h1>
	<div class="row pt-2 pb-4 catalogo_bandi_home">
		<c:forEach items="${bandi}" var="bandi" varStatus="status">
			<!-- Questa � la card -->
			<c:if test="${bandipreferiti[status.index]==1}">
			<div class="col-lg-4">
				<div class="card-wrapper card-space">
					<div class="card card-bg card-big">
							<div class="card-body p-4 mt-4">
									<form id="formRimozione" method="post" action="RimuoviPreferiti">
								<input type="hidden" id="codicebando" name="codicebando"
									value="${bandi.codice}">
								<button type="submit"
									style="float: right; background: transparent; border: none; ">
									Rimuovi dai preferiti
									<svg style="color: rgb(249, 235, 83);"
										xmlns="http://www.w3.org/2000/svg" width="20" height="20"
										fill="currentColor" class="bi bi-bookmark-fill"
										viewBox="0 0 16 16"> <path
											d="M2 2v13.5a.5.5 0 0 0 .74.439L8 13.069l5.26 2.87A.5.5 0 0 0 14 15.5V2a2 2 0 0 0-2-2H4a2 2 0 0 0-2 2z"
											fill="#f9eb53"></path> </svg>
								</button>
							</form>
								<div class="etichetta mt-1">

									<span> <small class="badge badge-success">Aperto</small>
										<em> <span>chiude il</span> <strong>${bandi.datascadenza}</strong>
									</em>
									</span>
								</div>

								<div class="wrapper-image">

									<img id="imgbando" src=${bandi.img}>
								</div>



								<a class="text-decoration-none" target="_blank">
									<h4 class="card-title">${bandi.titolo}</h4>
								</a>

								<p class="card-text">
									Codice: <strong>${bandi.codice}</strong><br> <br>
									${bandi.spiegazione }

								</p>

								<div class="it-card-footer">
									<!-- FAI DOMANDA -->
									<span class="card-signature"> <!-- Bandi SIAGE --> <c:if
											test="${codicefiscale != null and codicefiscale!='ADMIN'}">
											<button class="btn btn-xs btn-primary btn-nuova-bozza"
												type="button" data-toggle="modal" data-target="#loginModal">Fai
												domanda</button>
											<!-- Bandi SIAGE (no strumento) -->
											<!-- Bandi PEC -->
										</c:if> <!-- Bandi esterni --></span>
									<!-- END FAI DOMANDA -->




									<a class="read-more" href="#"
										rel="nofollow noopener noreferrer" target="_blank"> <span
										class="text">Vedi dettaglio</span>
									</a>

								</div>
							</div>
					</div>
				</div>
			</div>
			</c:if>

		</c:forEach>
	</div>
</body>