package com.example.demo.persistance;

import com.example.demo.persistance.dao.BandoDAO;
import com.example.demo.persistance.dao.DocumentiBandoDAO;
import com.example.demo.persistance.dao.DocumentiCaricatiBandoDAO;
import com.example.demo.persistance.dao.NotificaDAO;
import com.example.demo.persistance.dao.PreferitiDAO;
import com.example.demo.persistance.dao.RichiestaDocenteDAO;
import com.example.demo.persistance.dao.UtenteDAO;
import com.example.demo.persistance.dao.jdbc.BandoDAOJDBC;
import com.example.demo.persistance.dao.jdbc.DocumentiBandoDAOJDBC;
import com.example.demo.persistance.dao.jdbc.DocumentiCaricatiBandoDAOJDBC;
import com.example.demo.persistance.dao.jdbc.NotificaDAOJDBC;
import com.example.demo.persistance.dao.jdbc.PreferitiDAOJDBC;
import com.example.demo.persistance.dao.jdbc.RichiestaDocenteDAOJDBC;
import com.example.demo.persistance.dao.jdbc.UtenteDAOJDBC;

public class DBManager {
	private static DBManager instance = null;
	static DBSource dataSource;
	
	static {
		try {
			Class.forName("org.postgresql.Driver");
			dataSource=new DBSource("jdbc:postgresql://localhost:5432/postgres","postgres","postgres");
		} 
		catch (Exception e) {
			System.err.println("PostgresDAOFactory.class: failed to load MySQL JDBC driver\n"+e);
			e.printStackTrace();
		}
	}
	
	public static DBManager getInstance() {
		if (instance == null) {
			instance = new DBManager();
		}
		return instance;
	}
	
	private DBManager() {
	}
	
	public static DBSource getDataSource() {
		return dataSource;
	}
	
	public RichiestaDocenteDAO richiestaDocenteDAO() {
		return new RichiestaDocenteDAOJDBC(dataSource);
	}
	public UtenteDAO utenteDAO() {
		return new UtenteDAOJDBC(dataSource);
	}
	
	public BandoDAO bandoDAO() {
		return new BandoDAOJDBC(dataSource);
	}
	
	public PreferitiDAO preferitiDAO() {
		return new PreferitiDAOJDBC(dataSource);
	}
	
	public DocumentiBandoDAO documentiBandoDAO() {
		return new DocumentiBandoDAOJDBC(dataSource);
	}
	
	public DocumentiCaricatiBandoDAO documentiCaricatiBandoDAO() {
		return new DocumentiCaricatiBandoDAOJDBC(dataSource);
	}
	
	public NotificaDAO notificaDAO() {
		return new NotificaDAOJDBC(dataSource);
	}
}
