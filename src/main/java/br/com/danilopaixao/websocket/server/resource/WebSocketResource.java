package br.com.danilopaixao.websocket.server.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.danilopaixao.websocket.server.VehicleWebSocketClient;
import br.com.danilopaixao.websocket.server.model.VehicleTrackWSocket;

@RestController
@RequestMapping("api/v1/vehicle")
public class WebSocketResource {
	
	@Autowired
	VehicleWebSocketClient vehicleWebSocketClient;
	
	
	@MessageMapping("/updatestatus")
	@SendTo("/topic/status")
	public VehicleTrackWSocket getVehicleStatus(VehicleTrackWSocket vehicleTrack) throws InterruptedException {
		return vehicleTrack;
	}
	
	@PutMapping(value = "/{vin}/status", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(HttpStatus.ACCEPTED)
	public @ResponseBody VehicleTrackWSocket updateStatusWebSocket(@RequestBody(required=true) VehicleTrackWSocket vehicleTrack) throws Throwable {
		vehicleWebSocketClient.send(vehicleTrack);
		return vehicleTrack;
	}

}
