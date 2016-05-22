<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.3/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
    <script src="https://api-maps.yandex.ru/2.1/?lang=ru_RU" type="text/javascript"></script>
    <title>Map</title>
</head>
<body>
<div class="col-md-10">
    <h1>Point :</h1>
    <div class="col-md-6">
        <dl>
            <dt>id</dt>
            <dd>- ${it.id}</dd>
            <dt>title</dt>
            <dd>- ${it.title}</dd>
            <dt>longitude</dt>
            <dd>- ${it.longitude}</dd>
            <dt>latitude</dt>
            <dd>- ${it.latitude}</dd>
        </dl>
    </div>
    <div class="col-md-6">
        <div id="map" style="width: 600px; height: 400px"></div>
    </div>
    <h2><a href="/">Main</a></h2>
</div>
<script type="text/javascript">
    ymaps.ready(init);
    var myMap;

    function init(){
        myMap = new ymaps.Map("map", {
            center: [${it.latitude}, ${it.longitude}],
            zoom: 7
        });

        myPlacemark = new ymaps.Placemark([${it.latitude}, ${it.longitude}], {
            hintContent: ${it.latitude},
            balloonContent: ${it.latitude}
        });

        myMap.geoObjects.add(myPlacemark);
    }
</script>
</body>
</html>