package com.example.demo.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.EmailSender;
import com.example.demo.model.RichiestaDocente;
import com.example.demo.model.Utente;
import com.example.demo.persistance.DBManager;

import jakarta.servlet.http.HttpSession;

@RestController
public class LoginController {
	@PostMapping("loginService")
	public String failLogin(HttpSession session, @RequestBody Utente u) {
		if (DBManager.getInstance().utenteDAO().existsUser(u.getCodiceFiscale())) {
			if (DBManager.getInstance().utenteDAO().checkPassword(u.getCodiceFiscale(), u.getPassword())) {
				Utente utenteLoggato = DBManager.getInstance().utenteDAO().findByPrimaryKey(u.getCodiceFiscale());
				session.setAttribute("codicefiscale", utenteLoggato.getCodiceFiscale());
				session.setAttribute("password", utenteLoggato.getPassword());
				session.setAttribute("nome", utenteLoggato.getNome());
				session.setAttribute("cognome", utenteLoggato.getCognome());
				session.setAttribute("email", utenteLoggato.getEmail());
				session.setAttribute("docente", utenteLoggato.isDocente());
				session.setAttribute("notifica", utenteLoggato.getNotifica());
				return "successo";
			}
		}
		return "errore";
	}

	@PostMapping("registrationService")
	public String faiRegistration(HttpSession session, @RequestBody Utente u) {
		if (DBManager.getInstance().utenteDAO().existsUser(u.getCodiceFiscale())
				|| DBManager.getInstance().utenteDAO().existsUserEmail(u.getEmail())) {
			return "errore";
		} else {
			DBManager.getInstance().utenteDAO().save(u);
			session.setAttribute("codicefiscale", u.getCodiceFiscale());
			session.setAttribute("password", u.getPassword());
			session.setAttribute("nome", u.getNome());
			session.setAttribute("cognome", u.getCognome());
			session.setAttribute("email", u.getEmail());
			session.setAttribute("docente", false);
			session.setAttribute("notifica", 0);
			return "successo";
		}
	}

	@PostMapping("registrazioneDocente")
	public String registrazioneDoc(HttpSession session, @RequestBody Utente u) {
		if (DBManager.getInstance().utenteDAO().existsUser(u.getCodiceFiscale())
				|| DBManager.getInstance().utenteDAO().existsUserEmail(u.getEmail())) {
			return "errore";
		} else {
			DBManager.getInstance().utenteDAO().save(u);
			session.setAttribute("codicefiscale", u.getCodiceFiscale());
			session.setAttribute("password", u.getPassword());
			session.setAttribute("nome", u.getNome());
			session.setAttribute("cognome", u.getCognome());
			session.setAttribute("email", u.getEmail());
			session.setAttribute("docente", true);
			session.setAttribute("notifica", 0);
			return "successo";
		}
	}

	@PostMapping("richiestaRegistrazioneDocente")
	public String richiestaRegistrazioneDocente(HttpSession session, @RequestBody ArrayList<String> parametriDoc) {
		String messaggio = "Richiesta registrazione: " + parametriDoc.get(0) + " " + parametriDoc.get(1) + " "
				+ parametriDoc.get(2) + " " + parametriDoc.get(3) + " ";
		if (DBManager.getInstance().notificaDAO().nuovaNotifica("ADMIN", messaggio, false)) {
			DBManager.getInstance().richiestaDocenteDAO().aggiungiRichiesta(parametriDoc.get(0), parametriDoc.get(1),
					parametriDoc.get(2), parametriDoc.get(3));
			return "successo";
		}
		return "errore";
	}

	@PostMapping("eliminaRichiestaDocente")
	public String eliminaRichiestaDocente(HttpSession session,@RequestBody ArrayList<String> parametriDoc) {
		RichiestaDocente richiesta=new RichiestaDocente(parametriDoc.get(0),parametriDoc.get(1),parametriDoc.get(2),parametriDoc.get(3));
		DBManager.getInstance().richiestaDocenteDAO().rimuoviRichiesta(richiesta);
		return "successo";
		
	}

	@PostMapping("RecuperoPassword")
	public String recuperoPasswor(HttpSession session, @RequestBody Utente utente) {

		if (DBManager.getInstance().utenteDAO().existsUserEmail(utente.getEmail())) {
			try {
				String nuovaPassword = EmailSender.getInstance().ResetPassword(utente.getEmail());
				String cf = DBManager.getInstance().utenteDAO().getCodiceFiscale(utente.getEmail());
				DBManager.getInstance().utenteDAO().setPassword(cf, nuovaPassword);
				return "successo";
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "errore";
	}

	@PostMapping("emailRegistrazioneDocente")
	public String emailRegistrazioneDoc(HttpSession session, @RequestBody ArrayList<String> parametri) {
		try {
			EmailSender.getInstance().invioEmailRegistrazioneDoc(parametri.get(0), parametri.get(1), parametri.get(2));
			return "successo";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "errore";
	}

}
