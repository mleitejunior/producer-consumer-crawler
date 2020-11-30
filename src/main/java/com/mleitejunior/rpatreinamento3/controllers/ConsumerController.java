package com.mleitejunior.rpatreinamento3.controllers;

import com.mleitejunior.rpatreinamento3.crawler.MercadoLivreCrawler;
import com.mleitejunior.rpatreinamento3.models.MLSearch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ConsumerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerController.class);

    private List<MLSearch> list = new ArrayList<>();

    @Autowired
    private MercadoLivreCrawler crawler;

    @RabbitListener(queues = "${spring.rabbitmq-config.queues.queue-consumer-teste}")
    public String getMLSearch(String input) throws IOException {
        MLSearch search = crawler.search(input);

        list.add(search);
        String response = "Busca de " + search.getTitle() + " retornou " + search.getmLItems().size() + " items.";

        LOGGER.info(response);
        return response;
    }

    @GetMapping("/list")
    public List<MLSearch> getMLSearchList() {
        return list;
    }
}
