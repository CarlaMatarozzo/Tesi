function sendEmail(nome, email, oggetto, messaggio) {
	Email.send({
		//	SecureToken:"e32f3415-e014-4e08-92eb-7c8c4fe6e797",
		Host: "smtp.gmail.com",
		Username: "carlaferronirc@gmail.com",
		Password: "gjujxsavkunarojv",
		From: email,
		To: 'carlaferronirc@gmail.com',
		Subject: oggetto,
		Body: messaggio + "\n email inviata da " + nome,
	}).then(
		message => alert("mail sent successfully")
	);
}