<?php
	$cur_page="Type produit";
	include_once("headerz.php");
	include_once("menu.php");
        include_once("checkUser.php");
?>
<div id="datazone">
<?php
$idApp=isset($_POST['idApp']) ? $_POST['idApp'] : '';
$catego=isset($_POST['catego']) ? $_POST['catego'] :'';
$intApp=isset($_POST['intApp']) ? $_POST['intApp'] : '';
$descrApp=isset($_POST['descrApp']) ? $_POST['descrApp']:'';
$dateSoumission=date('Y-m-d',isset($_POST['dateSoumission']));
$souscripteur=isset($_POST['souscripteur']) ? $_POST['souscripteur'] :'';
$organisation=isset($_POST['organisation'])? $_POST['organisation']:'';


if ($idApp && $intApp) 
{
//Verification des donnees pre-existantes dans la table
$rq="select * from application where idApp='".$idApp."' && intApp='".$intApp."'";
$result=mysql_query($rq) or die('erreur de requete');

$nrow=mysql_num_rows($result);

if ($nrow==0) 
{
	
        //pas des correspondances alors enregistre les valeurs
	$rq="INSERT INTO application(`idApp`,`Catego`,`intApp`,`descrApp`,`dateSoumission`,`souscripteur`,`organisation`) VALUES ('$idApp','$catego','$intApp','$descrApp',$dateSoumission,'$souscripteur','$organisation')";
	$result=mysql_query($rq);
	
	echo "L'application ".'<b>'.$intApp.'</b>'." a bel et bien ete enregistre. Merci !";
?>
	<p><a href="application.php">Retour</a></p><br/>
<?php
}
else 
{
	echo "L'application existe deja ...";
}

}
else 
{
?>
<h2 align="left">Enregistrement d'une nouvelle application</h2>

<form action="application.php" method="post">
	<table align="center">
    <tr><td>ID de l'application : </td><td><input type="text" name="idApp" /></td></tr>
    <tr><td>Categorie de l'application :</td>
        <td>
            <select name="catego">
                <option>Application Mobile</option>
                <option>Application Desktop</option>
                <option>Projet Web (Website)</option>
            </select>
        </td>
    </tr>
    <tr><td>Intitule : </td><td><input type="text" name="intApp" /></td></tr>
    <tr><td>Description : </td><td><textarea name="descrApp" cols="20" rows="5"></textarea></td></tr>
    <tr><td>Date de soumission (YYYY-M-D): </td><td><input type="text" name="dateSoumission" /></td></tr>
    <tr><td>Souscripteur : </td><td><input type="text" name="souscripteur" /></td></tr>
    <tr><td>Organisation : </td><td><input type="text" name="organisation" /></td></tr>
    <tr><td></td><td><input type="submit" value="Save" /></td></tr>
    </table>
</form>  
<h3>Liste d'applications existantes dans la base des donnees :</h3>
<?php
$sql="select * from application";
$req=mysql_query($sql) or die ('Erreur requete');
echo "<table class=\"montablo\" align=\"center\" cellspacing=\".1em\" border=\".0em\">";
//`idApp`,`catego`,`intApp`,`descrApp`,`dateSoumission`,`souscripteur`,`organisation`)
echo "<tr><th>ID</th><th>Categorie</th><th>Intitule</th><th>Description</th><th>Souscripteur</th></tr>";
while ($valer=mysql_fetch_row($req)) 
{
	echo "<tr><td>$valer[0]</td><td>$valer[1]</td><td>$valer[2]</td><td>$valer[3]</td><td>$valer[5]</td></tr>";
}
echo "</table>";
?>
</div>
<?php
}
include_once("footer.php");
?>