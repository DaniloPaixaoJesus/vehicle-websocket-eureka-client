package br.com.danilopaixao.websocket.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.danilopaixao.websocket.server.model.Vehicle;


@Service
public class VehicleService {
	
	@Value("${br.com.danilopaixao.service.vehicle.host}")
	private String hostVehicleService;
	
	@Value("${br.com.danilopaixao.service.vehicle.protocol}")
	private String protocolVehicleService;
	
	@Value("${br.com.danilopaixao.service.vehicle.version}")
	private String versionVehicleService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public Vehicle getVehicle(final String vin) {
		return restTemplate.getForObject(this.protocolVehicleService+"://"+this.hostVehicleService+"/api/"+this.versionVehicleService+"/vehicles/"+vin, Vehicle.class);
	}
	public void updateVehicle(final String vin, String status) {
		String url = this.protocolVehicleService+"://"+this.hostVehicleService+"/api/"+this.versionVehicleService+"/vehicles/"+vin+"/status";
		restTemplate.put(url, status);
	}

}
