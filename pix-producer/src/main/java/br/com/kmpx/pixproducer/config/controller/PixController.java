package br.com.kmpx.pixproducer.config.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.kmpx.pixproducer.config.model.Pix;
import br.com.kmpx.pixproducer.service.PixService;

@RestController
@RequestMapping("/pix-producer")
public class PixController {
	
	@Autowired
	PixService service;

	@PostMapping("/publish")
    public void sendEvents(@RequestBody Pix pix) {
        service.sendEventsToTopic(pix);
    }
}
