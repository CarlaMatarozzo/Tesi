package com.example.demo.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Graduatoria;
import com.example.demo.persistance.DBManager;

import jakarta.servlet.http.HttpSession;

@RestController
public class GraduatoriaController {
	@PostMapping("inserisciCorrezione")
	public String inserisciPunteggio(HttpSession session, @RequestBody Graduatoria g) {
		System.out.println(g.getCodicebando()+"  "+g.getCodicefiscale()+"  "+g.getPunteggio());
		if(!DBManager.getInstance().graduatoriaDAO().partecipazioneCorretta(g.getCodicebando(), g.getCodicefiscale())){
			DBManager.getInstance().graduatoriaDAO().inserisciPunteggio(g);
			return "successo";
		}
		else if (DBManager.getInstance().graduatoriaDAO().partecipazioneCorretta(g.getCodicebando(),g.getCodicefiscale())) {
			DBManager.getInstance().graduatoriaDAO().modificaCorrezione(g);
			return "successo";
		}
	return "errore";
	}
}
