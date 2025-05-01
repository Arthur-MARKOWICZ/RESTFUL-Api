package com.aprendendo.test.service;

import com.aprendendo.test.domain.Repository.FuncionarioRepository;
import com.aprendendo.test.domain.Repository.TaskRepository;
import com.aprendendo.test.domain.model.Funcionario.Funcionario;
import com.aprendendo.test.domain.model.Task.Task;
import com.aprendendo.test.domain.model.Task.TaskCadastroDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final FuncionarioRepository funcionarioRepository;

    public TaskService(TaskRepository taskRepository, FuncionarioRepository funcionarioRepository) {
        this.taskRepository = taskRepository;
        this.funcionarioRepository = funcionarioRepository;
    }

    public Task createTask(TaskCadastroDTO dto) {
        Funcionario criador = funcionarioRepository.findById(dto.criadorId())
                .orElseThrow(() -> new EntityNotFoundException("Criador não encontrado"));
        Funcionario responsavel = funcionarioRepository.findById(dto.responsavelId())
                .orElseThrow(() -> new EntityNotFoundException("Responsável não encontrado"));

        Task task = new Task(
                dto.titulo(),
                dto.descricao(),
                dto.prioridade(),
                dto.status(),
                dto.prazo(),
                criador,
                responsavel
        );
        return taskRepository.save(task);
    }
}
