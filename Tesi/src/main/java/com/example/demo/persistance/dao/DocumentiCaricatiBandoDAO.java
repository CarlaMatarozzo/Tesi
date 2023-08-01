package com.example.demo.persistance.dao;

import java.util.List;

import com.example.demo.model.DocumentiCaricatiBando;

public interface DocumentiCaricatiBandoDAO {
	public boolean esisteDocumento(int codicebando,String titolodocumento, String codicefiscale);
	public void inserisciDocumento(DocumentiCaricatiBando doc);
	public void setDocumento(String documento, int codicebando,String titolodocumento, String codicefiscale);

	public List<Integer> getBandiCompilati(String codicefiscale);
	public boolean rimuoviBando(int codiceBando, String codiceFiscale);
}
