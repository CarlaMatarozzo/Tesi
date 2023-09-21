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

<link rel="stylesheet" href="css/Navbar.css" type="text/css">

<link rel="stylesheet" href="css/profilo.css" type="text/css">
<script src="./js/login.js"></script>

<title>Bandi di Concorso</title>
<link rel="icon" href="../image/Logo.png" type="image/png">
</head>
<body>
	<jsp:include page="Navbar.jsp"></jsp:include>
	<div class="form2" style="height: 350px !important;">
		<h1 style="text-align: center;">Aggiungi Docente</h1>
		<form method="post" action="#" id="formRegistrazioneDocente">
			<div class="form-group">
				<label for="exampleInputEmail1">Nome</label> <input type="text"
					name="nome" class="form-control" id="nome1"
					aria-describedby="emailHelp" placeholder="${richiesta.nome}"
					value="${richiesta.nome}" style="width: 300px;" required>
			</div>
			<div class="form-group">
				<label for="exampleInputEmail1">Cognome</label> <input type="text"
					name="cognome" class="form-control" id="cognome1"
					aria-describedby="emailHelp" placeholder="${richiesta.cognome}"
					value="${richiesta.cognome}" style="width: 300px;" required>
			</div>
			<div class="form-group">
				<label for="exampleInputEmail1">Codice Fiscale</label> <input
					type="text" name="username" class="form-control"
					id="codiceFiscale1" aria-describedby="emailHelp"
					placeholder="${richiesta.codicefiscale}"
					value="${richiesta.codicefiscale}" style="width: 300px;"
					pattern="^[A-Za-z]{6}\d{2}[A-Za-z]\d{2}[A-Za-z]\d{3}[A-Za-z]$"
					required>
			</div>
			<div class="form-group">
				<label for="exampleInputEmail1">Email</label> <input type="email"
					name="email" class="form-control" id="email1" style="width: 300px;"
					aria-describedby="emailHelp" placeholder="${richiesta.email}"
					value="${richiesta.email}" required>
			</div>

			<div class="col-md-12 text-center mb-3">
				<button type="submit" id="btnRegistraDocente"
					class="btn btn-block mybtn x-tfm mx-auto"
					style="background: #33CC66 !important; width: 200px; margin-top:20px;">Registra
					docente</button>

			</div>
		</form>
	</div>
</body>
</html>