package com.Restfulapi.domain.model.Task;

import java.time.LocalDateTime;

public record TaskCadastroDTO(String titulo, String descricao, Prioridade prioridade, Status status,
                              LocalDateTime prazo, Long criadorId,Long responsavelId
                              ) {
}
