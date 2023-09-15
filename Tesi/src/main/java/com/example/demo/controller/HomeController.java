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
import com.example.demo.model.DocumentiCaricatiBando;
import com.example.demo.model.Graduatoria;
import com.example.demo.model.Notifica;
import com.example.demo.model.RichiestaDocente;
import com.example.demo.persistance.DBManager;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

	public List<Integer> getBandiScaduti(List<Bando> bandi) {
		List<Integer> bandiScaduti = new ArrayList<Integer>();
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
		List<Bando> bandi = getBandi();
		session.setAttribute("bandi", bandi);
		session.setAttribute("bandiScaduti", getBandiScaduti(bandi));
		if (session.getAttribute("codicefiscale") != null) {
			session.setAttribute("docente",
					DBManager.getInstance().utenteDAO().isDocente(session.getAttribute("codicefiscale").toString()));
			List<Integer> idNotificheDaLeggere = getIdNotificheDaLeggere(
					session.getAttribute("codicefiscale").toString());
			session.setAttribute("numNotifiche", idNotificheDaLeggere.size());
			session.setAttribute("idNotificheDaLeggere", idNotificheDaLeggere);
			int[] b = getBandiPreferiti(session.getAttribute("codicefiscale").toString(), bandi);
			session.setAttribute("bandipreferiti", b);
			List<Integer> codiceBandiCompilati = getCodiceBandiCompilati(
					session.getAttribute("codicefiscale").toString());
			session.setAttribute("codiceBandiCompilati", codiceBandiCompilati);
			session.setAttribute("bandiCompilati", getBandiCompilati(codiceBandiCompilati));
			List<Notifica> notifiche = getTutteLeNotifiche(session.getAttribute("codicefiscale").toString());
			session.setAttribute("arraynotifiche", notifiche);
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

	@GetMapping("/aggiungiDocente")
	public String addDocente(HttpSession session, @RequestParam String codFiscale) {
		RichiestaDocente richiesta = DBManager.getInstance().richiestaDocenteDAO().getRichiestaDocente(codFiscale);
		session.setAttribute("richiesta", richiesta);
		return "AggiungiDocente";
	}

	@GetMapping("/correzioneBando")
	public String correzioneBando(HttpSession session, @RequestParam int codiceBando) {
		session.setAttribute("codiceBandoDaCorreggere", codiceBando);
		List<DocumentiBando> documentiBando = DBManager.getInstance().documentiBandoDAO().getDocumenti(codiceBando);
		session.setAttribute("documentiBando", documentiBando);
		List<String> codicifiscaliutenti = DBManager.getInstance().utenteDAO().getCodiciFiscaliUtenti();
		List<DocumentiCaricatiBando> documentiCaricatiBando = DBManager.getInstance().documentiCaricatiBandoDAO()
				.getDocumentiBando(codiceBando);
		List<Graduatoria> partecipazioniCorrette=DBManager.getInstance().graduatoriaDAO().getPartecipazioniCorrette(codiceBando);
		session.setAttribute("partecipazioniCorrette", partecipazioniCorrette);
		List<String> cfCorrette=new ArrayList();
		for(int i=0; i<partecipazioniCorrette.size();i++) {
			cfCorrette.add(partecipazioniCorrette.get(i).getCodicefiscale());
		}
		session.setAttribute("cfCorrette", cfCorrette);
		String titolodoc=DBManager.getInstance().documentiCaricatiBandoDAO().getTitolo(codiceBando);
		int numRichieste=DBManager.getInstance().documentiCaricatiBandoDAO().getNumeroRichieste(codiceBando, titolodoc);
		boolean tuttiCorretti=false;
		if(numRichieste==partecipazioniCorrette.size()) {
			tuttiCorretti=true;
			session.setAttribute("tuttiCorretti", tuttiCorretti);
		}
		List<String> dati = new ArrayList<>();
		boolean inserito = false;
		int x=0;
		for (int i = 0; i < codicifiscaliutenti.size(); i++) {
			for (int e = 0; e < documentiCaricatiBando.size(); e++) {
				if (documentiCaricatiBando.get(e).getCodicefiscale().equals(codicifiscaliutenti.get(i))) {
					if (!inserito) {
						x++;
						inserito = true;
						dati.add(codicifiscaliutenti.get(i));
					}
					dati.add(documentiCaricatiBando.get(e).getDocumento());
				}
			}


			if (inserito) {
				dati.add(" ");
			}
			inserito = false;

		}
		
		session.setAttribute("sizeX",documentiCaricatiBando.size()/x);
		session.setAttribute("dati", dati);
		return "Correzione";
	}
	
	@GetMapping("/navbar")
	public String navbar(HttpSession session) {
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
	public String creaBando(HttpSession session) {
		session.setAttribute("docenti", DBManager.getInstance().utenteDAO().getCognomiDocenti());
		return "CreaBando";
	}

	@GetMapping("/assistenza")
	public String paginaAssistenza() {
		return "Assistenza";
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

	@GetMapping("bandiDaCorreggere")
	public String getBandiDaCorreggere(HttpSession session) {
		String docente = DBManager.getInstance().utenteDAO()
				.getCognome(session.getAttribute("codicefiscale").toString());
		List<Bando> bandiDaCorreggere = DBManager.getInstance().bandoDAO().getBandiDocente(docente);
		session.setAttribute("bandiDaCorreggere", bandiDaCorreggere);
		List<Integer> numRichieste = new ArrayList<>();
		List<Integer> bandiCorretti=new ArrayList<>();
		List<String> pdf=new ArrayList<>();
		for (int i = 0; i < bandiDaCorreggere.size(); i++) {
			String titolo = DBManager.getInstance().documentiBandoDAO()
					.getDocumenti(bandiDaCorreggere.get(i).getCodice()).get(0).getTitolodocumento();
			numRichieste.add(DBManager.getInstance().documentiCaricatiBandoDAO()
					.getNumeroRichieste(bandiDaCorreggere.get(i).getCodice(), titolo));
			if(DBManager.getInstance().graduatoriaDAO().isCorretto(bandiDaCorreggere.get(i).getCodice())) {
				bandiCorretti.add(bandiDaCorreggere.get(i).getCodice());
				pdf.add(DBManager.getInstance().graduatoriaDAO().getpdf(bandiDaCorreggere.get(i).getCodice()));
			}
		}
		session.setAttribute("pdf", pdf);
		session.setAttribute("bandiCorretti", bandiCorretti);
		session.setAttribute("numRichieste", numRichieste);
		return "BandiDaCorreggere";
	}

	@GetMapping("/mieiBandi")
	public String getMieiBandi(HttpSession session) {
		List<Integer> codBandiCompilati=getCodiceBandiCompilati(session.getAttribute("codicefiscale").toString());
		List<String> pdfPartecipanti=new ArrayList<String>();
		for(int i=0; i<codBandiCompilati.size();i++) {
			if(DBManager.getInstance().graduatoriaDAO().isCorretto(codBandiCompilati.get(i))) {
				pdfPartecipanti.add(DBManager.getInstance().graduatoriaDAO().getpdf(codBandiCompilati.get(i)));
			} else {
				pdfPartecipanti.add(" ");
			}
		}
		session.setAttribute("pdfPartecipanti", pdfPartecipanti);
		return "MieiBandi";
	}

	public List<Bando> getBandi() {
		List<Bando> bandi = DBManager.getInstance().bandoDAO().getBandi();
		for (int i = 0, j = bandi.size() - 1; i < j; i++) {
			bandi.add(i, bandi.remove(j));
		}
		return bandi;
	}

	@GetMapping("/comunicazioni")
	public String getComunicazioni(HttpSession session) {
		List<Notifica> notifiche = getTutteLeNotifiche(session.getAttribute("codicefiscale").toString());
		for (int i = 0; i < notifiche.size(); i++) {
			DBManager.getInstance().notificaDAO().messaggioLetto(notifiche.get(i).getIdnotifica());
		}
		return "Comunicazioni";
	}

	public int[] getBandiPreferiti(String codiceFiscale, List<Bando> bandi) {
		int[] b = new int[bandi.size()];
		for (int i = 0; i < bandi.size(); i++) {
			if (DBManager.getInstance().preferitiDAO().existPreferito(codiceFiscale, bandi.get(i).getCodice())) {
				b[i] = 1;
			} else {
				b[i] = 0;
			}
		}
		return b;
	}

	public List<Integer> getCodiceBandiCompilati(String codiceFiscale) {
		List<Integer> codiceBandiCompilati = DBManager.getInstance().documentiCaricatiBandoDAO()
				.getBandiCompilati(codiceFiscale);
		return codiceBandiCompilati;
	}

	public List<Bando> getBandiCompilati(List<Integer> codiceBandiCompilati) {
		List<Bando> bandiCompilati = new ArrayList<Bando>();
		for (int i = 0; i < codiceBandiCompilati.size(); i++) {
			Bando ban = DBManager.getInstance().bandoDAO().ottieniBando(codiceBandiCompilati.get(i));
			bandiCompilati.add(ban);
		}
		return bandiCompilati;
	}

	public List<Integer> getIdNotificheDaLeggere(String codiceFiscale) {
		return DBManager.getInstance().utenteDAO().getIdNotificheDaLeggere(codiceFiscale);
	}

	public List<Notifica> getTutteLeNotifiche(String codiceFiscale) {
		return DBManager.getInstance().utenteDAO().ottieniNotifiche(codiceFiscale);
	}

	
}

