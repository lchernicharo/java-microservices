package br.com.tech4me.animaisms.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.tech4me.animaisms.model.Animal;

@Repository
public interface AnimalRepositorio extends MongoRepository<Animal, String> {

	List<Animal> findByDono(String dono);
    
}
