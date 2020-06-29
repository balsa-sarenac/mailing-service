package xws.team16.rabbitmqconsumer.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import xws.team16.rabbitmqconsumer.configuration.MailConfiguration;

@Service
public class MailService {

    ObjectMapper objectMapper = new ObjectMapper();

    @RabbitListener(queues = MailConfiguration.QUEUE)
    public void sendMail(String mail) {
        System.out.println("Received");
    }
}
