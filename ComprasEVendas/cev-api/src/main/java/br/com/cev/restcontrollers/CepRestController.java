package br.com.cev.restcontrollers;

import br.com.cev.dto.CepDto;
import br.com.cev.service.CEPService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class CepRestController extends AbstractRestController {

    private final CEPService cepService;

    public CepRestController(CEPService cepService) {
        this.cepService = cepService;
    }

    @GetMapping("/cep")
    public ResponseEntity<CepDto> getCep(
            @RequestParam String cep
    ){
        Optional<CepDto> cepOptional = cepService.getCep(cep);

        return cepOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/logged/teste")
    public String teste(){
        return "teste";
    }
}
