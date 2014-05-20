

<?php
	$cur_page="Rate per Site statictics";
	include_once("headerz.php");
	include_once("menu.php");
        include_once("checkUser.php");
?>
<div id="datazone">

<?php

//$sth = mysql_query("SELECT `id`, `Q1`, `Q2` FROM `table2` WHERE `id`=8710058770");
$myquery=mysql_query("SELECT  sum(c.rating) as rate,h.title as title 
            FROM hotel h JOIN rating c ON h.pointofinterestid=c.pointofinterestid
            GROUP BY h.title");
$rows = array();
$table = array();
$table['cols'] = array(

    // Labels for your chart, these represent the column titles
    // Note that one column is in "string" format and another one is in "number" format as pie chart only required "numbers" for calculating percentage and string will be used for column title
     array('label' => 'title', 'type' => 'string'),
     array('label' => 'rate', 'type' => 'number'),
//     array('label' => 'Q2', 'type' => 'number')

);

$rows = array();
while($r = mysql_fetch_assoc($myquery)) {
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
//echo $jsonTable;
//echo '</br>';
?>
  
<h2>Statistics Chart viewer :</h2>

<!--Load the AJAX API-->
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
<!--    <script type="text/javascript" src="jquery-1.6.2.min.js"></script>-->
    <script type="text/javascript">

    // Load the Visualization API and the piechart package.
    google.load('visualization', '1', {'packages':['corechart']});
    // Set a callback to run when the Google Visualization API is loaded.
    google.setOnLoadCallback(drawChart);
    
    function drawChart() {

      // Create our data table out of JSON data loaded from server.
      var data = new google.visualization.DataTable(<?php echo $jsonTable; ?>);
      
         var options = {
           title: 'Rate analysis',
           legend:'bottom',
           is3D: 'true',
           pieStartAngle: 100,
           width: 600,
           height: 480
        };

      // Instantiate and draw our chart, passing in some options.
      var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
      
      //The select handler call the chart's getselected() method
      function selectHandler(){
          var selectedItem=chart.getSelection()[0];
          if(selectedItem){
                var value=chart.getValue(selectedItem.row, selectedItem.column);
                alert ('you have select ' + value);
          }
      }
      google.visualization.events.addListener(chart,'select',selectHandler);
//      chart.draw(data, {width: 600, height: 500});
      chart.draw(data,options);
      
      var dataAsJson = new google.visualization.DataTable(<?php echo $jsonTable; ?>);
      // Set paging configuration options
        // Note: these options are changed by the UI controls in the example.
        options['page'] = 'enable';
        options['pageSize'] = 10;
        options['pagingSymbols'] = {prev: 'prev', next: 'next'};
        options['pagingButtonsConfiguration'] = 'auto';
      
      // Create and draw the visualization.
        visualization = new google.visualization.Table(document.getElementById('table'));
        draw();
        
        function draw() {
            visualization.draw(dataAsJson, options);
          }
      
    }

    </script>


<div id="chart_div">
    
</div>

<div id="table">
    
</div>
    
<link href="drawtable.css" rel="stylesheet" type="text/css" >

<h2>Rate values Table :</h2>
<?php

$myq=mysql_query("SELECT  *  FROM hotel h JOIN rating c ON h.pointofinterestid=c.pointofinterestid
            GROUP BY h.title");
$requ=mysql_query($myq) or die (mysql_error());
echo "<table align=\"center\" style=\"width:620px;font-family:trebuchet ms,sans-serif; font-size: 11px\" class=\"CSSTableGenerator\">";
echo "<tr><th>POI/Site Title</th><th># Sum Rate</th></tr>";
while ($rq= mysql_fetch_assoc($requ)) 
{
  
    echo "<tr><td>";
    echo (string)$rq['title'];
    echo "</td>";
    echo "<tr><td>";
    echo (int)$rq['rate'];
    echo "</td>";
    echo "</tr>";
    
}
echo "</table>";
?> 
    
</div>
<?php
include_once("footer.php");
?>