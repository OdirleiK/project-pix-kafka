package br.com.kmpx.pixproducer.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import br.com.kmpx.pixproducer.dto.PixDTO;

@Configuration
public class ProducerKafkaConfig {

	private String bootstrapAddress;
	
	public ProducerFactory<String, PixDTO> producerFactory() {
		Map<String, Object> configProps = new HashMap<>();
		configProps.put(
				ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, 
				bootstrapAddress);
		configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, 
						StringSerializer.class);
		configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
						JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
	}
	
	@Bean
    public KafkaTemplate<String, PixDTO> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
