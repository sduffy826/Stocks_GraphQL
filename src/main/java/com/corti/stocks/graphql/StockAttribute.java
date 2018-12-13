package com.corti.stocks.graphql;

import java.time.LocalDate;

public class StockAttribute {

  private final String id;
  private final String ticker;
  private final String name;
  
  public StockAttribute(String ticker, String name) {
    this(null, ticker, name);
  }

  public StockAttribute(String id, String ticker, String name) {
    super();
    this.id = id;
    this.ticker = ticker;
    this.name = name;
  }

  public String getId() {
    System.out.println("getId called");
    return id;
  }

  public String getTicker() {
    return ticker;
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return "StockAttribute [id=" + id + ", ticker=" + ticker + ", name=" + name + "]";
  }

}
