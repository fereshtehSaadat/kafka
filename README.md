# kafka
kafka project
Sample Code For Kafka

zkServer.cmd

zkCli.cmd

cd C:\ROOT\Kafka\bin\windows

./zookeeper-server-start.bat ../../config/zookeeper.properties

./kafka-server-start.bat ../../config/server.properties

./kafka-topics.bat --create --topic msxFirstTopic --bootstrap-server localhost:9092

./kafka-topics.bat --describe --topic msxFirstTopic --bootstrap-server localhost:9092

./kafka-console-producer.bat --topic msxFirstTopic --bootstrap-server localhost:9092

This is my first event This is my second event

./kafka-console-consumer.bat --topic msxFirstTopic --from-beginning --bootstrap-server localhost:9092 
This is my first event This is my second event
