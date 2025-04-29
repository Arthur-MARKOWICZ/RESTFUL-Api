package com.aprendendo.test.controller;

import com.aprendendo.test.domain.Repository.AdminRepository;
import com.aprendendo.test.domain.model.Admin.Admin;
import com.aprendendo.test.domain.model.Admin.CadastroAdminDTO;
import com.aprendendo.test.domain.model.LoginDTO;
import com.aprendendo.test.domain.model.LoginResponseDTO;
import com.aprendendo.test.infra.TokenService;
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
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/cadastro")
    public ResponseEntity cadastroAdmin(@RequestBody CadastroAdminDTO dados){
        String encryptedPassword = new BCryptPasswordEncoder().encode(dados.senha());
        Admin newAdmin = new Admin(dados);
        newAdmin.setSenha(encryptedPassword);
        System.out.println(dados);
        System.out.println(newAdmin);
        this.adminRepository.save(newAdmin);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/login")
    public  ResponseEntity loginAdmin(@RequestBody LoginDTO dados){
        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(dados.email(),dados.senha());
        Authentication authetication = authenticationManager.authenticate(usernamePassword);
        Admin admin = (Admin) authetication.getPrincipal();
        String token = tokenService.generateToken(admin);
        return ResponseEntity.ok(new LoginResponseDTO(token));


    }
}
