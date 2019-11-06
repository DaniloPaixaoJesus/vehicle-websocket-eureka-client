package br.com.danilopaixao.websocket.server.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.danilopaixao.websocket.server.model.Driver;
import br.com.danilopaixao.websocket.server.model.DriverList;


@Service
public class DriverService {
	
	private static final Logger logger = LoggerFactory.getLogger(DriverService.class);
	
	@Value("${br.com.danilopaixao.service.driver.url}")
	private String urlDriverService;
	
	@Autowired
	private RestTemplate restTemplate;

	public DriverList getAllDriver() {
		logger.info("##DriverService#getAllDriver() - no arguments");
		return restTemplate.getForObject(this.urlDriverService+"/drivers", DriverList.class);
	}
	public Driver getDriver(final String id) {
		logger.info("##DriverService#getDriver({})", id);
		return restTemplate.getForObject(this.urlDriverService+"/drivers/"+id, Driver.class);
	}
	
}
