package com.aprendendo.test.domain.model.enderco;

import jakarta.persistence.Embeddable;

@Embeddable
public class Endereco {
    private String pais;
    private String estado;
    private String cidade;
    private String rua;
}
