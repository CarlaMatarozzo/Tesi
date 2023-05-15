package com.example.demo.persistance.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.model.Bando;
import com.example.demo.persistance.DBSource;
import com.example.demo.persistance.dao.PreferitiDAO;



public class PreferitiDAOJDBC implements PreferitiDAO{
	DBSource dbSource;
	
	public PreferitiDAOJDBC(DBSource dbSource) {
		this.dbSource = dbSource;
	}

	@Override
	public boolean savePreferito(String codicefiscale, int codicebando) {
		Connection conn;

		try {
			conn = dbSource.getConnection();
			String query = "insert into preferiti values(?,?);"; 
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, codicebando);
			st.setString(2, codicefiscale);
			st.executeUpdate();
			st.close();						
		} catch (SQLException e) {
			  return false;
		}
		return true;

	}

	@Override
	public List<Bando> getPreferiti(String codicefiscale) {
		List<Bando> preferiti = new ArrayList<Bando>();
		try {
			Connection conn = dbSource.getConnection();
			String query="select * from bando inner join preferiti on (bando.codice = preferiti.codicebando and codicefiscale =?);";
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, codicefiscale);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				Bando b=new Bando();
				b.setCodice(rs.getInt("codice"));
				b.setTitolo(rs.getString("titolo"));
				b.setImg(rs.getString("img"));
				b.setDatascadenza(rs.getDate("datascadenza"));
				b.setPdfIta(rs.getString("pdfIta"));
				b.setPdfInglese(rs.getString("pdfInglese"));
				b.setSegretario(rs.getString("segretario"));
				preferiti.add(b);			
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return preferiti;
	}

	@Override
	public void deletePreferito(String codicefiscale, int codicebando) {
		Connection connection = null;
		try {
			connection = this.dbSource.getConnection();
			String delete = "delete FROM preferiti WHERE codicefiscale=? and codicebando=?;";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setString(1, codicefiscale);
			statement.setInt(2, codicebando);
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}

	@Override
	public boolean existPreferito(String codicefiscale, int codicebando) {
		try {
			Connection conn = dbSource.getConnection();
			String query = "select * from bando inner join preferiti on (preferiti.codicebando=? and preferiti.codicefiscale=?)";
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, codicebando);
			st.setString(2, codicefiscale);
			ResultSet rs = st.executeQuery();
			if(rs.next()==false) {
				return false;
			}else {
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	return false;
	}

}
