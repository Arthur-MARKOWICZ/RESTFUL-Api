package com.Restfulapi.service;

import com.Restfulapi.domain.Repository.FuncionarioRepository;
import com.Restfulapi.domain.model.Task.Task;
import com.Restfulapi.domain.model.Task.TaskCadastroDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring", uses = FuncionarioRepository.class)
public interface TaskMapper {
    @Mapping(target = "criador", expression = "java(funcionarioRepository.findById(dto.criadorId()).orElseThrow())")
    @Mapping(target = "responsavel", expression = "java(funcionarioRepository.findById(dto.responsavelId()).orElseThrow())")
    Task toEntity(TaskCadastroDTO dto);
}
