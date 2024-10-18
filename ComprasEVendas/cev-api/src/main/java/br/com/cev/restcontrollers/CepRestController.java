package br.com.cev.restcontrollers;

import br.com.cev.service.CEPService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CepRestController {

    private final CEPService cepService;

    public CepRestController(CEPService cepService) {
        this.cepService = cepService;
    }

    @GetMapping("/cep")
    public String getCep(){
        return cepService.getCep();
    }
}
