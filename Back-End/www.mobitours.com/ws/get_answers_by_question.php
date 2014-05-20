<?php

/*
 * Ce code va nous permettre de faire un retrieve de toutes les
 * questions par matiere (chapitre)
 */
//on cree un array pour la reponse JSON
$jsonResponse=array();
//on insere la classe de connection a la bd
require_once __DIR__.'/db_connect.php';

//on check le type de methode utilisee
if(isset($_GET["idQ"])){
    $idQ=$_GET['idQ'];
    //instruction sql to retrieve data by id
    $result= mysql_query("SELECT * FROM assertion WHERE idQ=$idQ")or die (mysql_error());
    
    //test le resultat de la requete
    if(!empty($result)){
        
        if(mysql_num_rows($result)>0){
            //Noeud de l'utilisateur
            $jsonResponse["assertion"]=array();
            //Boucle l'array
            while($row=  mysql_fetch_array($result)){
                $assertion=array();
                $assertion["idQ"]=$row["idQ"];
                $assertion["numero"]=$row["numero"];
                $assertion["assertion"]=$row["assertion"];
                $assertion["status"]=$row["status"];
                //push single question into final response array
                array_push($jsonResponse["assertion"], $assertion);
            }
            //succes 
            $jsonResponse["success"]=1;
            
            //JSON response dans un echo (affichage de la reponse JSON)
            echo json_encode($jsonResponse);
        }
        else {
            //dans le cas ou il n'y a aucun produit
            $jsonResponse["success"]=0;
            $jsonResponse["message"]="Aucune assertion pour cette question !";
            
            //On affiche la reponse JSON dans un echo
            echo json_encode($jsonResponse);
        }
    }
    
}

?>
