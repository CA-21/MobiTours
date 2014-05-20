<?php
// Declaration des variables de connection a la base des donnees
define('DB_USER', "root"); // db user
define('DB_PASSWORD', ""); // db password (mention your db password here)
define('DB_DATABASE', "mobidatabase"); // database name
define('DB_SERVER', "localhost"); // db server

// ici on se connecte au serveur avec les parametres de connection ci-haut
$iconect=mysql_connect(DB_SERVER,DB_USER,DB_PASSWORD) or die('Connexion a la base des donnees impossible');
// Selection de la base des donnees 
mysql_select_db(DB_DATABASE,$iconect) or die ('Impossible de se connecter a la base des donnees');

/*
 * Following code will create a new comment row
 * All comments details are read from HTTP Post Request
 */

// array for JSON response
$response = array();

// check for required fields
if (isset($_POST['pointofinterestid']) && isset($_POST['rating']) && isset($_POST['idsite']) && isset($_POST['useridid']) ) {
    
    //$ratingid = $_POST['ratingid'];
    $userid = $_POST['userid'];
    $pointofinterestid = $_POST['pointofinterestid'];
    $idsite = $_POST['idsite'];
    $rating = $_POST['rating'];
//    $date = $_POST['date'];

// ici on se connecte au serveur avec les parametres de connection ci-haut
$iconect=mysql_connect(DB_SERVER,DB_USER,DB_PASSWORD) or die('Connexion a la base des donnees impossible');
// Selection de la base des donnees 
mysql_select_db(DB_DATABASE,$iconect) or die ('Impossible de se connecter a la base des donnees');
    
    // mysql inserting a new row
    $result = mysql_query("INSERT INTO comments(userid, pointofinterestid, idsite, rating)
        VALUES('$userid', '$pointofinterestid', '$idsite', '$rating')");

    // check if row inserted or not
    if ($result) {
        // successfully inserted into database
        $response["success"] = 1;
        $response["message"] = "Rate successfully created.";

        // echoing JSON response
        echo json_encode($response);
    } else {
        // failed to insert row
        $response["success"] = 0;
        $response["message"] = "Oops! An error occurred.";
        
        // echoing JSON response
        echo json_encode($response);
    }
} else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";

    // echoing JSON response
    echo json_encode($response);
}

?>