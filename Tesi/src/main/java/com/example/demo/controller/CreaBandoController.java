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
			if (d.get(i).getFormatodocumento() != null && d.get(i).getTitolodocumento() != null
				&& d.get(i).getMindim() != 0 && d.get(i).getMaxdim()!=0) {
				if (DBManager.getInstance().documentiBandoDAO().esiste(d.get(i))
						|| !DBManager.getInstance().documentiBandoDAO().inserisci(d.get(i))) {
					DBManager.getInstance().documentiBandoDAO().eliminaDocumentiBando(d.get(i).getCodicebando());
					Bando b = DBManager.getInstance().bandoDAO().ottieniBando(d.get(i).getCodicebando());
					DBManager.getInstance().bandoDAO().eliminaBando(b);
					return "errore";

				}
			}
		}
		return "successo";
	}

	@PostMapping("ottieniImg")
	public void ottieniImg(HttpSession session, @RequestBody ArrayList<String> parametri) {
		System.out.println("IMMAGINE  "+parametri.get(1));
		DBManager.getInstance().bandoDAO().setImg(Integer.parseInt(parametri.get(0)), parametri.get(1));
		
	}

	@PostMapping("ottieniPdfItaliano")
	public void ottieniPdfIta(HttpSession session, @RequestBody ArrayList<String> parametri) {
		System.out.println("ITALIANO  "+parametri.get(1));
		DBManager.getInstance().bandoDAO().setPdfIta(Integer.parseInt(parametri.get(0)), parametri.get(1));
		
	}

	@PostMapping("ottieniPdfInglese")
	public void ottieniPdfIngelse(HttpSession session, @RequestBody ArrayList<String> parametri) {
		System.out.println("INGLESE  "+parametri.get(1));
		DBManager.getInstance().bandoDAO().setPdfIng(Integer.parseInt(parametri.get(0)), parametri.get(1));
	}
}
