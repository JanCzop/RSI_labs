package org.example._3_4;


public class ConsumerRInitializer {
    public static void main(String[] args) throws Exception {
        String[] routingKeys = {"info", "warning", "error"};

        for (String routingKey : routingKeys) {
            new Thread(() -> {
                try {
                    ConsumerR c = new ConsumerR(routingKey);
                    if(routingKey.equals("info"))  c.routingKey2 = "error";
                    c.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
        System.out.println();
    }
}

