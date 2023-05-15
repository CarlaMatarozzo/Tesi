package com.example.demo.model;

public class Utente {
	public String codiceFiscale;
	public String password;
	public String nome;
	public String cognome;
	public String email;
	public boolean docente;

	public Utente() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Utente(String codiceFiscale, String password, String nome, String cognome, String email, boolean docente) {
		super();
		this.codiceFiscale = codiceFiscale;
		this.password = password;
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.docente = docente;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isDocente() {
		return docente;
	}

	public void setDocente(boolean docente) {
		this.docente = docente;
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

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

}
