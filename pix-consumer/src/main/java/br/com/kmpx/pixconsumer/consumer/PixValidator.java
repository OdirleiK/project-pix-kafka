package br.com.kmpx.pixconsumer.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import br.com.kmpx.pixconsumer.dto.PixDTO;
import br.com.kmpx.pixconsumer.dto.PixStatus;
import br.com.kmpx.pixconsumer.model.Key;
import br.com.kmpx.pixconsumer.model.Pix;
import br.com.kmpx.pixconsumer.repositories.KeyRepository;
import br.com.kmpx.pixconsumer.repositories.PixRepository;

@Service
public class PixValidator {

    @Autowired
    private KeyRepository keyRepository;

    @Autowired
    private PixRepository pixRepository;

    @KafkaListener(topics = "PIX_TOPIC", groupId = "grupo")
    public void processaPix(PixDTO pixDTO) {
        System.out.println("Pix  recebido: " + pixDTO.getIdentifier());

        Pix pix = pixRepository.findByIdentifier(pixDTO.getIdentifier());

        Key origem = keyRepository.findByChave(pixDTO.getChaveOrigem());
        Key destino = keyRepository.findByChave(pixDTO.getChaveDestino());

        if (origem == null || destino == null) {
            pix.setStatus(PixStatus.ERRO);
        } else {
            pix.setStatus(PixStatus.PROCESSADO);
        }
        pixRepository.save(pix);
    }

}