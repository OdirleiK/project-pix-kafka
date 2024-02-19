package br.com.kmpx.pixstream.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.streams.StreamsConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration;
import org.springframework.kafka.config.KafkaStreamsConfiguration;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import br.com.kmpx.pixstream.serdes.PixSerdes;

@Configuration
public class ConsumerKafkaConfig {

	@Value(value = "${spring.kafka.bootstrap-servers:localhost:9092}")
	private String bootstrapAddress;

	@Bean(name = KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME)
    public KafkaStreamsConfiguration kafkaStreamConfig() {
        Map<String, Object> props = new HashMap<>();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "kafka-streams");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, org.apache.kafka.common.serialization.Serdes.String().getClass().getName());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, PixSerdes.class.getName());
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "java.util, java.lang, com.course.stream.broker.message, com.course.stream.broker.message.*");
        props.put(JsonDeserializer.USE_TYPE_INFO_HEADERS, false);
        return new KafkaStreamsConfiguration(props);
    }
	
//	@Bean
//	public ConsumerFactory<String, Object> consumerFactory() {
//	    	Map<String, Object> configProps = new HashMap<>();
//		    configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
//		    configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//		    configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
//		    //configProps.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 10);
//		    configProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
//		   // configProps.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, configProps);
//		    configProps.put(JsonDeserializer.VALUE_DEFAULT_TYPE, "br.com.kmpx.pixconsumer.dto.PixDTO");
//		    configProps.put(JsonDeserializer.USE_TYPE_INFO_HEADERS,false);
//		    configProps.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
//		    configProps.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, ErrorHandlingDeserializer.class);
//		    configProps.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, ErrorHandlingDeserializer.class);
//	        //props.put(JsonDeserializer.TRUSTED_PACKAGES, "br.com.kmpx.pixproducer");
//	        return new DefaultKafkaConsumerFactory<>(configProps);
//	    }
//
//	@Bean
//	public ConcurrentKafkaListenerContainerFactory<String, Object> kafkaListenerContainerFactory() {
//
//		ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
//		factory.setConsumerFactory(consumerFactory());
//		return factory;
//	}
//
//	@Bean
//	public Map<String, Object> producerConfig() {
//		Map<String, Object> props = new HashMap<>();
//		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
//		return props;
//	}
//
//	@Bean
//	public ProducerFactory<String, Object> producerFactory() {
//		return new DefaultKafkaProducerFactory<>(producerConfig());
//	}
//
//	@Bean
//	public KafkaTemplate<String, Object> kafkaTemplate() {
//		return new KafkaTemplate<>(producerFactory());
//	}
//	

}
