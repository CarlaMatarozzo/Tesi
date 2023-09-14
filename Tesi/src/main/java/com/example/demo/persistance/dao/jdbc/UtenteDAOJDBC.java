package com.example.demo.persistance.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.crypto.bcrypt.BCrypt;

import com.example.demo.model.Notifica;
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
			String query = "insert into utente values(?,?,?,?,?,?,?);";
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, utente.getCodiceFiscale());
			st.setString(2, BCrypt.hashpw(utente.getPassword(), BCrypt.gensalt(12)));
			st.setString(3, utente.getNome());
			st.setString(4, utente.getCognome());
			st.setString(5, utente.getEmail());
			st.setBoolean(6, utente.isDocente());
			st.setInt(7, utente.getNotifica());
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
				utente.setDocente(rs.getBoolean("docente"));
				utente.setNotifica(rs.getInt("notifica"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (utente.getCodiceFiscale() != null)
			return true;
		else
			return false;
	}

	@Override
	public boolean isDocente(String codicefiscale) {
		Utente utente = new Utente();

		try {
			Connection conn = dbSource.getConnection();
			String query = "select * from utente where codicefiscale=? and docente=?";
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, codicefiscale);
			st.setBoolean(2, true);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				utente.setCodiceFiscale(rs.getString("codicefiscale"));
				utente.setPassword(rs.getString("password"));
				utente.setNome(rs.getString("nome"));
				utente.setCognome(rs.getString("cognome"));
				utente.setEmail(rs.getString("email"));
				utente.setDocente(rs.getBoolean("docente"));
				utente.setNotifica(rs.getInt("notifica"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (utente.getCodiceFiscale() != null)
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
				utente.setNotifica(rs.getInt("notifica"));
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
				utente.setNotifica(rs.getInt("notifica"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (utente.getCodiceFiscale() != null)
			return true;
		else
			return false;
	}

	@Override
	public boolean checkPassword(String codicefiscale, String password) {
		String password_hash = null;

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
	public boolean updateWithoutPsw(Utente u) {//////////// to check

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

	@Override
	public String getCodiceFiscale(String email) {
		String cf = null;

		try {
			Connection conn = dbSource.getConnection();
			String query = "select codicefiscale from utente where email=?";
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, email);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				cf = rs.getString("codicefiscale");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cf;
	}

	@Override
	public void setNotifica(String codiceFiscale, int notifica, boolean letto) {
		Connection connection = null;

		try {
			connection = this.dbSource.getConnection();
			String update = "update utente SET notifica= ? WHERE codicefiscale=?";
			PreparedStatement statement = connection.prepareStatement(update);
			if (!letto) {
				statement.setInt(1, notifica + 1);
			}
			if (letto) {
				statement.setInt(1, notifica - 1);
			}
			statement.setString(2, codiceFiscale);
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int getNotifiche(String codiceFiscale) {
		int n = 0;

		try {
			Connection conn = dbSource.getConnection();
			String query = "select notifica from utente where codicefiscale=?";
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, codiceFiscale);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				n = rs.getInt("notifica");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n;
	}

	@Override
	public List<Integer> getIdNotificheDaLeggere(String codiceFiscale) {
		List<Integer> idNotifiche = new ArrayList<>();
		Set<Integer> id = new HashSet<>();
		try {
			Connection conn = dbSource.getConnection();
			String query = "select idnotifica from notifica inner join utente on (notifica.codicefiscale =?) where lettura=?;";
			PreparedStatement st = conn.prepareStatement(query);
			st.setBoolean(2, false);
			st.setString(1, codiceFiscale);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				int i = rs.getInt("idNotifica");
				if (!id.contains(i)) {
					id.add(i);
					idNotifiche.add(i);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return idNotifiche;
	}

	@Override
	public List<Notifica> ottieniNotifiche(String codiceFiscale) {
		List<Notifica> notifiche = new ArrayList<>();
		Set<Integer> idNotificheSet = new HashSet<>(); //
		try {
			Connection conn = dbSource.getConnection();
			String query = "select * from notifica inner join utente on (notifica.codicefiscale =?)";
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, codiceFiscale);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				int idNotifica = rs.getInt("idnotifica");
				if (!idNotificheSet.contains(idNotifica)) {
					Notifica n = new Notifica();
					n.setCodicefiscale(codiceFiscale);
					n.setMessaggio(rs.getString("messaggio"));
					n.setLettura(rs.getBoolean("lettura"));
					n.setIdnotifica(idNotifica);
					notifiche.add(n);
					idNotificheSet.add(idNotifica);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return notifiche;
	}

	@Override
	public List<String> getCognomiDocenti() {
		List<String> cognomi = new ArrayList<>();
		try {
			Connection conn = dbSource.getConnection();
			String query = "select cognome from utente where docente=?";
			PreparedStatement st = conn.prepareStatement(query);
			st.setBoolean(1, true);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				cognomi.add(rs.getString("cognome"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cognomi;
	}

	@Override
	public String getCognome(String codicefiscale) {
		String cognome=null;
		try {
			Connection conn = dbSource.getConnection();
			String query = "select cognome from utente where codicefiscale=?";
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, codicefiscale);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
	            cognome = rs.getString("cognome");
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cognome;
	}

	@Override
	public List<String> getCodiciFiscaliUtenti() {
		List<String> cf = new ArrayList<>();
		try {
			Connection conn = dbSource.getConnection();
			String query = "select codicefiscale from utente where docente=?";
			PreparedStatement st = conn.prepareStatement(query);
			st.setBoolean(1, false);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				cf.add(rs.getString("codicefiscale"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cf;
	}

	@Override
	public String getEmail(String codicefiscale) {
		String email=null;
		try {
			Connection conn = dbSource.getConnection();
			String query = "select email from utente where codicefiscale=?";
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, codicefiscale);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
	            email = rs.getString("email");
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return email;
	}
	
}
