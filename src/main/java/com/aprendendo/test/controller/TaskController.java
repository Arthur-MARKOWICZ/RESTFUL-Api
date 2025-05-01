package com.aprendendo.test.controller;

import com.aprendendo.test.domain.Repository.TaskRepository;
import com.aprendendo.test.domain.model.Task.*;
import com.aprendendo.test.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;
    private final TaskService taskService;


    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/cadastro")
    public ResponseEntity cadastroTask(@RequestBody TaskCadastroDTO dados){
        Task newTask = taskService.createTask(dados);
        taskRepository.save(newTask);
        return ResponseEntity.ok(newTask);
    }
    @GetMapping("/all")
    public ResponseEntity<Page<Task>> pegarTodasTask(Pageable pageable){
        Page<Task> tasks = taskRepository.findAll(pageable);
        return ResponseEntity.ok(tasks);
    }
    @PutMapping("/trocaStatus/{id}")
    @Transactional
    public ResponseEntity trocarStatus(@PathVariable Long id, @RequestBody TrocaStatusDTO dados){
       Task task = taskRepository.getReferenceById(id);
        Status novoStatus = dados.status();
       task.setStatus(novoStatus);
       return ResponseEntity.ok(task);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/trocaPrioridade/{id}")
    @Transactional
    public ResponseEntity trocarPrioridade(@PathVariable Long id, @RequestBody PrioridadeDTO dados){
        Task task = taskRepository.getReferenceById(id);
        Prioridade novaPrioridade = dados.prioridade();
        task.setPrioridade(novaPrioridade);
        return ResponseEntity.ok(task);
    }

}
