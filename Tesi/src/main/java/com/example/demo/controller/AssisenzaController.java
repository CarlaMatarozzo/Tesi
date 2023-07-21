package com.example.demo.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.EmailSender;

import jakarta.servlet.http.HttpSession;

@RestController
public class AssisenzaController {
	@PostMapping("EmailAssistenza")
	public String emailAssistenza(HttpSession session,@RequestBody ArrayList<String> parametri){
			try {			
				EmailSender.getInstance().invioEmailAssistenza(parametri.get(0),parametri.get(1),parametri.get(2));
				return "successo";
			} catch (Exception e) {			
				e.printStackTrace();
			}
		return "errore";	
	}
	@PostMapping("RicevutaEmail")
	public String emailAssistenza(HttpSession session,@RequestBody String mittente){
			try {			
				EmailSender.getInstance().emailRicevuta(mittente);
				return "successo";
			} catch (Exception e) {			
				e.printStackTrace();
			}
		return "errore";	
	}
}
