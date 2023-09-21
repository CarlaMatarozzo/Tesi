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
<link rel="stylesheet" href="css/creabando.css" type="text/css">

<title>Bandi di Concorso</title>
<link rel="icon" href="../image/Logo.png" type="image/png">
</head>
<body>
	<jsp:include page="Navbar.jsp"></jsp:include>
	<div class="container-fluid">
		<div>
			<div>
				<h1 style="text-align: center; text-shadow: 2px 2px 4px black;">Compila la domanda</h1>
				<h3 style="text-align: center;" id="erroreDomanda"></h3>
			</div>
		</div>
		<form method="post" action="#" id="compilaDomanda">
			<div class="row">
				<div class="col">
					<div class="form">
						<div class="form-group">
							<label class="l1">Codice Bando</label> <input class="input"
								type="text" name="codice" class="form-control" id="codiceBando"
								style="color: black;" placeholder="${bando.codice}"
								value="${bando.codice}" required>
						</div>
						<div class="form-group">
							<label class="l1">Titolo</label> <input class="input" type="text"
								name="titolo" class="form-control" id="titolo"
								placeholder="${bando.titolo}" value="${bando.titolo}"
								style="color: black;" required>
						</div>
						<div class="form-group">
							<label class="l1">Nome</label> <input class="input" type="text"
								class="form-control" id="nome" value="${nome}"
								placeholder="${nome}" style="color: black;" required>
						</div>
						<div class="form-group">
							<label class="l1">Cognome</label> <input class="input"
								type="text" class="form-control" id="cognome" value="${cognome}"
								placeholder="${cognome}" style="color: black;" required>
						</div>
						<div class="form-group">
							<label class="l1">Codice Fiscale</label> <input class="input"
								type="text" name="codiceFiscalee" class="form-control"
								id="codiceFiscalee" value="${codicefiscale}"
								placeholder="${codicefiscale}" style="color: black;" required>
						</div>

						<div class="form-group">
							<label class="l1">Email</label> <input class="input" type="email"
								class="form-control" id="email" value="${email}"
								placeholder="${email}" style="color: black;" required>
						</div>
						<input type="hidden" name="numeroDocDaCaricare"
							id="numeroDocDaCaricare" value='${numDoc}'>
						<c:forEach items="${doc}" var="doc" varStatus="status">
							<div class="form-group">
								<input type="hidden" id="titolodoc_${status.index}"
									value='${doc.titolodocumento}'> <input type="hidden"
									id="mindoc_${status.index}" value='${doc.mindim}'> <input
									type="hidden" id="maxdoc_${status.index}" value='${doc.maxdim}'>

								<label class="l2">Inserire ${doc.titolodocumento}</label>
								<h7>Formato documento: ${doc.formatodocumento}</h7>
								<br>
								<c:choose>
									<c:when test="${doc.formatodocumento == 'img'}">
										<input type="file" accept=".jpeg,.png,.jpg"
											id="doc_${status.index}" name="doc" maxlength="150" required>
									</c:when>
									<c:when test="${doc.formatodocumento == 'testo'}">
										<input type="file" accept=".txt" id="doc_${status.index}"
											name="doc" maxlength="150" required>
									</c:when>
									<c:when test="${doc.formatodocumento == 'pdf'}">
										<input type="file" accept=".pdf" id="doc_${status.index}"
											name="doc" maxlength="150" required>
									</c:when>
								</c:choose>
								<h7 id="size_${status.index}">*MaxSize: ${doc.maxdim}</h7>
								<br>
							</div>
						</c:forEach>
					</div>
					<div class="col-md-12 text-center mb-3">
						<button type="submit" id="btnInviaDomanda"
							style="width: 30%; border-color: transparent; background-color: #55d6aa">Invia
							domanda</button>
					</div>
				</div>
			</div>
		</form>
	</div>
</body>

<script src="./js/faidomanda.js"></script>
<script src="./js/uploadFile2.js"></script>
</html>
