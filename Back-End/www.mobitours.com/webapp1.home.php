<?php
	$cur_page="Acceuil";
	include_once("headerz.php");
	include_once("menu.php");
?>
<div id="datazone">
     <table border="0" cellspacing="2" cellpadding="2" width="100%">
    	<tr align="center"><td><img src="images/gtuggoma_v2.jpg" width="auto" height="100" /></td></tr>
        <tr align="center"><td><h1>Karibu sana<strong> &nbsp; <?php echo $_SESSION['usern']; ?></strong></h1></td></tr>
        <tr align="center"><td style="color:#F00; text-decoration:blink;">Please select a menu !</td></tr>
     </table>
     <p align="right"><?php $date_du_jour=date('d-m-Y'); echo $date_du_jour.'<br/>'; ?></p>
</div>
<!-- Insertion du pied de page -->
<?php
	include_once("footer.php");
?>