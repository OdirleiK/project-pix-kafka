package br.com.kmpx.pixconsumer.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class PixDTO {
    private String identifier;
    private String chaveOrigem;
    private String chaveDestino;
    private Double valor;
    private LocalDateTime dataTransferencia;
    private PixStatus status;
}