package com.Restfulapi.controller;

import com.Restfulapi.domain.Repository.FuncionarioRepository;
import com.Restfulapi.domain.model.Funcionario.*;
import com.Restfulapi.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {
    private final FuncionarioService funcionarioService;
    @Autowired
    private FuncionarioRepository funcionarioRepository;
    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> acharFuncionarioPorId(@PathVariable Long id){
        Funcionario funcionario = funcionarioService.buscarPorId(id);
        return ResponseEntity.ok(funcionario);
    }

    @PostMapping("/cadastro")
    public  ResponseEntity<Funcionario> cadastrarNovoFuncionario(@RequestBody FuncionarioCadastroDTO dados){
        Funcionario funcionario = new Funcionario(dados);
        String encryptedPassword = new BCryptPasswordEncoder().encode(dados.senha());
        funcionario.setSenha(encryptedPassword);
        Funcionario funcionarioCriado = funcionarioService.criar(funcionario);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(funcionarioCriado.getId())
                .toUri();
        return ResponseEntity.created(location).body(funcionarioCriado);
    }

        @PutMapping("/alterardados/{id}")
        @Transactional
    public ResponseEntity<FuncionarioAlterarDadosResponseDTO> alteraDadosFuncionario(@PathVariable Long id, @RequestBody FuncionarioAlterarDadosDTO dados){
       FuncionarioAlterarDadosResponseDTO dto = funcionarioService.alterarDados(id,dados);
        return ResponseEntity.ok(dto);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}/Atualizarroles")
    public ResponseEntity<FuncionarioAlterarRoleResponseDTO> atualizarRol(@PathVariable Long id,
                                                                          @RequestBody AtualizarRoleDTO dados){
     FuncionarioAlterarRoleResponseDTO dto =  funcionarioService.alterarRole(id,dados);
        return ResponseEntity.ok(dto);
    }

}
