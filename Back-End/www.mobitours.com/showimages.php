<?php
	$cur_page="Image management";
	include_once("headerz.php");
	include_once("menu.php");
        include_once("checkUser.php");
?>
<div id="datazone">

    
<link href="drawtable.css" rel="stylesheet" type="text/css" >
<!--<h2 align="left">Images in database</h2>-->

<?php

$q = "SELECT * FROM image";
$r = mysql_query("$q");
if($r)
{
    echo "<table align=\"center\" style=\"width:620px;font-family:trebuchet ms,sans-serif; font-size: 11px\" class=\"CSSTableGenerator\">";
    echo "<tr><td colspan=6><h2>Images in database :</h2></td></tr>";
    
while($row= mysql_fetch_array($r))
{
    
    echo "<tr>";
    //Affichage de l'id
    echo "<td>";
    echo $row['id'];
    echo "</td>";
    //Affichage de Id de la cite ou POI
    echo "<td>";
    echo $row['pointofinterestid'];
    echo "</td>";
    //Affichage du nom de l'image
    echo "<td>";
    echo $row['name'];
    echo "</td>";
    //Affichage de l'url
    echo "<td>";
    echo $row['url'];
    echo "</td>";
    //Affichage de l'image
    echo "<td>";
    echo "<img src=getimages.php?id=".$row['id']." width=200 height=100/>";
    echo "</td>";
    //Suite de l'affichage du tableau
    //Affichage de l'url
    echo "<td>";
    echo $row['imagetype'];
    echo "</td>";
    //Ferme la ligne
    echo "</tr>";
}
echo "</table>";
}
else
{
    echo mysql_error();
}
?>
</div>
<?php
    include_once("footer.php");
?>