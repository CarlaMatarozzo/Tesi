function Graduatoria(codicefiscale, codicebando, punteggio, pdf) {
	this.codicefiscale = codicefiscale;
	this.codicebando = codicebando;
	this.punteggio = punteggio;
	this.pdf = pdf;
}


$(document).ready(function() {
		$("#generaPDF").on("submit", function(e1) {
		e1.preventDefault();
		var cb1 = document.getElementById("cb").value;
		alert(cb);
		$.ajax({
			url: "emailGraduatoria",
			method: "POST",
			data: JSON.stringify(cb1),
			contentType: "application/json",
			success: function(risposta) {
				if (risposta == "successo") {
					$("#graduatoriaModal").modal("show");
				}
			}
		});
	});
	
	$("form[id^='correggiBando-']").on("submit", function(e) {
		e.preventDefault();
		var form = $(this);
		var formId = form.attr("id"); // Utilizza .attr() invece di .attr("id")
		var numero = formId.split("-")[1];
		var size = document.getElementById("sizeX").value;
		var num = numero - size - 1;
		var cf1 = "codfisc-" + num;
		var codbando1 = "codicebando-" + numero;
		var punteggio1 = "punteggio-" + numero;
		var cf2 = document.getElementById(cf1).value;
		var codbando2 = document.getElementById(codbando1).value;
		var punteggio2 = document.getElementById(punteggio1).value;
		var graduatoria = new Graduatoria(cf2, codbando2, punteggio2, null);

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
