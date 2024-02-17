package br.com.kmpx.pixproducer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import br.com.kmpx.pixproducer.config.model.Pix;
import br.com.kmpx.pixproducer.dto.PixDTO;
import br.com.kmpx.pixproducer.repositories.PixRepository;

@Service
public class PixService {

    @Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;
    
    @Autowired
    private PixRepository pixRepository;

	public PixDTO salvarPix(PixDTO pixDTO) {
		pixRepository.save(Pix.toEntity(pixDTO));
        kafkaTemplate.send("pix-topic", pixDTO);
        return pixDTO;
	}
	
	
}
