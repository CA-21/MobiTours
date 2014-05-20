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

<?php

// include_once("checkUser.php");

//ini_set('display_errors',1);
//error_reporting(E_ALL);
//$conn = mysql_connect("localhost","root","");
//if(!$conn)
//{
//echo mysql_error();
//}
//$db = mysql_select_db("mobidatabase",$conn);
//if(!$db)
//{
//echo mysql_error();
//} 


$pointofinterestid = $_POST['pointofinterestid'];
$name = $_POST['name'];
$url = $_POST['url'];
$image = addslashes (file_get_contents($_FILES['image']['tmp_name']));
$t_image = getimagesize($_FILES['image']['tmp_name']);//to know about image type etc

$imagetype = $t_image['mime'];


$q ="INSERT INTO image(`pointofinterestid`,`name`,`url`,`image`,`imagetype`) 
                      VALUES('$pointofinterestid','$name','$url','$image','$imagetype')";

$r = mysql_query($q);
if($r)
{
echo "Image Informations stored successfully";
}
else
{
echo mysql_error();
}


?>