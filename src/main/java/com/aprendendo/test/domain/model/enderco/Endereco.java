package com.aprendendo.test.domain.model.enderco;

import jakarta.persistence.Embeddable;

@Embeddable
public class Endereco {
    private String pais;
    private String estado;
    private String cidade;
    private String rua;
    public Endereco(){}
    public Endereco(EnderecoDTO endereco) {
        this.pais = endereco.pais();
        this.estado = endereco.estado();
        this.cidade = endereco.cidade();
        this.rua = endereco.rua();
    }
}
