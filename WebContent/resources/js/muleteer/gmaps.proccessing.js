	var map,
	    directionsService,
	    directionsDisplay,
	    markers = [],
		distances = [];

	function initMap() {

        map = new google.maps.Map(document.getElementById('map'), {
			zoom : 12,
			center : {
				lat : 50.4501,
				lng : 30.5234
			},
			mapTypeId : google.maps.MapTypeId.ROADMAP
		});

        directionsService = new google.maps.DirectionsService;
        directionsDisplay = new google.maps.DirectionsRenderer({
            draggable : true,
            map : map
        });

		directionsDisplay.addListener('directions_changed', function() {
			computeTotalDistance(directionsDisplay.getDirections());
		});

		displayRoute(50.4501, 30.5234, 'Метро Позняки, Киев',
				directionsService, directionsDisplay);
	}

	function displayRoute(originLat, originLng, destination, service, display) {
		service.route({
			origin : new google.maps.LatLng(originLat, originLng),
			destination : destination,
			travelMode : google.maps.TravelMode.DRIVING,
			avoidTolls : true
		}, function(response, status) {
			if (status === google.maps.DirectionsStatus.OK) {
				display.setDirections(response);
			} else {
				alert('Could not display directions due to: ' + status);
			}
		});

	}

	function computeTotalDistance(result) {
		var total = 0;
		var myroute = result.routes[0];
		for (var i = 0; i < myroute.legs.length; i++) {
			total += myroute.legs[i].distance.value;
		}
		total = total / 1000;
		document.getElementById('route').innerHTML = Number(total).toFixed(1) + ' km';
	}

	function setIntervalAndExecute(fn, t) {
		fn();
		return(setInterval(fn, t));
	}

	function updateMarkersOnMap() {
		var url = "http://"+location.host+location.pathname+"get-markers";

		$.getJSON(url, function(datas, status) {
			//console.log('Markers before '+markers);
			//console.log('Markers before '+distances);

            for (var i = 0; i < markers.length; i++) {
				if(markers[i] != null)
					markers[i].setMap(null);
				distances[i]=0;
			}
            //console.log('Markers clear '+markers);
			if (datas != null) {
				datas.forEach(function(data, i, datas) {
					//console.log('datas '+datas);
					//var position = new google.maps.LatLng(data.lat, data.lng);
					var marker = new google.maps.Marker({
						position : new google.maps.LatLng(data.lat, data.lng),
						title : data.name,
						icon : 'http://maps.google.com/mapfiles/ms/icons/blue-dot.png',
						map : map
					});
					markers[data.id]=marker;
					distances[data.id]=data.distance;
				});
			}
            //console.log('Markers after '+markers);
		});
	}

	$(document).ready(function() {
        //updateMarkersOnMap();
        //setInterval(updateMarkersOnMap, 30000);

		var doThis = setIntervalAndExecute(updateMarkersOnMap, 30000);

		$('#refresh').click(function() {
			updateMarkersOnMap();
		});

		$('#dest').click(function() {
			addr = $('#address').val();
			var i = $(":checked").val();

			if(i >= 0 && addr.length > 0)
				displayRoute(markers[i].position.lat(), markers[i].position.lng(),
					addr, directionsService, directionsDisplay);
		});

		$('#find').click(function() {
			var i = $(":checked").val();
			if(i>=0) {
				var marker = markers[i];
				markers[i].setMap(null);

				if(marker.getPosition().lat() != 0 && marker.getPosition().lng() != 0){
					map.setCenter(marker.getPosition());
					marker.setIcon('http://maps.google.com/mapfiles/ms/icons/yellow-dot.png')
					marker.setMap(map);
				}
				else
					alert('No coordinates avaible yet for this truck!');
			}
		});

		$("input[name=mules]:radio").change(function () {
			var i = $('input[name=mules]:checked').val();
			var marker = markers[i];
			document.getElementById('total').innerHTML = + Number((distances[i] / 1000).toFixed(1))  + ' km';;
		});
	});
