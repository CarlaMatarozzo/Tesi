package com.example.demo.persistance.dao;

import java.util.ArrayList;

import com.example.demo.model.Bando;

public interface BandoDAO {

	public void creaBando(Bando bando);
	public boolean eliminaBando(Bando bando);
	public boolean esisteBando(int codicebando);
	public ArrayList<Bando> getBandi();
	public void setImage(Bando b,String path);
	public Bando ottieniBando(int codicebando);
}
