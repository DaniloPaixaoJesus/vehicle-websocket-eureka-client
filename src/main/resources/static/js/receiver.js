

var stompClient = null;


$(document).ready(function(){
	
	if(stompClient!=null)
		stompClient.disconnect();

	 var socket = new SockJS('http://ec2-35-174-0-145.compute-1.amazonaws.com:8085/livestatus-websocket');
	 stompClient = Stomp.over(socket);
	 
	 stompClient.connect({}, function (frame) {
	        stompClient.subscribe('/topic/status', function (scoredata) {
	        	
	        	var scoreJson = JSON.parse(scoredata.body);
	        	
	        	$("#vin").html("Vin: "+scoreJson.vin);
	        	$("#status").html("Status: "+scoreJson.status);
	            
	        });
	    });
	
	 
});