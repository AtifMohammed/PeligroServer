<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <meta charset="utf-8">
    <title>Peligro</title>
    <style>
      html, body, #map-canvas {
        height: 100%;
        margin: 0px;
        padding: 0px
      }
    </style>
    <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&signed_in=true"></script>
    <script>
function initialize() {
  var sender = new google.maps.LatLng('<%=request.getParameter("lats") %>','<%=request.getParameter("lons") %>');
  var helper = new google.maps.LatLng('<%=request.getParameter("latr") %>','<%=request.getParameter("lonr") %>');
  var mapOptions = {
    zoom: 16,
    center: sender
  }
  var map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);

  var sender = new google.maps.Marker({
      position: sender,
      map: map,
      title: 'Sender or Victim'
  });
  var helper = new google.maps.Marker({
      position: helper,
      map: map,
      title: 'you'
  });
}

google.maps.event.addDomListener(window, 'load', initialize);

    </script>
  </head>
  <body>
    <div id="map-canvas"></div>
  </body>
</html>