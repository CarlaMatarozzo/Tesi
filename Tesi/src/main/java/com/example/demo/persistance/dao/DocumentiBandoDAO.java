package com.example.demo.persistance.dao;

import com.example.demo.model.DocumentiBando;

public interface DocumentiBandoDAO {
	public boolean inserisci(DocumentiBando d);
	public boolean esiste(DocumentiBando d);
	public boolean eliminaDocumentiBando(int codicebando);
}
