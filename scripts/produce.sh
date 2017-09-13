#! /bin/bash

cd ~/kafka_2.11-0.11.0.0/;

bin/kafka-console-producer.sh --broker-list localhost:9092 --topic stream1 < /vagrant/file-input.txt &
