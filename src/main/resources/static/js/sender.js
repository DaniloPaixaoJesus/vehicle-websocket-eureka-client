
var stompClient = null;

$(document).ready(function(){
//	if(stompClient!=null)
//		stompClient.disconnect();
//
//	 var socket = new SockJS('http://ec2-35-174-0-145.compute-1.amazonaws.com:8085/livestatus-websocket');
//	 stompClient = Stomp.over(socket);

	$("#start-random").click(function(){
		if(document.getElementById('start-random').innerHTML == 'Stop Random'){
			document.getElementById('start-random').innerHTML = 'Start Random';	
			$('#start-random').removeClass("btn-warning"); 
			$('#start-random').addClass("btn-primary");
			
		}else{
			document.getElementById('start-random').innerHTML = 'Stop Random';
			$('#start-random').removeClass("btn-primary"); 
			$('#start-random').addClass("btn-warning");
		}
	});
    $("btn-info").click(function(){
    	vin = this.value;
    	document.getElementById(vin).innerHTML = 'sending ...';
    	sendPingOn(vin);
    	this.value = vin;
    	
    });
});

function sendData2Socket(vin) {
	stompClient.send("/app/updatestatus", {}, JSON.stringify({'vin': vin, 'status': 'ON'}));

}

function sendPingOn(vin){

    var xhr = new XMLHttpRequest();
        xhr.open('PUT', 'http://localhost:8080/vehicle-track-service/api/v1/vehicle-track/'+vin);
        xhr.onload = function() {
            document.getElementById(vin).innerHTML = vin;
        };
        xhr.send();
    
}