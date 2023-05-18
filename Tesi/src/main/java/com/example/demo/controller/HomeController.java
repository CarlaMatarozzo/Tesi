package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.model.Bando;
import com.example.demo.persistance.DBManager;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

	@GetMapping({ "/" })
	public String index(HttpSession session) {
		List<Bando> bandi = DBManager.getInstance().bandoDAO().getBandi();
		session.setAttribute("bandi", bandi);
		if (session.getAttribute("codicefiscale") != null) {
			int[] b = new int[bandi.size()];
			for (int i = 0; i < bandi.size(); i++) {
				if (DBManager.getInstance().preferitiDAO().existPreferito(session.getAttribute("codicefiscale").toString(), bandi.get(i).getCodice())) {
					b[i] = 1;
				} else{
					b[i] = 0;
				}				
			}
			session.setAttribute("bandipreferiti", b);
		}
		return "index";
	}

	@GetMapping("/navbar")
	public String navbar() {
		return "Navbar";
	}

	@GetMapping("/profilo")
	public String vaiAlProfilo() {
		return "Profilo";
	}
	@GetMapping("/preferiti")
	public String vaiAiPreferiti() {
		return "Preferiti";
	}
	@GetMapping("/bandi")
	public String vaiAiBandi() {
		return "Bandi";
	}
	
	@GetMapping("/login")
	public String vaiAlLogin() {
		return "Login";
	}

	@GetMapping("/registrazione")
	public String vaiAllaRegistrazione() {
		return "Registrazione";
	}

	@GetMapping("/creabando")
	public String creaBando() {
		return "CreaBando";
	}
	
	@GetMapping("/Logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

	@GetMapping("/VisualizzaBandi")
	public String tuttiIBandi(HttpSession session) {
		List<Bando> bandi = DBManager.getInstance().bandoDAO().getBandi();
		System.out.println(bandi.size());
		session.setAttribute("bandi", bandi);
		return "index";
	}
}
