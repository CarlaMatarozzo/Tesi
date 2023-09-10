package com.example.demo.persistance.dao;

import com.example.demo.model.Graduatoria;

public interface GraduatoriaDAO {
	public void inserisciPunteggio(Graduatoria g);
	public boolean tuttePartecipazioniCorrette(int codiceBando);
	public boolean partecipazioneCorretta(int codiceBando, String codicefiscale);
	public void modificaCorrezione(Graduatoria g);
	public void rimuoviCorrezione(int codicebando, String codicefiscale);
}
