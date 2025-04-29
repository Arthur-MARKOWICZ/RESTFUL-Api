package com.aprendendo.test.controller;

import com.aprendendo.test.domain.model.Funcionario.Funcionario;
import com.aprendendo.test.domain.model.Funcionario.FuncionarioCadastroDTO;
import com.aprendendo.test.service.FuncionarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {
    private final FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> acharFuncionarioPorId(@PathVariable Long id){
        var funcionario = funcionarioService.findById(id);
        return ResponseEntity.ok(funcionario);
    }
    @GetMapping("/all")
    public ResponseEntity<List<Funcionario>> listarFuncionarios(){
        List<Funcionario> funcionarios = funcionarioService.getall();
        return ResponseEntity.ok(funcionarios);
    }
    @PostMapping("/cadastro")
    public  ResponseEntity<Funcionario> cadastrarNovoFuncionario(@RequestBody FuncionarioCadastroDTO dados){
        Funcionario funcionario = new Funcionario(dados);
        Funcionario funcionarioCriado = funcionarioService.criar(funcionario);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(funcionarioCriado.getId())
                .toUri();
        return ResponseEntity.created(location).body(funcionarioCriado);
    }
}
