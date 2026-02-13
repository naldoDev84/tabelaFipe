package com.tabelFipe.tabelaFipe.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosVeiculo(
        String codigo,
        String nome
) {
    @Override
    public String toString() {
        return  "codigo: " + codigo +
                " - descricao:'" + nome ;
    }
}
