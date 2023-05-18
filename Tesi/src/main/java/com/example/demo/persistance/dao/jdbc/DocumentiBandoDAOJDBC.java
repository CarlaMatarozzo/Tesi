package com.example.demo.persistance.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.demo.model.DocumentiBando;
import com.example.demo.persistance.DBSource;
import com.example.demo.persistance.dao.DocumentiBandoDAO;

public class DocumentiBandoDAOJDBC implements DocumentiBandoDAO {
	DBSource dbSource;

	public DocumentiBandoDAOJDBC(DBSource dbSource) {
		this.dbSource = dbSource;
	}

	@Override
	public boolean inserisci(DocumentiBando d) {
		Connection conn;
		try {
			conn = dbSource.getConnection();
			String query = "insert into documentibando values(?,?,?,?,?);";
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, d.getCodicebando());
			st.setString(2, d.getTitolodocumento());
			st.setString(3, d.getFormatodocumento());
			st.setInt(4, d.getMindim());
			st.setInt(5, d.getMaxdim());
			st.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean esiste(DocumentiBando d) {
		try {
			Connection conn = dbSource.getConnection();
			String query = "select * from documentibando where codicebando=? and titolodocumento=?";
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, d.getCodicebando());
			st.setString(2, d.getTitolodocumento());
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
	public boolean eliminaDocumentiBando(int codicebando) {
		try {
			Connection conn = dbSource.getConnection();
			String delete = "delete FROM documentibando WHERE codicebando=? ";
			PreparedStatement statement = conn.prepareStatement(delete);
			statement.setInt(1, codicebando);
			statement.executeUpdate();
		} catch (SQLException e) {
			return false;
		}
		return true;
	}

}
