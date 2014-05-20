<?php

	require_once __DIR__ .'/db_config.php';

	@mysql_pconnect($BD_serveur, $BD_utilisateur, $BD_motDePasse)
			or die("Server Error : Access refusé");
	@mysql_select_db($BD_base)
			or die("Server Error : DB Select:". mysql_error()); 

?>
