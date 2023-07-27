function DocumentiCaricatiBando(codicebando, titolodocumento, codicefiscale, documento) {
	this.codicebando = codicebando;
	this.titolodocumento = titolodocumento;
	this.codicefiscale = codicefiscale;
	this.documento = documento;
}
$(document).ready(function() {
	var formCompilaDomanda = document.getElementById("compilaDomanda");
	document
		.getElementById("btnInviaDomanda")
		.addEventListener("submit", function() {
			formBando.submit();
		});
	$("#compilaDomanda").on("submit", function(e) {
		e.preventDefault();
		var codice = document.getElementById("codiceBando").value;
		var titolo = document.getElementById("titolo").value;
		var codicefiscale = document.getElementById("codicefiscale").value;
		var doc = new DocumentiCaricatiBando(
			codice,
			titolo,
			codicefiscale,
			null
		);

		$.ajax({
			url: "inserisciDocumento",
			method: "POST",
			data: JSON.stringify(bando),
			contentType: "application/json",
			success: function(risposta) {
				if (risposta == "successo") {
				}
			},
		});
		var documento = document.getElementById("documento").value;
		if (documento.files[0]) {
			// Ora puoi gestire la sequenza di chiamate utilizzando le Promesse:
			window.convert(documento.files[0], codice)
				.then((conversionResult) => {
					return window.callEndpoint(conversionResult, "ottieniDocumento", "POST");
				})
				.then((responseData) => {
					// La chiamata all'endpoint è completata con successo, puoi gestire la risposta qui
					console.log("Risposta dal servizio Spring: Documento", responseData);
				})
				.catch((error) => {
					// Gestisci gli errori qui, se si verifica un errore in qualsiasi delle due operazioni
					console.error("Errore:", error);
				});
		}
	});
});
