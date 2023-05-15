package com.example.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Bando;
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

}
