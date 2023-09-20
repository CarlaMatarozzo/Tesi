<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>

<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
    content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta http-equiv="X-UA-Compatible" content="ie=edge">

<!-- bootstrap -->
<link rel="stylesheet"
    href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">

<script
    src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>

<script
    src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="./js/assistenza.js"></script>
<link rel="stylesheet" href="css/creabando.css" type="text/css">

<title>TESI!</title>

</head>
<body>
    <jsp:include page="Navbar.jsp"></jsp:include>
    <h1 style="text-align: center; text-shadow: 2px 2px 4px black;">Bandi In Corso</h1>
    <h4>Bandi In Corso è un servizio che permette a coloro i quali vogliono intraprendere un dottorato o qualcosa di simile di presentare online le domande di partecipazione ai bandi. Il portale mira a semplificare l'accesso alle informazioni e la partecipazione ai bandi in maniera tale che chi è interessato tramite la piattaforma possono presentare le domande online.</h4>
    <h2>Come funziona l'adesione ad un bando</h2>
    <h4>Puoi seguire la procedura del portale Bandi In Corso, dove è possibile trovare il bando che fa per te e compilare i dati necessari per l'adesione.</h4>
    <div class="row catalogo_bandi_home">
        <div class="col">
            <img src="image\ComeFunziona1.jpg" width="320" height="240">
            <h4 style="margin-left:15px;">Crea il tuo profilo</h4>
        </div>
        <div class="col">
            <img src="image\ComeFunziona2.jpg" width="320" height="240">
            <h4>Cerca il bando che ti interessa</h4>
        </div>
        <div class="col">
            <img src="image\ComeFunziona3.jpg" width="320" height="240">
            <h4>Compila la domanda inserendo</h4>
            <h4>gli allegati ed i dati richiesti</h4>
        </div>
        <div class="col">
            <img src="image\ComeFunziona4.jpg" width="320" height="240">
            <h4>Attendi la graduatoria, ti arriverà un'email</h4>
        </div>
    </div>
    </body>
</html>