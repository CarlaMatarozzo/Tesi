package com.example.demo.persistance.dao;

import java.util.List;

import com.example.demo.model.Notifica;
import com.example.demo.model.Utente;

public interface UtenteDAO {

	public boolean save(Utente utente);

	public Utente findByPrimaryKey(String codiceFiscale);

	public boolean updateUtente(Utente u);

	public boolean checkPassword(String codiceFiscale, String password);

	public boolean existsUser(String codiceFiscale);

	public boolean existsUserEmail(String email);

	public void setPassword(String codicefiscale, String password);

	public void setNotifica(String codiceFiscale, int notifica, boolean letto);

	public int getNotifiche(String codiceFiscale);
	
	public List<Integer> getIdNotificheDaLeggere(String codiceFiscale);

	public List<Notifica> ottieniNotifiche(String codiceFiscale);
	
	public boolean updateWithoutPsw(Utente u);

	public String getCodiceFiscale(String email);
	
	public boolean isDocente(String codicefiscale);
}
