package com.example.demo.controller;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Utente;
import com.example.demo.persistance.DBManager;

import jakarta.servlet.http.HttpSession;

@RestController
public class ProfiloController {

	@PostMapping("modificaProfilo")
	public String modificaProf(HttpSession session, @RequestBody Utente u) {
		if (u.getPassword() == "") {
			if (DBManager.getInstance().utenteDAO().updateWithoutPsw(u)) {
				setAttribute(session, u);
				return "successo";
			}
		} else if(u.getPassword()!=null) {
			if (DBManager.getInstance().utenteDAO().updateUtente(u)) {
				setAttribute(session, u);
				return "successo";
			}
		}
		System.out.println("errore");
		return "errore";

	}
	

	public void setAttribute(HttpSession session, Utente u) {
		session.setAttribute("codicefiscale", u.getCodiceFiscale());
		session.setAttribute("password", u.getPassword());
		session.setAttribute("nome", u.getNome());
		session.setAttribute("cognome", u.getCognome());
		session.setAttribute("email", u.getEmail());
		session.setAttribute("docente", false);
	}
	
}
