package br.com.kmpx.pixconsumer.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import br.com.kmpx.pixconsumer.model.Pix;


@Service
public class PixConsumer {

	Logger log = LoggerFactory.getLogger(PixConsumer.class);
	
	@KafkaListener(topics = "pix-topic", groupId = "pix-service" )
	public void consumePixMessage(Pix pix) {
		   log.info("consumer consume the events {} ", pix.toString());
	}
}
