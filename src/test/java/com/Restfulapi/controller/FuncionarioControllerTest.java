package com.Restfulapi.controller;

import com.Restfulapi.NoSecurityTestConfig;
import com.Restfulapi.domain.Repository.FuncionarioRepository;
import com.Restfulapi.domain.model.Funcionario.*;

import com.Restfulapi.domain.model.enderco.EnderecoDTO;
import com.Restfulapi.infra.TokenService;
import com.Restfulapi.service.impl.FuncionarioServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@Import(NoSecurityTestConfig.class)

@ActiveProfiles("teste")
class FuncionarioControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private FuncionarioRepository funcionarioRepository;
    @MockitoBean
    private FuncionarioServiceImpl funcionarioService;
    @MockitoBean
    private TokenService tokenService;

    private ObjectMapper objectMapper = new ObjectMapper();
    @BeforeEach
    void setup() {
        EnderecoDTO enderecoDTO = new EnderecoDTO("brasil","parana","curitiba","rua test");
        FuncionarioCadastroDTO dados = new FuncionarioCadastroDTO("admin",enderecoDTO,7
                ,"99415678","test@test.com","test");
        String encryptedPassword = new BCryptPasswordEncoder().encode(dados.senha());
        Funcionario admin = new Funcionario(dados);
        admin.setSenha(encryptedPassword);
        admin.setRole(Role.ADMIN);
        funcionarioRepository.save(admin);
    }
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    @Test
    @DisplayName("deve cadastrar o funcionario e retornar o funcionario criado")
    void cadastroFuncionarioCase1() throws Exception {
        EnderecoDTO enderecoDTO = new EnderecoDTO("brasil", "parana", "curitiba", "rua test");
        FuncionarioCadastroDTO dados = new FuncionarioCadastroDTO("test", enderecoDTO, 7,
                "99415678", "test@test.com", "test");

        Funcionario funcionario = new Funcionario(dados);
        funcionario.setId(1L);
        when(funcionarioService.criar(any(Funcionario.class))).thenReturn(funcionario);

        mockMvc.perform(post("/funcionarios/cadastro")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dados)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", containsString("/cadastro/1")))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("test"))
                .andExpect(jsonPath("$.email").value("test@test.com"));
    }
    @Test
    @DisplayName("deve alterar a role do funcionario")
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void atualizarRolCase1() throws Exception {
        EnderecoDTO enderecoDTO = new EnderecoDTO("brasil", "parana", "curitiba", "rua test");
        FuncionarioCadastroDTO dados = new FuncionarioCadastroDTO("test", enderecoDTO, 7,
                "99415678", "test@test.com", "test");

        Funcionario funcionario = new Funcionario(dados);
        funcionario.setRole(Role.FUNCIONARIO);

        Funcionario funcionarioSalvo = funcionarioRepository.save(funcionario);
        Long id = funcionarioSalvo.getId();
        AtualizarRoleDTO dadosAtualizar = new AtualizarRoleDTO(Role.ADMIN);
        Funcionario funcionarioAchado = funcionarioRepository.getReferenceById(id);
        funcionarioAchado.setRole(dadosAtualizar.role());
        FuncionarioAlterarRoleResponseDTO responseDto =
                new FuncionarioAlterarRoleResponseDTO(id, "test", "test@test.com", Role.ADMIN);
        when(funcionarioService.alterarRole(eq(id), eq(dadosAtualizar)))
                .thenReturn(responseDto);
        mockMvc.perform(put("/funcionarios/{id}/Atualizarroles", id)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(responseDto)))

                .andExpect(status().isOk())
                .andExpect(jsonPath("$.role").value("ADMIN"));
    }


    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    @DisplayName("deve achar o funcionario pelo id")
    void acharFuncionarioPorIdCase1() throws Exception {
        EnderecoDTO enderecoDTO = new EnderecoDTO("brasil", "parana", "curitiba", "rua test");
        FuncionarioCadastroDTO dados = new FuncionarioCadastroDTO("test", enderecoDTO, 7,
                "99415678", "test@test.com", "test");

        Funcionario funcionario = new Funcionario(dados);

        Funcionario funcionarioSalvo = funcionarioRepository.save(funcionario);
        Long id = funcionarioSalvo.getId();
        Funcionario funcionarioAchado = funcionarioRepository.getReferenceById(id);

        System.out.println(funcionarioAchado.getId());
        when(funcionarioService.buscarPorId(eq(id)))
                .thenReturn(funcionarioAchado);
        mockMvc.perform(get("/funcionarios/{id}",id)
                        .accept(MediaType.APPLICATION_JSON)

                )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.nome").value("test"))
                .andExpect(jsonPath("$.email").value("test@test.com"))
                .andExpect(jsonPath("$.numeroDeHorasPorDia").value(7.0))
                .andExpect(jsonPath("$.telefone").value("99415678"));



    }
    @Test
    @DisplayName("deve alterar o nome e email do funcionario")
    void alteraDadosFuncionario() throws Exception {
        EnderecoDTO enderecoDTO = new EnderecoDTO("brasil", "parana", "curitiba", "rua test");
        FuncionarioCadastroDTO dados= new FuncionarioCadastroDTO("test", enderecoDTO, 7,
                "99415678", "test@test.com", "test");

        Funcionario funcionario = new Funcionario(dados);
        Funcionario funcionarioSalvo = funcionarioRepository.save(funcionario);
        Long id = funcionarioSalvo.getId();
        FuncionarioAlterarDadosDTO dadosAlterar = new FuncionarioAlterarDadosDTO("test#",enderecoDTO,6
                ,"99415678","test@test.com.br");
        funcionarioSalvo.alterarDados(dadosAlterar);
        FuncionarioAlterarDadosResponseDTO dadosResponseDTO  = new FuncionarioAlterarDadosResponseDTO(funcionarioSalvo);
        when(funcionarioService.alterarDados(eq(id), any(FuncionarioAlterarDadosDTO.class)))
                .thenReturn(dadosResponseDTO);
        mockMvc.perform( put("/funcionarios/alterardados/{id}", id)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dadosAlterar)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("test#"))
                .andExpect(jsonPath("$.email").value("test@test.com.br"));
    }
}