<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="description" content="MobiTours" />
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
        <meta http-equiv="Content-Style-Type" content="text/css" />
        <link href="dessiner.css" rel="stylesheet" type="text/css" />
        <title>MobiTours - <?php echo $cur_page; ?></title>
        <?php include_once("login.php"); ?>
        <!-- Script pour suppression des valeurs -->
        <script language="javascript" type="text/javascript">
			function jesupprime(usern)
			{
				if(confirm("Do you realy want to delete current values ?"))
				{
					document.location="delform.php?$usern=" + usern;
				}
			}
		</script>
    </head>
    <body>
	<div id="header">
    <!-- Insertion des valeur du logo et/ou en-tete de page -->
    	<div id="logo">
        	<table align="left" cellspacing="2" height="auto">
        		<tr><td><h1><p><img src="images/logom.png" alt="logomobitours" />  MobiTours</p></h1></td></tr>
            </table>
        </div>
        <div id="ki-e-la">
        	<?php
				//On lance la session
				session_start();
				//test de la variable session
				if(empty($_SESSION['usern'])) {
					header("location:index.php");
					exit();
				}
				?>
    <p align="center"><?php echo "Logged as ".'<b>'.$_SESSION['usern'].'</b>'; ?> &nbsp;&nbsp;| &nbsp;&nbsp;<a href="index.php">Disconnect</a></p>        
        </div>
        <div class="clear"></div> <!-- Contenu vide pour laisser un peu d'espace entre blocs -->
        <div id="conteneur"> <!-- Conteneur de page -->
        	
            