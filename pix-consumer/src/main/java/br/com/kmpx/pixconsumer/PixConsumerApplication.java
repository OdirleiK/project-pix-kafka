package br.com.kmpx.pixconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class PixConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PixConsumerApplication.class, args);
	}

}
