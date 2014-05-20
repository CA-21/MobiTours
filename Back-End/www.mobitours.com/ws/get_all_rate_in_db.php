<?php
/*
 * this code will help us to retrieve all city by zone
 * villes par zone geographique 
 */
//Create an array for the JSON response 
$jsonResponse=array();
//Insert the database connection class
require_once __DIR__.'/db_connect.php';

//on check le type de methode utilisee
//if(isset($_GET["idzone"])){
//    $idzone=$_GET['idzone'];
    //instruction sql to retrieve data by id ---   WHERE idzone=$idzone
    $result= mysql_query("SELECT * FROM rating")or die (mysql_error());
    
    //test le resultat de la requete
    if(!empty($result)){
        
        if(mysql_num_rows($result)>0){
            //Noeud de l'utilisateur
            $jsonResponse["votes"]=array();
            //Boucle l'array			
			
            while($row=  mysql_fetch_array($result)){
                $ratings=array();
                $ratings["pointofinterestid"]=$row["pointofinterestid"];
//                $ratings["ratingid"]=$row["ratingid"];
                $ratings["userid"]=$row["userid"];
                $ratings["idsite"]=$row["idsite"];
                $ratings["rating"]=$row["rating"];
                //push single question into final response array
                array_push($jsonResponse["votes"], $ratings);
               // array_push($jsonResponse, $thecity);
            }
            //succes 
            $jsonResponse["success"]=1;
            
            //JSON response dans un echo (affichage de la reponse JSON)
            echo json_encode($jsonResponse);
        }
        else {
            //dans le cas ou il n'y a aucun rate dans la base des donnees
            $jsonResponse["success"]=0;
            $jsonResponse["message"]="No ratings found !";
            
            //On affiche la reponse JSON dans un echo
            echo json_encode($jsonResponse);
        }
//    }
}

?>
