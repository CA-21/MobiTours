<?php
/*	$cur_page="";
	include_once("headerz.php");
	include_once("menu.php");
	//include_once("delform.php"); 
*/
?> 
<?php

        $encours="edit_user.php";
	$tablename="users";
	$iden="usern";
	//session_start();
	$tocheck=$_SESSION['usern'];
	$slq="select * from users where usern='".$tocheck."'";
	$lancerq=mysql_query($slq) or die ('Error, incorrect User');
	$resultat=mysql_fetch_assoc($lancerq);
	if($resultat['type-acc'] !== 'admin')
	{
		echo "Sorry, your are not allowed to do this on mobitours !";
    /* <script language="javascript">
		alert ("Desole, vous n'etes pas administrateur de ChalStock !");
		header('location:webapp1.home.php');
		exit();
	</script> */
		header('Location:mapindex.php');
		exit();
	}
?>