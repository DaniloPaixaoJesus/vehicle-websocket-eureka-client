
var stompClient = null;
var maximum = 2;
var minimum = 0;
var index = 0;
var vins = ['YS2R4X20005399401', 'VLUR4X20009048066', 'YS2R4X20005388011'];

$(document).ready(function(){

    $("button").click(function(){
    	if(this.id == 'start-random'){
			document.getElementById('start-random').innerHTML = 'Sending ...';
			$('#start-random').removeClass("btn-primary"); 
			$('#start-random').addClass("btn-warning");
    		for(let i = 0; i <= 100; i++){
    			
	    		setTimeout(function () {
	    			index = Math.floor(Math.random() * (maximum - minimum + 1)) + minimum;
	    			sendPingOn(vins[index]);
	    		}, 5000);
    		}
    	}else{
    		vin = this.value;
        	document.getElementById(vin).innerHTML = 'sending ...';
        	sendPingOn(vin);
        	this.value = vin;
    	}
    });
});


function sendPingOn(vin){
	console.log('sending.. vin '+ vin);
    var xhr = new XMLHttpRequest();
        xhr.open('PUT', 'http://swedish-challenge.danilopaixao.com.br:8080/vehicle-track-service/api/v1/vehicle-track/'+vin);
        xhr.onload = function() {
            document.getElementById(vin).innerHTML = vin;
        };
        xhr.send();
    
}