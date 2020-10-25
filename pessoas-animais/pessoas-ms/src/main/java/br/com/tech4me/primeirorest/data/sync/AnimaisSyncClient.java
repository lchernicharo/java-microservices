package br.com.tech4me.primeirorest.data.sync;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.tech4me.primeirorest.compartilhado.Animal;

@FeignClient(
    name = "animais-ms",
    fallback = AnimaisClientFallback.class
)
public interface AnimaisSyncClient {
    @GetMapping(value = "/api/animais/dono/{dono}/animais")
    List<Animal> obterAnimais(@PathVariable String dono);
}

@Component
class AnimaisClientFallback implements AnimaisSyncClient {

    @Override
    public List<Animal> obterAnimais(String dono) {
        return new ArrayList<>();
    }

}