package br.com.danilopaixao.websocket.server.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	private static final Logger logger = LoggerFactory.getLogger(WebSocketResource.class);
	
	@Autowired
	VehicleWebSocketClient vehicleWebSocketClient;
	
	@MessageMapping("/updatestatus")
	@SendTo("/topic/status")
	public VehicleTrackWSocket sendToTopicStatus(VehicleTrackWSocket vehicleTrack) throws InterruptedException {
		logger.info("##WebSocketResource#sendToTopicStatus {}", vehicleTrack);
		return vehicleTrack;
	}
	
	@PutMapping(value = "/{vin}/status", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(HttpStatus.ACCEPTED)
	public @ResponseBody VehicleTrackWSocket updateStatusWebSocket(@RequestBody(required = true) VehicleTrackWSocket vehicleTrack, 
														@PathVariable("vin") final String vin) throws Throwable {
		logger.info("##WebSocketResource#updateStatusWebSocket - update client devices {}, {}", vin, vehicleTrack);
		vehicleWebSocketClient.send(vehicleTrack);
		return vehicleTrack;
	}

}
