package com.example.demo.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Bando;
import com.example.demo.model.DocumentiBando;
import com.example.demo.persistance.DBManager;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	public List<Integer> getBandiScaduti() {
		List<Bando> bandi = DBManager.getInstance().bandoDAO().getBandi();
		List<Integer> bandiScaduti=new ArrayList<Integer>();
		for (int i = 0; i < bandi.size(); i++) {
			Date sqlDate = bandi.get(i).getDatascadenza();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String dateString = formatter.format(sqlDate);
			LocalDate ds = LocalDate.parse(dateString);
			LocalDate dc = LocalDate.now();
			if (ds.isBefore(dc)) {
				bandiScaduti.add(bandi.get(i).getCodice());
			}
		}
		return bandiScaduti;
	}

	@GetMapping({ "/" })
	public String index(HttpSession session) {
		List<Bando> bandi=getBandi();
		session.setAttribute("bandi", bandi);
		session.setAttribute("bandiScaduti",getBandiScaduti());
		if (session.getAttribute("codicefiscale") != null) {

			int[] b = getBandiPreferiti(session.getAttribute("codicefiscale").toString(), bandi);
			session.setAttribute("bandipreferiti", b);
			List<Integer> codiceBandiCompilati=getCodiceBandiCompilati(session.getAttribute("codicefiscale").toString());
			session.setAttribute("codiceBandiCompilati",codiceBandiCompilati );
			session.setAttribute("bandiCompilati", getBandiCompilati(codiceBandiCompilati));
		}
		return "index";
	}

	@GetMapping("/compilaBando")
	public String compilaBando(HttpSession session, @RequestParam int codiceBando) {
		var num = DBManager.getInstance().documentiBandoDAO().numDocumentiBando(codiceBando);
		session.setAttribute("numDoc", num);
		List<DocumentiBando> doc = DBManager.getInstance().documentiBandoDAO().getDocumenti(codiceBando);
		session.setAttribute("doc", doc);
		Bando b = DBManager.getInstance().documentiBandoDAO().getBando(codiceBando);
		session.setAttribute("bando", b);
		return "CompilaBando";
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

	@GetMapping("/assistenza")
	public String paginaAssistenza() {
		return "Assistenza";
	}

	@GetMapping("/aggiungiDocente")
	public String addDocente() {
		return "AggiungiDocente";
	}

	@GetMapping("/Logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

	@GetMapping("/comeFunziona")
	public String comeFunziona(HttpSession session) {
		return "ComeFunziona";
	}

	@GetMapping("/VisualizzaBandi")
	public String tuttiIBandi(HttpSession session) {
		List<Bando> bandi = DBManager.getInstance().bandoDAO().getBandi();
		session.setAttribute("bandi", bandi);
		return "index";
	}

	@GetMapping("/mieiBandi")
	public String getMieiBandi(HttpSession session) {
		return "MieiBandi";
	}
	public List<Bando> getBandi(){
		List<Bando> bandi = DBManager.getInstance().bandoDAO().getBandi();
		for (int i = 0, j = bandi.size() - 1; i < j; i++) {
			bandi.add(i, bandi.remove(j));
		}
		return bandi;
	}
	
	public int[] getBandiPreferiti(String codiceFiscale, List<Bando> bandi) {
		int[] b = new int[bandi.size()];
		for (int i = 0; i < bandi.size(); i++) {
			if (DBManager.getInstance().preferitiDAO()
					.existPreferito(codiceFiscale, bandi.get(i).getCodice())) {
				b[i] = 1;
			} else {
				b[i] = 0;
			}
		}
		return b;
	}
	
	public List<Integer> getCodiceBandiCompilati(String codiceFiscale){
		List<Integer> codiceBandiCompilati = DBManager.getInstance().documentiCaricatiBandoDAO()
				.getBandiCompilati(codiceFiscale);
		return codiceBandiCompilati;
	}
	
	public List<Bando> getBandiCompilati(List<Integer> codiceBandiCompilati){
		List<Bando> bandiCompilati=new ArrayList<Bando>();
		for (int i = 0; i < codiceBandiCompilati.size(); i++) {
			Bando ban = DBManager.getInstance().bandoDAO().ottieniBando(codiceBandiCompilati.get(i));
			bandiCompilati.add(ban);
		}
		return bandiCompilati;
	}
	
}
