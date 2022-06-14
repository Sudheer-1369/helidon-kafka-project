package com.oracle.kafka.partitioner;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

public class CustomPartitioner implements Partitioner {

    /**
     * @param map The map is set of properties object that we pass to the producer object
     *            The properties object can also contain custom properties as well and the provided property will passed to the producer and used by producer if required or will leave it
     */
    @Override
    public void configure(Map<String, ?> map) {
        /**
         * Example of fetching the topic name from the map object ,the properties that are required for using in the partition method are fetched here using the Properties map
         */

        String topic = map.get("kafka.topic").toString();
        String inFlightRequests = map.get("kafka.inflight.request").toString();
    }

    @Override
    public int partition(String s, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        /**
         * In Here we need to decide to which partition a particular message belongs to and accordingly write the method and return the parition number finally so that partitcular message will be
         * sent to that partition
         *
         * Note : This method is called for each and every message sent by the producer
         */
        return 0;
    }

    @Override
    public void close() {

    }

}

//import org.apache.kafka.clients.producer.Partitioner;
//import org.apache.kafka.common.Cluster;
//import org.apache.kafka.common.InvalidRecordException;
//import org.apache.kafka.common.PartitionInfo;
//import org.apache.kafka.common.utils.Utils;
//
//import java.util.List;
//import java.util.Map;
//
//public class CustomPartitioner implements Partitioner {
//    private String speedSensorName;
//
//    public void configure(Map<String, ?> configs) {
//        speedSensorName =
//                configs.get(" speed.sensor.name ").toString();
//    }
//
//    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
//        List<PartitionInfo> partitions = cluster.partitionsForTopic(topic);
//        int numPartitions =
//                partitions.size();
//        int sp =
//                (int) Math.abs(numPartitions * 0.3);
//        int p = 0;
//        if ((keyBytes == null) || (!(key instanceof String)))
//            throw new InvalidRecordException(" All messages must have sensor name as key ");
//        if (((String) key).equals(speedSensorName))
//            p = Utils.toPositive(Utils.murmur2(valueBytes)) % sp;
//        else
//            p = Utils.toPositive(Utils.murmur2(keyBytes)) % (numPartitions - sp) + sp;
//
//        return p;
//    }
//
//    public void close() {
//    }
//}