package br.com.tech4me.animaisms.view.model;

import javax.validation.constraints.NotBlank;

public class AnimalModeloAlteracao {
    private String id;

    @NotBlank(message = "O nome do animal deve ser informado")
    private String nome;
    private Integer idade;
    private String raca;
    private Boolean vivo;

    //#region Get / Set
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public Boolean getVivo() {
        return vivo;
    }

    public void setVivo(Boolean vivo) {
        this.vivo = vivo;
    }
    //#endregion
}
