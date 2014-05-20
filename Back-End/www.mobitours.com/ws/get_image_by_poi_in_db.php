<?php
//        mysql_connect("localhost","root","");
//        mysql_select_db("mobidatabase");
       
$jsonResponse=array();
//Insert the database connection class
require_once __DIR__.'/db_connect.php';

if(isset($_GET["pointofinterestid"])){ 

    $pointofinterestid=$_GET['pointofinterestid'];
    $query=mysql_query("SELECT pointofinterestid,name,image FROM image where pointofinterestid=$pointofinterestid");
        
    if(!empty($query)){

           if(mysql_num_rows($query)>0){

           $jsonResponse["image"]=array();


           while ($row=mysql_fetch_array($query)) {

               $array=array();
               $array['pointofinterestid']=$row['pointofinterestid'];
               $array['name']=$row['name'];
               $array['image']=base64_encode($row['image']);
               //$str_dec= base64_encode($row['immagine']);
               //$row['immagine']=$str_dec;

               array_push($jsonResponse["image"],$array);
   //           $output[]=$array;
               print(json_encode($jsonResponse["image"]));
           }

               $jsonResponse["success"]=1;

               //JSON response dans un echo (affichage de la reponse JSON)
               echo json_encode($jsonResponse);
       }
           else {
               //dans le cas ou il n'y a aucun produit
               $jsonResponse["success"]=0;
               $jsonResponse["message"]="No Image found !";

               //On affiche la reponse JSON dans un echo
               echo json_encode($jsonResponse);
           }
   }
        mysql_close();
   
}
        
?>
