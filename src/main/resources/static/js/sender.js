
var stompClient = null;

$(document).ready(function(){
	
	if(stompClient!=null)
		stompClient.disconnect();

	 var socket = new SockJS('http://ec2-35-174-0-145.compute-1.amazonaws.com:8085/livestatus-websocket');
	 stompClient = Stomp.over(socket);
	
    $("button").click(function(){
    	sendData2Socket(this.value);
    	
    });
});

function sendData2Socket(vin) {
	stompClient.send("/app/updatestatus", {}, JSON.stringify({'vin': vin, 'status': 'ON'}));

}