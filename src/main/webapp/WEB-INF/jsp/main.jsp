<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.0-rc.2/themes/smoothness/jquery-ui.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.3/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
    <script src="https://api-maps.yandex.ru/2.1/?lang=ru_RU" type="text/javascript"></script>
    <script   src="https://code.jquery.com/ui/1.12.0-rc.2/jquery-ui.min.js"   integrity="sha256-55Jz3pBCF8z9jBO1qQ7cIf0L+neuPTD1u7Ytzrp2dqo=" crossorigin="anonymous"></script>
    <title>Map</title>
</head>
<body>
<div class="col-md-10">
    <h1>Map example:</h1>
    <div class="col-md-6">
        <ul class="list-group" id="groupPoint">
        </ul>
        <form role="form" id="form">
            <div class="form-group">
                <label for="title">Enter title point:</label>
                <input type="title" class="form-control" id="title">
            </div>
            <div class="form-group">
                <label for="address">address:</label>
                <input type="address" class="form-control" id="address">
                <input type="hidden" id="long" name="longitude" value="">
                <input type="hidden" id="lat" name="latitude" value="">
            </div>
            <button type="submit" class="btn btn-default">Submit</button>
        </form>
    </div>
    <div class="col-md-6">
        <div id="map" style="width: 600px; height: 400px"></div>
    </div>
</div>
<script type="text/javascript">
    function loadPoint() {
        $('#groupPoint').html(' ');
        $.get( "/point", function( data ) {
            for(pointNum in data) {

                myPlacemark = new ymaps.Placemark([data[pointNum].latitude, data[pointNum].longitude], {
                    hintContent: data[pointNum].title,
                    balloonContent: data[pointNum].title
                });

                myMap.geoObjects.add(myPlacemark);
                $('#groupPoint').append('<li class="list-group-item"><a href="/point/' + data[pointNum].id + '">' + data[pointNum].title + '</a></li>');
            }
        });
    }

    ymaps.ready(init);
    var myMap;

    function init(){
        myMap = new ymaps.Map("map", {
            center: [55.76, 37.64],
            zoom: 7
        });

        loadPoint();
    }

    $(document).ready(function(){
        $("#address").keyup(function(){
            var search_query = $(this).val();
            search_result = [];
            $.getJSON('http://geocode-maps.yandex.ru/1.x/?format=json&geocode='+search_query, function(data) {
                for(var i = 0; i < data.response.GeoObjectCollection.featureMember.length; i++) {
                    search_result.push({
                        label: data.response.GeoObjectCollection.featureMember[i].GeoObject.description+' - '+data.response.GeoObjectCollection.featureMember[i].GeoObject.name,
                        value:data.response.GeoObjectCollection.featureMember[i].GeoObject.description+' - '+data.response.GeoObjectCollection.featureMember[i].GeoObject.name,
                        longlat:data.response.GeoObjectCollection.featureMember[i].GeoObject.Point.pos});
                }
                $("#address").autocomplete({
                    source: search_result,
                    select: function(event, ui){
                        if(ui.item != undefined) {
                            $('#long').val(' ');
                            $('#lat').val(' ');
                            var longlat = ui.item.longlat.split(' ');
                            $('#long').val(longlat[0]);
                            $('#lat').val(longlat[1]);
                        }
                    }
                });
            });
        });
    });

    $(document).ready(function() {
        $('[type="submit"]').on('click', function() {
            var lon = $('#long').val() || '';
            var lat = $('#lat').val() || '';
            var title = $('#title').val() || '';
            if(title.length > 1 && lat.length > 1 &&  lon.length > 1) {
                $.ajax({
                    'url' : "/point",
                    'data' : JSON.stringify({'title': title.toString(), 'longitude': lon.toString(), 'latitude': lat.toString() }),
                    'type': 'POST',
                    'contentType': 'application/json',
                }).success(function() {
                    $('#form')[0].reset();
                    loadPoint();
                })
            }
            return false;
        })
    })
</script>
</body>
</html>
