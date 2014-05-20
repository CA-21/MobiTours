<?php
	$cur_page="Image management";
	include_once("headerz.php");
	include_once("menu.php");
        include_once("checkUser.php");
?>
<div id="datazone">

    
<link href="drawtable.css" rel="stylesheet" type="text/css" >
<!--<h2 align="left">New Image</h2>-->
<form enctype="multipart/form-data" action="storeimage.php" method="POST">

    <table align="center" style="width:620px;font-family:trebuchet ms,sans-serif; font-size: 11px" class="CSSTableGenerator">
        <tr><td colspan=2><h2>Image Management</h2></td></tr>
        <tr>
            <td>Point of Interest ID : </td><td><input type=text name="pointofinterestid"></td>
        </tr>
        <tr>
            <td>Image Name : (Optional) </td><td><input type=text name="name"></td>
        </tr>
        <tr>
            <td>URL : (Optional) </td><td><input type=text name="url"></td>
        </tr>
        <tr>
            <td>Image</td><td><input type=file name="image"></td>
        </tr>
        <tr>
            <td colspan="2"><input type=submit name="submit" value="Store Image"></td>
        </tr>
    </table>
</form>

<!--affichage ici-->
</br>
</br>
<h3 align="left">Have a look to all images</h3>
<form action="showimages.php" method="post">
    <table align="center" style="width:100px;font-family:trebuchet ms,sans-serif; font-size: 11px" class="CSSTableGenerator">
        <tr>
         <td><input type="submit" value="View all Images" /></td>
        </tr>
    </table> 
</form>

</div>
<?php
include_once("footer.php");
?>
