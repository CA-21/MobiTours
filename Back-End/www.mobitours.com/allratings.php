<?php
	$cur_page="All ratings";
	include_once("headerz.php");
	include_once("menu.php");
        include_once("checkUser.php");
?>

<div id="datazone">
    
<link href="drawtable.css" rel="stylesheet" type="text/css" >
<h2 align="left">VIEWING ALL RATINGS</h2>

<?php
$sql="select * from rating";
$requ=mysql_query($sql) or die (mysql_error());
echo "<table align=\"center\" style=\"width:620px;font-family:trebuchet ms,sans-serif; font-size: 11px\" class=\"CSSTableGenerator\">";
echo "<tr><td colspan=2><h2>Rates administration Form</h2></td></tr>";
echo "<tr><th>ID</th><th>City ID</th><th>Title</th><th>Type</th><th>URL</th><th>Clasification</th><th>Latitude</th><th>Longitude</th><th>Description</th><th>Specification</th><th>Picture URL</th></tr>";
while ($valer=mysql_fetch_row($requ)) 
{
    echo "<tr><td>$valer[0]</td><td>$valer[1]</td><td>$valer[2]</td><td>$valer[3]</td><td>$valer[4]</td><td>$valer[5]</td><td>$valer[6]</td><td>$valer[7]</td><td>$valer[8]</td><td>$valer[9]</td><td>$valer[10]</td></tr>";
}
echo "</table>";
?>
</div>
<?php
include_once("footer.php");
?>