package com.example.demo.model;

public class Graduatoria {
	private String codicefiscale;
	private int codicebando;
	private int punteggio;
	private String pdf;
	
	public Graduatoria(String codicefiscale, int codicebando, int punteggio,String pdf) {
		super();
		this.codicefiscale = codicefiscale;
		this.codicebando = codicebando;
		this.punteggio = punteggio;
		this.pdf=pdf;
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
	
	public String getPdf() {
		return pdf;
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
	
	public void setPdf(String pdf) {
		this.pdf=pdf;
	}
}
