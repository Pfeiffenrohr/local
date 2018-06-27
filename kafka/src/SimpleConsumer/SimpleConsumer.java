package SimpleConsumer;

import java.util.Arrays;
import java.util.Properties;
 
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
 
public class SimpleConsumer {
 
  public static void main(String[] args) {
    Properties props = new Properties();
    //props.put("bootstrap.servers", "72.16.138.151:2181"); ing04esbsand01:9092
    props.put("bootstrap.servers", "ing04esbsand01:9092");
   // props.put("bootstrap.servers", "localhost:9092");
   // props.put("bootstrap.servers", "81.169.141.91:9092");
    props.put("group.id", "group-1");
    props.put("enable.auto.commit", "true");
    props.put("auto.commit.interval.ms", "1000");
    props.put("auto.offset.reset", "earliest");
    props.put("session.timeout.ms", "60000");
    props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
    props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
 
    KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(props);
   // kafkaConsumer.subscribe(Arrays.asList("test-mysql-jdbc-kunden"));
    kafkaConsumer.subscribe(Arrays.asList("kafkatest"));
    while (true) {
      ConsumerRecords<String, String> records = kafkaConsumer.poll(100);
      for (ConsumerRecord<String, String> record : records) {
        System.out.printf("offset = %d, value = %s", record.offset(), record.value());
        System.out.println();
      }
    }
 
  }
 
} 