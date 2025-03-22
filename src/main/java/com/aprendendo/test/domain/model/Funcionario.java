package com.aprendendo.test.domain.model;

import com.aprendendo.test.domain.model.enderco.Endereco;
import jakarta.persistence.*;

@Entity(name = "tb_funcionario")
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    @Embedded
    private Endereco endereco;
    private double numeroDeHorasPorDia;
    private String telefone;
    private String email;

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
}
