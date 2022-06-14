package com.oracle.kafka;

public class ConsumeAndProcess {

    public static void process(String value) {

        if (value.equals("Sudheer")) {
            value = value.trim() + " Mounika";
        } else {
            value = "Sudheer Mounika";
        }

        System.out.println(value);
    }
}
