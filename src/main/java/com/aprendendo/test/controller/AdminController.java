package com.aprendendo.test.controller;

import com.aprendendo.test.domain.Repository.FuncionarioRepository;
import com.aprendendo.test.domain.model.Funcionario.Funcionario;
import com.aprendendo.test.domain.model.Funcionario.FuncionarioDeletarDTO;
import com.aprendendo.test.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    FuncionarioService funcionarioService;
    @Autowired
    FuncionarioRepository funcionarioRepository;

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    @Transactional
    public ResponseEntity<FuncionarioDeletarDTO> deletarFuncionario(@PathVariable Long id){
        Funcionario funcionario =  funcionarioService.findById(id);
        FuncionarioDeletarDTO dto = new FuncionarioDeletarDTO(funcionario);
        funcionarioRepository.delete(funcionario);
        return ResponseEntity.ok(dto);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<Funcionario>> listarFuncionarios(){
        List<Funcionario> funcionarios = funcionarioService.getall();
        return ResponseEntity.ok(funcionarios);
    }
}
