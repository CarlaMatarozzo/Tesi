package com.example.demo.model;

public class DocumentiBando {
	private int codicebando;
	private String titolodocumento;
	private String formatodocumento;
	private int maxdim;
	private int mindim;

	public DocumentiBando() {
		super();
	}

	public DocumentiBando(int codicebando, String titolodocumento, String formatodocumento, int maxdim, int mindim) {
		super();
		this.codicebando = codicebando;
		this.titolodocumento = titolodocumento;
		this.formatodocumento = formatodocumento;
		this.maxdim = maxdim;
		this.mindim = mindim;
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

	public String getFormatodocumento() {
		return formatodocumento;
	}

	public void setFormatodocumento(String formatodocumento) {
		this.formatodocumento = formatodocumento;
	}

	public int getMaxdim() {
		return maxdim;
	}

	public void setMaxdim(int maxdim) {
		this.maxdim = maxdim;
	}

	public int getMindim() {
		return mindim;
	}

	public void setMindim(int mindim) {
		this.mindim = mindim;
	}

}
