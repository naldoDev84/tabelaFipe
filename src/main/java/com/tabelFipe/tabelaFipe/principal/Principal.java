package com.tabelFipe.tabelaFipe.principal;

import com.tabelFipe.tabelaFipe.service.ConsumoApi;

import java.util.Scanner;

public class Principal {
    Scanner scan = new  Scanner(System.in);
    int opcao = 0;
    ConsumoApi consumoApi = new ConsumoApi();

    public void exibirMenu(){
        System.out.println("------ Menu Principal ------");
        System.out.println(" 1 - Carro" +
                "\n 2 - Moto" +
                "\n 3 - Caminhão");

        System.out.print("Escolha o tipo do veículo: ");
        opcao = scan.nextInt();

        String link = "https://parallelum.com.br/fipe/api/v1/";

        switch (opcao){
            case 1:
                consumoApi.obterDados(link + "carros/marcas");
                break;
            case 2:
                consumoApi.obterDados(link + "motos/marcas");
                break;
            case 3:
                consumoApi.obterDados(link + "caminhoes/marcas");
                break;
        }
    }
}
