package br.com.kmpx.pixproducer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.alura.pix.avro.PixRecord;

import br.com.kmpx.pixproducer.config.model.Pix;
import br.com.kmpx.pixproducer.dto.PixDTO;
import br.com.kmpx.pixproducer.repositories.PixRepository;

@Service
public class PixService {

	@Autowired
	private KafkaTemplate<String, PixRecord> kafkaTemplate;

	@Autowired
	private PixRepository pixRepository;

	public PixDTO salvarPix (PixDTO pixDTO) { 
        pixRepository.save(Pix.toEntity(pixDTO));

        PixRecord pixRecord = PixRecord.newBuilder()
                            .setIdentifier(pixDTO.getIdentifier()) 
                            .setChaveOrigem (pixDTO.getChaveOrigem()) 
                            .setChaveDestino (pixDTO.getChaveDestino()) 
                            .setStatus (pixDTO.getStatus().toString()) 
                            .setDataTransferencia (pixDTO.getDataTransferencia().toString()) 
                            .setValor (pixDTO.getValor()) 
                            .build();

        kafkaTemplate.send("pix-topic", pixDTO.getIdentifier(), pixRecord); 
        return pixDTO;
    }

}
