package com.corti.stocks.graphql;


import com.coxautodev.graphql.tools.GraphQLRootResolver;
import java.util.List;

public class Query implements GraphQLRootResolver {
  private static final boolean debugIt = true;
  
  private final StockAttributeRepository stockAttributeRepository;
    
  public Query(StockAttributeRepository stockAttributeRepository) {
    if (debugIt) System.out.println("In Query constructor");
    this.stockAttributeRepository = stockAttributeRepository;
  }
  
  public List<StockAttribute> allStockAttributes() {
    if (debugIt) System.out.println("In Query->allStockAttributes()");
    return stockAttributeRepository.getAllRecs();
  }
}
