<?php
	$cur_page="Geo Zone";
	include_once("headerz.php");
	include_once("menu.php");
        include_once("checkUser.php");
?>
<div id="datazone">
<?php

$_id=isset($_POST['_id'])? $_POST['_id']:'';
$zoneGeoId=isset($_POST['zoneGeoId']) ? $_POST['zoneGeoId'] : '';
$nameZoneGeo=isset($_POST['nameZoneGeo']) ? $_POST['nameZoneGeo'] :'';
$descZoneGeo=isset($_POST['descZoneGeo']) ? $_POST['descZoneGeo'] : '';
$etenduZoneGeo=isset($_POST['etenduZoneGeo']) ? $_POST['etenduZoneGeo']:'';
$latzone=isset($_POST['latzone']) ? $_POST['latzone'] :'';
$lngzone=isset($_POST['lngzone']) ? $_POST['lngzone'] :'';
$areazone=isset($_POST['areazone']) ? $_POST['areazone'] :'';


if ($zoneGeoId && $nameZoneGeo) 
{
//Verification des donnees pre-existantes dans la table
$rq="select * from zone_geo where zoneGeoId='".$zoneGeoId."' && nameZoneGeo='".$nameZoneGeo."'";
$result=mysql_query($rq) or die('Query error on zone_geo table');

$nrow=mysql_num_rows($result);

if ($nrow==0) 
{
	
        //pas des correspondances alors enregistre les valeurs
	$rq="INSERT INTO zone_geo(`_id`,`zoneGeoId`,`nameZoneGeo`,`descZoneGeo`,`etenduZoneGeo`,`latzone`,`lngzone`,`areazone`) 
            VALUES 
            ($_id,$zoneGeoId,'$nameZoneGeo','$descZoneGeo','$etenduZoneGeo','$latzone','$lngzone','$areazone')";
	$result=mysql_query($rq);
	
	echo "The Geo zone ".'<b>'.$nameZoneGeo.'</b>'." was registered. Thanks !";
?>
	<p><a href="geozone.php">Back</a></p><br/>
<?php
}
else 
{
	echo "The geo zone already exist ...";
}

}
else 
{
?>
        
<link href="drawtable.css" rel="stylesheet" type="text/css" >
        
<form action="geozone.php" method="post">
    <table align="center" style="width:620px;font-family:trebuchet ms,sans-serif; font-size: 11px" class="CSSTableGenerator">
    <tr><td colspan=2><h2>GeoZone administration Form</h2></td></tr>
    <tr><td>ID : </td><td><input type="text" name="_id" /></td></tr>
    <tr><td>Geo Zone ID : </td><td><input type="text" name="zoneGeoId" /></td></tr>
    <tr><td>Geo Zone name : </td><td><input type="text" name="nameZoneGeo" /></td></tr>
    <tr><td>Description : </td><td><textarea name="descZoneGeo" cols="20" rows="6"></textarea></td></tr>
    <tr><td>Etendu : </td><td><input type="text" name="desccity" /></td></tr>
    <tr><td>Latitude : </td><td><input type="text" name="latzone" /></td></tr>
    <tr><td>Longitude : </td><td><input type="text" name="lngzone" /></td></tr>
    <tr><td>Area : </td><td><input type="text" name="areazone" /></td></tr>
    <tr><td></td><td><input type="submit" value="Save" /></td></tr>
    </table>
</form>  
<h3>Existing Geo Zone in the database :</h3>
<?php
$sql="select * from zone_geo";
$req=mysql_query($sql) or die ('Query Error on table zone_geo');
echo "<table align=\"center\" style=\"width:620px;font-family:trebuchet ms,sans-serif; font-size: 11px\" class=\"CSSTableGenerator\">";
//echo "<table class=\"montablo\" align=\"center\" cellspacing=\".1em\" border=\".0em\">";
echo "<tr><th>ID</th><th>Geo Zone ID</th><th>Geo Zone name</th><th>Description</th><th>Etendu</th><th>Latitude</th><th>Longitude</th><th>Area</th></tr>";
while ($valer=mysql_fetch_row($req)) 
{
	echo "<tr><td>$valer[0]</td><td>$valer[1]</td><td>$valer[2]</td><td height=\"auto\">$valer[3]</td><td>$valer[4]</td><td>$valer[5]</td><td>$valer[6]</td><td>$valer[7]</td></tr>";
}
echo "</table>";
?>
</div>
<?php
}
include_once("footer.php");
?>