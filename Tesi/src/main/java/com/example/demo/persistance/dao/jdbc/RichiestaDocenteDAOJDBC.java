package com.example.demo.persistance.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.model.DocumentiBando;
import com.example.demo.model.RichiestaDocente;
import com.example.demo.persistance.DBSource;
import com.example.demo.persistance.dao.RichiestaDocenteDAO;

public class RichiestaDocenteDAOJDBC implements RichiestaDocenteDAO{
	DBSource dbSource;

	public RichiestaDocenteDAOJDBC(DBSource dbSource) {
		this.dbSource=dbSource;
	}
	
	@Override
	public boolean aggiungiRichiesta(String nome, String cognome, String codicefiscale, String email) {
		Connection conn;
		try {
			conn = dbSource.getConnection();
			String query = "insert into richiestadocente values(?,?,?,?);";
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, nome);
			st.setString(2, cognome);
			st.setString(3, codicefiscale);
			st.setString(4, email);
			st.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public void rimuoviRichiesta(RichiestaDocente richiesta) {
		try {
			Connection conn = dbSource.getConnection();
			String delete = "delete FROM richiestadocente WHERE nome=? and cognome=? and codicefiscale=? and email=?";
			PreparedStatement statement = conn.prepareStatement(delete);
			statement.setString(1, richiesta.getNome());
			statement.setString(2, richiesta.getCognome());
			statement.setString(3, richiesta.getCodicefiscale());
			statement.setString(4, richiesta.getEmail());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public RichiestaDocente getRichiestaDocente(String codFiscale) {
		RichiestaDocente richiesta = new RichiestaDocente();
		try {
			Connection conn = dbSource.getConnection();
			String query = "select * from richiestadocente where codicefiscale=?";
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, codFiscale);
			ResultSet rs = st.executeQuery();
			System.out.println(codFiscale);
			while (rs.next()) {
				richiesta.setNome(rs.getString("nome"));
				richiesta.setCognome(rs.getString("cognome"));
				richiesta.setCodicefiscale(codFiscale);
				richiesta.setEmail(rs.getString("email"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return richiesta;
	}

}
