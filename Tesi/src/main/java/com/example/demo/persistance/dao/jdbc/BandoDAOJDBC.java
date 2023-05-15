package com.example.demo.persistance.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.security.crypto.bcrypt.BCrypt;

import com.example.demo.model.Bando;
import com.example.demo.persistance.DBSource;
import com.example.demo.persistance.dao.BandoDAO;




public class BandoDAOJDBC implements BandoDAO{

	DBSource dbSource;

	public BandoDAOJDBC(DBSource dbSource) {
		this.dbSource = dbSource;
	}

	@Override
	public void creaBando(Bando bando) {
		Connection conn;
		try {
			conn = dbSource.getConnection();
			String query = "insert into bando values(?,?,?,?,?,?,?);";
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, bando.getCodice());
			st.setString(2, bando.getTitolo());
			st.setString(3, bando.getImg());
			st.setDate(4, bando.getDatascadenza());
			st.setString(5, bando.getPdfIta());
			st.setString(6,bando.getPdfInglese());
			st.setString(7, bando.getSegretario());
			st.executeUpdate();
			
		} catch (SQLException e) {
		e.printStackTrace();
		}
		
	}
	

	@Override
	public boolean eliminaBando(Bando bando) {
	Connection connection = null;
		
		try {
			connection = this.dbSource.getConnection();
			String delete = "delete FROM bando WHERE codice=? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setInt(1, bando.getCodice());
			statement.executeUpdate();		
		} catch (SQLException e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean esisteBando(int codicebando) {
		try {
			Connection conn = dbSource.getConnection();
			String query = "select * from bando where codice=?";
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, codicebando);
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

	@Override
	public ArrayList<Bando> getBandi() {
		ArrayList<Bando> bandi = new ArrayList<Bando>();
		try {
			Connection con = dbSource.getConnection();
			String query = "select * from bando";
			PreparedStatement st = con.prepareStatement(query);
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
				bandi.add(b);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return bandi;

	}

	@Override
	public void setImage(Bando b,String path) {
	Connection connection = null;
		
		try {
			connection = this.dbSource.getConnection();
			String update = "update bando SET img = ? WHERE codice=?";
			PreparedStatement statement = connection.prepareStatement(update);
			
			statement.setString(1,path);			
			statement.setInt(2, b.getCodice());			
			statement.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	@Override
	public Bando ottieniBando (int codicebando) {
		Bando b= new Bando();
		try {
			Connection con = dbSource.getConnection();
			String query = "select * from bando where codice=?";
			PreparedStatement st = con.prepareStatement(query);
			st.setInt(1, codicebando);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {				
				b.setCodice(rs.getInt("codice"));
				b.setTitolo(rs.getString("titolo"));
				b.setImg(rs.getString("img"));
				b.setDatascadenza(rs.getDate("datascadenza"));
				b.setPdfIta(rs.getString("pdfIta"));
				b.setPdfInglese(rs.getString("pdfInglese"));
				b.setSegretario(rs.getString("segretario"));
			}
			return b;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}

