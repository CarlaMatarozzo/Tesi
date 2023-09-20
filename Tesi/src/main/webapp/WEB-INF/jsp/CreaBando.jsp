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

<title>TESI!</title>
</head>
<body>
	<jsp:include page="Navbar.jsp"></jsp:include>
	    <style>
        /* Personalizza la dimensione del select */
        select{
            padding: 3px; /* Spazio interno */
            font-size: 12px; /* Dimensione del testo */
            width: 150px; /* Larghezza */
            height: 30px; /* Altezza */
        }
    </style>
	
	<div class="container-fluid">
		<div>
			<div>
				<h1 style="text-align: center; text-shadow: 2px 2px 4px black;">Crea Bando</h1>
				<h3 style="text-align: center;" id="erroreBando"></h3>
			</div>
		</div>
		<form method="post" action="#" id="creaBando">
			<div class="row">
				<div class="col">
					<div class="form">
						<div class="form-group">
							<label class="l1">Codice Bando</label> <input class="input"
								type="text" name="codice" class="form-control" id="codiceBando"
								style="color: black;" placeholder="Codice bando" required>
						</div>
						<div class="form-group">
							<label class="l1">Titolo</label> <input class="input" type="text"
								name="titolo" class="form-control" id="titolo"
								placeholder="Titolo" style="color: black;" required>
						</div>
						<div class="form-group">
							<label class="l2">Inserire immagine bando</label> <input
								type="file" accept=".jpeg,.png,.jpg" id="imgUpload"
								name="imgUpload">
						</div>
						<div class="form-group">
							<label class="l1">Data di scadenza</label> <input class="input"
								type="date" name="datascadenza" class="form-control" id="data"
								placeholder="Data di scadenza" style="color: black;" required>
						</div>
						<div class="form-group">
							<label class="l2">Inserire pdf italiano</label> <input
								type="file" accept=".pdf" id="pdfIta" name="pdfIta"
								maxlength="150" required>
							<h7 id="sizeIta">*MaxSize: 1500Kb</h7>
							<br>
						</div>
						<div class="form-group">
							<label class="l2">Inserire pdf del bando in inglese</label> <input
								type="file" accept=".pdf" id="pdfInglese" name="pdfInglese">
							<h7 id="sizeIng">*MaxSize: 1500Kb</h7>
							<br>
						</div>
						<div class="form-group">

						<label class="l1" for="scelta">Docente</label> 
								<select id="sceltaDocente">
								<c:forEach items="${docenti}" var="docenti" varStatus="status">
									<option value="${docenti}">${docenti}</option>
								</c:forEach>
								</select>
						</div>
					</div>
				</div>
				<div class="col">
					<div id="formContainer">
						<div class="form-group">
							<label class="l1">Titolo documento</label> <input class="input"
								type="text" name="doc0" class="form-control" id="doc0"
								placeholder="Titolo documento" style="color: black;" required>
						</div>
						<div class="form-group">
						<label class="l1" for="scelta">Formato documento</label> 
							<select id="scelta">
								<option value="pdf">Pdf</option>
								<option value="img">Immagine</option>
								<option value="txt">Testo</option>
							</select>
						</div>
						<div class="form-group">
							<label class="l1">Minima dimensione</label> <input class="input"
								type="number" name="minSize0" class="form-control" id="minSize0"
								placeholder="Minima dimensione" style="color: black;" required>
						</div>
						<div class="form-group">
							<label class="l1">Massima dimensione</label> <input class="input"
								type="number" name="maxSize0" class="form-control" id="maxSize0"
								placeholder="Massima dimensione" style="color: black;" required>
						</div>
					</div>
					<button id="addDoc">Aggiungi documento</button>
				</div>
				<div class="col-md-12 text-center mb-3">
					<button type="submit" id="btnCreaBando"
						style="width: 30%; border-color: transparent; background-color: #55d6aa">Aggiungi
						bando</button>
				</div>
			</div>
		</form>
	</div>
</body>
<script src="./js/creaBando.js"></script>
<script src="./js/uploadFile.js"></script>
</html>