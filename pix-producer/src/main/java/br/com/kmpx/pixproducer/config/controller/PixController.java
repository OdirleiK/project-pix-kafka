package br.com.kmpx.pixproducer.config.controller;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.kmpx.pixproducer.dto.PixDTO;
import br.com.kmpx.pixproducer.dto.PixStatus;
import br.com.kmpx.pixproducer.service.PixService;

@RestController
@RequestMapping("/pix-producer")
public class PixController {
	
	@Autowired
	private PixService pixService;

	@PostMapping
    public PixDTO salvarPix(@RequestBody PixDTO pixDTO) {

        pixDTO.setIdentifier(UUID.randomUUID().toString());
        pixDTO.setDataTransferencia(LocalDateTime.now());
        pixDTO.setStatus(PixStatus.EM_PROCESSAMENTO);

        return pixService.salvarPix(pixDTO);
    }
}
