package br.com.tech4me.primeirorest.view.model;

import javax.validation.constraints.NotBlank;

public class AnimalRequestModel {
    @NotBlank(message = "O nome do animal deve ser informado")
    private String nome;
    private Integer idade;
    private String raca;

    //#region Get / Set
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }
    //#endregion
}
