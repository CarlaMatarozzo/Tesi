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
<link rel="stylesheet" href="css/profilo.css" type="text/css">


<title>Bandi di Concorso</title>
<link rel="icon" href="../image/Logo.png" type="image/png">
</head>
<body>
	<jsp:include page="Navbar.jsp"></jsp:include>

	<div class="form2">

		<form method="post" action="#" id="modificaProfilo">
			<div>

				<div>
					<h1 style="text-align: center; margin-bottom: 50px;">Profilo</h1>
					<h5 style="text-align: center;" id="erroreModifica"></h5>
				</div>
			</div>
			<div class="form-group">
				<label for="exampleInputEmail1">Nome</label> <input type="text"
					name="nome" class="form-control" id="nome1"
					aria-describedby="emailHelp" value="${nome}" readonly="readonly"
					style="color: black;" required>
			</div>
			<div class="form-group">
				<label for="exampleInputEmail1">Cognome</label> <input type="text"
					name="cognome" class="form-control" id="cognome1"
					aria-describedby="emailHelp" placeholder="${cognome}"
					value="${cognome }" readonly="readonly" style="color: black;"
					required>
			</div>
			<div class="form-group">
				<label for="exampleInputEmail1">Codice Fiscale</label> <input
					type="text" name="username" class="form-control"
					id="codiceFiscale1" readonly="readonly" style="color: black;"
					aria-describedby="emailHelp" value="${codicefiscale}"
					placeholder="${codicefiscale}" required>
			</div>
			<div class="form-group">
				<label for="exampleInputEmail1">Email</label> <input type="email"
					name="email" class="form-control" id="email1"
					aria-describedby="emailHelp" placeholder="${email}"
					style="color: black;" required>
			</div>


			<div class="form-group">
				<label for="exampleInputEmail1">Nuova Password*</label> <input
					type="password" name="password" id="nuovaPassword"
					class="form-control" aria-describedby="emailHelp"
					placeholder="Inserisci Password" style="color: black;"
					pattern="^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[@$!%*?&]).{8,}$">
			</div>
			<div class="form-group">
				<p id="p1">*La password deve contenere minimo otto caratteri, almeno una
					lettera maiuscola, un numero e un simbolo</p>
			</div>

			<div class="col-md-12 text-center mb-3" style="display: flex; justify-content: center; align-items: center;">
				<button type="submit" id="btnModifica" class="btn btn-xs btn-danger btn-nuova-bozza"
					style="background: #33CC66 !important; width:30%;">Modifica Profilo</button>
			</div>
		</form>
	</div>
</body>
<script src="./js/modificaProfilo.js"></script>
</html>