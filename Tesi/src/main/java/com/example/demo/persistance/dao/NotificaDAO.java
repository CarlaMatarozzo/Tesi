package com.example.demo.persistance.dao;

public interface NotificaDAO {
	public boolean nuovaNotifica(String codicefiscale, String messaggio, Boolean lettura);
	public void messaggioLetto(int codiceNotifica);
	public int getCodiceNotifica(String codicefiscale, String messaggio);
	public int ultimoIdNotifica();
}
