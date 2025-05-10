package com.Restfulapi.domain.Repository;

import com.Restfulapi.domain.model.Funcionario.Funcionario;
import com.Restfulapi.domain.model.Funcionario.FuncionarioCadastroDTO;
import com.Restfulapi.domain.model.enderco.EnderecoDTO;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("teste")
class FuncionarioRepositoryTest {

    @Autowired
    FuncionarioRepository funcionarioRepository;

    @Autowired
    EntityManager entityManager;
    @Test
    @DisplayName("Deve retornar o funcionario do banco de dados pelo nome")
    void findByNomeCase1(){
        EnderecoDTO enderecoDTO = new EnderecoDTO("brasil","parana","curitiba","rua test");
        FuncionarioCadastroDTO dados = new FuncionarioCadastroDTO("test",enderecoDTO,7
                ,"99415678","test@test.com","test");
        this.CreateFuncionario(dados);
        Funcionario funcionarioAchado =  this.funcionarioRepository.findByNome("test");
        assertThat(funcionarioAchado.getNome()).isEqualTo("test");

 }
    @Test
    @DisplayName("deve nao pegar nenhum funcionario pelo nome")
    void findByNomeCase2(){
        Funcionario funcionarioAchado =  this.funcionarioRepository.findByNome("test");
        assertThat(funcionarioAchado == null);

    }
    @Test
    @DisplayName("Deve retornar o funcionario do banco de dados pelo email")
    void findByEmailCase1(){
        EnderecoDTO enderecoDTO = new EnderecoDTO("brasil","parana","curitiba","rua test");
        FuncionarioCadastroDTO dados = new FuncionarioCadastroDTO("test",enderecoDTO,7
                ,"99415678","test@test.com","test");
        this.CreateFuncionario(dados);
        UserDetails funcionarioAchado  = this.funcionarioRepository.findByEmail("test@test.com");
        assertThat(funcionarioAchado.getUsername()).isEqualTo("test@test.com");
    }
    @Test
    @DisplayName("deve nao pegar nenhum funcionario pelo email")
    void findByEmailCase2(){
        UserDetails funcionarioAchado = this.funcionarioRepository.findByEmail("test@test.com");
        assertThat(funcionarioAchado == null);
    }

 private Funcionario CreateFuncionario(FuncionarioCadastroDTO dto){
        Funcionario funcionario = new Funcionario(dto);
        this.entityManager.persist(funcionario);
        return funcionario;
 }

}