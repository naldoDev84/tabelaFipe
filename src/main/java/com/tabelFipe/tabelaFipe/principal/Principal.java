package com.tabelFipe.tabelaFipe.principal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tabelFipe.tabelaFipe.model.DadosDetalhesVeiculos;
import com.tabelFipe.tabelaFipe.model.DadosModelo;
import com.tabelFipe.tabelaFipe.model.DadosVeiculo;
import com.tabelFipe.tabelaFipe.service.ConsumoApi;
import com.tabelFipe.tabelaFipe.service.ConverterDados;

import java.util.*;

public class Principal {
    Scanner scan = new  Scanner(System.in);
    int opcao = 0;
    ConsumoApi consumoApi = new ConsumoApi();
    ConverterDados converter = new ConverterDados();

    public void exibirMenu() throws JsonProcessingException {
        System.out.println("------ Menu Principal ------");
        System.out.println(" 1 - Carro" +
                "\n 2 - Moto" +
                "\n 3 - Caminhão");

        System.out.print("Escolha o tipo do veículo: ");
        opcao = scan.nextInt();

        String linkBase = "https://parallelum.com.br/fipe/api/v1/";
        String endpoint = "";

        switch (opcao){
            case 1:
                endpoint = "carros/marcas/";
                break;
            case 2:
                endpoint = "motos/marcas/";
                break;
            case 3:

                endpoint = "caminhoes/marcas/";
                break;
            default:
                System.out.println("Opção inválida");
                return;
        }

        String jsonMarcas = consumoApi.obterDados(linkBase + endpoint);

        List<DadosVeiculo> listaMarcas =
                Arrays.asList(converter.converter(jsonMarcas, DadosVeiculo[].class));

        listaMarcas.forEach(System.out::println);

        //Parte 2
        System.out.print("\nDigite o código do veículo: ");
        opcao = scan.nextInt();

        String linkModelos = linkBase + endpoint + opcao + "/modelos";

        String jsonModelos = consumoApi.obterDados(linkModelos);

        DadosModelo respostaModelo =
                converter.converter(jsonModelos, DadosModelo.class);

        List<DadosVeiculo> listaModelos = respostaModelo.modelos();

        listaModelos.forEach(System.out::println);

        //Parte 3
        System.out.println("\nDigite um trecho do nome do veículo: ");
        String trechoVeiculo = scan.next().toLowerCase();

        List<DadosVeiculo> veiculosFiltrados = listaModelos.stream()
                .filter(v -> v.nome().toLowerCase()
                        .contains(trechoVeiculo))
                .toList();
        veiculosFiltrados.forEach(System.out::println);

        //Parte 4
        System.out.println("\nDigite o código do modelo para consultar os valores: ");
        String codigoVeiculo =  scan.next().toLowerCase();

        String linkAnosVeiculos =  linkBase + endpoint + opcao + "/modelos/" + codigoVeiculo + "/anos";

        String jsonAnosVeiculos = consumoApi.obterDados(linkAnosVeiculos);

        DadosVeiculo[] listaAnosVeiculos =
                converter.converter(jsonAnosVeiculos, DadosVeiculo[].class);

        List<DadosDetalhesVeiculos> listaDetalhesVeiculos = new ArrayList<>();

        System.out.println("\nTodos os veículos do modelo " + codigoVeiculo + " com os valores por ano: ");
        for (DadosVeiculo ano : listaAnosVeiculos) {
            String codioAno = ano.codigo();
            String linkDetalhe = linkAnosVeiculos + "/" + codioAno;
            String jsonDetalhe = consumoApi.obterDados(linkDetalhe);

            DadosDetalhesVeiculos detalhesVeiculo =
                    converter.converter(jsonDetalhe, DadosDetalhesVeiculos.class);
            listaDetalhesVeiculos.add(detalhesVeiculo);
            System.out.println(detalhesVeiculo);
        }
        System.out.println("\n Detalhes: ");
        DoubleSummaryStatistics statistics = listaDetalhesVeiculos.stream()
                .mapToDouble(DadosDetalhesVeiculos::getValorNumerico)
                .summaryStatistics();
        System.out.print("Qtde veículos: " + statistics.getCount());
        System.out.printf("\nMaior valor: R$ %.2f",  statistics.getMax());
        System.out.printf("\nMenor valor: R$ %.2f", statistics.getMin());
        System.out.printf("\nMédia de valor: R$ %.2f",  statistics.getAverage());
    }
}
