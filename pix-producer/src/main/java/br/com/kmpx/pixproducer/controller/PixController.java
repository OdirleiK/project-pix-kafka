package br.com.kmpx.pixproducer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.kmpx.pixproducer.model.Produto;
import br.com.kmpx.pixproducer.service.PixService;

@RestController
@RequestMapping("/pix")
public class PixController {

    //private final PixService pixService;

    @Autowired
    private KafkaTemplate<String, Produto> kafkaTemplate;
//    @PostMapping
//    public PixDTO salvarPix(@RequestBody PixDTO pixDTO) {
//
//        pixDTO.setIdentifier(UUID.randomUUID().toString());
//        pixDTO.setDataTransferencia(LocalDateTime.now());
//        pixDTO.setStatus(PixStatus.EM_PROCESSAMENTO);
//
//        return pixService.salvarPix(pixDTO);
//    }
    
    @GetMapping("/produtor/{message}/{name}")
    public void produtor(@PathVariable String message,
    					 @PathVariable String name) {
    	Produto produto = new Produto(message, name);
    	this.kafkaTemplate.send("pix.teste1", produto);
    }
}