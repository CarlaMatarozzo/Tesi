package com.example.demo.persistance.dao;

import java.util.List;

import com.example.demo.model.Bando;


public interface PreferitiDAO {
	public boolean savePreferito(String codicefiscale, int codicebando);  
	public List<Bando> getPreferiti(String codicefiscale);  
	public void deletePreferito(String codicefiscale, int codicebando);
	public boolean existPreferito(String codicefiscale, int codicebando);
}
