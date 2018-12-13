package com.corti.stocks.graphql;

import java.time.LocalDate;

public class StockDateAndPrice {
  private String id;
  private String ticker;
  private LocalDate stockDate;
  private float open, high, low, close, adjustedClose;
  private long volume;
  
  // Constructor with all fields (they're in the correct format)
  public StockDateAndPrice(String _id, String _ticker, String _sdate, 
                           float _open, float _high, float _low, float _close, 
                           float _adjustedClose, long _volume) {
   super();
   setFields(_id, _ticker, _sdate, _open, _high, _low, _close, _adjustedClose, _volume);
  }
  
  // Constructor with all fields except 'id', also in correct format
  public StockDateAndPrice(String _ticker, String _sdate, 
                           float _open, float _high, float _low, float _close, 
                           float _adjustedClose, long _volume) {
    super();
    setFields(null, _ticker, _sdate, _open, _high, _low, _close, _adjustedClose, _volume);
  }
  
  // Constructor with fields as string format, no id here
  public StockDateAndPrice(String _ticker, String _sdate, 
                           String _open, String _high, String _low, String _close, 
                           String _adjustedClose,  String _volume) {
    super();
    setFields(null, _ticker, _sdate, _open, _high, _low, _close, _adjustedClose, _volume);
  }
  
  // Constructor with fields as string format, no id here
  public StockDateAndPrice(String _id, String _ticker, String _sdate, 
                           String _open, String _high, String _low, String _close, 
                           String _adjustedClose, String _volume) {
    super();
    setFields(_id, _ticker, _sdate, _open, _high, _low, _close, _adjustedClose, _volume);
  }
  
  // setFields where all attributes are in proper format 
  protected void setFields(String _id, String _ticker, String _sdate, 
                           float _open, float _high, float _low, float _close, 
                           float _adjustedClose, long _volume) {
    this.setId(_id);
    this.setTicker(_ticker);
    this.setStockDate(_sdate);
    this.setOpen(_open);
    this.setHigh(_high);
    this.setLow(_low);
    this.setClose(_close);
    this.setAdjustedClose(_adjustedClose);
    this.setVolume(_volume);
  }
  
  // setFields where all fields are string, we'll convert fields and call other 'setFields' method
  protected void setFields(String _id, String _ticker, String _sdate, 
                           String _open, String _high, String _low, String _close,
                           String _adjustedClose, String _volume) {
    float _o, _h, _l, _c, _a;
    long _v;
    _o = Float.parseFloat(_open);
    _h = Float.parseFloat(_high);
    _l = Float.parseFloat(_low);
    _c = Float.parseFloat(_close);
    _a = Float.parseFloat(_adjustedClose);
    _v = Long.parseLong(_volume);
    System.out.println("In setFields with strings: " + _id);
    setFields(_id, _ticker, _sdate, _o, _h, _l, _c, _a, _v);    
  }  
  
  public String getId() {
    System.out.println("getId called: " + id);
    return id;
  }
  public String getTicker() {    
    return ticker;
  }
  public LocalDate getStockDate() {
    return stockDate;
  }
  public float getOpen() {
    return open;
  }
  public float getHigh() {
    return high;
  }
  public float getLow() {
    return low;
  }
  public float getClose() {
    return close;
  }
  public float getAdjustedClose() {
    return adjustedClose;
  }
  public long getVolume() {
    return volume;
  }
  
  protected void setId(String _id) {
    this.id = _id;
  }
  
  protected void setTicker(String _ticker) {
    this.ticker = _ticker;
  }
  
  // Set stock date, value passed in is a LocalDate object, local dates are immutable so
  // it's ok if it has same reference as the object passed in.
  protected void setStockDate(LocalDate _sDate) {
    this.stockDate = _sDate;    
  }
  
  // Set stock date value from string (should be iso format)
  protected void setStockDate(String _sDate) {
    this.stockDate = LocalDate.parse(_sDate);    
  }  
  
  protected void setStockDate(int _year, int _month, int _day) {
    this.stockDate = LocalDate.of(_year, _month, _day);
  }
  
  protected void setOpen(float _open) {
    this.open = _open;
  }
  protected void setHigh(float _high) {
    this.high = _high;
  }
  protected void setLow(float _low) {
    this.low = _low;
  }
  protected void setClose(float _close) {
    this.close = _close;
  }
  protected void setAdjustedClose(float _adjustedClose) {
    this.adjustedClose = _adjustedClose;
  }
  protected void setVolume(long _volume) {
    this.volume = _volume;
  }
  
  public String toString() {
    return this.getId() + " " +
           this.getTicker() + " " +
           this.getStockDate() + " " +
           "Open: " + Float.toString(this.getOpen()) +
           "High: " + Float.toString(this.getHigh()) + 
           "Low: " + Float.toString(this.getLow()) +
           "Close: " + Float.toString(this.getClose()) +
           "adjustedClose: " + Float.toString(this.getAdjustedClose());           
  }
  
  // We put open in fourth column after date in case they want to include dividends
  public String toCsvAll() {
        return this.getTicker() + ",," + 
                this.getStockDate() + ",,,," +
                Float.toString(this.getOpen()) + ",," +
                Float.toString(this.getHigh()) + ",," + 
                Float.toString(this.getLow()) + ",," +
                Float.toString(this.getClose()) + ",," +
                Float.toString(this.getAdjustedClose()) + ",," +
                Long.toString(this.getVolume());
  }
  public static String csvHdrAll() {
        return "Ticker,," + 
               "Date,,,," +
               "Open,," +
               "High,," + 
               "Low,," +
               "Close,," +
               "adjustedClose,," +
               "Volume";           
  }
  
  // We put open in fourth column after date in case they want to include dividends
  public String toCsvClose() {
        return this.getTicker() + ",," +
               this.getStockDate() + ",," +
               Float.toString(this.getClose());            
  }
  public static String csvHdrClose() {
        return "Ticker,," + 
               "Date,," +
               "Close";    
  }
  
}
