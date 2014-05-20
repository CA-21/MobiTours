<?php
	$cur_page="Gestion des utilisateurs";
	include_once("headerz.php");
	include_once("menu.php");
	//include_once("delform.php"); 
	$encours="edit_user.php";
	$tablename="users";
	$iden="usern";
?>
<?php
	//session_start();
	$tocheck=$_SESSION['usern'];
	$slq="select * from users where usern='".$tocheck."'";
	$lancerq=mysql_query($slq) or die ('Erreur, utilisateur incorrect');
	$resultat=mysql_fetch_assoc($lancerq);
	if($resultat['type-acc'] !== 'admin')
	{
		echo "Desole, vous n'etes pas administrateur de GDGCotation !";
    /* <script language="javascript">
		alert ("Desole, vous n'etes pas administrateur de ChalStock !");
		header('location:webapp1.home.php');
		exit();
	</script> */
		header('Location:webapp1.home.php');
		exit();
	}
?>
<div id="datazone">
<?php
$util=isset($_POST['util']) ? $_POST['util'] : '';
$pwd=isset($_POST['pwd']) ? $_POST['pwd'] : '';
$typeac=isset($_POST['pwd']) ? $_POST['typeac'] :'';

if ($util && $pwd && $typeac) 
{
//Verification des donnees pre-existantes dans la table
$rq="select * from users where usern='".$util."'";
$result=mysql_query($rq) or die('erreur de requete');

$nrow=mysql_num_rows($result);

if ($nrow==0) 
{
	//pas des correspondances alors enregistre les valeurs
	$rq="INSERT INTO users(`usern`,`pswd`,`type-acc`) VALUES ('$util','$pwd','$typeac')";
	$result=mysql_query($rq);
	
	echo "L'utilisateur ".'<b>'.$util.'</b>'." a bel et bien ete cree. Merci !";
?>
	<p><a href="edit_user.php">Retour</a></p><br/>
<?php
}
else 
{
	echo "Utilsateur existe deja ...";
}

}
else 
{
?>
<h2 align="left">Nouvel utilisateur</h2>

<form action="edit_user.php" method="post">
	<table align="center">
    <tr><td>Nom d'utilisateur : </td><td><input type="text" name="util" /></td></tr>
    <tr><td>Mot de passe : </td><td><input type="password" name="pwd" /></td></tr>
    <tr><td>Type d'acces : </td><td><input type="text" name="typeac" /></td></tr>
    <tr><td></td><td><input type="submit" value="Save" /></td></tr>
    </table>
</form>  
<h3>Listes des utilisateurs enregistres</h3>
<?php
$sql="select * from users";
$req=mysql_query($sql) or die ('Erreur requete');
echo "<table class=\"montablo\" align=\"center\" cellspacing=\".2em\" border=\"1em\">";
echo "<tr bgcolor=#000 font-color:#FFF><th>User Name</th><th>Type acces</th></tr>";
while ($valer=mysql_fetch_row($req)) 
{
	echo "<tr bgcolor=#000 font-color:#FFF><td>$valer[0]</td><td>$valer[2]</td></tr>";
}
echo "</table>";
?>
</div>
<?php
}
include_once("footer.php");
?>