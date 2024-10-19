package br.com.cev.service;

import br.com.cev.dto.CepDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;
import java.util.Optional;

@Service
public class CEPService {

    private final RestTemplate restTemplate;

    private final String urlApiCep;

    public CEPService(
            RestTemplate restTemplate,
            @Value("${cep.api}") String urlApiCep
    ) {
        this.restTemplate = restTemplate;
        this.urlApiCep = urlApiCep;
    }

    public Optional<CepDto> getCep(String cep) {

        ResponseEntity<CepDto> responseEntity = restTemplate.getForEntity(
                urlApiCep + "/api/cep?cep=" + cep,
                CepDto.class
        );


        if(
                Objects.equals(
                        HttpStatus.OK.value(),
                        responseEntity.getStatusCode().value()
                )
        ){
            return Optional.of(responseEntity.getBody());
        }

        return Optional.empty();
    }

}