package com.Restfulapi.controller;


import com.Restfulapi.domain.model.Funcionario.Funcionario;
import com.Restfulapi.domain.model.Funcionario.FuncionarioCadastroDTO;
import com.Restfulapi.domain.model.Funcionario.Role;
import com.Restfulapi.domain.model.LoginDTO;
import com.Restfulapi.domain.model.LoginResponseDTO;
import com.Restfulapi.infra.TokenService;
import com.Restfulapi.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AutheticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    private final FuncionarioService funcionarioService;
    @Autowired
    private TokenService tokenService;
    public AutheticationController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @PostMapping("/funcionario/login")
    public  ResponseEntity loginFuncionario(@RequestBody LoginDTO dados){
        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(dados.email(),dados.senha());
        Authentication authetication = authenticationManager.authenticate(usernamePassword);
        Funcionario funcionario = (Funcionario) authetication.getPrincipal();
        String token = tokenService.generateToken(funcionario);
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }
    @PostMapping("/cadastro")
    public ResponseEntity cadastroAdmin(@RequestBody FuncionarioCadastroDTO dados){
        Funcionario admin = new Funcionario(dados);
        String encryptedPassword = new BCryptPasswordEncoder().encode(dados.senha());
        admin.setSenha(encryptedPassword);
        admin.setRole(Role.ADMIN);
        Funcionario funcionarioCriado = funcionarioService.criar(admin);
        return ResponseEntity.ok(funcionarioCriado);
    }
}
