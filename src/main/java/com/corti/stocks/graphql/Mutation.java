package com.corti.stocks.graphql;

import com.coxautodev.graphql.tools.GraphQLRootResolver;

public class Mutation implements GraphQLRootResolver {
  private final boolean DEBUGIT = true;
  
  private final StockAttributeRepository stockAttributeRepository;
  private final StockDateAndPriceRepository stockDateAndPriceRepository;
  
  public Mutation(StockAttributeRepository stockAttributeRepository, StockDateAndPriceRepository stockDateAndPriceRepository) {
    if (DEBUGIT) System.out.println("In Mutation - constructor");
    this.stockAttributeRepository = stockAttributeRepository;
    this.stockDateAndPriceRepository = stockDateAndPriceRepository;
  }
  
  public StockAttribute modifyStockAttribute(String ticker, String name) {
    if (DEBUGIT) System.out.println("In Mutation - modifyStockAttribute, ticker: " + ticker + " name: " + name);
    StockAttribute stockAttribute = new StockAttribute(ticker, name);
    stockAttributeRepository.modifyRec(stockAttribute);
    return stockAttribute;
  }
  
  // Method to update/insert the stock date and price
  public StockDateAndPrice modifyStockDateAndPrice(String _ticker, String _sdate, String _open, String _high, 
                                                   String _low, String _close, String _adjustedClose, String _volume) {
    if (DEBUGIT) System.out.println("In Mutation - modifyStockDateAndPrice, ticker: " + _ticker + " sdate: " + _sdate +
                                      "open: " + _open + " high: " + _high + " low: " + _low + " close: " + _close +
                                      " adjustedClose: " + _adjustedClose + " volume: " + _volume);
    StockDateAndPrice stockDateAndPrice = new StockDateAndPrice(_ticker, _sdate, _open, _high, _low, _close, _adjustedClose, _volume);
    stockDateAndPriceRepository.modifyRec(stockDateAndPrice);
    return stockDateAndPrice;
  }
  
  public StockDateAndPrice modifyStockDateAndPrice(String _ticker, String _sdate, Float _open, Float _high, 
                                                   Float _low, Float _close, Float _adjustedClose, int _volume) {
    if (DEBUGIT) System.out.println("In Mutation - modifyStockDateAndPriceREALTYPES, ticker: " + _ticker + " sdate: " + _sdate +
                                       "open: " + _open + " high: " + _high + " low: " + _low + " close: " + _close +
                                       " adjustedClose: " + _adjustedClose + " volume: " + _volume);
    StockDateAndPrice stockDateAndPrice = new StockDateAndPrice(_ticker, _sdate, _open, _high, _low, _close, _adjustedClose, _volume);
    stockDateAndPriceRepository.modifyRec(stockDateAndPrice);
    return stockDateAndPrice;
  }
    
  public void deleteStockAttribute(String ticker) {
    if (DEBUGIT) System.out.println("In Mutation - deleteStockAttribute, ticker: " + ticker);
    stockAttributeRepository.deleteRec(ticker);
    return;
  }
  
  public String deleteStockDateAndPrice(String _ticker, String _sdate) {
    if (DEBUGIT) System.out.println("In Mutation - deleteStockDateAndPrice, ticker: " + _ticker + " stockDate: " + _sdate);
    return (stockDateAndPriceRepository.deleteRec(_ticker, _sdate) ? "Success" : "Failed");    
  }
  
  
}
