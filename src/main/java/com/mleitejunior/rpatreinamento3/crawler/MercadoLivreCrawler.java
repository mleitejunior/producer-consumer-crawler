package com.mleitejunior.rpatreinamento3.crawler;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import com.mleitejunior.rpatreinamento3.models.MLItem;
import com.mleitejunior.rpatreinamento3.models.MLSearch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class MercadoLivreCrawler {

    private static final Logger LOGGER = LoggerFactory.getLogger(MercadoLivreCrawler.class);

    private final List<MLItem> itemList = new ArrayList<>();

    private static final String ITEM_DESCRIPTION_HEADER = ".//h2[@class='ui-search-item__title']";
    private static final String ITEM_PRE_VISUALIZATION_IMAGE = ".//img[@class='ui-search-result-image__element' or @class='ui-search-result-image__element lazy-loadable']";

    private static final String ITEM_PRICE_SYMBOL = ".//span[@class='price-tag-symbol']";
    private static final String ITEM_PRICE_FRACTION = ".//span[@class='price-tag-fraction']";
    private static final String ITEM_PRICE_CENTS = ".//span[@class='price-tag-cents']";

    private static final String NEXT_PAGE_ANCHOR = "//a[@title='Próxima']";

    private static final String ITEM_LIST_CONTAINER = "//li[@class='ui-search-layout__item']";

    // URL
    private static final String BASE_URL = "https://lista.mercadolivre.com.br/";

    public MLSearch search(String searchTerm) throws IOException {
        LOGGER.info("# Buscando {} no mercado livre", searchTerm);

        WebClient webClient = new WebClient();
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(false);
        webClient.getOptions().setUseInsecureSSL(true);

        HtmlPage page = webClient.getPage( BASE_URL + searchTerm + "_DisplayType_LF");

        try {
            HtmlAnchor nextPageAnchor;

            do {
                nextPageAnchor = page.getFirstByXPath(NEXT_PAGE_ANCHOR);
                List<HtmlElement> items = page.getByXPath(ITEM_LIST_CONTAINER);

                items.forEach(i -> {
                    HtmlHeading2 itemDescriptionHeader = i.getFirstByXPath(ITEM_DESCRIPTION_HEADER);

                    HtmlSpan itemPriceSymbolSpan = i.getFirstByXPath(ITEM_PRICE_SYMBOL);
                    HtmlSpan itemPriceFractionSpan = i.getFirstByXPath(ITEM_PRICE_FRACTION);
                    HtmlSpan itemPriceCentsSpan = i.getFirstByXPath(ITEM_PRICE_CENTS);

                    HtmlImage itemPreVisualizationImage = i.getFirstByXPath(ITEM_PRE_VISUALIZATION_IMAGE);

                    String description = itemDescriptionHeader.asText();
                    String price =
                            itemPriceSymbolSpan.asText() +
                                    itemPriceFractionSpan.asText() + "," +
                                    ((itemPriceCentsSpan == null) ? "00" : itemPriceCentsSpan.asText());
                    String preVisualizationImageUrl = (itemPreVisualizationImage.getAttribute("src") == null) ?
                            itemPreVisualizationImage.getAttribute("data-src") :
                            itemPreVisualizationImage.getAttribute("src");

                    itemList.add(new MLItem(preVisualizationImageUrl, price, description));
                });

                if (nextPageAnchor != null) {
                    page = webClient.getPage(nextPageAnchor.getAttribute("href"));
                    // Melhora o filtro de resultados duplicados, porém ainda não está assertivo
                    Thread.sleep(200);
                }
            } while (nextPageAnchor != null);
        } catch (Exception e) {
            LOGGER.error("# Erro durante a busca, mensagem: {}", e.getMessage());
        }

        webClient.close();

        LOGGER.info("# Sucesso, {} itens encontrados", itemList.size());

        return new MLSearch(searchTerm, itemList);
    }
}
