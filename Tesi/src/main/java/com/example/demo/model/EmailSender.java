package com.example.demo.model;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.Properties;

public class EmailSender {
	private static EmailSender instance = null;
	private static String Server = "smtp.gmail.com";
	private static String Porta = "587";
	private static String myEmail = "bandidiconcorso@gmail.com";
	private static String myPass = "higqqdrdzinodrvu";
	private String anteprima = "";
	private String titolo = "";

	public static EmailSender getInstance() {
		if (instance == null) {
			instance = new EmailSender();
		}
		return instance;
	}

	private EmailSender() {
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		String tag1 = "<h1>";
		String tag2 = "</h1><br>";
		this.titolo = tag1 + titolo + tag2;
	}

	public String getAnteprima() {
		return anteprima;
	}

	public void setAnteprima(String anteprima) {
		this.anteprima = anteprima;
	}

	public String ResetPassword(String emailUtente) throws Exception {

		String nuovaPassword = generaPassword();
		Properties properties = new Properties();

		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", Server);
		properties.put("mail.smtp.port", Porta);

		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(myEmail, myPass);
			}
		});

		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(myEmail));
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(emailUtente));
		message.setSubject("BandiDiConcorso: Reset Password");
		message.setContent(creaEmailResetpassword(nuovaPassword), "text/html");
		Transport.send(message);

		return nuovaPassword;

	}

	public void invioEmailAssistenza(String mittente, String oggetto, String messaggio) throws Exception {

		Properties properties = new Properties();

		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", Server);
		properties.put("mail.smtp.port", Porta);

		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(myEmail, myPass);
			}
		});

		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(myEmail));
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(mittente));
		message.setSubject(oggetto);
		message.setContent(creaEmailAssistenza(mittente,messaggio), "text/html");
		Transport.send(message);
	}

	public void emailRicevuta(String mittente) throws Exception {

		Properties properties = new Properties();

		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", Server);
		properties.put("mail.smtp.port", Porta);

		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(myEmail, myPass);
			}
		});

		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(myEmail));
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(mittente));
		message.setSubject("Richiesta Assistenza");
		message.setContent(creaEmailRicevuta(mittente), "text/html");
		Transport.send(message);
	}

	public void invioEmailRegistrazioneDoc(String cognome, String email, String codiceFiscale) throws Exception {

		Properties properties = new Properties();

		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", Server);
		properties.put("mail.smtp.port", Porta);

		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(myEmail, myPass);
			}
		});

		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(myEmail));
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
		message.setSubject("Conferma registrazione docente "+cognome);
		message.setContent(creaEmailRegistrazioneDocente(cognome,codiceFiscale), "text/html");
		Transport.send(message);
	}

	private String generaPassword() {

		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";

		StringBuilder sb = new StringBuilder(20);

		for (int i = 0; i < 8; i++) {
			int index = (int) (AlphaNumericString.length() * Math.random());
			sb.append(AlphaNumericString.charAt(index));
		}

		return sb.toString();

	}

	private String creaEmailResetpassword(String password) {
	    String corpoEmail = "<!DOCTYPE html>" +
	            "<html lang=\"en\">" +
	            "<head>" +
	            "  <meta charset=\"utf-8\">" +
	            "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">" +
	            "</head>" +
	            "<body>" +
	            "<h4> Gentile utente,<br> Ecco la tua nuova password:"+password+
	            "</h4>" +
	            "</div>" +
	            "</body>" +
	            "</html>";

	    return corpoEmail;
	}


	private String creaEmailRicevuta(String mittente) {
		String corpoEmail = "<!DOCTYPE html>" +
	            "<html lang=\"en\">" +
	            "<head>" +
	            "  <meta charset=\"utf-8\">" +
	            "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">" +
	            "</head>" +
	            "<body>" +
	            "<h4> Gentile "+mittente+
	            "Bandi di Concorso la ringrazia per averci contattato, <br>"+
	            "Le risponderemo il prima possibile, <br>"+
	            "Distinti saluti </h4>"+
	            "</div>" +
	            "</body>" +
	            "</html>";

		return corpoEmail;
	}

	private String creaEmailAssistenza(String mittente, String contenuto) {
		String corpoEmail = "<!DOCTYPE html>" +
	            "<html lang=\"en\">" +
	            "<head>" +
	            "  <meta charset=\"utf-8\">" +
	            "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">" +
	            "</head>" +
	            "<body>" +
	            "<h4> Richiesta assistenza <br> Mittente: "+mittente+
	            "<br>"+contenuto+
	            "</h4>" +
	            "</div>" +
	            "</body>" +
	            "</html>";
	    return corpoEmail;
	}


	public String creaEmailRegistrazioneDocente(String cognome, String codiceFiscale) {
		String corpoEmail = "<!DOCTYPE html>\r\n" +
				 "<html lang=\"en\">" +
		            "<head>" +
		            "  <meta charset=\"utf-8\">" +
		            "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">" +
		            "</head>" +
		            "<body>" +
		            "<h4> Gentile prof/prof.ssa " + cognome + "<br> Le credenziali per accedere al suo profilo sono: <br>" +
		            "Codice Fiscale: "+ codiceFiscale+ 
		            "<br> Password: Docente1. <br> Le ricordiamo di cambiare al più presto la password." +
		            "</h4>" +
		            "</div>" +
		            "</body>" +
		            "</html>";
		return corpoEmail;
	}
	

}