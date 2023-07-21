$(document).ready(function() {
	$("#emailAssistenza").on("submit", function(e) {

		e.preventDefault();
		var mittente = document.getElementById("mittente").value;
		var oggetto = document.getElementById("oggetto").value;
		var messaggio = document.getElementById("messaggio").value;
		var parametri = [mittente, oggetto, messaggio];
		$.ajax({
			url: "EmailAssistenza",
			method: "POST",
			data: JSON.stringify(parametri),
			contentType: "application/json",
			success: function(risposta) {
				if (risposta == "successo") {
					$.ajax({
						url: "RicevutaEmail",
						method: "POST",
						data: JSON.stringify(mittente),
						contentType: "application/json",
						success: function(risposta1) {
							if (risposta1 == "successo") {
								
							}
						},
					});
				}
			},
		});
	});

});