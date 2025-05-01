package com.aprendendo.test.controller;

import com.aprendendo.test.domain.Repository.FuncionarioRepository;
import com.aprendendo.test.domain.model.Funcionario.*;
import com.aprendendo.test.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {
    private final FuncionarioService funcionarioService;
    @Autowired
    private FuncionarioRepository funcionarioRepository;
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
        String encryptedPassword = new BCryptPasswordEncoder().encode(dados.senha());
        funcionario.setSenha(encryptedPassword);
        funcionario.setRole(Role.FUNCIONARIO);
        Funcionario funcionarioCriado = funcionarioService.criar(funcionario);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(funcionarioCriado.getId())
                .toUri();
        return ResponseEntity.created(location).body(funcionarioCriado);
    }
    @DeleteMapping("/delete/{id}")
    @Transactional
    public  ResponseEntity<FuncionarioDeletarDTO> deletarFuncionario(@PathVariable Long id){
       Funcionario funcionario =  funcionarioService.findById(id);
       FuncionarioDeletarDTO dto = new FuncionarioDeletarDTO(funcionario);
       funcionarioRepository.delete(funcionario);
       return ResponseEntity.ok(dto);
    }
        @PutMapping("/alterardados/{id}")
        @Transactional
    public ResponseEntity<FuncionarioAlterarDadosResponseDTO> alteraDadosFuncionario(@PathVariable Long id, @RequestBody FuncionarioAlterarDadosDTO dados){
        Funcionario funcionario = funcionarioService.findById(id);
        funcionario.alterarDados(dados);
        FuncionarioAlterarDadosResponseDTO dto = new FuncionarioAlterarDadosResponseDTO(funcionario);
        return ResponseEntity.ok(dto);
    }
    @PutMapping("/{id}/Atualizarroles")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity atualizarRol( @PathVariable Long id,
                                        @RequestBody AtualizarRoleDTO dados){
        Funcionario funcionario = funcionarioService.findById(id);
        funcionario.setRole(dados.role());
        funcionarioRepository.save(funcionario);

        return ResponseEntity.ok().build();
    }

}
