<?php
$usern=$_GET['usern'];
$rktdel=mysql_query("DELETE FROM users WHERE usern='$usern'") or die ('Erreur de requete');

header("Location:edit_user.php");

?>
