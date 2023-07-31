package com.example.demo.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.DocumentiCaricatiBando;
import com.example.demo.persistance.DBManager;

import jakarta.servlet.http.HttpSession;

@RestController
public class CompilaDomandaController {

	@PostMapping("inserisciDocumento")
	public String inserisciDoc(HttpSession session, @RequestBody ArrayList<DocumentiCaricatiBando> doc1) {
		int x=0;
		for (int i = 0; i < doc1.size(); i++) {
			if (!DBManager.getInstance().documentiCaricatiBandoDAO().esisteDocumento(doc1.get(i).getCodicebando(),doc1.get(i).getTitolodocumento(), doc1.get(i).getCodicefiscale())) {
				DBManager.getInstance().documentiCaricatiBandoDAO().inserisciDocumento(doc1.get(i));
				x++;
			}
		}
		if(x==doc1.size()) {
			return "successo";
		}
		return "errore";
	}

	@PostMapping("ottieniDocumento")
	public void ottieniPdfIta(HttpSession session, @RequestBody ArrayList<String> parametri) {
		DBManager.getInstance().documentiCaricatiBandoDAO().setDocumento(Integer.parseInt(parametri.get(0)),
				parametri.get(1), parametri.get(2));

	}
}
