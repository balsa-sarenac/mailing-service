package xws.team16.rabbitmqconsumer.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MailConfiguration {

    public static final String QUEUE = "mail-queue";

    @Bean
    public Queue queue(){
        return new Queue(QUEUE, false);
    }
}
