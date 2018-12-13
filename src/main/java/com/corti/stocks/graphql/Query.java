package com.corti.stocks.graphql;


import com.coxautodev.graphql.tools.GraphQLRootResolver;
import java.util.List;

public class Query implements GraphQLRootResolver {
  private static final boolean debugIt = true;
  
  private final StockAttributeRepository stockAttributeRepository;
  private final StockDateAndPriceRepository stockDateAndPriceRepository;
    
  public Query(StockAttributeRepository stockAttributeRepository, StockDateAndPriceRepository stockDateAndPriceRepository) {
    if (debugIt) System.out.println("In Query constructor");
    this.stockAttributeRepository = stockAttributeRepository;
    this.stockDateAndPriceRepository = stockDateAndPriceRepository;
  }
  
  public List<StockAttribute> allStockAttributes() {
    if (debugIt) System.out.println("In Query->allStockAttributes()");
    return stockAttributeRepository.getAllRecs();
  }
  
  public List<StockDateAndPrice> allStockDateAndPrice() {
    if (debugIt) System.out.println("In Query->allStockDateAndPrice()");
    return stockDateAndPriceRepository.getAllRecs();
  }
}
