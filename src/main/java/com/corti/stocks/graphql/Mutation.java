package com.corti.stocks.graphql;


import com.coxautodev.graphql.tools.GraphQLRootResolver;

public class Mutation implements GraphQLRootResolver {
  private final StockAttributeRepository stockAttributeRepository;
  private final StockDateAndPriceRepository stockDateAndPriceRepository;
  
  public Mutation(StockAttributeRepository stockAttributeRepository, StockDateAndPriceRepository stockDateAndPriceRepository) {
    this.stockAttributeRepository = stockAttributeRepository;
    this.stockDateAndPriceRepository = stockDateAndPriceRepository;
  }
  
  public StockAttribute modifyStockAttribute(String ticker, String name) {
    StockAttribute stockAttribute = new StockAttribute(ticker, name);
    stockAttributeRepository.modifyRec(stockAttribute);
    return stockAttribute;
  }
  
  public void deleteStockAttribute(String ticker) {
    stockAttributeRepository.deleteRec(ticker);
    return;
  }
}
