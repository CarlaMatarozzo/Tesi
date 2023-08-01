package com.example.demo.persistance.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.model.DocumentiBando;
import com.example.demo.model.DocumentiCaricatiBando;
import com.example.demo.persistance.DBSource;
import com.example.demo.persistance.dao.DocumentiCaricatiBandoDAO;

public class DocumentiCaricatiBandoDAOJDBC implements DocumentiCaricatiBandoDAO {
	DBSource dbSource;

	public DocumentiCaricatiBandoDAOJDBC(DBSource dbSource) {
		this.dbSource = dbSource;
	}

	@Override
	public boolean esisteDocumento(int codicebando, String titolodocumento, String codicefiscale) {
		try {
			Connection conn = dbSource.getConnection();
			String query = "select * from documenticaricatibando where codicebando=? and titolodocumento=? and codicefiscale=?";
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, codicebando);
			st.setString(2, titolodocumento);
			st.setString(3, codicefiscale);
			ResultSet rs = st.executeQuery();
			if (rs.next() == false) {

				return false;
			} else {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}

	@Override
	public void inserisciDocumento(DocumentiCaricatiBando doc) {
		Connection conn;
		try {
			conn = dbSource.getConnection();
			String query = "insert into documenticaricatibando values(?,?,?,?);";
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, doc.getCodicebando());
			st.setString(2, doc.getTitolodocumento());
			st.setString(3, doc.getCodicefiscale());
			st.setString(4, doc.getDocumento());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setDocumento(String documento,int codicebando, String titolodocumento, String codicefiscale) {
		Connection connection = null;

		try {
			connection = this.dbSource.getConnection();
			String update = "update documenticaricatibando SET \"documento\" = ? WHERE codicebando=? and titolodocumento=? and codicefiscale=?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, documento);
			statement.setInt(2, codicebando);
			statement.setString(3, titolodocumento);
			statement.setString(4, codicefiscale);
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}

	}

	@Override
	public List<Integer> getBandiCompilati(String codicefiscale) {
		List<Integer> d = new ArrayList<Integer>();
		try {
			Connection conn = dbSource.getConnection();
			String query = "select codicebando from documenticaricatibando where codicefiscale=?";
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, codicefiscale);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				int x=rs.getInt("codicebando");
				if(!d.contains(x)) {
					d.add(x);
				}
			}
			return d;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return d;
	}

	@Override
	public boolean rimuoviBando(int codiceBando, String codiceFiscale) {
		try {
			Connection conn = dbSource.getConnection();
			String delete = "delete FROM documenticaricatibando WHERE codicebando=? and codicefiscale=? ";
			PreparedStatement statement = conn.prepareStatement(delete);
			statement.setInt(1, codiceBando);
			statement.setString(2, codiceFiscale);
			statement.executeUpdate();
		} catch (SQLException e) {
			return false;
		}
		return true;
	}

}
