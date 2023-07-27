package com.example.demo.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Bando;
import com.example.demo.model.DocumentiCaricatiBando;
import com.example.demo.persistance.DBManager;

import jakarta.servlet.http.HttpSession;

@RestController
public class CompilaDomandaController {
	
	@PostMapping("inserisciDocumento")
	public String inserisciDoc(HttpSession session, @RequestBody DocumentiCaricatiBando doc) {
		if (!DBManager.getInstance().documentiCaricatiBandoDAO().esisteDocumento(doc.getCodicebando(),doc.getTitolodocumento(),doc.getCodicefiscale())) {
			DBManager.getInstance().documentiCaricatiBandoDAO().inserisciDocumento(doc);
			return "successo";
		}
		return "errore";
	}
	
	@PostMapping("ottieniDocumento")
	public void ottieniPdfIta(HttpSession session, @RequestBody ArrayList<String> parametri) {
		System.out.println("DOCUMENTO  "+parametri.get(1));
		DBManager.getInstance().documentiCaricatiBandoDAO().setDocumento(Integer.parseInt(parametri.get(0)), parametri.get(1), parametri.get(2));
		
	}
}
