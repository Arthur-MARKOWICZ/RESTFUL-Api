package com.aprendendo.test.controller;

import com.aprendendo.test.domain.model.User.User;
import com.aprendendo.test.domain.model.User.UserCadastroDTO;
import com.aprendendo.test.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

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
}
