package com.example.demo.persistance.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.model.Bando;
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
			if (rs.next()) {
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

	@Override
	public List<DocumentiBando> getDocumenti(int codicebando) {
			List<DocumentiBando> doc=new ArrayList<DocumentiBando>();
		try {
			Connection conn = dbSource.getConnection();
			String query = "select * from documentibando where codicebando=?";
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, codicebando);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				DocumentiBando db=new DocumentiBando();
				db.setCodicebando(codicebando);
				db.setTitolodocumento(rs.getString("titolodocumento"));
				db.setFormatodocumento(rs.getString("formatodocumento"));
				db.setMaxdim(rs.getInt("maxdim"));
				db.setMindim(rs.getInt("mindim"));
				doc.add(db);			
				}	

			}catch (SQLException e) {
				e.printStackTrace();
			}
		return doc;
	}

	@Override
	public Bando getBando(int codiceBando) {
		Bando b = new Bando();
		try {
			Connection conn = dbSource.getConnection();
			String query="select * from bando inner join documentiBando on (bando.codice =?);";
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, codiceBando);
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return b;
	
	}
}
		
