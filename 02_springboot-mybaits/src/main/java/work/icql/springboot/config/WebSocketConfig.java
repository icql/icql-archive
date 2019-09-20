package work.icql.springboot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.handler.invocation.HandlerMethodArgumentResolver;
import org.springframework.messaging.handler.invocation.HandlerMethodReturnValueHandler;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;

import java.util.List;

/**
 * @author icql
 * @version 1.0
 * @date 2018/11/30 19:00
 * @Title WebSocketConfig
 * @Description WebSocketConfig
 */
@Configuration
//使Controller开始支持@MessageMapping，用于接收客户端的send请求，用法类似于RequestMapping
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Value("${websocket.socket-path}")
    private String socketPath;

    @Value("${websocket.broadcast-path}")
    private String broadcastPath;

    @Value("${websocket.point-path}")
    private String pointPath;

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint(socketPath).withSockJS();//注册WebSocket连接url
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        /*
        * 注意：
        * 1、广播模式订阅地址：broadcastPath/自定义广播类型名
        * 2、点对点订阅地址：pointPath/用户唯一id/自定义点对点类型名
        * */

        registry.enableSimpleBroker(broadcastPath, pointPath);//广播、点对点订阅的基础url
        registry.setUserDestinationPrefix(pointPath);//点对点订阅url的前缀
    }

    @Override
    public void configureWebSocketTransport(WebSocketTransportRegistration registry) {

    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {

    }

    @Override
    public void configureClientOutboundChannel(ChannelRegistration registration) {

    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {

    }

    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {

    }

    @Override
    public boolean configureMessageConverters(List<MessageConverter> messageConverters) {
        return false;
    }

}
