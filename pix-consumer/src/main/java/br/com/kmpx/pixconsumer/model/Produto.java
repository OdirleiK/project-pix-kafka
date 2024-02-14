package br.com.kmpx.pixconsumer.model;

import lombok.Data;

@Data
public class Produto {

	String message;
	String name;
	
	public Produto(String message, String name) {
		this.message = message;
		this.name = name;
	}
	
	
}
