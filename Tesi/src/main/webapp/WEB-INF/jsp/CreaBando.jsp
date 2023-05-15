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

<link rel="stylesheet" href="css/profilo.css" type="text/css">

<title>TESI!</title>
</head>
<body>
	<jsp:include page="Navbar.jsp"></jsp:include>
	<div>
		<div>
			<h1 style="text-align: center;">Crea Bando</h1>
			<h3 style="text-align: center;" id="erroreBando"></h3>
		</div>
	</div>
	<div class="form">
		<form method="post" action="#" id="creaBando">
			<div class="form-group">
				<label>Codice Bando</label> <input class="input" type="text"
					name="codice" class="form-control" id="codiceBando"
					style="color: black;" placeholder="Codice bando" required>
			</div>
			<div class="form-group">
				<label>Titolo</label> <input class="input" type="text" name="titolo"
					class="form-control" id="titolo" placeholder="Titolo"
					style="color: black;" required>
			</div>
			<div class="form-group">
				<label>Inserire immagine bando</label> <input type="file"
					accept=".jpg, .jpeg, .png" id="imgUpload" name="imgUpload">
			</div>
			<div class="form-group">
				<label>Data di scadenza</label> <input class="input" type="date"
					name="datascadenza" class="form-control" id="data"
					placeholder="Data di scadenza" style="color: black;" required>
				<script>
					// Ottieni la data di domani
					var tomorrow = new Date();
					tomorrow.setDate(tomorrow.getDate() + 1);

					// Formatta la data nel formato 'YYYY-MM-DD'
					var formattedDate = tomorrow.toISOString().split('T')[0];

					// Imposta il valore dell'attributo 'min' dell'input
					document.getElementById('data').setAttribute('min',
							formattedDate);
				</script>
			</div>
			<div class="form-group">
				<label>Inserire pdf italiano</label> <input type="file"
					accept=".pdf" id="pdfIta" name="pdfIta" required> <br>
			</div>
			<div class="form-group">
				<label>Inserire pdf del bando in inglese</label> <input type="file"
					accept=".pdf" id="pdfInglese" name="pdfInglese"> <br>
			</div>
			<div class="form-group">
				<label>Segretario</label> <input class="input" type="text"
					name="segretario" class="form-control" id="segretario"
					placeholder="Segretario" style="color: black;" required>
			</div>


			<div class="col-md-12 text-center mb-3">
				<button type="submit" id="btnCreaBando"
					style="width: 30%; border-color: transparent; background-color: #55d6aa">Aggiungi
					bando</button>
			</div>
		</form>
	</div>



</body>

<script src="./js/creaBando.js"></script>
</html>