/*
 * **
 *  * @project : DeliX
 *  * @created : 04/05/2024, 23:22
 *  * @modified : 04/05/2024, 23:22
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */

var map;
var marker;

function initMap() {
    map = new google.maps.Map(document.getElementById('map'), {
        center: {lat: 32.8792832, lng: -5.685523},
        zoom: 6
    });

    // Add a click event listener to the map
    map.addListener('click', function(event) {
        var latitude = event.latLng.lat();
        var longitude = event.latLng.lng();
        updateMarkerAndInputs(latitude, longitude);
    });

    // Add onchange event listeners to the latitude and longitude input fields
    document.getElementById('latitude').addEventListener('change', updateMarkerFromInputs);
    document.getElementById('longitude').addEventListener('change', updateMarkerFromInputs);
}

function updateMarkerAndInputs(latitude, longitude) {
    // Update the latitude and longitude input fields
    document.getElementById('latitude').value = latitude;
    document.getElementById('longitude').value = longitude;

    if (marker) {
        marker.setMap(null);
    }

    marker = new google.maps.Marker({
        position: {lat: latitude, lng: longitude},
        map: map,
        icon: '/home/assets/images/icons/flag.png' // replace with the URL of your icon
    });

    // Move the map to the new marker
    map.panTo(marker.getPosition());

    // Do something with the latitude and longitude
    console.log('Latitude: ' + latitude + ', Longitude: ' + longitude);
}

function updateMarkerFromInputs() {
    var latitude = parseFloat(document.getElementById('latitude').value);
    var longitude = parseFloat(document.getElementById('longitude').value);
    updateMarkerAndInputs(latitude, longitude);
}