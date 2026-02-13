**🚗 Tabela FIPE - Consulta de Veículos (Java)**

**Aplicação Java console que consome a API pública da Tabela FIPE para:**

 - Listar marcas
 - Listar modelos por marca
 - Listar anos por modelo
 - Consultar valores por ano

**Calcular estatísticas de preços usando DoubleSummaryStatistics**

📌 **Sobre o Projeto**
Este projeto foi desenvolvido com foco em:
 - Prática de consumo de API REST com HttpClient
 - Desserialização JSON com Jackson
 - Uso de record no Java
 - Programação funcional com Stream API
 - Cálculo de estatísticas com DoubleSummaryStatistics
 - Organização em camadas (model, service, principal)

A aplicação consome a API pública: https://parallelum.com.br/fipe/api/v1/

****🛠️ Tecnologias Utilizadas****
 - Java 17+
 - Maven
 - HttpClient (Java nativo)
 - Jackson (JsonAlias, JsonIgnoreProperties)
 - Stream API
 - DoubleSummaryStatistics

**📂 Estrutura do Projeto**
```text
src
└── main<br>
    └── java
        └── com.tabelFipe.tabelaFipe
            ├── model
            │     ├── DadosDetalhesVeiculos
            │     ├── DadosModelo
            │     └── DadosVeiculo
            │
            ├── service
            │     ├── ConsumoApi
            │     └── ConverterDados
            │
            ├── principal
            │     └── Principal
            │
            └── TabelaFipeApplication
```
🧠 **Arquitetura**
📦 **model**

Responsável por representar os dados retornados pela API.

 - DadosVeiculo → Código e nome (usado para anos)
 - DadosModelo → Modelos de uma marca
 - DadosDetalhesVeiculos → Detalhes completos do veículo

Exemplo de JSON retornado pela API:
```
{
    "TipoVeiculo": 1,
    "Valor": "R$ 84.478,00",
    "Marca": "VW - VolksWagen",
    "Modelo": "AMAROK High.CD 2.0 16V TDI 4x4 Dies. Aut",
    "AnoModelo": 2013,
    "Combustivel": "Diesel"
}
```

🔧 **service**

**ConsumoApi**

Responsável por realizar chamadas HTTP usando HttpClient.

```
HttpClient.newHttpClient();
HttpRequest.newBuilder()
```

**ConverterDados**

Responsável por converter JSON em objetos Java utilizando Jackson.

🎯 **principal**

Classe responsável por:

 - Interação com usuário via Scanner
 - Construção dinâmica das URLs
 - Processamento de listas
 - Cálculo estatístico dos valores

🔁 **Fluxo da Aplicação**

 1. Usuário escolhe tipo de veículo (carro, moto, caminhão)
 2. Lista marcas
 3. Usuário escolhe marca
 4. Lista modelos
 5. Usuário escolhe modelo
 6. Lista anos disponíveis
 7. Para cada ano:
    - Consulta detalhes
    - Converte valor monetário para Double
8. Calcula estatísticas dos preços

**📊 Estatísticas de Preço**

Os valores vêm formatados como:
 - R$ 84.478,00


**Foi implementado um método para converter para Double:**
```
public Double getValorNumerico() {
String valorFormatado = valor
.replace("R$", "")
.replace(".", "")
.replace(",", ".")
.trim();

    return Double.parseDouble(valorFormatado);
}
```

**Uso de DoubleSummaryStatistics:**
```
DoubleSummaryStatistics estatisticas = lista.stream()
.mapToDouble(DadosDetalhesVeiculos::getValorNumerico)
.summaryStatistics();
```

A aplicação exibe:

 - Quantidade de anos
 - Menor valor
 - Maior valor
 - Média

💻 Como Executar
 1. Clonar o projeto 
    - git clone https://github.com/seu-usuario/seu-repositorio.git

2. Entrar na pasta 
   - cd seu-repositorio
   - rodar o projeto

🚀 **Conceitos Aplicados**

✔ Consumo de API REST<br>
✔ Desserialização com Jackson<br>
✔ Records no Java<br>
✔ Stream API<br>
✔ Programação Funcional<br>
✔ Tratamento de Strings monetárias<br>
✔ Estatísticas com DoubleSummaryStatistics<br>
✔ Organização em camadas<br>

👨‍💻 Autor

Erinaldo Raimundo da Silva Backend Java Developer