package com.Restfulapi.domain.model.Funcionario;

import com.Restfulapi.domain.model.enderco.Endereco;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "tb_funcionario")
public class Funcionario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Embedded
    private Endereco endereco;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
    private String senha;
    private double numeroDeHorasPorDia;
    private String telefone;
    private String email;
    public Funcionario(){}
    public Funcionario(FuncionarioCadastroDTO dados) {
        this.nome = dados.nome();
        this.endereco = new Endereco(dados.endereco());
        this.numeroDeHorasPorDia = dados.numeroDeHorasPorDia();
        this.telefone = dados.telefone();
        this.email = dados.email();
        this.senha = dados.senha();
        this.role = Role.FUNCIONARIO;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public double getNumeroDeHorasPorDia() {
        return numeroDeHorasPorDia;
    }

    public void setNumeroDeHorasPorDia(double numeroDeHorasPorDia) {
        this.numeroDeHorasPorDia = numeroDeHorasPorDia;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void alterarDados(FuncionarioAlterarDadosDTO dados) {
        if(dados.nome() != null){
            this.nome = dados.nome();
        }
        if (dados.endereco() != null){
            this.endereco = new Endereco(dados.endereco());
        }
        if(dados.email() != null){
            this.email = dados.email();
        }
        if(dados.numeroDeHorasPorDia() != 0){
            this.numeroDeHorasPorDia = dados.numeroDeHorasPorDia();
        }
        if(dados.telefone() != null){
            this.telefone = dados.telefone();
        }
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
