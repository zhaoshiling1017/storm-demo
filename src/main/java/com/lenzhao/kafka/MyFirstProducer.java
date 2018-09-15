package com.lenzhao.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.Future;

public class MyFirstProducer {
    /**
     * 初始化kafka参数配置
     * @return
     */
    public static Properties initConfig() {
        Properties kafkaProps = new Properties();
        //配置kafka集群地址
        kafkaProps.put("bootstrap.servers", "localhost:9092");
        //序列化器
        kafkaProps.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProps.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        return kafkaProps;
    }
    public static void main(String[] args) {
        //第一步:初始化kafka参数
        Properties kafkaProps = initConfig();
        //第二步:创建生成者
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(kafkaProps);
        //第三步:创建一条消息
        /**
         * 使用ProducerRecord<String, String>(String topic, String key, String value)构造函数创建消息对象
         * 构造函数接受三个参数：
         * topic--告诉kafkaProducer消息发送到哪个topic;
         * key--告诉kafkaProducer，所发送消息的key值，注意：key值类型需与前面设置的key.serializer值匹配
         * value--告诉kafkaProducer，所发送消息的value值，即消息内容。注意：value值类型需与前面设置的value.serializer值匹配
         */
        ProducerRecord<String, String> record =new ProducerRecord<String, String>("test", "messageKey", "测试producer");
        //向kafka集群发送消息,除了消息值本身,还包括key信息,key信息用于消息在partition之间均匀分布。

        try {
            //第四步:发送消息
            Future<RecordMetadata> f = producer.send(record);

            producer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
