package com.tabelFipe.tabelaFipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosDetalhesVeiculos(
        @JsonAlias("Valor") String valor,
        @JsonAlias("Marca") String marca,
        @JsonAlias("Modelo") String modelo,
        @JsonAlias("AnoModelo") Integer anoModelo) {

    public Double getValorNumerico() {
        if (valor == null || valor.isEmpty()) return 0.0;

        String valorFormatado = valor
                .replace("R$", "")
                .replace(".", "")
                .replace(",", ".")
                .trim();
        return Double.parseDouble(valorFormatado);
    }

    @Override
    public String toString() {
        return "Veiculo - " +
                " valor: " + valor +
                " marca: " + marca +
                " modelo: " + modelo +
                " anoModelo: " + anoModelo;
    }
}


