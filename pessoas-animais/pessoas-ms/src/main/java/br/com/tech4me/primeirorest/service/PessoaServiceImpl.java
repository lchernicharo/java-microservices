package br.com.tech4me.primeirorest.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import br.com.tech4me.primeirorest.compartilhado.Animal;
import br.com.tech4me.primeirorest.compartilhado.PessoaDto;
import br.com.tech4me.primeirorest.compartilhado.ResultadoEnvioMensagemAsync;
import br.com.tech4me.primeirorest.data.async.model.AnimalAsyncModel;
import br.com.tech4me.primeirorest.data.sync.AnimaisSyncClient;
import br.com.tech4me.primeirorest.model.Pessoa;
import br.com.tech4me.primeirorest.repository.PessoaRepository;

@Service
public class PessoaServiceImpl implements PessoaService {
    @Autowired
    private PessoaRepository repo;

    @Autowired
    private AnimaisSyncClient animalSyncClient;

    @Autowired
    private KafkaTemplate<String, AnimalAsyncModel> animalAsyncClient;

    @Value("${api.async.animais.incluir.topic.name}")
    private String topic;

    @Override
    public PessoaDto criarPessoa(PessoaDto pessoa) {
        return salvarPessoa(pessoa);
    }

    @Override
    public List<PessoaDto> obterTodos() {
        List<Pessoa> pessoas = repo.findAll();

        return pessoas.stream().map(pessoa -> new ModelMapper().map(pessoa, PessoaDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PessoaDto> obterPorId(String id) {
        Optional<Pessoa> pessoa = repo.findById(id);

        if (pessoa.isPresent()) {
            return Optional.of(new ModelMapper().map(pessoa.get(), PessoaDto.class));
        }

        return Optional.empty();
    }

    @Override
    public void removerPessoa(String id) {
        repo.deleteById(id);
    }

    @Override
    public PessoaDto atualizarPessoa(String id, PessoaDto pessoa) {
        pessoa.setId(id);
        return salvarPessoa(pessoa);
    }

    private PessoaDto salvarPessoa(PessoaDto pessoa) {
        ModelMapper mapper = new ModelMapper();
        Pessoa pessoaEntidade = mapper.map(pessoa, Pessoa.class);
        pessoaEntidade = repo.save(pessoaEntidade);

        return mapper.map(pessoaEntidade, PessoaDto.class);
    }

    @Override
    public List<Animal> obterAnimaisPorPessoa(String id) {
        return animalSyncClient.obterAnimais(id);
    }

    @Override
    public ResultadoEnvioMensagemAsync adicionarAnimal(String dono, Animal animal) {
        Optional<Pessoa> pessoa = repo.findById(dono);

        if(pessoa.isEmpty()) {
            return ResultadoEnvioMensagemAsync.PESSOA_NAO_EXISTE;
        }

        try{
            AnimalAsyncModel animalMsg = new ModelMapper().map(animal, AnimalAsyncModel.class);
            animalMsg.setDono(dono);
            animalAsyncClient.send(topic, animalMsg);
            
            return ResultadoEnvioMensagemAsync.MENSAGEM_ENVIADA_COM_SUCESSO;
        } catch(Exception e) {
            return ResultadoEnvioMensagemAsync.ERRO_AO_ENVIAR_MENSAGEM;
        }
    }
}
