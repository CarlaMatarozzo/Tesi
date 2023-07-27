function Bando(
	codice,
	titolo,
	img,
	datascadenza,
	pdfIta,
	pdfInglese,
	segretario
) {
	this.codice = codice;
	this.titolo = titolo;
	this.img = img;
	this.datascadenza = datascadenza;
	this.pdfIta = pdfIta;
	this.pdfInglese = pdfInglese;
	this.segretario = segretario;
}

function DocumentiBando(
	codicebando,
	titolodocumento,
	formatodocumento,
	maxdim,
	mindim
) {
	this.codicebando = codicebando;
	this.titolodocumento = titolodocumento;
	this.formatodocumento = formatodocumento;
	this.maxdim = maxdim;
	this.mindim = mindim;
}

var base64img;
var base64ita;
var base64ing;

$(document).ready(function() {
	let uniqueId = 1;

	$("#addDoc").click(function() {
		// Genera il codice HTML degli elementi del form con l'ID univoco
		var formElementsHtml = `
 	<h2>Nuovo documento</h2>
    <div class="form-group">
      <label class="l1">Titolo documento</label>
      <input class="input" type="text" name="doc${uniqueId}" id="doc${uniqueId}"
        placeholder="Titolo documento" style="color: black;"    </div>
    <div class="form-group">
      <label class="l1">Formato documento</label>
      <input class="input" type="text" name="formatoDoc${uniqueId}" id="formatoDoc${uniqueId}"
        placeholder="Formato documento" style="color: black;">
    </div>
    <div class="form-group">
      <label class="l1">Minima dimensione</label>
      <input class="input" type="number" name="minSize${uniqueId}" id="minSize${uniqueId}"
        placeholder="Minima dimensione" style="color: black;">
    </div>
    <div class="form-group">
      <label class="l1">Massima dimensione</label>
      <input class="input" type="number" name="maxSize${uniqueId}" id="maxSize${uniqueId}"
        placeholder="Massima dimensione" style="color: black;">
    </div>
  `;
		uniqueId++;
		// Aggiungi gli elementi del form al
		formElementsHtml.cla;
		formContainer.insertAdjacentHTML("beforeend", formElementsHtml);
	});
	var formBando = document.getElementById("creaBando");
	document
		.getElementById("btnCreaBando")
		.addEventListener("submit", function() {
			formBando.submit();
		});

	$("#creaBando").on("submit", function(e) {
		e.preventDefault();
		var codice = document.getElementById("codiceBando").value;
		var titolo = document.getElementById("titolo").value;
		var datascadenza = document.getElementById("data").value;
		var segretario = document.getElementById("segretario").value;

		var bando = new Bando(
			codice,
			titolo,
			null,
			datascadenza,
			null,
			null,
			segretario
		);
		var doc = [];
		for (var i = 0; i < uniqueId; i++) {
			var tit = document.getElementById("doc" + i).value;
			var formato = document.getElementById("formatoDoc" + i).value;
			var minSize = document.getElementById("minSize" + i).value;
			var maxSize = document.getElementById("maxSize" + i).value;
			var d = new DocumentiBando(codice, tit, formato, minSize, maxSize);
			doc.push(d);
		}

		$.ajax({
			url: "creaNuovoBando",
			method: "POST",
			data: JSON.stringify(bando),
			contentType: "application/json",
			success: function(risposta) {
				if (risposta == "successo") {
					$.ajax({
						url: "inserisciDocumenti",
						method: "POST",
						data: JSON.stringify(doc),
						contentType: "application/json",
						success: function(risposta) {
							//if (risposta == "successo") {
							//	window.location.href = "http://localhost:8080/";
							//}
							if (risposta == "errore") {
								var err = document.getElementById("erroreBando");
								err.innerHTML = "Errore documenti inseriti";
							}
						},
					});
				}
				if (risposta == "errore") {
					var err = document.getElementById("erroreBando");
					err.innerHTML = "Esiste gi&#224 un bando con questo codice";
				}
			},
		});

		var img = document.getElementById("imgUpload");
		if (img.files[0]) {
			// Ora puoi gestire la sequenza di chiamate utilizzando le Promesse:
			window.convert(img.files[0], codice)
				.then((conversionResult) => {
					return window.callEndpoint(conversionResult, "ottieniImg", "POST");
				})
				.then((responseData) => {
					// La chiamata all'endpoint è completata con successo, puoi gestire la risposta qui
					console.log("Risposta dal servizio Spring: IMMAGINE", responseData);
				})
				.catch((error) => {
					// Gestisci gli errori qui, se si verifica un errore in qualsiasi delle due operazioni
					console.error("Errore:", error);
				});
		}

		/*				var pdfita = document.getElementById("pdfIta");
							if (pdfita != undefined ) {
								var readerita = new FileReader();
								readerita.onload = function() {
									base64ita = readerita.result;
									console.log("base64imgta"+base64ita);
									var parametri1 = [codice.toString(), base64ita];
									$.ajax({
										url: "ottieniPdfItaliano",
										method: "POST",
										data: JSON.stringify(parametri1),
										contentType: "application/json",
									});
								}
									readerita.readAsDataURL(pdfita.files[0]);
								
							}
									 else {
				console.error("non è andato a buon fine ita");
					}*/

		var pdfita = document.getElementById("pdfIta");
		if (pdfita.files[0]) {
			//if (window.checkFileSize("pdfIta", 1, 1500)) {
				window.convert(pdfita.files[0], codice)
					.then((conversionResult) => {
						return window.callEndpoint(conversionResult, "ottieniPdfItaliano", "POST");
					})
					.then((responseData) => {
						// La chiamata all'endpoint è completata con successo, puoi gestire la risposta qui
						console.log("Risposta dal servizio Spring: ITALIANO", responseData);
					})
					.catch((error) => {
						// Gestisci gli errori qui, se si verifica un errore in qualsiasi delle due operazioni
						console.error("Errore:", error);
					});
			//}
		}

		var pdfing = document.getElementById("pdfInglese");
		if (pdfing.files[0]) {
			//if (window.checkFileSize("pdfIng", 1, 1500)) {
				window.convert(pdfing.files[0], codice)
					.then((conversionResult) => {
						return window.callEndpoint(conversionResult, "ottieniPdfInglese", "POST");
					})
					.then((responseData) => {
						// La chiamata all'endpoint è completata con successo, puoi gestire la risposta qui
						console.log("Risposta dal servizio Spring: INGLESE", responseData);
					})
					.catch((error) => {
						// Gestisci gli errori qui, se si verifica un errore in qualsiasi delle due operazioni
						console.error("Errore:", error);
					});

			//}
		}
	});
});
