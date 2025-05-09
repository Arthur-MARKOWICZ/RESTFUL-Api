package com.Restfulapi.controller;

import com.Restfulapi.NoSecurityTestConfig;
import com.Restfulapi.domain.Repository.FuncionarioRepository;
import com.Restfulapi.domain.Repository.TaskRepository;
import com.Restfulapi.domain.model.Funcionario.Funcionario;
import com.Restfulapi.domain.model.Funcionario.FuncionarioCadastroDTO;
import com.Restfulapi.domain.model.Funcionario.Role;
import com.Restfulapi.domain.model.Task.*;
import com.Restfulapi.domain.model.enderco.EnderecoDTO;
import com.Restfulapi.service.TaskService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static com.Restfulapi.domain.model.Task.Prioridade.BAIXA;
import static com.Restfulapi.domain.model.Task.Prioridade.MEDIA;
import static com.Restfulapi.domain.model.Task.Status.A_FAZER;
import static com.Restfulapi.domain.model.Task.Status.EM_ANDAMENTO;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@Import(NoSecurityTestConfig.class)
@ActiveProfiles("teste")
@AutoConfigureTestDatabase
public class TaskControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private TaskService taskService;
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    private ObjectMapper objectMapper = new ObjectMapper();

    {
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }
    private Long criadorId;
    private Long responsavelId;

    @BeforeEach
    void setUp() {
        EnderecoDTO enderecoDTO1 = new EnderecoDTO("brasil", "parana", "curitiba", "rua test");
        FuncionarioCadastroDTO dados1 = new FuncionarioCadastroDTO("admin", enderecoDTO1, 7,
                "99415678", "admin@test.com", "test");
        String encryptedPassword1 = new BCryptPasswordEncoder().encode(dados1.senha());
        Funcionario admin = new Funcionario(dados1);
        admin.setSenha(encryptedPassword1);
        admin.setRole(Role.ADMIN);
        Funcionario adminSalvo = funcionarioRepository.save(admin);
        this.criadorId = adminSalvo.getId();

        EnderecoDTO enderecoDTO2 = new EnderecoDTO("brasil", "parana", "curitiba", "rua test");
        FuncionarioCadastroDTO dados2 = new FuncionarioCadastroDTO("test", enderecoDTO2, 7,
                "99415678", "test@test.com", "test");
        String encryptedPassword2 = new BCryptPasswordEncoder().encode(dados2.senha());
        Funcionario funcionario = new Funcionario(dados2);
        funcionario.setSenha(encryptedPassword2);
        Funcionario funcionarioSalvo = funcionarioRepository.save(funcionario);
        this.responsavelId = funcionarioSalvo.getId();
        System.out.println("responsavelId no BeforeEach: " + this.responsavelId);
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void cadastroTaskCase1() throws Exception {
        System.out.println("criadorId: " + criadorId);
        System.out.println("responsavelId: " + responsavelId);
        LocalDateTime localDateTime = LocalDateTime.of(2025, 7, 25, 5, 0);

        TaskCadastroDTO dto = new TaskCadastroDTO("test", "teste para o cadastro de task", BAIXA,
                A_FAZER, localDateTime, criadorId, responsavelId);
        Task task = taskService.createTask(dto);
        System.out.println("Task criada: " + task);

        mockMvc.perform(post("/task/cadastro")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }

    @Test
    void pegarTodasTaskCase1() throws Exception {
        Pageable pageable = PageRequest.of(0, 10);
        LocalDateTime localDateTime = LocalDateTime.of(2025, 7, 25, 5, 0);
        TaskCadastroDTO dto = new TaskCadastroDTO("test", "teste para o cadastro de task", BAIXA,
                A_FAZER, localDateTime, criadorId, responsavelId);
        taskService.createTask(dto);

        mockMvc.perform(get("/task/all")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].titulo").value("test"));
    }
    @Test
    void trocarStatusCase1() throws Exception{
        LocalDateTime localDateTime = LocalDateTime.of(2025, 7, 25, 5, 0);

        TaskCadastroDTO dto = new TaskCadastroDTO("test", "teste para o cadastro de task", BAIXA,
                A_FAZER, localDateTime, criadorId, responsavelId);
        Task task = taskService.createTask(dto);
        Long id = task.getId();
        TrocaStatusDTO dados = new TrocaStatusDTO(EM_ANDAMENTO);
        Task task1 = taskRepository.getReferenceById(id);
        Status novoStatus = dados.status();
        task1.setStatus(novoStatus);

        mockMvc.perform(put("/task/trocaStatus/{id}",id)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(task1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("EM_ANDAMENTO"));
    }
    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void trocarPrioridadeCase1() throws Exception{
        LocalDateTime localDateTime = LocalDateTime.of(2025, 7, 25, 5, 0);

        TaskCadastroDTO dto = new TaskCadastroDTO("test", "teste para o cadastro de task", BAIXA,
                A_FAZER, localDateTime, criadorId, responsavelId);
        Task task = taskService.createTask(dto);
        Long id = task.getId();
        PrioridadeDTO dados = new PrioridadeDTO(MEDIA);
        Task task1 = taskRepository.getReferenceById(id);
        Prioridade novoPrioriade = dados.prioridade();
        task1.setPrioridade(novoPrioriade);

        mockMvc.perform(put("/task/trocaPrioridade/{id}",id)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(task1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.prioridade").value("MEDIA"));
    }

}