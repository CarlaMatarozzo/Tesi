function Graduatoria(codicefiscale, codicebando, punteggio) {
	this.codicefiscale = codicefiscale;
	this.codicebando = codicebando;
	this.punteggio = punteggio;
}

$(document).ready(function() {

	$(document).ready(function() {
		$(document).on("submit", "form[id^='correggiBando-']", function(e) {
			e.preventDefault();
			var form = $(this);
			var formId = $(this).attr("id"); // Ottieni l'ID del form corrente
			var numero = formId.split("-")[1];
			alert(numero);

			var codfisc = form.find("input[name='cf-" + numero + "']").val();
			var codbando = form.find("input[name='codicebando-" + numero + "']").val();
			var punteggio = form.find("input[name='punteggio-" + numero + "']").val();
			var graduatoria = new Graduatoria(codfisc, codbando, punteggio);
			
			console.log("CF:" + codfisc + codbando + punteggio);

			$.ajax({
				url: "inserisciCorrezione",
				method: "POST",
				data: JSON.stringify(graduatoria),
				contentType: "application/json",
				success: function(risposta) {
					if (risposta == "successo") {
						alert("OK");
					}
				}
			});
		});
	});



});