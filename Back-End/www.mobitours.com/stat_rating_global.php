

<?php
	$cur_page="Rate per Site statictics";
	include_once("headerz.php");
	include_once("menu.php");
        include_once("checkUser.php");
?>
<div id="datazone">
        
<link href="drawtable.css" rel="stylesheet" type="text/css" >

<form action="city.php" method="post">
    <table align="center" style="width:620px;font-family:trebuchet ms,sans-serif; font-size: 11px" class="CSSTableGenerator">
        <tr><td colspan=2><h2>POI/Site rate statistics</h2></td></tr>
        <tr><td>ID : </td><td><input type="text" name="_id" /></td></tr>
        <tr><td>City ID : </td><td><input type="text" name="idcity" /></td></tr>
        <tr><td></td><td><input type="submit" value="Save" /></td></tr>
    </table>
</form>  
<h2>Statistics Chart viewer :</h2>

<?php

//$sth = mysql_query("SELECT `id`, `Q1`, `Q2` FROM `table2` WHERE `id`=8710058770");
$myquery_h=mysql_query("SELECT  sum(c.rating) as rate,h.title as title 
            FROM hotel h JOIN rating c ON h.pointofinterestid=c.pointofinterestid
            GROUP BY h.title");
$myquery_s=mysql_query("SELECT  sum(c.rating) as rate,s.title as title 
            FROM sitenaturel s JOIN rating c ON s.idsite=c.idsite
            GROUP BY s.title");
//UTILISATION POUR LE STATISTIQUE DE L'HOTEL
$table = array();
$table['cols'] = array(
    // Labels for your chart, these represent the column titles
    // Note that one column is in "string" format and another one is in "number" format as pie chart only required "numbers" for calculating percentage and string will be used for column title
     array('label' => 'title', 'type' => 'string'),
     array('label' => 'rate', 'type' => 'number'),
//     array('label' => 'Q2', 'type' => 'number')
);
$rows = array();
while($r = mysql_fetch_assoc($myquery_h)) {
    $temp = array();
    // the following line will be used to slice the Pie chart
    $temp[] = array('v' => (string) $r['title']);
    // Values of each slice
    $temp[] = array('v' => (int) $r['rate']);
//    $temp[] = array('v' => (int) $r['Q2']); 
    $rows[] = array('c' => $temp);
}
$table['rows'] = $rows;
$jsonTable = json_encode($table);
echo $jsonTable;
echo '</br>';
echo '</br>';

//UTILISATION POUR LE STATISTIQUE DES SITES NATURELS
$table_s = array();
$rows_s = array();
while($rr = mysql_fetch_assoc($myquery_s)) {
    $tempo = array();
    // the following line will be used to slice the Pie chart
    $tempo[] = array('v' => (string) $rr['title']);
    // Values of each slice
    $tempo[] = array('v' => (int) $rr['rate']);
//    $temp[] = array('v' => (int) $r['Q2']); 
    $rows_s[] = array('c' => $tempo);
}
$table_s['rows_s'] = $rows_s;
$jsonTable_s = json_encode($table_s);
echo $jsonTable_s;

?>

<!--Load the AJAX API-->
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
<!--    <script type="text/javascript" src="jquery-1.6.2.min.js"></script>-->
    <script type="text/javascript">
    // Load the Visualization API and the piechart package.
    google.load('visualization', '1', {'packages':['corechart']});
    // Set a callback to run when the Google Visualization API is loaded.
    google.setOnLoadCallback(drawChart);
    
    var data=[];
    data[0]=google.visualization.arrayToDataTable(<?php echo $jsonTable; ?>);
    data[1]=google.visualization.arrayToDataTable(<?php echo $jsonTable_s; ?>);
    
    var options={
      width:400,
      height:240,
      vAxis:{title:"Rate"},
      hAxis:{title:"Site"},
      seriesType:"bars",
      series: {5: {type: "line"}},
      animation:{
        duration: 1000,
        easing: 'out'
      }
    };
    var current=0;
    
    var chart=new google.visualization.ComboChart(document.getElementById('visualization'));
    var button=document.getElementById('b1');
    
    function drawChart(){
      // Disabling the button while the chart is drawing.
      button.disabled = true;
      google.visualization.events.addListener(chart, 'ready',
          function() {
            button.disabled = false;
            button.value = 'Switch to ' + (current ? 'POI' : 'Site');
          });
      options['title'] = 'Global ' + (current ? 'POI' : 'Site') + ' Rating';

      chart.draw(data[current], options);
    }
    drawChart();

    button.onclick = function() {
      current = 1 - current;
      drawChart();
    }
    
    </script>


<div id="visualization">
    
</div>

</div>
<?php
include_once("footer.php");
?>