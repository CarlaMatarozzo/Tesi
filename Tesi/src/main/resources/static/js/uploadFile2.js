function checkFileSize2(inputId, minSizeInKb, maxSizeInKb, num) {
	var fileInput = document.getElementById(inputId);
	var sizeElement;
	var fileSizeKB = 0;

	if (fileInput.files.length > 0) {
		fileSizeKB = fileInput.files[0].size / 1024;
	}

	sizeElement = document.getElementById("size_" + num);

	if (fileSizeKB < minSizeInKb) {
		sizeElement.textContent = "Dimensione minima non raggiunta, min size " + minSizeInKb + "Kb";
		sizeElement.style.color = "red";
		fileInput.value="";
	} else if (fileSizeKB > maxSizeInKb) {
		sizeElement.textContent = "Dimensione superata, max size " + maxSizeInKb + "Kb";
		sizeElement.style.color = "red";
		fileInput.value = "";
	} else {
		sizeElement.textContent = "*MaxSize: " + maxSizeInKb;
		sizeElement.style.color = "initial";
	}
}

var numeroDoc = document.getElementById("numeroDocDaCaricare").value;
var y = 0;
while (y < numeroDoc) {
    var min = document.getElementById("mindoc_" + y).value;
    var max = document.getElementById("maxdoc_" + y).value;
    addEventListenerToInput(y, min, max);
    y++;
}

function addEventListenerToInput(index, minSizeInKb, maxSizeInKb) {
    document.getElementById("doc_" + index).addEventListener("change", function () {
        console.log("FUNZIONE i:" + index);
        checkFileSize2("doc_" + index, minSizeInKb, maxSizeInKb, index);
    });
}
function convert2(file, codice, titoloDoc, codFiscale) {
	return new Promise((resolve, reject) => {
		var reader = new FileReader();
		reader.onload = function() {
			var base64 = reader.result;
			var parametri = [base64, codice.toString(), titoloDoc, codFiscale];
			resolve(parametri); // Risolve la Promise con i parametri desiderati
		};
		reader.onerror = function() {
			reject(new Error('Errore durante la lettura del file.'));
		};
		reader.readAsDataURL(file);
	});
}


function callEndpoint2(data, url, method) {
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

window.checkFileSize2 = checkFileSize2;
window.convert2 = convert2;
window.callEndpoint2 = callEndpoint2;