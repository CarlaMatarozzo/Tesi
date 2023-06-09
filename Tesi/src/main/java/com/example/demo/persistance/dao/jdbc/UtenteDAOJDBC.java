package com.example.demo.persistance.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCrypt;

import com.example.demo.model.Utente;
import com.example.demo.persistance.DBSource;

import com.example.demo.persistance.dao.UtenteDAO;

//import org.springframework.security.crypto.bcrypt.BCrypt;


public class UtenteDAOJDBC implements UtenteDAO {
	DBSource dbSource;

	public UtenteDAOJDBC(DBSource dbSource) {
		this.dbSource = dbSource;
	}

	@Override
	public boolean save(Utente utente) {
		Connection conn;
		try {
			conn = dbSource.getConnection();
			String query = "insert into utente values(?,?,?,?,?,?);";
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, utente.getCodiceFiscale());
			st.setString(2, BCrypt.hashpw(utente.getPassword(), BCrypt.gensalt(12)));
			st.setString(3, utente.getNome());
			st.setString(4, utente.getCognome());
			st.setString(5, utente.getEmail());
			st.setBoolean(6, utente.isDocente());

			st.executeUpdate();
			
		} catch (SQLException e) {
			  return false;
		}
		
		return true;
	}

	@Override
	public boolean existsUser(String codicefiscale) {
		Utente utente = new Utente();
			
			try {
				Connection conn = dbSource.getConnection();
				String query = "select * from utente where codicefiscale=?";
				PreparedStatement st = conn.prepareStatement(query);
				st.setString(1, codicefiscale);
				ResultSet rs = st.executeQuery();
				while (rs.next()) {
					utente.setCodiceFiscale(rs.getString("codicefiscale"));
					utente.setPassword(rs.getString("password"));
					utente.setNome(rs.getString("nome"));
					utente.setCognome(rs.getString("cognome"));
					utente.setEmail(rs.getString("email"));
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		
			if (utente.getCodiceFiscale()!=null)
				return true;
			else
				return false;
	}

	
	@Override
	public Utente findByPrimaryKey(String codicefiscale) {
		Utente utente = new Utente();
			
			try {
				Connection conn = dbSource.getConnection();
				String query = "select * from utente where codicefiscale=?";
				PreparedStatement st = conn.prepareStatement(query);
				st.setString(1, codicefiscale);
				ResultSet rs = st.executeQuery();
				while (rs.next()) {
					utente.setCodiceFiscale(rs.getString("codicefiscale"));
					utente.setPassword(rs.getString("password"));
					utente.setNome(rs.getString("nome"));
					utente.setCognome(rs.getString("cognome"));
					utente.setEmail(rs.getString("email"));
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		return utente;
	}

	@Override
	public boolean existsUserEmail(String email) {
		Utente utente = new Utente();
			
			try {
				Connection conn = dbSource.getConnection();
				String query = "select * from utente where email=?";
				PreparedStatement st = conn.prepareStatement(query);
				st.setString(1, email);
				ResultSet rs = st.executeQuery();
				while (rs.next()) {
					utente.setCodiceFiscale(rs.getString("codicefiscale"));
					utente.setPassword(rs.getString("password"));
					utente.setNome(rs.getString("nome"));
					utente.setCognome(rs.getString("cognome"));
					utente.setEmail(rs.getString("email"));
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		
			if (utente.getCodiceFiscale()!=null)
				return true;
			else
				return false;
	}
	
	
	@Override
	public boolean checkPassword(String codicefiscale, String password) {
			String password_hash=null;
			
			try {
				Connection conn = dbSource.getConnection();
				String query = "select password from utente where codicefiscale=?";
				PreparedStatement st = conn.prepareStatement(query);
				st.setString(1, codicefiscale);
				ResultSet rs = st.executeQuery();
				if (rs.next()) {
					password_hash = rs.getString("password");
				}
				st.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}	
			return BCrypt.checkpw(password, password_hash);
		}
			

	@Override
	public void setPassword(String codicefiscale, String password) {
		
		Connection connection = null;
		
		try {
			connection = this.dbSource.getConnection();
			String update = "update utente SET password = ? WHERE codicefiscale=?";
			PreparedStatement statement = connection.prepareStatement(update);
			
			statement.setString(1, BCrypt.hashpw(password, BCrypt.gensalt(12)));			
			statement.setString(2, codicefiscale);			
			statement.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	@Override
	public boolean updateWithoutPsw(Utente u) {////////////to check
		
		Connection connection = null;
		
		try {
			connection = this.dbSource.getConnection();
			String update = "update utente SET email = ? WHERE codicefiscale= ?";
			PreparedStatement statement = connection.prepareStatement(update);						
			statement.setString(1, u.getEmail());
			statement.setString(2, u.getCodiceFiscale());
			statement.executeUpdate();
			return true;
		} catch (SQLException e) {
			return false;
		} 
		
	}
	@Override
	public boolean updateUtente(Utente u) {
		
		Connection connection = null;
		
		try {
			connection = this.dbSource.getConnection();
			String update = "update utente SET email = ?, password = ? WHERE codicefiscale=?";
			PreparedStatement statement = connection.prepareStatement(update);	
			statement.setString(1, u.getEmail());					
			statement.setString(2, BCrypt.hashpw(u.getPassword(), BCrypt.gensalt(12)));	
			statement.setString(3, u.getCodiceFiscale());
			statement.executeUpdate();
			return true;
			
		} catch (SQLException e) {
			return false;
		} 			
	}

}
