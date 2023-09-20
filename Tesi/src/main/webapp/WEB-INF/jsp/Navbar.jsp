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

	<header style="height:150px !important;">
		<div id="navbar">
			<a href="/"> <img class="logo" src="image\Logo.png" width="200px"
				height="140"></a>
			<nav id="nav-response">
				<div id="hormenu">
					<ul>
						<c:choose>
							<c:when test="${codicefiscale == null}">
								<li><a href="#"><span class="glyphicon glyphicon-user"></span></a>
									<ul>
										<li><a type="button" data-toggle="modal"
											data-target="#loginModal">Accedi</a></li>
										<li><a type="button" data-toggle="modal"
											data-target="#registrazioneModal">Registrati</a></li>
										
										<li><a type="button" data-toggle="modal"
											data-target="#registrazioneDocenteModal">Registrazione
												Docente</a></li>
									</ul></li>
							</c:when>
							<c:when
								test="${codicefiscale != null and codicefiscale!='ADMIN' and docente==false}">
								<li><c:choose>
										<c:when test="${numNotifiche==0}">
											<a href="#"><span class="glyphicon glyphicon-user"></span></a>
										</c:when>
										<c:otherwise>
											<a href="#"><span class="notification-badge"
												style="color: red !important;">${numNotifiche}</span><span
												class="glyphicon glyphicon-user"></span></a>
										</c:otherwise>
									</c:choose>
									<ul>
										<li><a href="comeFunziona">Come funziona</a></li>
										<li><a href="comunicazioni">Comunicazioni"</a></li>
										<li><a href="assistenza">Assistenza</a></li>
										<li><a href="mieiBandi">I miei bandi</a></li>
										<li><a href="preferiti">Preferiti</a></li>
										<li><a href="profilo">Profilo</a></li>
										<li><a href="Logout">Logout</a></li>
									</ul></li>
							</c:when>
							<c:when
								test="${codicefiscale != null and codicefiscale!='ADMIN' and docente==true}">

								<li><c:choose>
										<c:when test="${numNotifiche==0}">
											<a href="#"><span class="glyphicon glyphicon-user"></span></a>
										</c:when>
										<c:otherwise>
											<a href="#"><span class="notification-badge"
												style="color: red !important;">${numNotifiche}</span><span
												class="glyphicon glyphicon-user"></span></a>
										</c:otherwise>
									</c:choose>
									<ul>
										<li><a href="comunicazioni">Comunicazioni</a></li>

										<li><a href="assistenza">Assistenza</a></li>
										<li><a href="bandiDaCorreggere">Bandi da correggere</a></li>
										<li><a href="profilo">Profilo</a></li>
										<li><a href="Logout">Logout</a></li>
									</ul></li>
							</c:when>
							<c:when test="${codicefiscale=='ADMIN'}">
								<li><c:choose>
										<c:when test="${numNotifiche==0}">
											<a href="#"><span class="glyphicon glyphicon-user"></span></a>
										</c:when>
										<c:otherwise>
											<a href="#"><span class="notification-badge"
												style="color: red !important;">${numNotifiche}</span><span
												class="glyphicon glyphicon-user"></span></a>
										</c:otherwise>
									</c:choose>
									<ul>
										<li><a href="creabando">Crea Bando</a></li>
										<li><a href="#">Risultati bandi</a></li>
										<li><a href="comunicazioni">Comunicazioni</a></li>
										<li><a href="profilo">Profilo</a></li>
										<li><a href="Logout">Logout</a></li>
									</ul></li>
							</c:when>
						</c:choose>
					</ul>
				</div>
			</nav>

			<nav id="navbar-right">
				<ul>
					<c:choose>
						<c:when test="${codicefiscale == null}">
							<li><a href="comeFunziona">Come funziona</a></li>
							<li><a href="assistenza">Assistenza</a></li>
							<li>
								<div id="hormenu">
									<ul>
										<li><a href="#"><span
												class="glyphicon glyphicon-user"></span></a>
											<ul>
												<li><a type="button" data-toggle="modal"
													data-target="#loginModal">Accedi</a></li>
													
												<li><a type="button" data-toggle="modal"
													data-target="#registrazioneModal">Registrati</a></li>
												<li><a type="button" data-toggle="modal"
													data-target="#registrazioneDocenteModal">Registrazione
														Docente</a></li>
											</ul></li>
									</ul>
								</div>
							</li>
						</c:when>
						<c:when
							test="${codicefiscale != null and codicefiscale!='ADMIN' and docente==false}">
							<li><a href="mieiBandi">I miei bandi</a></li>
							<li><a href="comeFunziona">Come funziona</a></li>
							<li><a href="assistenza">Assistenza</a></li>
							<li>
								<div id="hormenu">
									<ul>
										<li><c:choose>
												<c:when test="${numNotifiche==0}">
													<a href="#"><span class="glyphicon glyphicon-user"></span></a>
												</c:when>
												<c:otherwise>
													<a href="#"><span class="notification-badge"
														style="color: red !important;">${numNotifiche}</span><span
														class="glyphicon glyphicon-user"></span></a>
												</c:otherwise>
											</c:choose>
											<ul>
												<li><a href="comunicazioni">Comunicazioni</a></li>
												<li><a href="preferiti">Preferiti</a></li>
												<li><a href="profilo">Profilo</a></li>
												<li><a href="Logout">Logout</a></li>
											</ul></li>
									</ul>
								</div>
							</li>
						</c:when>
						<c:when
							test="${codicefiscale != null and codicefiscale!='ADMIN' and docente==true}">

							<li><a href="bandiDaCorreggere">Bandi da correggere</a></li>
							<li><a href="assistenza">Assistenza</a></li>
							<li>
								<div id="hormenu">
									<ul>
										<li><c:choose>
												<c:when test="${numNotifiche==0}">
													<a href="#"><span class="glyphicon glyphicon-user"></span></a>
												</c:when>
												<c:otherwise>
													<a href="#"><span class="notification-badge"
														style="color: red !important;">${numNotifiche}</span><span
														class="glyphicon glyphicon-user"></span></a>
												</c:otherwise>
											</c:choose>
											<ul>
												<li><a href="comunicazioni">Comunicazioni</a></li>
												<li><a href="profilo">Profilo</a></li>
												<li><a href="Logout">Logout</a></li>
											</ul></li>
									</ul>
								</div>
							</li>
						</c:when>
						<c:when test="${codicefiscale=='ADMIN'}">
							<li><a href="creabando">Crea Bando</a></li>
							<li><a href="#">Risultati bandi</a></li>
							<li><a href="comunicazioni">Comunicazioni</a></li>
							<li>
								<div id="hormenu">
									<ul>
										<li><c:choose>
												<c:when test="${numNotifiche==0}">
													<a href="#"><span class="glyphicon glyphicon-user"></span></a>
												</c:when>
												<c:otherwise>
													<a href="#"><span class="notification-badge"
														style="color: red !important;">${numNotifiche}</span><span
														class="glyphicon glyphicon-user"></span></a>
												</c:otherwise>
											</c:choose>
											<ul>
												<li><a href="profilo">Profilo</a></li>
												<li><a href="Logout">Logout</a></li>
											</ul></li>
									</ul>
								</div>
							</li>
						</c:when>
					</c:choose>
				</ul>
			</nav>
		</div>
		<!-- LOGIN -->
		<div class="modal fade" id="loginModal">
			<div class="modal-dialog">
				<div class="modal-content">

					<!-- Modal body -->

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
								<div class="form-group col-md-12 text-center ">
									<button type="submit" id="btnLogin"
										class="mybtn btn btn-block "
										style="background: #ffffb3 !important; width: 50%; display: flex; justify-content: center; align-items: center;">Login</button>
								</div>

							</form>
							<br>
							<div class="form-group">
								<p class="text-center">
									Non hai un account? <a href="#" id="registrati">Registrati
										qui</a>
								</p>
							</div>
							<div class="form-group">
								<p class="text-center">
									<button type="button" class="btn btn-sm btn-outline-success"
										data-toggle="modal" data-target="#recuperaPassword"
										style="color: #ffffb3 !important;">- Ho dimenticato
										la password</button>
								</p>
							</div>

						</div>

					</div>
				</div>
			</div>
		</div>
		<script>
			$(document).ready(function() {
				// Ascolta il click sul pulsante di recupero password
				$("#recuperaPassword").on("show.bs.modal", function() {
					$("#loginModal").modal("hide"); // Chiudi la finestra modale di login
				});
			});
		</script>



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
									<h5 id="erroreRegistrazione" style="color:red;"></h5>
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
								</div>
								<div class="form-group">
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
										style="background: #ffffb3 !important;">Iscriviti</button>
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

		<!-- REGISTRAZIONE -->
		<div class="modal fade" id="registrazioneDocenteModal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div id="second">
						<div class="myform form ">
							<div class="logo mb-3">
								<div class="col-md-12 text-center">
									<button type="button" class="close" data-dismiss="modal">×</button>
									<h1 class="titolo-loginForm">Registrazione docente</h1>
									<h5 id="erroreRegistrazioneDoc" style="color: red;"></h5>
									<p>Compilando il seguente form verrà inviata una richiesta
										all'amministratore per la creazione del suo profilo. Se
										l'admin accetta la sua iscrizione le arriverà un'email con la
										password.</p>
									<h4 id="erroreDocente"></h4>
								</div>
							</div>
							<form method="post" action="#"
								id="formRichiestaRegistrazioneDocente">
								<div class="form-group">
									<label for="exampleInputEmail1">Nome</label> <input type="text"
										name="nome" class="form-control" id="nomeDoc"
										aria-describedby="emailHelp" placeholder="Inserisci Nome"
										required>
								</div>
								<div class="form-group">
									<label for="exampleInputEmail1">Cognome</label> <input
										type="text" name="cognome" class="form-control"
										id="cognomeDoc" aria-describedby="emailHelp"
										placeholder="Inserisci Cognome" required>
								</div>
								<div class="form-group">
									<label for="exampleInputEmail1">Codice Fiscale</label> <input
										type="text" name="username" class="form-control"
										id="codiceFiscaleDoc" aria-describedby="emailHelp"
										placeholder="Inserisci username"
										pattern="^[A-Za-z]{6}\d{2}[A-Za-z]\d{2}[A-Za-z]\d{3}[A-Za-z]$"
										required>
								</div>
								<div class="form-group">
									<label for="exampleInputEmail1">Email</label> <input
										type="email" name="email" class="form-control" id="emailDoc"
										aria-describedby="emailHelp" placeholder="Inserisci email"
										required>
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
										style="background: #ffffb3 !important;">Invia Form</button>
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
		<div class="modal fade" id="recuperaPassword">
			<div class="modal-dialog">
				<div class="modal-content">
					<!-- Modal body -->
					<div class="second">
						<div class="myform form ">
							<div class="logo mb-3">
								<div class="col-md-12 text-center">
									<button type="button" class="close" data-dismiss="modal">×</button>
									<br> <br>
									<form id="recuperoPassword" action="#">
										<h4>
											<strong>Inserisci la tua email per recuperare la
												password</strong>
										</h4>
										<br> <input style="font-size: 15px;" type="email"
											id="emailRecupero" name="emailRecupero" required><br>
										<br>
										<button type="submit" id="btnRecuperoPassword" class="btn"
											style="float: right; background: #ffffb3;">Recupera</button>
									</form>
									<br>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<script>
			$(document).ready(function() {
				// Ascolta il submit del form
				$("#recuperoPassword").submit(function(e) {
					e.preventDefault(); // Impedisce l'invio del form
					$("#recuperaPassword").modal("hide"); // Chiude la finestra modale
					// Qui puoi aggiungere ulteriori azioni come l'invio del form tramite AJAX
				});
			});
		</script>
		
		 <!-- Modal -->
    

		<div class="modal fade" id="invioNuovaPassword">
			<div class="modal-dialog">
				<div class="modal-content">
					<!-- Modal body -->
					<div class="second">
						<div class="myform form ">
							<div class="logo mb-3">
								<div class="col-md-12 text-center">
									<button id="chiudi" type="button" class="close"
										data-dismiss="modal">×</button>
									<br>
									<p>
										<strong>Se la tua email è presente nel sistema ti è
											stata inviata una nuova password, controlla nella la posta in
											arrivo ed accedi con la nuova password! </strong>
									<p>
										<br>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
