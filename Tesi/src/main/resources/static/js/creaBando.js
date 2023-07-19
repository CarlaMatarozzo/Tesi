function Bando(codice, titolo, img, datascadenza, pdfIta, pdfInglese, segretario) {
	this.codice = codice;
	this.titolo = titolo;
	this.img = img;
	this.datascadenza = datascadenza;
	this.pdfIta = pdfIta;
	this.pdfInglese = pdfInglese;
	this.segretario = segretario;
}

function DocumentiBando(codicebando, titolodocumento, formatodocumento, maxdim, mindim) {
	this.codicebando = codicebando;
	this.titolodocumento = titolodocumento;
	this.formatodocumento = formatodocumento;
	this.maxdim = maxdim;
	this.mindim = mindim;
}
function checkFileSize(inputId, maxSizeInKb) {
	var fileInput = document.getElementById(inputId);
	var maxSizeInBytes = maxSizeInKb * 1024;

	if (fileInput.files.length > 0) {
		var fileSize = fileInput.files[0].size;
		if (fileSize > maxSizeInBytes) {
			alert("Dimensione massima consentita: " + maxSizeInKb + " MB");
		}
	}
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
		formElementsHtml.cla
		formContainer.insertAdjacentHTML("beforeend", formElementsHtml);
	});
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

		var bando = new Bando(codice, titolo, null, datascadenza, null, null, segretario);
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
							/*if (risposta == "successo") {
								window.location.href = "http://localhost:8080/";
							}*/
							if (risposta == "errore") {
								var err = document.getElementById("erroreBando");
								err.innerHTML = "Errore documenti inseriti";
							}
						}
					})
				}
				if (risposta == "errore") {
					var err = document.getElementById("erroreBando");
					err.innerHTML = "Esiste gi&#224 un bando con questo codice";
				}
			},
		});
		var img = document.getElementById("imgUpload");
		if (img != undefined) {
			var reader = new FileReader();
			reader.onload = function() {
				base64img = reader.result;
				console.log("base64img" + base64img);
				var parametri = [codice.toString(), base64img];
				$.ajax({
					url: "ottieniImg",
					method: "POST",
					data: JSON.stringify(parametri),
					contentType: "application/json",
				});
			}
			reader.readAsDataURL(img.files[0]);
		}
		else {
			console.error("non è andato a buon fine img");
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
		if (pdfita != undefined) {
			if (checkFileSize("pdfIta", 1500)) { // Verifica la dimensione del file prima di continuare
				var readerita = new FileReader();
				readerita.onload = function() {
					base64ita = readerita.result;
					console.log("base64imgta" + base64ita);
					var parametri1 = [codice.toString(), base64ita];
					$.ajax({
						url: "ottieniPdfItaliano",
						method: "POST",
						data: JSON.stringify(parametri1),
						contentType: "application/json",
					});
				};
				readerita.readAsDataURL(pdfita.files[0]);
			} else {
				console.error("La dimensione del file italiano supera la dimensione massima consentita.");
			}
		} else {
			console.error("File italiano non selezionato.");
		}






		var pdfing = document.getElementById("pdfInglese");
		if (pdfing != undefined) {
			var readering = new FileReader();
			readering.onloadend = function() {
				base64ing = readering.result;
				console.log("base64ing" + base64ing);
				var parametri2 = [codice.toString(), base64ing];
				$.ajax({
					url: "ottieniPdfInglese",
					method: "POST",
					data: JSON.stringify(parametri2),
					contentType: "application/json",
				});
			}
			readering.readAsDataURL(pdfing.files[0]);
		}
		else {
			console.error("non è andato a buon fine ing");
		}

		/*
		var base64imgPromise = new Promise(function(resolve, reject) {
			var img = document.getElementById("imgUpload");
			if (img) {
				var reader = new FileReader();
				reader.onload = function() {
					var base64img = reader.result;
					console.log("base64img: " + base64img);
					resolve(base64img);
				};
				reader.onerror = function(error) {
					reject(error);
				};
				reader.readAsDataURL(img.files[0]);
			} else {
				resolve(null); // Nessun file da leggere, risolvi con valore null
			}
		});

		var base64itaPromise = new Promise(function(resolve, reject) {
			var pdfita = document.getElementById("pdfIta");
			if (pdfita) {
				var readerita = new FileReader();
				readerita.onload = function() {
					var base64ita = readerita.result;
					console.log("base64ita: " + base64ita);
					resolve(base64ita);
				};
				readerita.onerror = function(error) {
					reject(error);
				};
				readerita.readAsDataURL(pdfita.files[0]);
			} else {
				resolve(null); // Nessun file da leggere, risolvi con valore null
			}
		});

		var base64ingPromise = new Promise(function(resolve, reject) {
			var pdfing = document.getElementById("pdfInglese");
			if (pdfing) {
				var readering = new FileReader();
				readering.onload = function() {
					var base64ing = readering.result;
					console.log("base64ing: " + base64ing);
					resolve(base64ing);
				};
				readering.onerror = function(error) {
					reject(error);
				};
				readering.readAsDataURL(pdfing.files[0]);
			} else {
				resolve(null); // Nessun file da leggere, risolvi con valore null
			}
		});

		// ...

		Promise.all([base64imgPromise, base64itaPromise, base64ingPromise])
			.then(function(results) {
				var base64img = results[0];
				var base64ita = results[1];
				var base64ing = results[2];

				console.log("Tutte le basi64 sono state memorizzate correttamente!");
				console.log("base64img: " + base64img);
				console.log("base64ita: " + base64ita);
				console.log("base64ing: " + base64ing);
				var parametri = [codice.toString(), base64img];
				$.ajax({
					url: "ottieniImg",
					method: "POST",
					data: JSON.stringify(parametri),
					contentType: "application/json",
				});
				var parametri1 = [codice.toString(), base64ita];
				$.ajax({
					url: "ottieniPdfItaliano",
					method: "POST",
					data: JSON.stringify(parametri1),
					contentType: "application/json",
				});
				var parametri2 = [codice.toString(), base64ing];
				$.ajax({
					url: "ottieniPdfInglese",
					method: "POST",
					data: JSON.stringify(parametri2),
					contentType: "application/json",
				});
			})
			.catch(function(error) {
				console.error("Si è verificato un errore durante la generazione delle basi64:", error);
			});*/
	});
});

