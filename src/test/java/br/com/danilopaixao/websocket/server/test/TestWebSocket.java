package br.com.danilopaixao.websocket.server.test;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.danilopaixao.websocket.server.model.VehicleTrackWSocket;

public class TestWebSocket {

	public static void main(String[] args) throws Exception {
		VehicleTrackWSocket v = new VehicleTrackWSocket("YS5664X28413B87949", "ON");
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(mapper.writeValueAsString(v));
	}
	
}
