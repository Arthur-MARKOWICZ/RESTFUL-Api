package com.aprendendo.test.domain.model.User;

import com.aprendendo.test.domain.model.enderco.Endereco;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    @Embedded
    private Endereco endereco;
    private String email;
    private String senha;
    public User(){}

    public User(UserCadastroDTO dados) {
        this.nome = dados.nome();
        this.endereco = new Endereco(dados.endereco());
        this.email = dados.email();
        this.senha = dados.senha();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
