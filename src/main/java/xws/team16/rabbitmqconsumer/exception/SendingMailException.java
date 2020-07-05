package xws.team16.rabbitmqconsumer.exception;

public class SendingMailException extends RuntimeException {
    public SendingMailException() {
    }

    public SendingMailException(String message) {
        super(message);
    }
}
