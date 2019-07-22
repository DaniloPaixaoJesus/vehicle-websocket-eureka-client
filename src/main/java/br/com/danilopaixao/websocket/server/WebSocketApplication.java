package br.com.danilopaixao.websocket.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
public class WebSocketApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebSocketApplication.class, args);
    }
    
    @Bean
    @LoadBalanced
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
    
    @Bean
    public VehicleWebSocketClient getVehicleWebSocketClient() {
    	return new VehicleWebSocketClient();
    }
}
