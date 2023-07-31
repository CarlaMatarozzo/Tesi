package com.example.demo.persistance.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
	public void setDocumento(int codicebando, String titolodocumento, String codicefiscale) {
		Connection connection = null;

		try {
			connection = this.dbSource.getConnection();
			String update = "update documenticaricatibando SET \"documento\" = ? WHERE codicebando=?, titolodocumento=? and codicefiscale=?";
			PreparedStatement statement = connection.prepareStatement(update);

			statement.setInt(1, codicebando);
			statement.setString(2, titolodocumento);
			statement.setString(3, codicefiscale);
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}

	}

}
