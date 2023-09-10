package com.example.demo.model;

public class Graduatoria {
	private String codicefiscale;
	private int codicebando;
	private int punteggio;
	
	public Graduatoria(String codicefiscale, int codicebando, int punteggio) {
		super();
		this.codicefiscale = codicefiscale;
		this.codicebando = codicebando;
		this.punteggio = punteggio;
	}
	
	public Graduatoria() {
	}
	
	public String getCodicefiscale() {
		return codicefiscale;
	}
	
	public void setCodicefiscale(String codicefiscale) {
		this.codicefiscale = codicefiscale;
	}
	
	public int getCodicebando() {
		return codicebando;
	}
	
	public void setCodicebando(int codicebando) {
		this.codicebando = codicebando;
	}
	
	public int getPunteggio() {
		return punteggio;
	}
	
	public void setPunteggio(int punteggio) {
		this.punteggio = punteggio;
	}
	
	
}
