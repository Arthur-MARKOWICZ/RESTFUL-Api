package com.Restfulapi.service;

import com.Restfulapi.domain.Repository.FuncionarioRepository;
import com.Restfulapi.domain.Repository.TaskRepository;
import com.Restfulapi.domain.model.Funcionario.Funcionario;
import com.Restfulapi.domain.model.Task.Task;
import com.Restfulapi.domain.model.Task.TaskCadastroDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
        taskRepository.save(task);
        return task;
    }
    public Page<Task> pegarTodasTask(Pageable pageable){
        Page<Task> tasks = taskRepository.findAll(pageable);
        return  tasks;
    }
}

