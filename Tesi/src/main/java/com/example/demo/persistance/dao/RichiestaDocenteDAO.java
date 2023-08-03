package com.example.demo.persistance.dao;

import com.example.demo.model.RichiestaDocente;

public interface RichiestaDocenteDAO {
	public boolean aggiungiRichiesta(String nome, String cognome, String codicefiscale, String email);
	public void rimuoviRichiesta(RichiestaDocente richiesta);
	public RichiestaDocente getRichiestaDocente(String codFiscale);
}
