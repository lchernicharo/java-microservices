package br.com.tech4me.animaisms.service;

import java.util.List;
import java.util.Optional;

import br.com.tech4me.animaisms.compartilhado.AnimalDto;

public interface AnimalService {
    AnimalDto criarAnimal(AnimalDto animal);
    List<AnimalDto> obterTodos();
    Optional<AnimalDto> obterPorId(String id);
    List<AnimalDto> obterPorDono(String dono);
    void removerAnimal(String id);
    boolean definirComoMorto(String id);
    AnimalDto atualizarAnimal(String id, AnimalDto animal);
}
