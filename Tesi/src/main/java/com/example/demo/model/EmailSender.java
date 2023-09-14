package com.example.demo.model;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import com.example.demo.persistance.DBManager;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
		message.setContent(creaEmailAssistenza(mittente, messaggio), "text/html");
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
		message.setSubject("Conferma registrazione docente " + cognome);
		message.setContent(creaEmailRegistrazioneDocente(cognome, codiceFiscale), "text/html");
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
		String corpoEmail = "<!DOCTYPE html>" + "<html lang=\"en\">" + "<head>" + "  <meta charset=\"utf-8\">"
				+ "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">" + "</head>" + "<body>"
				+ "<h4> Gentile utente,<br> Ecco la tua nuova password:" + password + "</h4>" + "</div>" + "</body>"
				+ "</html>";

		return corpoEmail;
	}

	private String creaEmailRicevuta(String mittente) {
		String corpoEmail = "<!DOCTYPE html>" + "<html lang=\"en\">" + "<head>" + "  <meta charset=\"utf-8\">"
				+ "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">" + "</head>" + "<body>"
				+ "<h4> Gentile " + mittente + "Bandi di Concorso la ringrazia per averci contattato, <br>"
				+ "Le risponderemo il prima possibile, <br>" + "Distinti saluti </h4>" + "</div>" + "</body>"
				+ "</html>";

		return corpoEmail;
	}

	private String creaEmailAssistenza(String mittente, String contenuto) {
		String corpoEmail = "<!DOCTYPE html>" + "<html lang=\"en\">" + "<head>" + "  <meta charset=\"utf-8\">"
				+ "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">" + "</head>" + "<body>"
				+ "<h4> Richiesta assistenza <br> Mittente: " + mittente + "<br>" + contenuto + "</h4>" + "</div>"
				+ "</body>" + "</html>";
		return corpoEmail;
	}

	public String creaEmailRegistrazioneDocente(String cognome, String codiceFiscale) {
		String corpoEmail = "<!DOCTYPE html>\r\n" + "<html lang=\"en\">" + "<head>" + "  <meta charset=\"utf-8\">"
				+ "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">" + "</head>" + "<body>"
				+ "<h4> Gentile prof/prof.ssa " + cognome + "<br> Le credenziali per accedere al suo profilo sono: <br>"
				+ "Codice Fiscale: " + codiceFiscale
				+ "<br> Password: Docente1. <br> Le ricordiamo di cambiare al più presto la password." + "</h4>"
				+ "</div>" + "</body>" + "</html>";
		return corpoEmail;
	}

	public void invioEmailGraduatoria(String mittente, String oggetto, int codicebando) throws Exception {
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

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(myEmail));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(mittente));
			message.setSubject(oggetto);

			// Creazione della parte mista per il messaggio (testo e allegato)
			Multipart multipart = new MimeMultipart();

			// Creazione del corpo del messaggio (testo HTML)
			MimeBodyPart messageBodyPart = new MimeBodyPart();
			String messaggio = "<!DOCTYPE html>\r\n" + "<html lang=\"en\">" + "<head>" + "  <meta charset=\"utf-8\">"
					+ "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">" + "</head>"
					+ "<body>" + "<h4> Gentile utente, " + "<br>"
					+ "Abbiamo corretto la sua candidatura, in allegato la graduatoria." + "<br> Distinti saluti."
					+ "</h4>" + "</div>" + "</body>" + "</html>";
			messageBodyPart.setContent(messaggio, "text/html");
			multipart.addBodyPart(messageBodyPart);

			// Creazione della parte per l'allegato PDF
			MimeBodyPart attachmentPart = new MimeBodyPart();
			List<Graduatoria> grad = DBManager.getInstance().graduatoriaDAO().getPartecipazioniCorrette(codicebando);
			List<String> cf = new ArrayList();
			List<Integer> punteggio = new ArrayList();
			for (int i = 0; i < grad.size(); i++) {
				cf.add(grad.get(i).getCodicefiscale());
				punteggio.add(grad.get(i).getPunteggio());
			}
			DataSource source = new ByteArrayDataSource(generatePdf(codicebando, cf, punteggio), "application/pdf");
			attachmentPart.setDataHandler(new DataHandler(source));
			attachmentPart.setFileName("Graduatoria.pdf"); // Specifica il nome del file PDF
			multipart.addBodyPart(attachmentPart);

			// Imposta la parte mista come contenuto del messaggio
			message.setContent(multipart);

			// Invia l'email
			Transport.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public static byte[] generatePdf(int codicebando, List<String> codiciFiscali, List<Integer> punteggi)
			throws IOException {
		try (PDDocument document = new PDDocument()) {
			// Creazione di una nuova pagina
			PDPage page = new PDPage(PDRectangle.A4);
			document.addPage(page);

			// Inizializzazione del contenuto della pagina
			PDPageContentStream contentStream = new PDPageContentStream(document, page);

			// Impostazione del font e della dimensione del testo
			contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);

			// Aggiunta del titolo alla pagina
			contentStream.beginText();
			PDPageContentStream imageStream = new PDPageContentStream(document, page,
					PDPageContentStream.AppendMode.APPEND, true);

			// Carica e aggiungi l'immagine al content stream dell'immagine
			PDImageXObject image = PDImageXObject.createFromFile("src/main/resources/static/image/Logo.png", document);
			imageStream.drawImage(image, 10, 700, 150, 180);

			imageStream.close(); // Chiudi il content stream dell'immagine

			contentStream.newLineAtOffset(50, 690); // Posizione del titolo sulla pagina
			contentStream.newLine();
			contentStream.showText("Graduatoria Bando " + codicebando);
			contentStream.endText();

			// Creazione della tabella
			int startX = 50;
			int startY = 650;
			int cellWidth = 200;
			int cellHeight = 20;

			// Disegna la prima riga della tabella (intestazioni)
			contentStream.setLineWidth(1f);
			contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10);
			contentStream.beginText();
			contentStream.newLineAtOffset(startX + 5, startY - 15);
			contentStream.showText("Codice Fiscale");
			contentStream.endText();
			contentStream.beginText();
			contentStream.newLineAtOffset(startX + cellWidth - 75, startY - 15);
			contentStream.showText("Punteggio");
			contentStream.endText();

			// Disegna le righe della tabella con i dati
			contentStream.setFont(PDType1Font.HELVETICA, 10);
			for (int i = 0; i < codiciFiscali.size(); i++) {
				startY -= cellHeight;
				contentStream.setLineWidth(1f);
				contentStream.beginText();
				contentStream.newLineAtOffset(startX + 5, startY - 15);
				contentStream.showText(codiciFiscali.get(i));
				contentStream.endText();
				contentStream.beginText();
				contentStream.newLineAtOffset(startX + cellWidth - 75, startY - 15);
				contentStream.showText(Integer.toString(punteggi.get(i)));
				contentStream.endText();
			}

			// Chiusura del contenuto della pagina
			contentStream.close();

			// Creazione di un array di byte per il documento PDF
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			document.save(outputStream);

			return outputStream.toByteArray();
		}
	}

}