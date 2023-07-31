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
		var codFiscale = document.getElementById("codiceFiscalee").value;
		var numDoc = document.getElementById("numeroDocDaCaricare").value;
		var doc1 = [];
		for (var i = 0; i < numDoc; i++) {
			var titoloDoc = document.getElementById("titolodoc_" + i).value;
			var caricaDoc = document.getElementById("doc_" + i).value;
			var d = new DocumentiCaricatiBando(codice, titoloDoc, codFiscale, null);
			doc1.push(d);
		}
		$.ajax({
			url: "inserisciDocumento",
			method: "POST",
			data: JSON.stringify(doc1),
			contentType: "application/json",
			success: function(risposta) {
				if (risposta == "successo") {
					for (var i = 0; i < numDoc; i++) {
						var pdf = document.getElementById("doc_" + i);
						var tit=document.getElementById("titolodoc_"+i).value;
						if (pdf.files[0]) {
							window.convert2(pdf.files[0], codice, tit, codFiscale)
								.then((conversionResult) => {
									return window.callEndpoint2(conversionResult, "ottieniDocumento", "POST");
								})
								.catch((error) => {
									// Gestisci gli errori qui, se si verifica un errore in qualsiasi delle due operazioni
									console.error("Errore:", error);
								});
						}
					}
					window.location.href = "http://localhost:8080/";
				}
				if (risposta == "errore") {
					alert("NO");
				}

			},
		});
	});
});
