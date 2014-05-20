<?php
	$cur_page="City";
	include_once("headerz.php");
	include_once("menu.php");
        include_once("checkUser.php");
?>
<div id="datazone">
<?php
	
$_id=isset($_POST['_id'])? $_POST['_id']:'';
$idcity=isset($_POST['idcity']) ? $_POST['idcity'] : '';
$idzone=isset($_POST['idzone']) ? $_POST['idzone'] :'';
$namecity=isset($_POST['namecity']) ? $_POST['namecity'] : '';
$desccity=isset($_POST['desccity']) ? $_POST['desccity']:'';
/* $datesaved=date('Y-m-d',isset($_POST['dateSoumission']));  */
$latcity=isset($_POST['latcity']) ? $_POST['latcity'] :'';
$lngcity=isset($_POST['lngcity']) ? $_POST['lngcity'] :'';
$altcity=isset($_POST['altcity']) ? $_POST['altcity'] :'';
$cityurlimage=isset($_POST['cityurlimage'])?$_POST['cityurlimage']:'';


if ($idcity && $namecity) 
{
//Verification des donnees pre-existantes dans la table
$rq="select * from city where idcity='".$idcity."' && namecity='".$namecity."'";
$result=mysql_query($rq) or die('Query error on city table');

$nrow=mysql_num_rows($result);

if ($nrow==0) 
{
	
        //pas des correspondances alors enregistre les valeurs
	$rq="INSERT INTO city(`_id`,`idcity`,`idzone`,`namecity`,`desccity`,`latcity`,`lngcity`,`altcity`.`cityurlimage`) VALUES ($_id,$idcity,$idzone,'$namecity','$desccity','$latcity','$lngcity',$altcity,'$cityurlimage')";
	$result=mysql_query($rq);
	
	echo "The town ".'<b>'.$namecity.'</b>'." was registered. Thanks !";
?>
	<p><a href="city.php">Back</a></p><br/>
<?php
}
else 
{
	echo "The city already exist ...";
}

}
else 
{
?>
        
<link href="drawtable.css" rel="stylesheet" type="text/css" >

<form action="city.php" method="post">
    <table align="center" style="width:620px;font-family:trebuchet ms,sans-serif; font-size: 11px" class="CSSTableGenerator">
        <tr><td colspan=2><h2>City administration Form</h2></td></tr>
        <tr><td>ID : </td><td><input type="text" name="_id" /></td></tr>
        <tr><td>City ID : </td><td><input type="text" name="idcity" /></td></tr>
        <tr><td>Geo Zone ID : </td><td><input type="text" name="idzone" /></td></tr>
        <tr><td>City name : </td><td><input type="text" name="namecity" /></td></tr>
        <tr><td>Description : </td><td><textarea name="desccity" cols="20" rows="6"></textarea></td></tr>
        <tr><td>Latitude : </td><td><input type="text" name="latcity" /></td></tr>
        <tr><td>Longitude : </td><td><input type="text" name="lngcity" /></td></tr>
        <tr><td>Altitude : </td><td><input type="text" name="altcity" /></td></tr>
        <tr><td>Url Image : </td><td><input type="text" name="cityurlimage" /></td></tr>
        <tr><td></td><td><input type="submit" value="Save" /></td></tr>
    </table>
</form>  
<h3>Existing cities in the database :</h3>
<?php
$sql="select * from city";
$requ=mysql_query($sql) or die (mysql_error());
echo "<table align=\"center\" style=\"width:620px;font-family:trebuchet ms,sans-serif; font-size: 11px\" class=\"CSSTableGenerator\">";
echo "<tr><th>ID</th><th>City ID</th><th>Zone ID</th><th>City Name</th><th>Description</th><th>Latitude</th><th>Longitude</th><th>Altitude</th><th>Image URL</th></tr>";
while ($valer=mysql_fetch_row($requ)) 
{
    echo "<tr><td>$valer[0]</td><td>$valer[1]</td><td>$valer[2]</td><td>$valer[3]</td><td>$valer[4]</td><td>$valer[5]</td><td>$valer[6]</td><td>$valer[7]</td><td>$valer[8]</td></tr>";
}
echo "</table>";
?>
</div>
<?php
}
include_once("footer.php");
?>