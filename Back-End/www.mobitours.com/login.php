<?php
// Declaration des variables de connection a la base des donnees
$server="localhost"; // Le server ou on va se connecter
$conectname="root"; // nom d'access au serveur
$monpass=""; //Le mot de passe
$mabd="mobidatabase"; // Le nom de la base des donnees

// ici on se connecte au serveur avec les parametres de connection ci-haut
$iconect=mysql_connect($server,$conectname,$monpass) or die('Connexion a la base des donnees impossible');
// Selection de la base des donnees 
mysql_select_db($mabd,$iconect) or die ('Impossible de se connecter a la base des donnees');
?>
