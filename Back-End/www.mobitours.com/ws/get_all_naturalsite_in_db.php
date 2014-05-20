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
    $result= mysql_query("SELECT * FROM sitenaturel")or die (mysql_error());
    
    //test le resultat de la requete
    if(!empty($result)){
        
        if(mysql_num_rows($result)>0){
            //Noeud de l'utilisateur
            $jsonResponse["naturalsite"]=array();
            //Boucle l'array			
            while($row=  mysql_fetch_array($result)){
                $sitenature=array();
                $sitenature["idsite"]=$row["idsite"];
                $sitenature["title"]=$row["title"];
                $sitenature["type"]=$row["type"];
                 $sitenature["idcity"]=$row["idcity"];
                $sitenature["area"]=$row["area"];
                $sitenature["sitedesc"]=$row["sitedesc"];
                $sitenature["latitude"]=$row["latitude"];
                $sitenature["longitude"]=$row["longitude"];
                $sitenature["attractourist"]=$row["attractourist"];
                $sitenature["largeur"]=$row["largeur"];
                $sitenature["longueur"]=$row["longueur"];
                $sitenature["security"]=$row["security"];
                $sitenature["visitorperan"]=$row["visitorperan"];
                $sitenature["site_url_image"]=$row["site_url_image"];
                //push single question into final response array
                array_push($jsonResponse["naturalsite"], $sitenature); //ceci affiche avec le nom thecity dans le json
               // array_push($jsonResponse, $thecity);
            }
            //succes 
            $jsonResponse["success"]=1;
            //JSON response dans un echo (affichage de la reponse JSON)
            echo json_encode($jsonResponse);
        }
        else {
            //dans le cas ou il n'y a aucun produit
            $jsonResponse["success"]=0;
            $jsonResponse["message"]="No Natural site found!";
            //On affiche la reponse JSON dans un echo
            echo json_encode($jsonResponse);
        }
//    }
}

?>
