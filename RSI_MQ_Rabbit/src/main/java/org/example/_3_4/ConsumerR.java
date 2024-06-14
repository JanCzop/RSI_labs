package org.example._3_4;

import com.rabbitmq.client.*;

import java.io.IOException;

public class ConsumerR {
    private static final String EXCHANGE_NAME = "direct_logs";
    private String routingKey;
    public String routingKey2;

    public ConsumerR(String routingKey) {
        this.routingKey = routingKey;
        //System.out.println(" [*] Waiting for messages with routing key '" + routingKey);
    }

    public void start() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);

            String queueName = channel.queueDeclare().getQueue();

            channel.queueBind(queueName, EXCHANGE_NAME, routingKey);
            if(routingKey2!=null){channel.queueBind(queueName, EXCHANGE_NAME, routingKey2);}



            com.rabbitmq.client.Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String message = new String(body, "UTF-8");
                    System.out.println(" [x] Received with routing key '" + routingKey + "': '" + message + "'");
                }
            };

            channel.basicConsume(queueName, true, consumer);
            System.in.read();
        }
    }
}
