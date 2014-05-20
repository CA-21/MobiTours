<?php

/*
 * Ce code va nous permettre de faire un retrieve de toutes les
 * questions par matiere (chapitre)
 */
//on cree un array pour la reponse JSON
$jsonResponse=array();
//on insere la classe de connection a la bd
require_once __DIR__.'/db_connect.php';
    //instruction sql to retrieve data by id
    $result= mysql_query("SELECT * FROM courtier")or die (mysql_error());
    
    //test le resultat de la requete
    if(!empty($result)){
        
        if(mysql_num_rows($result)>0){
            //Noeud de l'utilisateur
            $jsonResponse["courtier"]=array();
            //Boucle l'array
            while($row=  mysql_fetch_array($result)){
                $c_array=array();
                $c_array["courtierid"]=$row["courtierid"];
                $c_array["email"]=$row["email"];
                $c_array["name"]=$row["lastname"].' '.$row["firstname"];
                $c_array["website"]=$row["website"];
                $c_array["phone"]=$row["phone"];
                $c_array["country"]=$row["country"].'-'.$row["city"];
                //push single question into final response array
                array_push($jsonResponse["courtier"], $c_array);
            }
            //succes 
            $jsonResponse["success"]=1;
            
            //JSON response dans un echo (affichage de la reponse JSON)
            echo json_encode($jsonResponse);
        }
        else {
            //dans le cas ou il n'y a aucune donnee
            $jsonResponse["success"]=0;
            $jsonResponse["message"]="No broker fund !";
            
            //On affiche la reponse JSON dans un echo
            echo json_encode($jsonResponse);
        }
    }

?>
