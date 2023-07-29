function checkFileSize(inputId, minSizeInKb, maxSizeInKb) {
	var fileInput = document.getElementById(inputId);
	var maxSizeInBytes = maxSizeInKb * 1024;
	if (fileInput.files.length > 0) {
		var fileSize = fileInput.files[0].size;
		if (fileSize > maxSizeInBytes && fileSize < minSizeInKb) {
			fileInput.value = "";
			if (inputId === "pdfIta") {
				var it = document.getElementById("sizeIta");
				it.textContent = "Dimensione superata, max size 1500Kb";
				it.style.color = "RED";
			} else {
				var ingl = document.getElementById("sizeIng");
				ingl.textContent = "Dimensione superata, max size 1500Kb";
				ingl.style.color = "RED";
			}
		}
	}
}

function convert(file, codice) {
	return new Promise((resolve, reject) => {
		console.log("INIZIO CONVERSIONE");
		var reader = new FileReader();
		reader.onload = function() {
			var base64 = reader.result;
			console.log(base64);
			var parametri = [codice.toString(), base64];
			resolve(parametri); // Risolve la Promise con i parametri desiderati
		};
		reader.onerror = function() {
			reject(new Error('Errore durante la lettura del file.'));
		};
		reader.readAsDataURL(file);
		console.log("FINE CONVERSIONE");
	});
}


function callEndpoint(data, url, method) {
	 return new Promise((resolve, reject) => {
		 $.ajax({
			 url: url,
			 method: method,
			 data: JSON.stringify(data),
			 contentType: "application/json",
			 success: function(responseData) {
				 // La chiamata ha avuto successo, risolvi la Promise con i dati della risposta
				 resolve(responseData);
			 },
			 error: function(error) {
				 // Si è verificato un errore durante la chiamata, rifiuta la Promise con l'errore
				 reject(error);
			 }
		 });
	 });
}

window.checkFileSize = checkFileSize;
window.convert = convert;
window.callEndpoint = callEndpoint;