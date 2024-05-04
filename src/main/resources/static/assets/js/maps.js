/*
 * **
 *  * @project : DeliX
 *  * @created : 04/05/2024, 17:24
 *  * @modified : 04/05/2024, 17:24
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */

function initMap() {
    // Map center coordinates
    const center = { lat: 32.8792832, lng: -5.685523 };

    // Map options
    const options = {
        zoom: 7,
        center: center
    };

    // Create the map object
    const map = new google.maps.Map(document.getElementById("map-container"), options);

    fetch('/home/get_location_agencys', {
        method: 'GET', // or 'POST'
        headers: {
            'Content-Type': 'application/json',
            // 'Content-Type': 'application/x-www-form-urlencoded',
        },
    })
        .then(response => response.json())
        .then(data => {

            // convert data to a array list like this
            // const locations = [
            //     { lat: -33.8688, lng: 151.2093, content: "Sydney, Australia",  },
            //     { lat: -37.8136, lng: 144.9631, content: "Melbourne, Australia",},
            //     { lat: -41.2905, lng: 174.7792, content: "Wellington, New Zealand", }
            // ];
            const locations = []
            for (const location of data) {
                locations.push({ lat: location.locationPoint.latitude, lng: location.locationPoint.longitude, content: location.agencyName })
            }
            for (const location of locations) {
                const marker = new google.maps.Marker({
                    position: location,
                    map: map,
                    icon: "/assets/images/icons/flag.png",


                });

                // Optional: Add info window content
                if (location.content) {
                    const infowindow = new google.maps.InfoWindow({
                        content: location.content
                    });

                    marker.addListener("click", () => {
                        infowindow.open(map, marker);
                    });
                }
            }





            console.log(data)})
        .catch((error) => {
            console.error('Error:', error);
        });




    // Define locations
    // const locations = [
    //     { lat: -33.8688, lng: 151.2093, content: "Sydney, Australia",  },
    //     { lat: -37.8136, lng: 144.9631, content: "Melbourne, Australia",},
    //     { lat: -41.2905, lng: 174.7792, content: "Wellington, New Zealand", }
    // ];

    // Add markers to the map

}
