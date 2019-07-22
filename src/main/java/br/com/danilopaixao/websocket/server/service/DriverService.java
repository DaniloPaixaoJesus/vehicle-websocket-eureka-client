package br.com.danilopaixao.websocket.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.danilopaixao.websocket.server.model.Driver;
import br.com.danilopaixao.websocket.server.model.DriverList;


@Service
public class DriverService {
	
	@Value("${br.com.danilopaixao.service.driver.host}")
	private String hostDriverService;
	
	@Value("${br.com.danilopaixao.service.driver.protocol}")
	private String protocolDriverService;
	
	@Value("${br.com.danilopaixao.service.driver.version}")
	private String versionDriverService;
	
	@Autowired
	private RestTemplate restTemplate;

	public DriverList getAllDriver() {
		return restTemplate.getForObject(this.protocolDriverService+"://"+this.hostDriverService+"/api/"+this.versionDriverService+"/drivers", DriverList.class);
	}
	public Driver getDriver(final String id) {
		return restTemplate.getForObject(this.protocolDriverService+"://"+this.hostDriverService+"/api/"+this.versionDriverService+"/drivers/"+id, Driver.class);
	}
	
}
