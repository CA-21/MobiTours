<?php
	$cur_page="All comments";
	include_once("headerz.php");
	include_once("menu.php");
        include_once("checkUser.php");
?>

<div id="datazone">
    
<link href="drawtable.css" rel="stylesheet" type="text/css" >

<?php
$sql="select * from comments";
$requ=mysql_query($sql) or die (mysql_error());
echo "<table align=\"center\" style=\"width:620px;font-family:trebuchet ms,sans-serif; font-size: 11px\" class=\"CSSTableGenerator\">";
echo "<tr><td colspan=2><h2>Comment administration Form</h2></td></tr>";
echo "<tr><th>ID</th><th>Comment ID</th><th>User ID</th><th>Point Of Interest ID</th><th>Site ID</th><th>Comments</th><th>Date</th><th>Unique Identifier</th></tr>";
while ($valer=mysql_fetch_row($requ)) 
{
    echo "<tr><td>$valer[0]</td><td>$valer[1]</td><td>$valer[2]</td><td>$valer[3]</td><td>$valer[4]</td><td>$valer[5]</td><td>$valer[6]</td><td>$valer[7]</td></tr>";
}
echo "</table>";
?>
</div>
<?php
include_once("footer.php");
?>