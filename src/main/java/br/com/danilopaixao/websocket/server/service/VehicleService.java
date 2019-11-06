package br.com.danilopaixao.websocket.server.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.danilopaixao.websocket.server.model.Vehicle;


@Service
public class VehicleService {
	
	private static final Logger logger = LoggerFactory.getLogger(VehicleService.class);
	
	@Value("${br.com.danilopaixao.service.vehicle.url}")
	private String urlVehicleService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public Vehicle getVehicle(final String vin) {
		logger.info("##VehicleService#getVehicle({})", vin);
		return restTemplate.getForObject(this.urlVehicleService+"/vehicles/"+vin, Vehicle.class);
	}
	public void updateVehicle(final String vin, String status) {
		logger.info("##VehicleService#updateVehicle({}, {})", vin, status);
		String url = this.urlVehicleService+"/vehicles/"+vin+"/status";
		restTemplate.put(url, status);
	}

}
