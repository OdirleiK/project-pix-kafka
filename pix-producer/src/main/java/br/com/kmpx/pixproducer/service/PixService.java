package br.com.kmpx.pixproducer.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import br.com.kmpx.pixproducer.config.model.Pix;

@Service
public class PixService {

    @Autowired
	KafkaTemplate<String, Object> template;
	
	public void sendEventsToTopic(Pix pix) {
		try {
            CompletableFuture<SendResult<String, Object>> future = template.send("pix-topic", pix);
            future.whenComplete((result, ex) -> {
                if (ex == null) {
                    System.out.println("Sent message=[" + pix.toString() +
                            "] with offset=[" + result.getRecordMetadata().offset() + "]");
                } else {
                    System.out.println("Unable to send message=[" +
                            pix.toString() + "] due to : " + ex.getMessage());
                }
            });

        return pixDTO;
	}
}
