<?php
ini_set('display_errors',1);
error_reporting(E_ALL);
$conn = mysql_connect("localhost","root","");
if(!$conn)
{
echo mysql_error();
}
$db = mysql_select_db("mobidatabase",$conn);
if(!$db)
{
echo mysql_error();
} 
$id = $_GET['id'];
$q = "SELECT image,imagetype FROM image where id='$id'";
$r = mysql_query("$q",$conn);
if($r)
{

$row = mysql_fetch_array($r);
$type = "Content-type: ".$row['imagetype'];
header($type);
echo $row['image'];
}
else
{
echo mysql_error();
}

?>
