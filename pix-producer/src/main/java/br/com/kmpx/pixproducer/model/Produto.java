package br.com.kmpx.pixproducer.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Produto {

	String message;
	String name;
	
	public Produto(String message, String name) {
		this.message = message;
		this.name = name;
	}
	
	
}
