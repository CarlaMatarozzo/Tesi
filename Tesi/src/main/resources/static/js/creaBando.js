function Bando(codice, titolo, img, datascadenza, pdfIta, pdfInglese, segretario) {
	this.codice = codice;
	this.titolo = titolo;
	this.img = img;
	this.datascadenza = datascadenza;
	this.pdfIta = pdfIta;
	this.pdfInglese = pdfInglese;
	this.segretario = segretario;
}

$(document).ready(function() {
	var formBando = document.getElementById("creaBando");
	document.getElementById("btnCreaBando").addEventListener("submit", function() {
		formBando.submit();
	});
	$("#creaBando").on("submit", function(e) {
		e.preventDefault();

		var codice = document.getElementById("codiceBando").value;
		var titolo = document.getElementById("titolo").value;
		var datascadenza = document.getElementById("data").value;
		var segretario = document.getElementById("segretario").value;

		var base64img;
		var fileToLoadimg;
		var fileReaderimg;

		var selectedFileimg = document.getElementById("imgUpload").files;
		fileToLoadimg = selectedFileimg[0];
		fileReaderimg = new FileReader();
		fileReaderimg.onload = function(fileLoadedEvent) {
			base64img = fileLoadedEvent.target.result;

		};
		fileReaderimg.readAsDataURL(fileToLoadimg);

		alert(base64img);

		var base64ita;
		var selectedFileIta = document.getElementById("pdfIta").files;
		if (selectedFileIta.length > 0) {
			var fileToLoadIta = selectedFileIta[0];
			var fileReaderIta = new FileReader();
			fileReaderIta.onload = function(fileLoadedEvent) {
				base64ita = fileLoadedEvent.target.result;

			};
			fileReaderIta.readAsDataURL(fileToLoadIta);
		}

		var base64Inglese;
		var selectedFileIng = document.getElementById("pdfInglese").files;
		if (selectedFileIng.length > 0) {
			var fileToLoadIng = selectedFileIng[0];
			var fileReaderIng = new FileReader();
			fileReaderIng.onload = function(fileLoadedEvent) {
				base64Inglese = fileLoadedEvent.target.result;
			};
			fileReaderIng.readAsDataURL(fileToLoadIng);
		}

		var bando = new Bando(codice, titolo, base64img, datascadenza, base64ita, base64Inglese, segretario);
		$.ajax({
			url: "creaNuovoBando",
			method: "POST",
			data: JSON.stringify(bando),
			contentType: "application/json",
			success: function(risposta) {
				if (risposta == "successo") {
					location.reload();
					//window.location.href = "http://localhost:8080/";
				}
				if (risposta == "errore") {
					var err = document.getElementById("erroreBando");
					err.innerHTML = "Esiste gi&#224 un bando con questo codice";
				}
			},

		});

	});
});