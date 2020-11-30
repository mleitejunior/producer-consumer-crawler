package com.mleitejunior.rpatreinamento3.messageria;

import com.mleitejunior.rpatreinamento3.crawler.MercadoLivreCrawler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// Se estiver como Autowired na chamada da API, deve por @Service
@Service
public class Producer {

    // Lista de Items
    private static final Logger LOGGER = LoggerFactory.getLogger(Producer.class);

    @Autowired
    private RabbitTemplate template;

    public void send(String item) {
        this.template.convertAndSend("teste-treinamento", item);
        LOGGER.info(" [x] Sent '{}'", item);
    }
}
