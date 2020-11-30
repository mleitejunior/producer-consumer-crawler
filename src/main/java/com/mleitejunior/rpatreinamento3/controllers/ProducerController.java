package com.mleitejunior.rpatreinamento3.controllers;

import com.mleitejunior.rpatreinamento3.crawler.MercadoLivreCrawler;
import com.mleitejunior.rpatreinamento3.messageria.Producer;
import com.mleitejunior.rpatreinamento3.models.MLSearch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ProducerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProducerController.class);

    @Autowired
    private Producer producer;

    @GetMapping("/search/{searchTerm}")
    public String setMLSearch(@PathVariable("searchTerm") String term) throws IOException {

        producer.send(term);

        return term;
    }
}
