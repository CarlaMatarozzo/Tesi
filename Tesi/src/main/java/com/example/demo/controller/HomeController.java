package com.example.demo.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.model.Bando;
import com.example.demo.persistance.DBManager;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	public void eliminaScaduti() {
		List<Bando> bandi = DBManager.getInstance().bandoDAO().getBandi();
		for(int i=0; i<bandi.size();i++) {
			Date sqlDate = bandi.get(i).getDatascadenza();
	        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	        String dateString = formatter.format(sqlDate);
	        LocalDate ds = LocalDate.parse(dateString);
	        LocalDate dc = LocalDate.now();
	        if(ds.isBefore(dc)) {
	        	DBManager.getInstance().bandoDAO().eliminaBando(bandi.get(i));
	        }
		
		}
	}
	
	@GetMapping({ "/" })
	public String index(HttpSession session) {
		eliminaScaduti();
		List<Bando> bandi = DBManager.getInstance().bandoDAO().getBandi();
		for (int i = 0, j = bandi.size() - 1; i < j; i++) {
			 bandi.add(i, bandi.remove(j));
        }
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
		session.setAttribute("bandi", bandi);
		return "index";
	}
}
