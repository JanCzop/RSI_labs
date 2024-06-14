package org.example._3_3;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class ProducerPS {
    private static final String EXCHANGE_NAME = "logs";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

            for (int i = 0; i < 10; i++) {
                String message = "Task " + i;
                channel.basicPublish(EXCHANGE_NAME,"", null, message.getBytes("UTF-8"));
                System.out.println(" [x] Sent '" + message + "'");

            }

        }
    }
}

