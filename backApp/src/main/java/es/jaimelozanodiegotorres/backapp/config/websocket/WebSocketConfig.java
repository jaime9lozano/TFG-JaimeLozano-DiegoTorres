package es.jaimelozanodiegotorres.backapp.config.websocket;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    //@Value("${api.version}")
    //private String apiVersion;

    // Registra uno por cada tipo de notificación que quieras con su handler y su ruta (endpoint)
    // Cuidado con la ruta que no se repita
    // Para coinectar con el cliente, el cliente debe hacer una petición de conexión
    // wss://localhost:3000/ws/orders
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketFunkosHandler(), "/ws/orders");
    }

    // Cada uno de los handlers como bean para que cada vez que nos atienda
    @Bean
    public WebSocketHandler webSocketFunkosHandler() {
        return new WebSocketHandler("Orders");
    }

}
