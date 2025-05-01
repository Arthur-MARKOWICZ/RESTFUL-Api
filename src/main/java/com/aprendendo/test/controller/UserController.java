package com.aprendendo.test.controller;

import com.aprendendo.test.domain.Repository.UserRepository;
import com.aprendendo.test.domain.model.Funcionario.Funcionario;
import com.aprendendo.test.domain.model.Funcionario.FuncionarioAlterarDadosDTO;
import com.aprendendo.test.domain.model.Funcionario.FuncionarioAlterarDadosResponseDTO;
import com.aprendendo.test.domain.model.User.*;
import com.aprendendo.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    @Autowired
    UserRepository userRepository;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> pegarUsuarioProId(@PathVariable Long id){
        var user = userService.findById(id);
        return ResponseEntity.ok(user);
    }
    @GetMapping("/all")
    public ResponseEntity<List<User>> pegarTodosUsuarios(){
        List<User> users = userService.getall();
        return ResponseEntity.ok(users);
    }
    @PostMapping("/cadastro")
    public  ResponseEntity<User> CadastrarUsuario(@RequestBody UserCadastroDTO dados){
        User user = new User(dados);
        var userCriado = userService.criar(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(userCriado.getId())
                .toUri();
        return ResponseEntity.created(location).body(userCriado);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<UserDeletarDTO> deletarUser(@PathVariable Long id){
        User user = userService.findById(id);
        UserDeletarDTO dto = new UserDeletarDTO(user);
        userRepository.delete(user);
        return ResponseEntity.ok(dto);
    }
    @PutMapping("/alterardados/{id}")
    @Transactional
    public ResponseEntity<UserAlterarDadosResponseDTO> alteraDadosFuncionario(@PathVariable Long id, @RequestBody UserAlterarDadosDTO dados){
        User user = userService.findById(id);
        user.alterarDados(dados);
        UserAlterarDadosResponseDTO dto = new UserAlterarDadosResponseDTO(user);
        return ResponseEntity.ok(dto);
    }
}
