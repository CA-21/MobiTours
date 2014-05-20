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
    $result= mysql_query("SELECT * FROM hotel")or die (mysql_error());
    
    //test le resultat de la requete
    if(!empty($result)){
        
        if(mysql_num_rows($result)>0){
            //Noeud de l'utilisateur
            $jsonResponse["hotels"]=array();
            //Boucle l'array			
			
            while($row=  mysql_fetch_array($result)){
                $hotels=array();
                $hotels["pointofinterestid"]=$row["pointofinterestid"];
                $hotels["idcity"]=$row["idcity"];
                $hotels["title"]=$row["title"];
                $hotels["type"]=$row["type"];
                $hotels["url"]=$row["url"];
                $hotels["latitude"]=$row["latitude"];
                $hotels["longitude"]=$row["longitude"];
                $hotels["adresse"]=$row["adresse"];
                $hotels["deschotel"]=$row["deschotel"];
                $hotels["star"]=$row["star"];
                $hotels["nbroom"]=$row["nbroom"];
		$hotels["nbroomdispo"]=$row["nbroomdispo"];
                $hotels["roompricemin"]=$row["roompricemin"];
                $hotels["roompricemax"]=$row["roompricemax"];
                $hotels["mail"]=$row["mail"];
                $hotels["pictureurl"]=  stripslashes($row["pictureurl"]);
                //push single question into final response array
                array_push($jsonResponse["hotels"], $hotels);
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
            $jsonResponse["message"]="No hotels found !";
            
            //On affiche la reponse JSON dans un echo
            echo json_encode($jsonResponse);
        }
//    }
}

?>
