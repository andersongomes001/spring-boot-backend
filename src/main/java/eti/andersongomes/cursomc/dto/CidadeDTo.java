package eti.andersongomes.cursomc.dto;

import eti.andersongomes.cursomc.domain.Cidade;

import java.io.Serializable;

public class CidadeDTo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nome;

    public CidadeDTo() {
    }
    public CidadeDTo(Cidade cidade) {
        this.id = cidade.getId();
        this.nome = cidade.getNome();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
