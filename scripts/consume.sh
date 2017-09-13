#! /bin/bash

cd ~/kafka_2.11-0.11.0.0/;

bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic stream2 --from-beginning --formatter kafka.tools.DefaultMessageFormatter --property print.key=true --property print.value=true --property key.deserializer=org.apache.kafka.common.serialization.StringDeserializer --property value.deserializer=org.apache.kafka.common.serialization.LongDeserializer;
