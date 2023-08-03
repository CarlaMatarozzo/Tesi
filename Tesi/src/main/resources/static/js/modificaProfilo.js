function Utente(codiceFiscale,password,nome,cognome,email,docente) {
	this.codiceFiscale = codiceFiscale;
	this.password = password;
	this.nome = nome;
	this.cognome = cognome;
	this.email = email;
	this.docente = docente;
}

$(document).ready(function(){
	var form = document.getElementById("modificaProfilo");
		document.getElementById("btnModifica").addEventListener("submit", function () {
 	 	form.submit();
	});
	$("#modificaProfilo").on("submit", function(e){
		e.preventDefault();		
		var nome = document.getElementById("nome1").value;
		var cognome = document.getElementById("cognome1").value;
		var codicefiscale = document.getElementById("codiceFiscale1").value;
		var email = document.getElementById("email1").value;
		var password = document.getElementById("nuovaPassword").value;
		var utente = new Utente(codicefiscale,password,nome,cognome,email,false,0);
		$.ajax({
				  url: "modificaProfilo",  
		          method: "POST",	         
		          data: JSON.stringify(utente),	       
		          contentType: "application/json",	         
		          success: function(risposta){						
					if(risposta=="successo"){
						location.reload();
					}
					if (risposta=="errore"){
					var errore=document.getElementById("erroreModifica");
					errore.innerHTML = "Campi errati";
					}											
		          },	            	  
		    });	
	});
});
