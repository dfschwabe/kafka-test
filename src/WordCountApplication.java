import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KStreamBuilder;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.KeyValueMapper;
import org.apache.kafka.streams.kstream.ValueMapper;

import java.util.Arrays;
import java.util.Properties;

public class WordCountApplication {

  public static void main(final String[] args) throws Exception {
    Properties config = new Properties();
    config.put(StreamsConfig.APPLICATION_ID_CONFIG, "wordcount-application");
    config.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
    config.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
    config.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

    KStreamBuilder builder = new KStreamBuilder();
    KStream<String, String> textLines = builder.stream("streams-file-input");

    KTable<String, Long> wordCounts = textLines
      .flatMapValues(new ValueMapper<String, Iterable<String>>() {
        @Override
        public Iterable<String> apply(String value) {
          return Arrays.asList(value.toLowerCase().split("\\W+"));
        }
      })
    .groupBy(new KeyValueMapper<String, String, String>() {
      @Override
      public String apply(String key, String value) {
        return value;
      }
    })
    .count("Counts");

    wordCounts.to(Serdes.String(), Serdes.Long(), "streams-wordcount-output");

    final KafkaStreams streams = new KafkaStreams(builder, config);
    streams.start();

    Runtime.getRuntime().addShutdownHook(new Thread() {
      public void run() {
        streams.close();
      }
    });
  }
}
