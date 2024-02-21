package br.com.kmpx.pixconsumer.consumer;

import org.apache.avro.generic.GenericData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.kmpx.pixconsumer.dto.PixDTO;
import br.com.kmpx.pixconsumer.dto.PixStatus;
import br.com.kmpx.pixconsumer.expection.KeyNotFoundException;
import br.com.kmpx.pixconsumer.model.Key;
import br.com.kmpx.pixconsumer.model.Pix;
import br.com.kmpx.pixconsumer.repositories.KeyRepository;
import br.com.kmpx.pixconsumer.repositories.PixRepository;


@Service
public class PixConsumer {
	
	@Autowired
    private KeyRepository keyRepository;

    @Autowired
    private PixRepository pixRepository;

	Logger log = LoggerFactory.getLogger(PixConsumer.class);
	
	@KafkaListener(topics = "pix-service.public.pix", groupId = "pix-service" )
	@RetryableTopic(backoff = @Backoff(value = 3000l),
					autoCreateTopics = "true",
					include = KeyNotFoundException.class)
	public void consumePixMessage(GenericData.Record data) throws JsonMappingException, JsonProcessingException {
		
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.findAndRegisterModules();
		
		PixDTO pixDTO =  objectMapper.readValue(data.get("after").toString(), PixDTO.class);
		System.out.println("Pix procesado: " + pixDTO.getIdentifier());
		
		if(pixDTO.getStatus().equals(PixStatus.EM_PROCESSAMENTO)) {
	        Pix pix = pixRepository.findByIdentifier(pixDTO.getIdentifier());
	
	        Key origem = keyRepository.findByChave(pixDTO.getChaveOrigem());
	        Key destino = keyRepository.findByChave(pixDTO.getChaveDestino());
				
	        if (origem == null || destino == null) {
	            pix.setStatus(PixStatus.ERRO);
	            throw new KeyNotFoundException();
	        } else {
	            pix.setStatus(PixStatus.PROCESSADO);
	        }
				
				       pix.setStatus(PixStatus.PROCESSADO);
				       pixRepository.save(pix);
		}
	}
}
