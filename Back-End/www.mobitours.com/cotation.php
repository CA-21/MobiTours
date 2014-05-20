<?php
	$cur_page="Nouveau produit";
	include_once("headerz.php");
	include_once("menu.php");
?>

<div id="datazone">
<?php
$idjuge=isset($_POST['idjuge']) ? $_POST['idjuge'] :'';
$intApp=isset($_POST['intApp']) ? $_POST['intApp'] :'';
$facility_usage=isset($_POST['$facility_usage']) ? $_POST['$facility_usage'] : '';
$val_ajout_user=isset($_POST['$val_ajout_user']) ? $_POST['$val_ajout_user'] :'';
$originalty_creativity=isset($_POST['$originalty_creativity']) ? $_POST['$originalty_creativity'] :'';
$innovation=isset($_POST['$innovation']) ? $_POST['$innovation'] : '';
$platform_usage=isset($_POST['$platform_usage']) ? $_POST['$platform_usage'] : '';
$branding=isset($_POST['$branding']) ? $_POST['$branding'] : '';
$necessity=isset($_POST['$necessity']) ? $_POST['$necessity'] : '';


if ($idjuge && $intApp ) //&& $facility_usage && $val_ajout_user && $val_ajout_user && $innovation && $platform_usage && $branding && $necessity
{
//Verification des donnees pre-existantes dans la table
$rq="select * from cotation where idJuge='".$idjuge."' and intApp='".$intApp."'";
$result=mysql_query($rq) or die('erreur de requete');

$nrow=mysql_num_rows($result);

if ($nrow==0) 
{
	//pas des correspondances alors enregistre les valeurs
	$rq="INSERT INTO cotation(`idJuge`,`intApp`,`facility_usage`,`val_ajout_user`,`originalty_creativity`,`innovation`,`platform_usage`,`branding`,`necessity`) 
        VALUES ($idjuge,'$intApp','$facility_usage','$val_ajout_user','$originalty_creativity','$innovation','$platform_usage','$branding','$necessity')";
	$result=mysql_query($rq);
	
	echo "Votre cote  a bel et bien ete enregistre. Merci !";
?>
	<p><a href="cotation.php">Retour</a></p><br/>
<?php
}
else 
{
	echo "La cote pour ce Juge et pour cette application existe deja svp !...";
}

}
else 
{
?>
<h2 align="left">Formulaire de cotation d'application</h2>

<form action="cotation.php" method="post">
<table align="center">
<tr><td>ID du Juge : </td>
<td>
<?php
    
    echo "<select name=\"idjuge\">";
        $query=mysql_query("select * from juge");
        $meslignes=mysql_numrows($query);
        for($i=0;$i<$meslignes;$i++){
            $Resultat=mysql_result($query, $i, "idJuge");
            echo "<option value=$Resultat>$Resultat</option>";
        }

        echo "</select></td></tr>" ;

    echo "<tr><td>Intitule de l'application :</td>";
    echo "<td>";
    echo "<select name=\"intApp\">";
        $query=mysql_query("select * from application");
        $meslignes=mysql_numrows($query);
        for($j=0;$j<$meslignes;$j++){
            $Resultat2=mysql_result($query, $j, "intApp");
            echo "<option value=$Resultat2>$Resultat2</option>";
        }
        echo "</select></td></tr>";
        
 ?>
    
    <!-- Boutons radio pour la facilite d'usage -->
     <tr><td>Facilite d'utilisation :</td>
     <td>
     <td>1</td><td><input type="radio" name="facility_usage" value=1 /></td>
     <td>2</td><td><input type="radio" name="facility_usage" value=2 /></td>
     <td>3</td><td><input type="radio" name="facility_usage" value=3 /></td>
     <td>4</td><td><input type="radio" name="facility_usage" value=4 /></td>
     <td>5</td><td><input type="radio" name="facility_usage" value=5 /></td>
     </td>
     </tr>
    <!-- Idem pour critere Pertinence == valeur ajoute a l'utilisateur -->
     <tr><td>valeur ajoutee a l'utilisateur :</td>
     <td>
     <td>1</td><td><input type="radio" name="val_ajout_user" value="1" /></td>
     <td>2</td><td><input type="radio" name="val_ajout_user" value="2" /></td>
     <td>3</td><td><input type="radio" name="val_ajout_user" value="3" /></td>
     <td>4</td><td><input type="radio" name="val_ajout_user" value="4" /></td>
     <td>5</td><td><input type="radio" name="val_ajout_user" value="5" /></td>
     </td>
     </tr>
    <!-- Idem pour critere Innovation et creativite -->
     <tr><td>Innovation et creativite :</td>
     <td>
     <td>1</td><td><input type="radio" name="originalty_creativity" value="1" /></td>
     <td>2</td><td><input type="radio" name="originalty_creativity" value="2" /></td>
     <td>3</td><td><input type="radio" name="originalty_creativity" value="3" /></td>
     <td>4</td><td><input type="radio" name="originalty_creativity" value="4" /></td>
     <td>5</td><td><input type="radio" name="originalty_creativity" value="5" /></td>
     </td>
     </tr>
    <!-- Idem pour critere Originalite de l'idee et mise en oeuvre -->
     <tr><td>Originalite de l'idee et mise en oeuvre :</td>
     <td>
     <td>1</td><td><input type="radio" name="innovation" value="1" /></td>
     <td>2</td><td><input type="radio" name="innovation" value="2" /></td>
     <td>3</td><td><input type="radio" name="innovation" value="3" /></td>
     <td>4</td><td><input type="radio" name="innovation" value="4" /></td>
     <td>5</td><td><input type="radio" name="innovation" value="5" /></td>
     </td>
     </tr>
    <!-- Idem pour critere Utilisation de la plate-forme materielle -->
     <tr><td>Utilisation de la plate-forme materielle :</td>
     <td>
     <td>1</td><td><input type="radio" name="platform_usage" value="1" /></td>
     <td>2</td><td><input type="radio" name="platform_usage" value="2" /></td>
     <td>3</td><td><input type="radio" name="platform_usage" value="3" /></td>
     <td>4</td><td><input type="radio" name="platform_usage" value="4" /></td>
     <td>5</td><td><input type="radio" name="platform_usage" value="5" /></td>
     </td>
     </tr>
    <!-- Idem pour critere Branding -->
     <tr><td>Branding et design :</td>
     <td>
     <td>1</td><td><input type="radio" name="branding" value="1" /></td>
     <td>2</td><td><input type="radio" name="branding" value="2" /></td>
     <td>3</td><td><input type="radio" name="branding" value="3" /></td>
     <td>4</td><td><input type="radio" name="branding" value="4" /></td>
     <td>5</td><td><input type="radio" name="branding" value="5" /></td>
     </td>
     </tr>
    <!-- Idem pour critere Necessite  -->
     <tr><td>Necessite (resoud un probleme unique) :</td>
     <td>
     <td>1</td><td><input type="radio" name="necessity" value="1" /></td>
     <td>2</td><td><input type="radio" name="necessity" value="2" /></td>
     <td>3</td><td><input type="radio" name="necessity" value="3" /></td>
     <td>4</td><td><input type="radio" name="necessity" value="4" /></td>
     <td>5</td><td><input type="radio" name="necessity" value="5" /></td>
     </td>
     </tr>
     <tr></tr>
     <tr></tr>
     <tr><td></td><td><input type="submit" value="Save" /></td></tr>
</table>
</form> 


</div>
<?php
}
include_once("footer.php");
?>