package com.example.demo.persistance.dao;

import java.util.List;

import com.example.demo.model.Bando;
import com.example.demo.model.DocumentiBando;

public interface DocumentiBandoDAO {
	public boolean inserisci(DocumentiBando d);
	public boolean esiste(DocumentiBando d);
	public boolean eliminaDocumentiBando(int codicebando);
	public List<DocumentiBando> getDocumenti(int codicebando);
	public Bando getBando(int codiceBando);
	public int numDocumentiBando(int codiceBando);
}
