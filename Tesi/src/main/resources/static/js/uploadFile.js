function checkFileSize(inputId, minSizeInKb, maxSizeInKb) {
	var fileInput = document.getElementById(inputId);
    var sizeElement;
    var fileSizeKB = 0; 

    if (fileInput.files.length > 0) {
        fileSizeKB = fileInput.files[0].size / 1024;
    }

    if (inputId === "pdfIta") {
        sizeElement = document.getElementById("sizeIta");
    } else {
        sizeElement = document.getElementById("sizeIng");
    }

    if (fileSizeKB < minSizeInKb) {
        sizeElement.textContent = "Dimensione minima non raggiunta, min size " + minSizeInKb + "Kb";
        sizeElement.style.color = "red";
    } else if (fileSizeKB > maxSizeInKb) {
        sizeElement.textContent = "Dimensione superata, max size " + maxSizeInKb + "Kb";
        sizeElement.style.color = "red";
        fileInput.value="";
    } else {
        sizeElement.textContent = "*MaxSize: 1500Kb";
        sizeElement.style.color = "initial";
    }
}

document.getElementById("pdfIta").addEventListener("change", function() {
    checkFileSize("pdfIta", 1, 1500);
});

document.getElementById("pdfInglese").addEventListener("change", function() {
    checkFileSize("pdfInglese", 1, 1500);
});


function convert(file, codice) {
	return new Promise((resolve, reject) => {
		var reader = new FileReader();
		reader.onload = function() {
			var base64 = reader.result;
			var parametri = [codice.toString(), base64];
			resolve(parametri); // Risolve la Promise con i parametri desiderati
		};
		reader.onerror = function() {
			reject(new Error('Errore durante la lettura del file.'));
		};
		reader.readAsDataURL(file);
	});
}

function convert1(file, codice, titoloDoc, codFiscale) {
	return new Promise((resolve, reject) => {
		var reader = new FileReader();
		reader.onload = function() {
			var base64 = reader.result;
			var parametri = [base64,codice.toString(), titoloDoc, codFiscale];
			resolve(parametri); // Risolve la Promise con i parametri desiderati
		};
		reader.onerror = function() {
			reject(new Error('Errore durante la lettura del file.'));
		};
		reader.readAsDataURL(file);
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