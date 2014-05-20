<?php
	$cur_page="Naturel Site";
	include_once("headerz.php");
	include_once("menu.php");
        include_once("checkUser.php");
?>

<div id="datazone">
        
<?php

$idsite=isset($_POST['idsite']) ? $_POST['idsite'] :'';
$idcity=isset($_POST['idcity']) ? $_POST['idcity'] :'';
$title=isset($_POST['title']) ? $_POST['title'] :'';
$type=isset($_POST['type']) ? $_POST['type'] :'';
$area=isset($_POST['area']) ? $_POST['area'] : '';
$latitude=isset($_POST['latitude']) ? $_POST['latitude'] : '';
$longitude=isset($_POST['longitude']) ? $_POST['longitude'] : '';
$sitedesc=isset($_POST['sitedesc']) ? $_POST['sitedesc'] : '';
$attractourist=isset($_POST['attractourist']) ? $_POST['attractourist'] : '';
$largeur=isset($_POST['largeur'])?$_POST['largeur']:'';
$longueur=isset($_POST['longueur']) ? $_POST['longueur'] : '';
$security=isset($_POST['security']) ? $_POST['security'] : '';
$visitorperan=isset($_POST['visitorperan']) ? $_POST['visitorperan'] : '';

if ($idsite) 
{
    //TABLE HOTEL
    //if($type=='hotel'){
        //verification des donnees pre-existantes dans la table == HOTEL
        $rq="select * from sitenaturel where idsite='".$idsite."'";
        $result=  mysql_query($rq) or die('erreur de requete');
        $nrows=  mysql_num_rows($result);
        if($nrows==0){
            $rq="INSERT INTO sitenaturel(`idsite`,`idcity`,`title`,`type`,`area`,`latitude`,`longitude`,`sitedesc`,`attractourist`,`largeur`,`longueur`,`security`,`visitorperan`)
                VALUES ('$idsite','$idcity','$title','$type','$area','$latitude','$longitude','$sitedesc','$attractourist','$largeur','$longueur','$security','$visitorperan')";
            $result=  mysql_query($rq);
            echo "Your natural site have been saved. Thank you !";
            echo "<p><a href=\"sitenaturel.php\">Retour</a></p><br/>";
        //}
    }
    
else 
{
	echo "Le Site Naturel existe deja svp !...";
}

}
else 
{
?>

<link href="drawtable.css" rel="stylesheet" type="text/css" >

<form action="sitenaturel.php" method="post">
   <table align="center" style="width:620px;font-family:trebuchet ms,sans-serif; font-size: 11px" class="CSSTableGenerator">
   <tr><td colspan=2><h2>Site administration Form</h2></td></tr>
   <tr><td>Site ID : </td><td><input type="text" name="idsite" /></td></tr>
   <tr><td>City Id : </td><td><input type="text" name="idcity" /></td></tr>
   <tr><td>Title : </td><td><input type="text" name="title" /></td></tr>
   <tr><td>Type of Site : </td><td>
   <!-- ici le type from tables types -->
   <?php
    
    echo "<select name=\"type\">";
        $query1=mysql_query("select * from types");
        $meslignes1=mysql_numrows($query1);
        for($i=0;$i<$meslignes1;$i++){
            $type=mysql_result($query1, $i, "type");
            echo "<option value=$type>$type</option>";
        }
    echo "</select></td></tr>" ;
 ?>
   <tr><td>Area : </td><td><input type="text" name="area" /></td></tr>
   <tr><td>Latitude : </td><td><input type="text" name="latitude" /></td></tr>
   <tr><td>longitude : </td><td><input type="text" name="longitude" /></td></tr>
   <tr><td>Site Description : </td><td><textarea name="sitedesc" cols="20" rows="7"></textarea></td></tr>
   <tr><td>Attraction : </td><td><input type="text" name="attractourist" /></td></tr>
   <tr><td>Largeur : </td><td><input type="text" name="largeur" /></td></tr>
   <tr><td>Longueur : </td><td><input type="text" name="longueur" /></td></tr>
   <tr><td>Security : </td><td><input type="text" name="security" /></td></tr>
   <tr><td>Visitor/Year : </td><td><input type="text" name="visitorperan" /></td></tr>
   <tr><td colspan="2"><input type="submit" value="Save" /></td></tr>
</table>
</form> 
<h3>Existing natural site in the database :</h3>
<?php
$sql="select * from sitenaturel";
$requ=mysql_query($sql) or die (mysql_error());
echo "<table align=\"center\" style=\"width:620px;font-family:trebuchet ms,sans-serif; font-size: 11px\" class=\"CSSTableGenerator\">";
echo "<tr><th>ID</th><th>Site ID</th><th>City ID</th><th>Title</th><th>Type</th><th>Area</th><th>Description</th><th>Latitude</th><th>Longitude</th><th>Attraction</th><th>Largeur</th><th>Longueur</th><th>Security</th><th>Visitor/Year</th></tr>";
while ($valer=mysql_fetch_row($requ)) 
{
    echo "<tr><td>$valer[0]</td><td>$valer[1]</td><td>$valer[2]</td><td>$valer[3]</td><td>$valer[4]</td><td>$valer[5]</td><td>$valer[6]</td><td>$valer[7]</td><td>$valer[8]</td><td>$valer[9]</td><td>$valer[10]</td><td>$valer[11]</td><td>$valer[12]</td></tr>";
}
echo "</table>";
?>
</div>
<?php
}
include_once("footer.php");
?>