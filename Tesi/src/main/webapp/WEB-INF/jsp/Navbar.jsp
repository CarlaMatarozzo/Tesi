<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>

<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<!-- font -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Source+Sans+3:ital,wght@1,500&display=swap"
	rel="stylesheet">
<!-- fine font -->
<title>TESI!</title>
<script src="./js/login.js"></script>
<link rel="stylesheet" href="css/Navbar.css" type="text/css">

</head>
<body>

	<header>
		<div id="navbar">
			<a href="/"> <img class="logo"
				src="https://www.comune.carmagnola.to.it/it-it/immagine/img-258581-O-29-954-0-0-8048e8239efac140e63085dcebcb3c23"
				width=auto height="130"></a>
			<nav id="nav-response">
				<div id="hormenu">
					<c:if test="${codicefiscale == null }">
						<!-- div che contiene il menu -->
						<ul>
							<li><a href="#"> <span class="glyphicon glyphicon-user"></span></a>
								<ul>
									<li><a type="button" data-toggle="modal"
										data-target="#loginModal">Accedi</a></li>
									<li><a type="button" data-toggle="modal"
										data-target="#registrazioneModal">Registrati</a></li>
								</ul>
						</ul>
					</c:if>
					<c:if test="${codicefiscale != null and codicefiscale!='ADMIN'}">

						<ul>
							<li><a href="#"> <span class="glyphicon glyphicon-user"></span></a>
								<!-- primo list-item, prima voce del menu -->
								<ul>
									<li><a href="#">Come funziona</a></li>
									<li><a href="#">Assistenza</a></li>
									<li><a href="#">I miei bandi</a></li>
									<li><a href="preferiti">Preferiti</a></li>
									<li><a href="profilo">Il mio profilo</a></li>
									<li><a href="Logout">Logout</a></li>
								</ul>
					</c:if>
					<c:if test="${codicefiscale=='ADMIN'}">
						<ul>
							<li><a href="#"> <span class="glyphicon glyphicon-user"></span></a>
								<!-- primo list-item, prima voce del menu -->
								<ul>
									<li><a href="#">Risultati Bandi</a></li>
									<li><a href="#">Comunicazioni</a></li>
									<li><a href="creabando">Crea Bando</a></li>
									<li><a href="profilo">Profilo</a></li>
									<li><a href="Logout">Logout</a></li>
								</ul>
					</c:if>
				</div>
			</nav>

			<nav id="navbar-right">
				<c:if test="${codicefiscale == null }">
					<ul>
						<li><a href="#">Come funziona</a></li>
						<li><a href="#">Assistenza</a></li>
						<div id="hormenu">
							<!-- div che contiene il menu -->
							<ul>
								<!-- lista principale: definisce il menu nella sua interezza -->
								<li><a href="#"> <span class="glyphicon glyphicon-user"></span></a>
									<!-- primo list-item, prima voce del menu -->
									<ul>
										<!-- Lista annidata: voci del sotto-menu -->
										<li><a type="button" data-toggle="modal"
											data-target="#loginModal">Accedi</a></li>
										<li><a type="button" data-toggle="modal"
											data-target="#registrazioneModal">Registrati</a></li>
									</ul>
									</li>
							</ul>
						</div>
					</ul>
				</c:if>
				<c:if test="${codicefiscale != null and codicefiscale!='ADMIN'}">
					<ul>
						<li><a href="#">I miei bandi</a></li>
						<li><a href="#">Come funziona</a></li>
						<li><a href="#">Assistenza</a></li>
						<div id="hormenu">
							<ul>
								<li><a href="#"> <span class="glyphicon glyphicon-user"></span></a>
									<!-- primo list-item, prima voce del menu -->
									<ul>

										<li><a href="preferiti">Preferiti</a></li>
										<li><a href="profilo">Il mio profilo</a></li>
										<li><a href="Logout">Logout</a></li>
									</ul>
									</li>
									</ul>
									</div>
									</ul>
									
				</c:if>

				<c:if test="${codicefiscale=='ADMIN'}">
					<li><a href="creabando">Crea Bando</a></li>
					<li><a href="#">Risultati bandi</a></li>
					<li><a href="#">Comunicazioni</a></li>
					<div id="hormenu">

						<ul>
							<li><a href="#"> <span class="glyphicon glyphicon-user"></span></a>
								<!-- primo list-item, prima voce del menu -->
								<ul>
									<li><a href="profilo">Profilo</a></li>
									<li><a href="Logout">Logout</a></li>
								</ul></li>
						</ul>
				</c:if>
		</div>

		<!-- LOGIN -->
		<div class="modal fade" id="loginModal">
			<div class="modal-dialog">
				<div class="modal-content">

					<!-- Modal body -->
					<div class="modal-body">

						<div id="first">
							<div class="myform form ">
								<div class="logo mb-3">
									<div class="col-md-12 text-center">
										<button type="button" class="close" data-dismiss="modal">×</button>
										<h1 class="titolo-loginForm">Login</h1>
										<h5 id="erroreLogin"></h5>

									</div>
								</div>
								<form method="post" action="#" id="loginForm">
									<div class="form-group ">
										<label for="exampleInputEmail1">Codice Fiscale</label> <input
											type="text" name="codiceFiscaleLogin" class="form-control"
											id="codiceFiscaleLogin" aria-describedby="emailHelp"
											placeholder="Inserisci username" required>
									</div>
									<div class="form-group">
										<label for="exampleInputEmail1">Password</label> <input
											type="password" name="passwordLogin" id="passwordLogin"
											class="form-control" aria-describedby="emailHelp"
											placeholder="Inserisci Password" required>
									</div>
									<div class="col-md-12 text-center ">
										<button type="submit" id="btnLogin"
											class="mybtn btn btn-block "
											style="background: #e9e4e2 !important;">Login</button>
									</div>

								</form>
								<br>
								<div class="form-group">
									<p class="text-center">
										Non hai un account? <a href="#" id="registrati">Registrati
											qui</a>
									</p>
									<p class="text-center">
										<button type="button" class="btn btn-sm btn-outline-success"
											data-toggle="modal" data-target="#recuperaPassword">
											- Ho dimenticato la password</button>
									</p>
								</div>

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!-- REGISTRAZIONE -->
		<div class="modal fade" id="registrazioneModal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div id="second">
						<div class="myform form ">
							<div class="logo mb-3">
								<div class="col-md-12 text-center">
									<button type="button" class="close" data-dismiss="modal">×</button>
									<h1 class="titolo-loginForm">Registrati</h1>
									<h5 id="erroreRegistrazione"></h5>
								</div>
							</div>
							<form method="post" action="#" id="formRegistrazione">
								<div class="form-group">
									<label for="exampleInputEmail1">Nome</label> <input type="text"
										name="nome" class="form-control" id="nome"
										aria-describedby="emailHelp" placeholder="Inserisci Nome"
										required>
								</div>
								<div class="form-group">
									<label for="exampleInputEmail1">Cognome</label> <input
										type="text" name="cognome" class="form-control" id="cognome"
										aria-describedby="emailHelp" placeholder="Inserisci Cognome"
										required>
								</div>
								<div class="form-group">
									<label for="exampleInputEmail1">Codice Fiscale</label> <input
										type="text" name="username" class="form-control"
										id="codiceFiscale" aria-describedby="emailHelp"
										placeholder="Inserisci username"
										pattern="^[A-Za-z]{6}\d{2}[A-Za-z]\d{2}[A-Za-z]\d{3}[A-Za-z]$"
										required>
								</div>
								<div class="form-group">
									<label for="exampleInputEmail1">Email</label> <input
										type="email" name="email" class="form-control" id="email"
										aria-describedby="emailHelp" placeholder="Inserisci email"
										required>
								</div>
								<div class="form-group">
									<label for="exampleInputEmail1">Password</label> <input
										type="password" name="password" id="password"
										class="form-control" aria-describedby="emailHelp"
										placeholder="Inserisci Password" required
										pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$">
									<p id="parametriPSW">Minimo otto caratteri, almeno una
										lettera maiuscola, una lettere minuscola, un numero e un
										carattere speciale tra: [@$!%*?&]</p>
								</div>
								<div class="form-group">
									<p class="text-center">
										Registrandoti accetti i nostri
										<button style="box-shadow: none;" id="btnMostraTermini"
											type="button" class="btn btn-link" data-toggle="modal"
											data-target="#apriTermini">Termini e Condizioni
											d'uso</button>
									</p>
								</div>

								<div class="col-md-12 text-center mb-3">
									<button type="submit" id="btnIscriviti"
										class=" btn btn-block mybtn x-tfm "
										style="background: #e9e4e2 !important;">Iscriviti</button>
								</div>
								<div class="col-md-12 ">
									<div class="form-group">
										<p class="text-center">
											<a href="#" id="accedi">Hai gia' un account?</a>
										</p>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
		</div>

		<div class="modal fade" id="recuperaPassword">
			<div class="modal-dialog">
				<div class="modal-content">
					<!-- Modal body -->
					<div class="modal-body">
						<div class="myform2 form ">
							<div class="logo mb-3">
								<div class="col-md-12 text-center">
									<button type="button" class="close" data-dismiss="modal">×</button>
									<br> <br>
									<form id="recuperoPassword" action="#">
										<h5>Inserisci la tua email per recuperare la password</h5>
										<br> <input style="font-size: 15px;" type="email"
											id="emailRecupero" name="emailrecupero" required><br>
										<br>
										<button type="submit" id="btnRecuperoPassword" class="btn"
											style="float: right; background: #e9e4e2;">Recupera</button>
									</form>
									<br>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="modal fade" id="invioNuovaPassword">
			<div class="modal-dialog">
				<div class="modal-content">
					<!-- Modal body -->
					<div class="modal-body">
						<div class="myform2 form ">
							<div class="logo mb-3">
								<div class="col-md-12 text-center">
									<button id="chiudi" type="button" class="close"
										data-dismiss="modal">×</button>
									<br>
									<p>Email inviata
									<p>
										<br>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="modal fade" id="apriTermini">
			<div class="modal-dialog">
				<div class="modal-content">

					<!--  Modal body -->
					<div class="modal-body">

						<div class="myform2 form ">
							<div class="logo mb-3">
								<div class="col-md-12 text-center">
									<button type="button" class="close" data-dismiss="modal">×</button>
									<br> <br>
									<h3 class="titolo-loginForm">Termini e Condizioni di
										utilizzo</h3>
									<br>
								</div>
							</div>
							<div class="row" style="padding: 10px 10px 10px">
								<p>Registrandoti al sito decidi di accettare i termini e le
									condizioni di utilizzo.</p>
								<br>
								<p>
									Gli amministratori del sito avranno accesso a dati sensibili
									riguardanti il tuo profilo (ad esclusione della password,
									inserita al momento della registrazione).<br> &#200
									SEVERAMENTE VIETATO:
								</p>
								<ul>
									<li>Registrarsi con identità falsa</li>
									<li>Inserire informazioni non valide</li>
								</ul>
								<p>Se non vengono rispettati i seguenti criteri, gli
									amministratori elimineranno il vostro account.
								<p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>





	</header>
</body>
</html>