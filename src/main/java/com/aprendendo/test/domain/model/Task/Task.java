package com.aprendendo.test.domain.model.Task;

import com.aprendendo.test.domain.model.Funcionario.Funcionario;
import com.aprendendo.test.domain.model.User.User;
import com.aprendendo.test.service.FuncionarioService;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;


@Entity
@Table(name = "tb_task")
public class Task {
    @Autowired
    FuncionarioService funcionarioServicel;
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

}
