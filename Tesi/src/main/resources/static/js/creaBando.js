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

var indice = 0;
$(document).ready(function() {
	var uniqueId = 1;

	$("#addDoc").click(function() {

		// Genera il codice HTML degli elementi del form con l'ID univoco
		var formElementsHtml = `
 	<h2>Nuovo documento</h2>
    <div class="form-group">
      <label class="l1">Titolo documento</label>
      <input class="input" type="text" name="doc${uniqueId}" id="doc${uniqueId}"
        placeholder="Titolo documento" style="color: black;" required>
    </div>
    <div class="form-group">
      <label class="l1">Formato documento</label>
      <input class="input" type="text" name="formatoDoc${uniqueId}" id="formatoDoc${uniqueId}"
        placeholder="Formato documento" style="color: black;" required>
    </div>
    <div class="form-group">
      <label class="l1">Minima dimensione</label>
      <input class="input" type="number" name="minSize${uniqueId}" id="minSize${uniqueId}"
        placeholder="Minima dimensione" style="color: black;" required>
    </div>
    <div class="form-group">
      <label class="l1">Massima dimensione</label>
      <input class="input" type="number" name="maxSize${uniqueId}" id="maxSize${uniqueId}"
        placeholder="Massima dimensione" style="color: black;" required>
    </div>
  `;
		uniqueId++;
		// Aggiungi gli elementi del form al 
		formElementsHtml.cla
		formContainer.insertAdjacentHTML("beforeend", formElementsHtml);
	});
	indice = uniqueId;
})
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

		/*var reader= new FileReader();
		var img=document.getElementById("imgUpload").files;
		reader.readAsDataURL(img[0]);
		document.getElementById("imgUpload").addEventListener('change',function(){
			base64img=reader.readAsDataURL(img[0]);
			reader.onload=function(){
				base64img=reader.result;
				console.log("onload"+base64img);
			}
		})
		console.log(base64img);*/
		let base64img = ' ';

		function readFileAsBase64(file) {
			return new Promise((resolve, reject) => {
				const reader = new FileReader();

				reader.onload = () => {
					base64img = reader.result;
					resolve();
				};

				reader.onerror = () => {
					reject(new Error('Error reading file.'));
				};

				reader.readAsDataURL(file);
			});
		}

		async function handleFileUpload(event) {
			const file = event.target.files[0];

			try {
				await readFileAsBase64(file);
				console.log(base64img); // Do something with the base64 string
			} catch (error) {
				console.error(error);
			}
		}

		// Attach the event listener to the file input element
		const fileInput = document.getElementById('imgUpload');
		fileInput.addEventListener('change', handleFileUpload);

		alert(base64img);
		/*var img=document.getElementById("imgUpload").files;
		async function readFileAsDataURL(file) {
			let result_base64 = await new Promise((resolve) => {
				let fileReader = new FileReader();
				fileReader.onload = (e) => resolve(fileReader.result);
				fileReader.readAsDataURL(file);
			});
			//console.log(result_base64);
			base64img=result_base64;
		}
		readFileAsDataURL(img[0]);
		console.log(base64img);*/
		/*var base64ita;
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
		*/
		var bando = new Bando(codice, titolo, null, datascadenza, null, null, segretario);
		var doc = [];
		for (var i = 0; i < indice; i++) {
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
							if (risposta == "successo") {
								window.location.href = "http://localhost:8080/";
							}
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


	});
});
