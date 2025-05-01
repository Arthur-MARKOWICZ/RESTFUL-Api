package com.aprendendo.test.controller;

import com.aprendendo.test.domain.Repository.TaskRepository;
import com.aprendendo.test.domain.model.Task.Task;
import com.aprendendo.test.domain.model.Task.TaskCadastroDTO;
import com.aprendendo.test.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
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
}
