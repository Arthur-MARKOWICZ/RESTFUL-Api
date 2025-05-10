package com.Restfulapi.domain.model.Task;

import com.Restfulapi.domain.model.Funcionario.Funcionario;

import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "tb_task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String titulo;
    @Enumerated(EnumType.STRING)
    private Prioridade prioridade;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String descricao;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime prazo;
    @ManyToOne(optional = false)
    @JoinColumn(name = "criador_id", nullable = false, updatable = false)
    private Funcionario criador;
    @ManyToOne(optional = true)
    @JoinColumn(name = "responsavel_id", nullable = true)
    private Funcionario responsavel;
    public Task(){}
    public Task(String titulo,
                String descricao,
                Prioridade prioridade,
                Status status,
                LocalDateTime prazo,
                Funcionario criador,
                Funcionario responsavel) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.prioridade = prioridade;
        this.status = status;
        this.prazo = prazo;
        this.criador = criador;
        this.responsavel = responsavel;
    }


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Prioridade getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getPrazo() {
        return prazo;
    }

    public void setPrazo(LocalDateTime prazo) {
        this.prazo = prazo;
    }

    public Funcionario getCriador() {
        return criador;
    }

    public void setCriador(Funcionario criador) {
        this.criador = criador;
    }

    public Funcionario getResponsavel() {
        return responsavel;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setResponsavel(Funcionario responsavel) {
        this.responsavel = responsavel;
    }
}

