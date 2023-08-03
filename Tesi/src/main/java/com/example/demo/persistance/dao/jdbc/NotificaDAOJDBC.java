package com.example.demo.persistance.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.demo.persistance.DBManager;
import com.example.demo.persistance.DBSource;
import com.example.demo.persistance.dao.NotificaDAO;

public class NotificaDAOJDBC implements NotificaDAO {
	DBSource dbSource;
	
	public NotificaDAOJDBC(DBSource dbSource) {
		this.dbSource = dbSource;
	}

	@Override
	public int ultimoIdNotifica() {
		Connection conn;
		int x=0;
		try {
			conn=dbSource.getConnection();
			String query="select MAX(idnotifica) from notifica;";
			PreparedStatement st = conn.prepareStatement(query);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
			    x = rs.getInt(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return x;
	}
	
	@Override
	public boolean nuovaNotifica(String codicefiscale, String messaggio, Boolean lettura) {
		Connection conn;
		int x=ultimoIdNotifica();
		try {
			conn = dbSource.getConnection();
			String query = "insert into notifica values(?,?,?,?);";
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, codicefiscale);
			st.setString(2, messaggio);
			st.setBoolean(3, lettura);
			st.setInt(4, x+1);
			st.executeUpdate();
			int notifica = DBManager.getInstance().utenteDAO().getNotifiche(codicefiscale);
			DBManager.getInstance().utenteDAO().setNotifica(codicefiscale, notifica, false);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void messaggioLetto(int codiceNotifica) {
		Connection connection = null;

		try {
			connection = this.dbSource.getConnection();
			String update = "update notifica SET lettura = ? WHERE idnotifica=?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setBoolean(1, true);
			statement.setInt(2, codiceNotifica);
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}

	}

	@Override
	public int getCodiceNotifica(String codicefiscale, String messaggio) {
		int d = 0;
		try {
			Connection conn = dbSource.getConnection();
			String query = "select idnotifica from notifica where codicefiscale=? and messaggio=?";
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, codicefiscale);
			st.setString(2, messaggio);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				d = rs.getInt("idnotifica");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return d;
	}

}
