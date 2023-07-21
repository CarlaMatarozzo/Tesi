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

<script src="./js/assistenza.js"></script>
<link rel="stylesheet" href="css/creabando.css" type="text/css">

<title>TESI!</title>
</head>

<body>
	<jsp:include page="Navbar.jsp"></jsp:include>

	<h1 style="text-align: center;">Benvenuto nell'area Assistenza</h1>
	<h3 style="text-align: center;">Hai bisogno d'aiuto? Scrivici un'email</h3>
	<div class="container-fluid">

		<form id="emailAssistenza" action="#">
			<div class="form">
				<div class="form-group">


					<label for="11">Mittente:</label> <input class="input1"
						type="email" class="form-control" style="color: black;"
						id="mittente" placeholder="Inserisci il tuo indirizzo email"
						required>
				</div>
				<div class="form-group">
					<label for="11">Oggetto:</label> <input class="input1" type="text"
						class="form-control" style="color: black;" id="oggetto"
						placeholder="Inserisci l'oggetto della email" required>
				</div>
				<div class="form-group">
					<label for="messaggio">Messaggio:</label>
					<textarea id="messaggio" class="form-control"
						class="input1"  class="fixed-size-textarea"
						placeholder="Inserisci il messaggio della email" required></textarea>
				</div>
				<button type="submit" id="inviaEmail"
					class=" btn btn-block mybtn x-tfm "
					style="background: #e9e4e2 !important;">Invia</button>
			</div>
		</form>
	</div>

</body>
</html>
</html>