<?php
	$cur_page="Courtier";
	include_once("headerz.php");
	include_once("menu.php");
        include_once("checkUser.php");
?>
<div id="datazone">
<?php
	
$_id=isset($_POST['_id'])? $_POST['_id']:'';
$courtierid=isset($_POST['courtierid']) ? $_POST['courtierid'] : '';
$email=isset($_POST['email']) ? $_POST['email'] :'';
$password=isset($_POST['password']) ? $_POST['password'] : '';
$title=isset($_POST['title']) ? $_POST['title']:'';
$lastname=isset($_POST['lastname']) ? $_POST['lastname'] :'';
$firstname=isset($_POST['firstname']) ? $_POST['firstname'] :'';
$website=isset($_POST['website']) ? $_POST['website'] :'';
$city=isset($_POST['city']) ? $_POST['city'] :'';
$country=isset($_POST['country']) ? $_POST['country'] : '';
$company=isset($_POST['company']) ? $_POST['company']:'';
$phone=isset($_POST['phone']) ? $_POST['phone'] :'';
$biography=isset($_POST['biography']) ? $_POST['biography'] :'';
$specification=isset($_POST['specification']) ? $_POST['specification'] :'';



if ($courtierid && $email) 
{
//Verification des donnees pre-existantes dans la table
$rq="select * from courtier where courtierid='".$courtierid."' && email='".$email."'";
$result=mysql_query($rq) or die('Query error on city table');

$nrow=mysql_num_rows($result);

if ($nrow==0) 
{
	
        //pas des correspondances alors enregistre les valeurs
	$rq="INSERT INTO courtier(`_id`,`courtierid`,`email`,`password`,`title`,`lastname`,`firstname`,`website`,`city`,`country`,`company`,`phone`,`biography`,`specification`) 
             VALUES
             ($_id,$courtierid,'$email','$password','$title','$lastname','$firstname','$website','$city','$country','$company','$phone','$biography','$specification')";
        
	$result=mysql_query($rq);
	
	echo "The broker ".'<b>'.$lastname.' '.$firstname.'</b>'." was registered. Thanks !";
?>
	<p><a href="courtier.php">Back</a></p><br/>
<?php
}
else 
{
	echo "The broker already exist ...";
}

}
else 
{
?>
        
<link href="drawtable.css" rel="stylesheet" type="text/css" >

<form action="courtier.php" method="post">
    <table align="center" style="width:620px;font-family:trebuchet ms,sans-serif; font-size: 11px" class="CSSTableGenerator">
        <tr><td colspan=2><h2>Broker administration Form</h2></td></tr>
        <tr><td>ID : </td><td><input type="text" name="_id" /></td></tr>
        <tr><td>BrokerID : </td><td><input type="text" name="courtierid" /></td></tr>
        <tr><td>E-mail : </td><td><input type="text" name="email" /></td></tr>
        <tr><td>Password : </td><td><input type="password" name="password" /></td></tr>
        <tr><td>Title : </td><td><input type="text" name="title" /></td></tr>
        <tr><td>Last Name : </td><td><input type="text" name="lastname"></td></tr>
        <tr><td>First Name : </td><td><input type="text" name="firstname" /></td></tr>
        <tr><td>Website : </td><td><input type="text" name="website" /></td></tr>
        <tr><td>City : </td><td><input type="text" name="city" /></td></tr>
        <tr><td>Country : </td><td><input type="text" name="country" /></td></tr>
        <tr><td>Company : </td><td><input type="text" name="company" /></td></tr>
        <tr><td>Phone : </td><td><input type="text" name="phone"></td></tr>
        <tr><td>Biography : </td><td><textarea name="biography" cols="20" rows="6" /></textarea></td></tr>
        <tr><td>Specification : </td><td><input type="text" name="specification" /></td></tr>
        <tr><td></td><td><input type="submit" value="Save" /></td></tr>
    </table>
</form>  
<h3>Existing brokers in the database :</h3>
<?php
$sql="select * from courtier";
$requ=mysql_query($sql) or die (mysql_error());
echo "<table align=\"center\" style=\"width:620px;font-family:trebuchet ms,sans-serif; font-size: 11px\" class=\"CSSTableGenerator\">";
echo "<tr><th>ID</th><th>Broker ID</th><th>E-mail</th><th>title</th><th>Name</th><th>website</th><th>From</th><th>Company</th><th>Phone</th></tr>";
while ($valer=mysql_fetch_row($requ)) 
{
    echo "<tr><td>$valer[0]</td><td>$valer[1]</td><td>$valer[2]</td><td>$valer[4]</td><td>$valer[5] $valer[6]</td><td>$valer[7]</td><td>$valer[8]/$valer[9]</td><td>$valer[10]</td><td>$valer[11]</td><td>$valer[12]</td></tr>";
}
echo "</table>";
?>
</div>
<?php
}
include_once("footer.php");
?>