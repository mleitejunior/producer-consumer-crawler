package com.mleitejunior.rpatreinamento3.messageria;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mleitejunior.rpatreinamento3.controllers.ProducerController;
import com.mleitejunior.rpatreinamento3.controllers.QueueController;
import com.mleitejunior.rpatreinamento3.messageria.Producer;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class RabbitConfig {

//    @Bean
//    public Queue helloTreinamento() {
//        return new Queue("teste-treinamento");
//    }

//    @Bean
//    public MessageConverter messageConverter(ObjectMapper objectMapper) {​​​​​​​
//
//        return new Jackson2JsonMessageConverter(objectMapper);
//    }

}
