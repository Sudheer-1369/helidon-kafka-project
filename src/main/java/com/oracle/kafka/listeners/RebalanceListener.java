package com.oracle.kafka.listeners;

import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class RebalanceListener implements ConsumerRebalanceListener {

    KafkaConsumer consumer;
    Map<TopicPartition, OffsetAndMetadata> currentOffsets = new HashMap<>();

    public RebalanceListener(KafkaConsumer consumer) {
        this.consumer = consumer;
        System.out.println("The Consumer is set ");
    }

    public void addOffset(String topic, int partition, long offset) {
        currentOffsets.put(new TopicPartition(topic, partition), new OffsetAndMetadata(offset, "Commit"));
    }

    public Map<TopicPartition, OffsetAndMetadata> getCurrentOffsets() {
        return this.currentOffsets;
    }

    @Override
    public void onPartitionsRevoked(Collection<TopicPartition> partitions) {

        System.out.println("Following partitions revoked");
        for (TopicPartition topicPartition : partitions)
            System.out.println(topicPartition.partition());

        System.out.println("Following partitions will be comitted");
        for (TopicPartition topicPartition : currentOffsets.keySet())
            System.out.println(topicPartition.partition());

        consumer.commitSync(currentOffsets);
        currentOffsets.clear();
    }

    @Override
    public void onPartitionsAssigned(Collection<TopicPartition> partitions) {

        System.out.println("Following Partition assigned");
        for (TopicPartition topicPartition : partitions)
            System.out.println(topicPartition.partition());
    }
}
