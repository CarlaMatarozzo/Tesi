package com.example.demo.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Bando;
import com.example.demo.model.DocumentiBando;
import com.example.demo.persistance.DBManager;

import jakarta.servlet.http.HttpSession;

@RestController
public class CreaBandoController {
	@PostMapping("creaNuovoBando")
	public String creaBando(HttpSession session, @RequestBody Bando b) {
		if (!DBManager.getInstance().bandoDAO().esisteBando(b.getCodice())) {
			DBManager.getInstance().bandoDAO().creaBando(b);
			return "successo";
		}
		return "errore";
	}

	@PostMapping("inserisciDocumenti")
	public String addDocumenti(HttpSession session, @RequestBody ArrayList<DocumentiBando> d) {
		for (int i = 0; i < d.size(); i++) {
			if (DBManager.getInstance().documentiBandoDAO().esiste(d.get(i))
					|| !DBManager.getInstance().documentiBandoDAO().inserisci(d.get(i))) {

				DBManager.getInstance().documentiBandoDAO().eliminaDocumentiBando(d.get(i).getCodicebando());
				Bando b = DBManager.getInstance().bandoDAO().ottieniBando(d.get(i).getCodicebando());
				DBManager.getInstance().bandoDAO().eliminaBando(b);
				return "errore";

			} 		}
		return "successo";
	}
}
