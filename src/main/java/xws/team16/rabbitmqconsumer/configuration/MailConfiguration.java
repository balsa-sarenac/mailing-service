package xws.team16.rabbitmqconsumer.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MailConfiguration {

    public static final String QUEUE = "mail-queue";

    @Bean
    public Queue queue(){
        return new Queue(QUEUE, false);
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setUsername("falysqic");
        connectionFactory.setPassword("WGXFaLcXLNsampQVJGT8FO-6nXQne-zh");
        connectionFactory.setVirtualHost("falysqic");
        connectionFactory.setHost("wasp.rmq.cloudamqp.com");
//        connectionFactory.setPort();
        return connectionFactory;
    }
}
