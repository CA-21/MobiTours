<?php
	$cur_page="Mobitours";
	include_once("headerz.php");
	include_once("menu.php");
?>
<html>
<head>
    <script src="http://maps.googleapis.com/maps/api/js?key=AIzaSyBQCB2MmO2TCEQzYSMfqDH83t_gkfcLGeU&sensor=false">
    </script>
    
    <script type="text/javascript">
	
		var myCenter=new google.maps.LatLng(-2.24064,23.818359);
		 //initialise the infowindow so it's ready for use
    	var infowindow = null;
		
		var locations = [
				['Nord-Kivu', -1.654586,29.220371],
				['Sud-Kivu', -2.510259,28.844948],
				['Maniema', -2.948698,25.950222],
				['Province Oriental', 0.520642,25.196114],
				['Kinshasa', -4.318339,15.314255],
				['Bandundu', -3.312077,17.385521],
				['Bas-Congo', -5.848107,13.056049],
				['Katanga', -11.649546,27.479553],
				['Equateur', 0.049782,18.255844],
				['Kasai Oriental', -2.751018,23.389893],
				['Kasai Occidental', -5.123772,21.851807]
			];

				
		function initialize()
		{
			var mapProp = {
				  center:myCenter,
				  zoom:5,
				  mapTypeId:google.maps.MapTypeId.ROADMAP
		     };
			 var map=new google.maps.Map(document.getElementById("thedrcmap")
			  ,mapProp);
			
			setMarkers(map, locations);
			infowindow = new google.maps.InfoWindow({
			content: "holding..."
			});
   		}
		
	//	google.maps.event.addDomListener(window, 'load', initialize);
	
		function setMarkers(map,markers)
		{
			 //loop through and place markers
        for (var i = 0; i < markers.length; i++) 
        {
            var sites = markers[i];
            var siteLatLng = new google.maps.LatLng(sites[1], sites[2]);
            var marker = new google.maps.Marker({
                position: siteLatLng,
                map: map,
                title: sites[0],
            });
				 //initial content string
            var contentString = "Some content";

            //attach infowindow on click
            google.maps.event.addListener(marker, "click", function () 
            {
                infowindow.setContent(this.title);
                infowindow.open(map, this);
            });
        }
    }
  
</script>
</head>

<body>
	<div id="datazone">
		<div id="thedrcmap" style="width:auto;height:420px;"></div>
        <script type="text/javascript">initialize();</script>
	</div>

</body>
</html>


<?php
	include_once("footer.php");
?>