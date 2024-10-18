package br.com.cev.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CEPServiceTest {

    @Autowired
    private CEPService cepService;

    @Test
    public void cpfTest(){
        Assertions.assertThat(cepService.getCep()).isNotNull();
    }

}