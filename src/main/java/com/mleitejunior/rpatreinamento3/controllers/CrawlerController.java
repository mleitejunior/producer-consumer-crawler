package com.mleitejunior.rpatreinamento3.controllers;

import com.mleitejunior.rpatreinamento3.crawler.MercadoLivreCrawler;
import com.mleitejunior.rpatreinamento3.models.MLSearch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

//@RestController
public class CrawlerController {

    //
    // Converter o objeto para JSON (jackson)
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(ProducerController.class);
//
//    @GetMapping("/search/{searchTerm}")
//    public MLSearch getMLSearch(@PathVariable("searchTerm") String term, MercadoLivreCrawler crawler) throws IOException {
//        MLSearch search = crawler.searchWithHtmlUnit(term);
//
//        return search;
//    }
}
