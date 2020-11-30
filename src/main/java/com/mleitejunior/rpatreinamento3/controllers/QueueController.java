package com.mleitejunior.rpatreinamento3.controllers;

import com.mleitejunior.rpatreinamento3.crawler.MercadoLivreCrawler;
import com.mleitejunior.rpatreinamento3.models.MLSearch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

//@RestController
public class QueueController {

//    private static final Logger LOGGER = LoggerFactory.getLogger(QueueController.class);
//
//    @Autowired
//    private RabbitTemplate template;
//
//    @Autowired
//    private Queue queue;
//
//    @GetMapping("/search/{searchTerm}")
//    public MLSearch getMLSearch(@PathVariable("searchTerm") String term, MercadoLivreCrawler crawler) throws IOException {
//        MLSearch search = crawler.searchWithHtmlUnit(term);
//        this.template.convertAndSend(queue.getName(), search);
//
//        LOGGER.info(" [x] Sent '{}'", search.getTitle());
//
//        return crawler.searchWithHtmlUnit(term);
//    }
//
//    @RabbitListener(queues = "${spring.rabbitmq-config.queues.queue-consumer-teste}")
//    @RabbitHandler
//    @GetMapping("/list")
//    public MLSearch receive(MLSearch mLSearch) {
//        LOGGER.info(" [x] Received '{}'", mLSearch);
//
//        return mLSearch;
//    }
}
