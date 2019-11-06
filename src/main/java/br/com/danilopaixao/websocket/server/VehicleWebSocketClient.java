package br.com.danilopaixao.websocket.server;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import br.com.danilopaixao.websocket.server.model.VehicleTrackWSocket;

@Component
public class VehicleWebSocketClient{
	
    private static final String WEBSOCKET_SEND_TOPIC = "/app/updatestatus";
	private static final String WEBSOCKET_USER_ID = "spring-restapi-client";
	private static final String WEBSOCKET_ENDPOINT = "ws://localhost:8085/livestatus-websocket";


	static public class VehicleStompSessionHandler extends StompSessionHandlerAdapter {
		
    	private static final String SUBSCRIBE_TOPIC = "/topic/status";

		public VehicleStompSessionHandler(String userId){
		}

		private void showHeaders(StompHeaders headers){
		    for (Map.Entry<String,List<String>> e:headers.entrySet()) {
			System.err.print("  " + e.getKey() + ": ");
			boolean first = true;
			for (String v : e.getValue()) {
			    if ( ! first ) System.err.print(", ");
			    System.err.print(v);
			    first = false;
			}
			System.err.println();
		    }
		}

		private void subscribeTopic(String topic,StompSession session){
		    session.subscribe(topic, new StompFrameHandler() {
	
			    @Override
			    public Type getPayloadType(StompHeaders headers) {
				return VehicleTrackWSocket.class;
			    }
	
			    @Override
			    public void handleFrame(StompHeaders headers,
						    Object payload)
			    {
				System.err.println(payload.toString());
			    }
			});
		}

		@Override
		public void afterConnected(StompSession session, StompHeaders connectedHeaders){
		    System.err.println("Connected! Headers:");
		    showHeaders(connectedHeaders);
		    subscribeTopic(SUBSCRIBE_TOPIC, session);
		}
    }
    

    public void send(VehicleTrackWSocket msg) throws Exception
	{
		WebSocketClient simpleWebSocketClient = new StandardWebSocketClient();
		List<Transport> transports = new ArrayList<>(1);
		transports.add(new WebSocketTransport(simpleWebSocketClient));

		SockJsClient sockJsClient = new SockJsClient(transports);
		WebSocketStompClient stompClient = new WebSocketStompClient(sockJsClient);
		stompClient.setMessageConverter(new MappingJackson2MessageConverter());

		String url = WEBSOCKET_ENDPOINT;
		String userId = WEBSOCKET_USER_ID;
		StompSessionHandler sessionHandler = new VehicleStompSessionHandler(userId);
		StompSession session = stompClient.connect(url, sessionHandler).get();
		synchronized(session) {
			session.send(WEBSOCKET_SEND_TOPIC, msg);
			session.disconnect();
		}
	
	}
}