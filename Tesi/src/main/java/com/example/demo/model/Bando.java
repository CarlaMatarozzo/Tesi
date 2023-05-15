package com.example.demo.model;

import java.sql.Date;

public class Bando {
	private int codice;
	private String titolo;
	private String img;
	private Date datascadenza;
	private String pdfIta;
	private String pdfInglese;
	private String segretario;
	
	
	public Bando() {
		super();
	}


	public Bando(int codice, String titolo, String img, Date datascadenza, String pdfIta, String pdfInglese,
			String segretario) {
		super();
		this.codice = codice;
		this.titolo = titolo;
		this.img = img;
		this.datascadenza = datascadenza;
		this.pdfIta = pdfIta;
		this.pdfInglese = pdfInglese;
		this.segretario = segretario;
	}


	public int getCodice() {
		return codice;
	}


	public void setCodice(int codice) {
		this.codice = codice;
	}


	public String getTitolo() {
		return titolo;
	}


	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}


	public String getImg() {
		return img;
	}


	public void setImg(String img) {
		this.img = img;
	}


	public Date getDatascadenza() {
		return datascadenza;
	}


	public void setDatascadenza(Date datascadenza) {
		this.datascadenza = datascadenza;
	}


	public String getPdfIta() {
		return pdfIta;
	}


	public void setPdfIta(String pdfIta) {
		this.pdfIta = pdfIta;
	}


	public String getPdfInglese() {
		return pdfInglese;
	}


	public void setPdfInglese(String pdfInglese) {
		this.pdfInglese = pdfInglese;
	}


	public String getSegretario() {
		return segretario;
	}


	public void setSegretario(String segretario) {
		this.segretario = segretario;
	}
		
}
