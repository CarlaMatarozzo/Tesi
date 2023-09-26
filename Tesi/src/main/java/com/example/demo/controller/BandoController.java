package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Bando;
import com.example.demo.persistance.DBManager;

import jakarta.servlet.http.HttpSession;

@Controller
public class BandoController {
	@PostMapping("/AggiungiPreferiti")
	public String aggiungiAiPreferiti(HttpSession session, @RequestParam int codicebando) {
		if (!DBManager.getInstance().preferitiDAO().existPreferito(session.getAttribute("codicefiscale").toString(),
				codicebando)) {
			DBManager.getInstance().preferitiDAO().savePreferito(session.getAttribute("codicefiscale").toString(),
					codicebando);
		}
		return "redirect:/";
	}

	@PostMapping("/RimuoviPreferiti")
	public String rimuoviDaiPreferiti(HttpSession session, @RequestParam int codicebando) {
		if (DBManager.getInstance().preferitiDAO().existPreferito(session.getAttribute("codicefiscale").toString(),
				codicebando)) {
			DBManager.getInstance().preferitiDAO().deletePreferito(session.getAttribute("codicefiscale").toString(),
					codicebando);
		}
		return "redirect:/";
	}

	@PostMapping("/RimuoviBando")
	public String rimuoviBando(HttpSession session, @RequestParam int codicebando) {
		if (DBManager.getInstance().bandoDAO().esisteBando(codicebando)) {
			Bando b = DBManager.getInstance().bandoDAO().ottieniBando(codicebando);
			DBManager.getInstance().bandoDAO().eliminaBando(b); 
				return "redirect:/";
			
		}
		return "redirect:/";
	}

	@PostMapping("/RimuoviDomanda")
	public String rimuoviDomanda(HttpSession session, @RequestParam int codbando) {
		if (DBManager.getInstance().documentiCaricatiBandoDAO().rimuoviBando(codbando,
				session.getAttribute("codicefiscale").toString())) {
			return "redirect:/";
		}
		return "redirect:/";
	}
}
