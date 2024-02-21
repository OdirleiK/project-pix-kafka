package br.com.kmpx.pixconsumer.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class PixDTO {
    private String identifier;
    @JsonProperty("chave_origem")
    private String chaveOrigem;
    @JsonProperty("chave_destino")
    private String chaveDestino;
    private Double valor;
    @JsonProperty("data_transferencia")
    private LocalDateTime dataTransferencia;
    private PixStatus status;
}