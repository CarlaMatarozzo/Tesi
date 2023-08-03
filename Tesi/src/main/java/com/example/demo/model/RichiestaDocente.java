package com.example.demo.model;

public class RichiestaDocente {
	private String nome;
	private String cognome;
	private String codicefiscale;
	private String email;
	
	
	public RichiestaDocente() {
		super();
	}
	
	
	public RichiestaDocente(String nome, String cognome, String codicefiscale, String email) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.codicefiscale = codicefiscale;
		this.email = email;
	}


	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getCodicefiscale() {
		return codicefiscale;
	}
	public void setCodicefiscale(String codicefiscale) {
		this.codicefiscale = codicefiscale;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
