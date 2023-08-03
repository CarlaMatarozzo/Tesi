package com.example.demo.model;

public class Notifica {
	private String codicefiscale;
	private String messaggio;
	private boolean lettura;
	private int idnotifica;
	public Notifica() {
	}
	
	public Notifica(String codicefiscale, String messaggio, boolean lettura, int idnotifica) {
		this.codicefiscale=codicefiscale;
		this.messaggio=messaggio;
		this.lettura=lettura;
		this.idnotifica=idnotifica;
	}

	public String getCodicefiscale() {
		return codicefiscale;
	}

	public void setCodicefiscale(String codicefiscale) {
		this.codicefiscale = codicefiscale;
	}

	public String getMessaggio() {
		return messaggio;
	}

	public void setMessaggio(String messaggio) {
		this.messaggio = messaggio;
	}

	public boolean getLettura() {
		return lettura;
	}

	public void setLettura(boolean lettura) {
		this.lettura = lettura;
	}
	
	public int getIdnotifica() {
	    return idnotifica;
	}

	public void setIdnotifica(int idnotifica) {
	    this.idnotifica = idnotifica;
	}
	
}
