package br.com.tech4me.animaisms.data.async;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import br.com.tech4me.animaisms.compartilhado.AnimalAsyncModel;

@Configuration
@EnableKafka
public class KafkaConsumerAsyncClient {
    @Value("${api.async.server}")
    private String server;

    @Value("${api.async.animais.incluir.group-id}")
    private String groupId;

    @Bean
    public ConsumerFactory<String, AnimalAsyncModel> consumerFactory() {
        Map<String, Object> config = new HashMap<>();

        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, server);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);

        var deserializer = new ErrorHandlingDeserializer<AnimalAsyncModel>(
                                            new JsonDeserializer<>(AnimalAsyncModel.class, false));

        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), deserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, AnimalAsyncModel> kafkaListenerContainerFactory() {
        var factory = new ConcurrentKafkaListenerContainerFactory<String, AnimalAsyncModel>();
        factory.setConsumerFactory(consumerFactory());

        return factory;
    }
}
