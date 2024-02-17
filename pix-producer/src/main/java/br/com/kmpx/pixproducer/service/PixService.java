package br.com.kmpx.pixproducer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PixService {

    @Autowired
	KafkaTemplate<String, Object> template;
	
	
}
