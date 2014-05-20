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
    $result= mysql_query("SELECT * FROM zone_geo")or die (mysql_error());
    
    //test le resultat de la requete
    if(!empty($result)){
        
        if(mysql_num_rows($result)>0){
            //Noeud de l'utilisateur
            $jsonResponse["geozonelist"]=array();
            //Boucle l'array
            while($row=  mysql_fetch_array($result)){
                $geozone_array=array();
                $geozone_array["zoneGeoId"]=$row["zoneGeoId"];
                $geozone_array["nameZoneGeo"]=$row["nameZoneGeo"];
                $geozone_array["descZoneGeo"]=$row["descZoneGeo"];
                $geozone_array["etenduZoneGeo"]=$row["etenduZoneGeo"];
                $geozone_array["latzone"]=$row["latzone"];
                $geozone_array["lngzone"]=$row["lngzone"];
                $geozone_array["areazone"]=$row["areazone"];
                //push single question into final response array
                array_push($jsonResponse["geozonelist"], $geozone_array);
            }
            //succes 
            $jsonResponse["success"]=1;
            
            //JSON response dans un echo (affichage de la reponse JSON)
            echo json_encode($jsonResponse);
        }
        else {
            //dans le cas ou il n'y a aucune donnee
            $jsonResponse["success"]=0;
            $jsonResponse["message"]="No GeoZone fund !";
            
            //On affiche la reponse JSON dans un echo
            echo json_encode($jsonResponse);
        }
    }

?>
