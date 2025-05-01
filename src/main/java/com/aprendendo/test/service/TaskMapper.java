package com.aprendendo.test.service;

import com.aprendendo.test.domain.Repository.FuncionarioRepository;
import com.aprendendo.test.domain.model.Task.Task;
import com.aprendendo.test.domain.model.Task.TaskCadastroDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring", uses = FuncionarioRepository.class)
public interface TaskMapper {
    @Mapping(target = "criador", expression = "java(funcionarioRepository.findById(dto.criadorId()).orElseThrow())")
    @Mapping(target = "responsavel", expression = "java(funcionarioRepository.findById(dto.responsavelId()).orElseThrow())")
    Task toEntity(TaskCadastroDTO dto);
}
