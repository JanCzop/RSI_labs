package org.example._3_2;

import com.rabbitmq.client.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConsumerWorker {

    private final static String QUEUE_NAME = "work_queue";
    private final static int WORK_SLEEP_TIME_MS = 500;

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            channel.queueDeclare(QUEUE_NAME, true, false, false, null);
            channel.basicQos(1);

            System.out.println("Klient uruchomiony. Śpi przez " + WORK_SLEEP_TIME_MS / 1000);

            com.rabbitmq.client.Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String message = new String(body, "UTF-8");

                    String currentTime = new SimpleDateFormat("HH:mm:ss").format(new Date());
                    System.out.println(" [x] Received at " + currentTime + " : '" + message + "'");

                    try {
                        doWork(message);
                    } finally {
                        System.out.println(" [x] Done");
                        channel.basicAck(envelope.getDeliveryTag(), false);
                    }
                }
            };

            channel.basicConsume(QUEUE_NAME, false, consumer);

            System.in.read();
        }
    }

    private static void doWork(String task) {
        try {
            Thread.sleep(WORK_SLEEP_TIME_MS);
        } catch (InterruptedException _ignored) {
            Thread.currentThread().interrupt();
        }
    }
}
