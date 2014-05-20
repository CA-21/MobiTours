<?php
include ("login.php");

if (isset($_POST) && !empty($_POST['usern']) && !empty($_POST['pswd']) ) {
	extract($_POST);
	//test du password en fonction du user name choisit dans la base des donnees
	$rsq="select pswd from users where usern='".$usern."'";
	$req=mysql_query($rsq) or die ('Erreur de selection'); //execute la requete
	$ok=mysql_fetch_assoc($req); //
	
	if($ok['pswd'] != $pswd) 
	{
	?>
		<script language="JavaScript">
			alert ("The entry user name and/or password are not correct !"); //message d'erreur
			windows.location.replace("index.php"); // on relance la page d'acceuil pour re-entrer les coordonnees de connection
		</script>
	<?php
	}
	else {
	session_start();
	$_SESSION['usern']=$usern;
	//header("location:webapp1.home.php"); //Ouvre la page de bienvenue de l'application 
	header("location:mapindex.php"); //Ouvre la page de bienvenue de l'application 
	}
}
else {
	?>
		<script language="JavaScript">
			alert("All fields are obligatory. please fill !");
			windows.location.replace("index.php");
		</script>
	<?php
	//on ferme notre 1er bloc if
}
?>
	