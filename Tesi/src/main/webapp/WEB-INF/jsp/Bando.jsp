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
<body style="background:  #ffffb3;">
	<jsp:include page="Navbar.jsp"></jsp:include>
	<main class="container">
		<!-- Left Column / Headphones Image -->
		<div class="col-6">
			<img data-image="red" class="active" src="${bandop.img}" width="400"
				height="400" align="left" style="margin-left: 150px">
		</div>


		<!-- Right Column -->
		<div class="right-column" style="margin-left: 150px">

			<!--     Product Description -->
			<div class="product-description">
				<span> <small class="badge badge-success">Aperto</small> <em>
						<span>chiude il</span> <strong>${bandop.datascadenza}</strong>
				</em>
				</span>
				<h1>${bandop.titolo}</h1>
				<h1>Codice: ${bandop.codice}</h1>
				<p>${bandop.spiegazione}</p>
			</div>

			<!--     Product Configuration -->
			<div class="product-configuration">

				<!--       Product Color -->
				<!--        <div class="product-color">   -->

				<div class="color-choose">
					<div>
						<!--             <c:if test="${prodPreferito==null}">
           <form id="formAggiunta" method="post" action="AggiungiPreferiti">
					<input type="hidden" id="idProdotto" name="idProdotto" value="${prodotto.id}">
<button type="submit" style="float:right; background: transparent; border: none;"><svg xmlns="http://www.w3.org/2000/svg" width="23" height="23" fill="currentColor" class="bi bi-heart" viewBox="0 0 16 16">
  <path d="m8 2.748-.717-.737C5.6.281 2.514.878 1.4 3.053c-.523 1.023-.641 2.5.314 4.385.92 1.815 2.834 3.989 6.286 6.357 3.452-2.368 5.365-4.542 6.286-6.357.955-1.886.838-3.362.314-4.385C13.486.878 10.4.28 8.717 2.01L8 2.748zM8 15C-7.333 4.868 3.279-3.04 7.824 1.143c.06.055.119.112.176.171a3.12 3.12 0 0 1 .176-.17C12.72-3.042 23.333 4.867 8 15z"/>
</svg> Aggiungi ai preferiti</button>
			</form>
              </c:if>
           <c:if test="${prodPreferito!=null}">
             <form id="formRimozione" method="post" action="RimuoviPreferiti">
					<input type="hidden" id="idP" name="idProdotto" value="${prodotto.id}">
					<button type="submit" style="float:right; background: transparent; border: none;"><svg xmlns="http://www.w3.org/2000/svg" width="23" height="23" fill="currentColor" class="bi bi-heart-fill" viewBox="0 0 16 16">
  <path fill-rule="evenodd" d="M8 1.314C12.438-3.248 23.534 4.735 8 15-7.534 4.736 3.562-3.248 8 1.314z"/>
</svg> Rimuovi dai preferiti</button>
			</form>

           </c:if>
             <label for="red"><span></span></label>
           </div> 
          
 -->
					</div>

				</div>
	</main>
</body>