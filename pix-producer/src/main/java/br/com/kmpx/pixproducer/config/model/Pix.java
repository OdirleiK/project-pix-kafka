package br.com.kmpx.pixproducer.config.model;

import java.time.LocalDateTime;

import br.com.kmpx.pixproducer.dto.PixDTO;
import br.com.kmpx.pixproducer.dto.PixStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Pix {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String identifier;
	private String chaveOrigem;
	private String chaveDestino;
	private Double valor;
	private LocalDateTime dataTransferencia;
	@Enumerated(EnumType.STRING)
	private PixStatus status;

	public static Pix toEntity(PixDTO pixDTO) {
		Pix pix = new Pix();
		pix.setIdentifier(pixDTO.getIdentifier());
		pix.setChaveDestino(pixDTO.getChaveDestino());
		pix.setStatus(pixDTO.getStatus());
		pix.setValor(pixDTO.getValor());
		pix.setDataTransferencia(pixDTO.getDataTransferencia());
		pix.setChaveOrigem(pixDTO.getChaveOrigem());
		return pix;
	}
}
