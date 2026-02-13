package com.tabelFipe.tabelaFipe.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.util.List;


public class ConverterDados {
    private ObjectMapper mapper = new ObjectMapper();

    public<T> T converter(String json, Class<T> classe) {
        try {
            return mapper.readValue(json, classe);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    public <T> T converterLista(String json, TypeReference<T> typeReference){
        try {
            return mapper.readValue(json, typeReference);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    //outra forma:
/*    public <T> List<T> obterLista(String json, Class<T> classe){
        CollectionType lista = mapper.getTypeFactory()
            .constructCollectionType(List.class, classe);

        try {
            return mapper.readValue(json, lista);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }*/

}