<!-- 		
<div id="registrazione2Modal" class="modal fade">
	<div class="modal-dialog">

		<div class="myform form ">
			<button id="chiudi" type="button" class="close" data-dismiss="modal">×</button>
			<br>

			<h2>Sei un docente?</h2>
			<label for="option1"> <input type="radio" id="option1"
				name="choice" value="Option 1"> Si, sono un docente
			</label> <br> <label for="option2"> <input type="radio"
				id="option2" name="choice" value="Opzione 2"> No, non sono
				un docente
			</label> <br>
			<button id="confirmBtn" type="submit">Conferma</button>
		</div>
		</div>
	</div>

	<script>
	var openModalBtn = document.getElementById("openModalBtn");
    var modal = document.getElementById("registrazioneModal");
    var docenteModal = document.getElementById("registrazioneDocenteModal");
    var confirmBtn = document.getElementById("confirmBtn");
    confirmBtn.addEventListener("submit", function () {
        var options = document.getElementsByName("choice");
        var choice;
        for (var i = 0; i < options.length; i++) {
            if (options[i].checked) {
                choice = options[i].value;
                break;
            }
        }

        // Apri la modal corretta in base alla scelta
        if (choice === "Si") {
        	$("#registrazione2Modal").modal("hide");
        	$("#registrazioneDocenteModal").modal("show");
        } else if (choice === "No") {
        	$("#registrazione2Modal").modal("hide");
        	$("#registrazioneModal").modal("show");
        }
    });
 -->
    </script>
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