window.addEventListener("load", function() { //"load"-->al caricamento della pagina inizializziamo il form per effettuare login e sign up
	//checklogin();
});

var modalLogin = true;
/*
function checklogin() {


	$("#registrati").click(function() {
		$("#first").fadeOut("fast", function() {
			$("#second").fadeIn("fast");
			modalLogin = false;
		});
	});

	$("#accedi").click(function() {
		$("#second").fadeOut("fast", function() {
			$("#first").fadeIn("fast");
			modalLogin = true;
		});
	});
}*/

function Utente(codiceFiscale,password,nome,cognome,email,docente) {
	this.codiceFiscale = codiceFiscale;
	this.password = password;
	this.nome = nome;
	this.cognome = cognome;
	this.email = email;
	this.docente = docente;
}



$(document).ready(function() {
	var Login = document.getElementById("loginForm");
	document.getElementById("btnLogin").addEventListener("submit", function() {
		Login.submit();
	});
	
	var btnRegistrati=document.getElementById("registrati").addEventListener("click", function(){
		$("#loginModal").modal('hide');
		$("#registrazioneModal").modal('show');
		
	});
	
	var Registrazione = document.getElementById("formRegistrazione");
	document.getElementById("btnIscriviti").addEventListener("submit", function() {
		Registrazione.submit();
	});

	var btnLogin=document.getElementById("accedi").addEventListener("click", function(){
		$("#registrazioneModal").modal('hide');
		$("#loginModal").modal('show');
	});
	
	$("#loginForm").on("submit", function(e) {

			e.preventDefault();
			var codicefiscale1 = document.getElementById("codiceFiscaleLogin").value;
			var password1 = document.getElementById("passwordLogin").value;
			var utente1;
			if (codicefiscale1 == "admin") {
				var utente1 = new Utente(codicefiscale1.toUpperCase(), password1, null, null, null, true);
			} else {
				var utente1 = new Utente(codicefiscale1.toUpperCase(), password1, null, null, null, false);
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
						var errorLogin=document.getElementById("erroreLogin");
						errorLogin.innerHTML = "Codice fiscale o password errati";
					}
				},
			});
		
	});

	$("#formRegistrazione").on("submit", function(e) {
		e.preventDefault();
		var nome2 = document.getElementById("nome").value;
		var cognome2 = document.getElementById("cognome").value;
		var codicefiscale2=document.getElementById("codiceFiscale").value;
		var email2 = document.getElementById("email").value;
		var password2 = document.getElementById("password").value;
		var utente2 = new Utente (codicefiscale2.toUpperCase(), password2, nome2, cognome2, email2.toLowerCase(), false);
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
					var errorRegistrazione=document.getElementById("erroreRegistrazione");
					errorRegistrazione.innerHTML = "Codice fiscale o email gi&#224 in uso";
				}
			},
		});
	});
});