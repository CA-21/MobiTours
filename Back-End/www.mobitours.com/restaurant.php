<?php
	$cur_page="Point Of Interest";
	include_once("headerz.php");
	include_once("menu.php");
        include_once("checkUser.php");
?>

<div id="datazone">
        
<?php

$pointofinterestid=isset($_POST['pointofinterestid']) ? $_POST['pointofinterestid'] :'';
$idcity=isset($_POST['idcity']) ? $_POST['idcity'] :'';
$title=isset($_POST['title']) ? $_POST['title'] : '';
$type=isset($_POST['type']) ? $_POST['type'] :'';
$url=isset($_POST['url']) ? $_POST['url'] :'';
$classification=isset($_POST['$classification']) ? $_POST['$classification'] : '';
$latitude=isset($_POST['$latitude']) ? $_POST['$latitude'] : '';
$longitude=isset($_POST['$longitude']) ? $_POST['$longitude'] : '';
$description=isset($_POST['$description']) ? $_POST['$description'] : '';
$mealofday=isset($_POST['$mealofday']) ? $_POST['$mealofday'] : '';
$mealprice=isset($_POST['mealprice'])?$_POST['mealprice']:'';
$pictureurl=isset($_POST['$pictureurl'])?$_POST['$pictureurl']:'';

if ($pointofinterestid && $type ) 
{
    
        //verification des donnees pre-existantes dans la table == RESTAURANT
        $rq="select * from restaurant where pointofinterestid='".$pointofinterestid."' and type='".$type."'";
        $result=  mysql_query($rq) or die('erreur de requete');
        $nrows=  mysql_num_rows($result);
        if($nrows==0){
            $rq="INSERT INTO restaurant(`pointofinterestid`,`idcity`,`title`,`type`,`url`,`classification`,`latitude`,`longitude`,`description`,`mealofday`,`mealprice`,`pictureurl`)
                VALUES ('$pointofinterestid','$idcity','$title','$type','$url','$classification','$latitude','$longitude','$description','$mealofday','$mealprice','$pictureurl')";
            $result=  mysql_query($rq);
            echo "Your restaurant have been saved. Thank you !";
            echo "<p><a href=\"restaurant.php\">Retour</a></p><br/>";
        
    }
    
else 
{
	echo "Le POI restaurant existe deja svp !...";
}

}
else 
{
?>

<link href="drawtable.css" rel="stylesheet" type="text/css" >

<form action="restaurant.php" method="post">
   <table align="center" style="width:620px;font-family:trebuchet ms,sans-serif; font-size: 11px" class="CSSTableGenerator">
   <tr><td colspan=2><h2>Restaurant administration Form</h2></td></tr>
   <tr><td>Point Of Interest Id : </td><td><input type="text" name="pointofinterestid" /></td></tr>
   <tr><td>City Id : </td><td><input type="text" name="idcity" /></td></tr>
   <tr><td>Title : </td><td><input type="text" name="title" /></td></tr>
   <tr><td>Type of POI : </td><td>
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
   <tr><td>URL : </td><td><input type="text" name="url" /></td></tr>
   <tr><td>Classification : </td><td><input type="text" name="classification" /></td></tr>
   <tr><td>Latitude : </td><td><input type="text" name="latitude" /></td></tr>
   <tr><td>longitude : </td><td><input type="text" name="longitude" /></td></tr>
   <tr><td>Description : </td><td><textarea name="description" cols="20" rows="7"></textarea></td></tr>
   <tr><td>Meal of Day : </td><td><input type="text" name="mealofday" /></td></tr>
   <tr><td>Meal Price : </td><td><input type="text" name="Meal price" /></td></tr>
   <tr><td>Picture URL : </td><td><input type="text" name="pictureurl" /></td></tr>
   <tr><td colspan="2"><input type="submit" value="Save" /></td></tr>
</table>
</form> 
<h3>Existing restaurants in the database :</h3>
<?php
$sql="select * from restaurant";
$requ=mysql_query($sql) or die (mysql_error());
echo "<table align=\"center\" style=\"width:620px;font-family:trebuchet ms,sans-serif; font-size: 11px\" class=\"CSSTableGenerator\">";
echo "<tr><th>ID</th><th>City ID</th><th>Title</th><th>Type</th><th>URL</th><th>Clasification</th><th>Latitude</th><th>Longitude</th><th>Description</th><th>Meal Of day</th><th>Meal Price</th><th>Picture URL</th></tr>";
while ($valer=mysql_fetch_row($requ)) 
{
    echo "<tr><td>$valer[0]</td><td>$valer[1]</td><td>$valer[2]</td><td>$valer[3]</td><td>$valer[4]</td><td>$valer[5]</td><td>$valer[6]</td><td>$valer[7]</td><td>$valer[8]</td><td>$valer[9]</td><td>$valer[10]</td><td>$valer[11]</td></tr>";
}
echo "</table>";
?>
</div>
<?php
}
include_once("footer.php");
?>