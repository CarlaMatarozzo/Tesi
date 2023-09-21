window.addEventListener("load", function() {
});

var modalLogin = true;

function Utente(codiceFiscale, password, nome, cognome, email, docente, notifica) {
	this.codiceFiscale = codiceFiscale;
	this.password = password;
	this.nome = nome;
	this.cognome = cognome;
	this.email = email;
	this.docente = docente;
	this.notifica = notifica;
}



$(document).ready(function() {
	var Login = document.getElementById("loginForm");
	document.getElementById("btnLogin").addEventListener("submit", function() {
		Login.submit();
	});

	var btnRegistrati = document.getElementById("registrati").addEventListener("click", function() {
		$("#loginModal").modal('hide');
		$("#registrazione2Modal").modal('show');

	});

	var Registrazione = document.getElementById("formRegistrazione");
	document.getElementById("btnIscriviti").addEventListener("submit", function() {
		Registrazione.submit();
	});

	var btnLogin = document.getElementById("accedi").addEventListener("click", function() {
		$("#registrazioneModal").modal('hide');
		$("#loginModal").modal('show');
	});

	$("#loginForm").on("submit", function(e) {

		e.preventDefault();
		var codicefiscale1 = document.getElementById("codiceFiscaleLogin").value;
		var password1 = document.getElementById("passwordLogin").value;
		var utente1;
		if (codicefiscale1 == "admin") {
			var utente1 = new Utente(codicefiscale1.toUpperCase(), password1, null, null, null, true, 0);
		} else {
			var utente1 = new Utente(codicefiscale1.toUpperCase(), password1, null, null, null, false, 0);
		}
		$.ajax({
			url: "loginService",
			method: "POST",
			data: JSON.stringify(utente1),
			contentType: "application/json",
			success: function(risposta) {
				if (risposta == "successo") {
					location.reload();
				}
				if (risposta == "errore") {
					var errorLogin = document.getElementById("erroreLogin");
					errorLogin.innerHTML = "Codice fiscale o password errati";
				}
			},
		});

	});

	$("#formRegistrazione").on("submit", function(e) {
		e.preventDefault();
		var nome2 = document.getElementById("nome").value;
		var cognome2 = document.getElementById("cognome").value;
		var codicefiscale2 = document.getElementById("codiceFiscale").value;
		var email2 = document.getElementById("email").value;
		var password2 = document.getElementById("password").value;
		var utente2 = new Utente(codicefiscale2.toUpperCase(), password2, nome2, cognome2, email2.toLowerCase(), false, 0);
		$.ajax({
			url: "registrationService",
			method: "POST",
			data: JSON.stringify(utente2),
			contentType: "application/json",
			success: function(risposta) {
				if (risposta == "successo") {
					location.reload();
				}
				if (risposta == "errore") {
					var errorRegistrazione = document.getElementById("erroreRegistrazione");
					errorRegistrazione.innerHTML = "Codice fiscale o email gi&#224 in uso";
				}
			},
		});
	});
	$("#formRegistrazioneDocente").on("submit", function(e) {
		e.preventDefault();
		var nome4 = document.getElementById("nome1").value;
		var cognome4 = document.getElementById("cognome1").value;
		var codicefiscale4 = document.getElementById("codiceFiscale1").value;
		var email4 = document.getElementById("email1").value;
		var utente4 = new Utente(codicefiscale4.toUpperCase(), "Docente1.", nome4, cognome4, email4.toLowerCase(), true, 0);
		var parametri4 = [cognome4, email4, codicefiscale4];
		$.ajax({
			url: "registrazioneDocente",
			method: "POST",
			data: JSON.stringify(utente4),
			contentType: "application/json",
			success: function(risposta) {
				if (risposta == "successo") {
					$.ajax({
						url: "emailRegistrazioneDocente",
						method: "POST",
						data: JSON.stringify(parametri4),
						contentType: "application/json",
						success:function(risposta){
							if(risposta=="successo"){
								location.reload();
							}
						}
					});
				}
				if (risposta == "errore") {
					var errorRegistrazione = document.getElementById("erroreRegistrazione");
					errorRegistrazione.innerHTML = "Docente gi&#224 registrato";
				}
			},
		});
	});

	$("#formRichiestaRegistrazioneDocente").on("submit", function(e) {
		e.preventDefault();
		var nomedoc = document.getElementById("nomeDoc").value;
		var cognomedoc = document.getElementById("cognomeDoc").value;
		var codicefiscaledoc = document.getElementById("codiceFiscaleDoc").value;
		var emaildoc = document.getElementById("emailDoc").value;
		var parametridoc = [nomedoc, cognomedoc, codicefiscaledoc, emaildoc];
		$.ajax({
			url: "richiestaRegistrazioneDocente",
			method: "POST",
			data: JSON.stringify(parametridoc),
			contentType: "application/json",
			success: function(risposta) {
				if (risposta == "successo") {
					$.ajax({
						url: "eliminaRichiestaDocente",
						method: "POST",
						data: JSON.stringify(parametridoc),
						contentType: "application/json",
						success: function(risposta) {
							if (risposta == "successo") {
								location.reload();
							}
						},
					});
				}
				if(risposta=="errore"){
					var errorRegistrazione = document.getElementById("erroreRegistrazioneDoc");
					errorRegistrazione.innerHTML = "Docente gi&#224 registrato";
				}
			},
		});
	});


	$("#recuperoPassword").on("submit", function(e) {

		$("#btnRecuperoPassword").prop("disabled", true);
		$("#btnRecuperoPassword").html(`<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span> Loading...`);

		e.preventDefault();
		var email3 = document.getElementById("emailRecupero").value;
		var utente3 = new Utente(null, null, null, null, email3.toLowerCase(), true);
		$.ajax({
			url: "RecuperoPassword",
			method: "POST",
			data: JSON.stringify(utente3),
			contentType: "application/json",
			success: function(risposta) {
				if (risposta == "successo") {
					$("#btnRecuperoPassword").prop("disabled", false);
					$("#btnStartUploads i").removeAttr('class');
					$("#btnStartUploads i").addClass('class="btn btn-sm btn-outline-info"');
					$("#btnRecuperoPassword").html(`recupera`);
					$('#invioNuovaPassword').modal('show');
				}
			},
		});
	});

	$("#chiudi").on("click", function(e) {
		$('#recuperaPassword').modal('hide');
	});
});