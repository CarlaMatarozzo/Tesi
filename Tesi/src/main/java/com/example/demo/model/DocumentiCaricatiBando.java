package com.example.demo.model;

public class DocumentiCaricatiBando {
	private int codicebando;
	private String titolodocumento;
	private String codicefiscale;

	public DocumentiCaricatiBando() {
		super();
	}

	public DocumentiCaricatiBando(int codicebando, String titolodocumento, String codicefiscale) {
		super();
		this.codicebando = codicebando;
		this.titolodocumento = titolodocumento;
		this.codicefiscale = codicefiscale;
	}

	public int getCodicebando() {
		return codicebando;
	}

	public void setCodicebando(int codicebando) {
		this.codicebando = codicebando;
	}

	public String getTitolodocumento() {
		return titolodocumento;
	}

	public void setTitolodocumento(String titolodocumento) {
		this.titolodocumento = titolodocumento;
	}

	public String getCodicefiscale() {
		return codicefiscale;
	}

	public void setCodicefiscale(String codicefiscale) {
		this.codicefiscale = codicefiscale;
	}

}
