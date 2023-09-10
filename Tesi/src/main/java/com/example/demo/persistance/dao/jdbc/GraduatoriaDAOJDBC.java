package com.example.demo.persistance.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.demo.model.Graduatoria;
import com.example.demo.persistance.DBManager;
import com.example.demo.persistance.DBSource;
import com.example.demo.persistance.dao.GraduatoriaDAO;

public class GraduatoriaDAOJDBC implements GraduatoriaDAO{
	DBSource dbSource;
	
	public GraduatoriaDAOJDBC(DBSource dbSource) {
		this.dbSource = dbSource;
	}

	@Override
	public void inserisciPunteggio(Graduatoria g) {
		Connection conn;
		try {
			conn=dbSource.getConnection();
			String query="insert into graduatoria values(?,?,?);";
			PreparedStatement st=conn.prepareStatement(query);
			st.setString(1, g.getCodicefiscale());
			st.setInt(2, g.getCodicebando());
			st.setInt(3, g.getPunteggio());
			st.executeUpdate();
			st.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean tuttePartecipazioniCorrette(int codiceBando) {
		Connection conn;
		int x=0;
		try {
			conn=dbSource.getConnection();
			String query="select count(*) from graduatoria where codicebando=?;";
			PreparedStatement st=conn.prepareStatement(query);
			st.setInt(1, codiceBando);
			ResultSet rs=st.executeQuery();
			while(rs.next()) {
				x=rs.getInt(1);
			}
			String titolo=DBManager.getInstance().bandoDAO().ottieniBando(codiceBando).getTitolo();
			if(x==DBManager.getInstance().documentiCaricatiBandoDAO().getNumeroRichieste(codiceBando, titolo)) {
				return true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return false;

		}
		return false;
	}

	@Override
	public boolean partecipazioneCorretta(int codiceBando, String codicefiscale) {
		Connection conn;
		try {
			conn=dbSource.getConnection();
			String query="select * from graduatoria where codicefiscale=? and codicebando=?;";
			PreparedStatement st=conn.prepareStatement(query);
			st.setString(1, codicefiscale);
			st.setInt(2, codiceBando);
			ResultSet rs=st.executeQuery();
			if(rs.next()==false) {
				return false;
			}else {
				return true;
			}
		}
			catch (SQLException e) {
				e.printStackTrace();
			}	
		return false;
		
	}

	@Override
	public void modificaCorrezione(Graduatoria g) {
		Connection conn;
		try {
			conn=dbSource.getConnection();
			String update="update graduatoria SET punteggio=? where codicefiscale=? and codicebando=?;";
			PreparedStatement st=conn.prepareStatement(update);
			st.setInt(1, g.getPunteggio());
			st.setString(2, g.getCodicefiscale());
			st.setInt(3, g.getCodicebando());
			st.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void rimuoviCorrezione(int codicebando, String codicefiscale) {
		Connection conn;
		try {
			conn=dbSource.getConnection();
			String delete="delete FROM graduatoria WHERE codicefiscale=? and codicebando=?;";
			PreparedStatement st=conn.prepareStatement(delete);
			st.setString(1, codicefiscale);
			st.setInt(2, codicebando);
			st.executeUpdate();
			st.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
