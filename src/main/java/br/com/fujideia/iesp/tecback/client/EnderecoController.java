package br.com.fujideia.iesp.tecback.client;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("endereco")
public class EnderecoController {
    private final ViaCepClient viaCepClient;

    @GetMapping("/cep/{cep}")
    public ResponseEntity<EnderecoDTO> getEndereco(@PathVariable String cep) {
        return ResponseEntity.ok(viaCepClient.buscarEnderecoPorCep(cep));
    }
}
