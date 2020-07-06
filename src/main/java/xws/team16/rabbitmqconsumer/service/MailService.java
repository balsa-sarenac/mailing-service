package xws.team16.rabbitmqconsumer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import xws.team16.rabbitmqconsumer.configuration.MailConfiguration;
import xws.team16.rabbitmqconsumer.dto.MailDTO;
import xws.team16.rabbitmqconsumer.exception.SendingMailException;

import java.util.Objects;

@Service
public class MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private Environment environment;

    private ObjectMapper objectMapper = new ObjectMapper();

    @RabbitListener(queues = MailConfiguration.QUEUE)
    public void sendMail(String mail) {
        System.out.println("Received");
        try {
            MailDTO mailDTO = objectMapper.readValue(mail, MailDTO.class);
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(Objects.requireNonNull(environment.getProperty("spring.mail.username")));
            message.setTo(mailDTO.getEmail());
            message.setSubject(mailDTO.getSubject());
            message.setText(mailDTO.getMessage());
            javaMailSender.send(message);
        } catch (JsonProcessingException exception) {
            throw new SendingMailException("Mail wasn't send. Couldn't read sent info");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
