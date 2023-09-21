package com.example.demo.controller;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.EmailSender;
import com.example.demo.model.Graduatoria;
import com.example.demo.persistance.DBManager;

import jakarta.servlet.http.HttpSession;

@RestController
public class GraduatoriaController {
	@PostMapping("inserisciCorrezione")
	public String inserisciPunteggio(HttpSession session, @RequestBody Graduatoria g) {
		if (!DBManager.getInstance().graduatoriaDAO().partecipazioneCorretta(g.getCodicebando(),
				g.getCodicefiscale())) {
			DBManager.getInstance().graduatoriaDAO().inserisciPunteggio(g);
			return "successo";
		} else if (DBManager.getInstance().graduatoriaDAO().partecipazioneCorretta(g.getCodicebando(),
				g.getCodicefiscale())) {
			DBManager.getInstance().graduatoriaDAO().modificaCorrezione(g);
			return "successo";
		}
		return "errore";
	}

	@PostMapping("emailGraduatoria")
	public String emailGraduatoria(HttpSession session, @RequestBody int codicebando) {
		try {
			List<Graduatoria> partecipazioni = DBManager.getInstance().graduatoriaDAO()
					.getPartecipazioniCorrette(codicebando);
			for (int i = 0; i < partecipazioni.size(); i++) {
				EmailSender.getInstance().invioEmailGraduatoria(DBManager.getInstance().utenteDAO().getEmail(partecipazioni.get(i).getCodicefiscale()), "Graduatoria bando: " + codicebando, codicebando);
			}
			List<Graduatoria> grad = DBManager.getInstance().graduatoriaDAO().getPartecipazioniCorrette(codicebando);
			List<String> cf = new ArrayList();
			List<Integer> punteggio = new ArrayList();
			for (int i = 0; i < grad.size(); i++) {
				cf.add(grad.get(i).getCodicefiscale());
				punteggio.add(grad.get(i).getPunteggio());
				if(grad.get(i).getCodicefiscale()!="ADMIN") {
				DBManager.getInstance().notificaDAO().nuovaNotifica(grad.get(i).getCodicefiscale(), "Consulta la graduatoria del bando "+codicebando+" nella pagina i miei bandi.", false);
				}
				else {
					DBManager.getInstance().notificaDAO().nuovaNotifica(grad.get(i).getCodicefiscale(), "&#200 stato corretto il bando "+codicebando+" consulta la graduatoria", false);	
				}
			}
			String base64String = Base64.getEncoder().encodeToString(EmailSender.generatePdf(codicebando, cf,punteggio)); 
			DBManager.getInstance().graduatoriaDAO().aggiungiPDF(base64String);
			return "successo";

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "errore";
	}
}
