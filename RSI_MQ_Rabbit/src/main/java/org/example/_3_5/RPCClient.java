package org.example._3_5;
import com.rabbitmq.client.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class RPCClient implements AutoCloseable {
    private final Connection connection;
    private final Channel channel;
    private final String RPC_QUEUE_NAME = "rpc_queue";

    public RPCClient() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        connection = factory.newConnection();
        channel = connection.createChannel();
    }

    public String call(String msg) throws Exception {
        String correlationID = UUID.randomUUID().toString();
        String replyQueueName = channel.queueDeclare().getQueue();

        AMQP.BasicProperties props = new AMQP.BasicProperties.Builder()
                .correlationId(correlationID)
                .replyTo(replyQueueName)
                .build();

        CompletableFuture<String> response = new CompletableFuture<>();

        String tag = channel.basicConsume(replyQueueName, true, (consumerTag, delivery) -> {
            if (delivery.getProperties().getCorrelationId().equals(correlationID)) {
                response.complete(new String(delivery.getBody(), "UTF-8"));
            }
        }, consumerTag -> {});

        channel.basicPublish("", RPC_QUEUE_NAME, props, msg.getBytes("UTF-8"));

        String result = response.get();
        channel.basicCancel(tag);
        return result;
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }

    public static void main(String[] args) {
        try (RPCClient rpcClient = new RPCClient()) {
            String response = rpcClient.call("Hello RPC Server!");
            System.out.println("Response from server: " + response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
