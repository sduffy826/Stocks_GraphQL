package com.corti.stocks.graphql;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.regex;

// Purpose is to save and load links from storage
public class StockDateAndPriceRepository {
  private final MongoCollection<Document> repo;
  private static final boolean debugIt = true; 
  
  public StockDateAndPriceRepository(MongoCollection<Document> repo) {
    this.repo = repo;
  }

  // Return document with this id
  private Document getById(String id) {
    return repo.find(eq("_id", new ObjectId(id))).first();
  }

  // Return document for the ticker symbol
  private Document getByTickerAndDate(String ticker, String stockDate) {
    
    BasicDBObject andQuery = new BasicDBObject();
    List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
    obj.add(new BasicDBObject("ticker", ticker.trim().toUpperCase()));
    obj.add(new BasicDBObject("stockDate", stockDate.toString()));
    andQuery.put("$and", obj);
  
    if (debugIt) System.out.println(andQuery.toString());
  
    //DBCursor cursor = collection.find(andQuery);
    //while (cursor.hasNext()) {
    //    System.out.println(cursor.next());
    //}
   
    
    return repo.find(andQuery).first();

  }
  
  public StockDateAndPrice findById(String id) {
    if (debugIt) System.out.println("In StockAttributeRepository->findById()");    
    return stockDateAndPrice(getById(id));
  }
  
  public StockDateAndPrice findByTickerAndDate(String ticker, String stockDate) {
    if (debugIt) System.out.println("In StockDateAndPriceRepository->findByTickerAndDate()");    
    return stockDateAndPrice(getByTickerAndDate(ticker, stockDate));
  }
  
  public List<StockDateAndPrice> getAllRecs() {
    if (debugIt) System.out.println("In StockDateAndPriceRepository->getAllRecs()");
    List<StockDateAndPrice> allRecs = new ArrayList<>();
    for (Document doc : repo.find()) {
      allRecs.add(stockDateAndPrice(doc));
    }
    return allRecs;
  }
  
  public void modifyRec(StockDateAndPrice stockDateAndPrice) {
    if (debugIt) System.out.println("In StockDateAndPriceRepository->modifyRec(stockDateAndPrice)");
    Document doc = getByTickerAndDate(stockDateAndPrice.getTicker(), stockDateAndPrice.getStockDate().toString());
    if (doc == null) {
      doc = new Document();
      doc.append("ticker", stockDateAndPrice.getTicker());
      doc.append("stockDate",  stockDateAndPrice.getStockDate().toString());
      doc.append("open",  stockDateAndPrice.getOpen());
      doc.append("high",  stockDateAndPrice.getHigh());
      doc.append("low",  stockDateAndPrice.getLow());
      doc.append("close",  stockDateAndPrice.getClose());      
      doc.append("adjustedClose",  stockDateAndPrice.getAdjustedClose());      
      doc.append("volume",  stockDateAndPrice.getVolume());
      repo.insertOne(doc);
      if (debugIt) System.out.println("In StockAttributeRepository->modifyRec-insertOne");
    }
    else {
      // It's an update, create document with fields to update, a document with the operation
//      Document docWithUpdatedFields = new Document("name",stockAttribute.getName());
 //     Document updateOperation = new Document("$set", docWithUpdatedFields);
      // Perform update, the 'doc' is the filter doc (i.e. search)
 //     repo.updateOne(doc, updateOperation);
  //    if (debugIt) System.out.println("In StockAttributeRepository->modifyRec-updateOne");
    }
  }
  
  public void deleteRec(String ticker, String stockDate) {
    if (debugIt) System.out.println("In StockAttributeRepository->deleteRec(ticker)");
    Document doc = getByTickerAndDate(ticker, stockDate);
    if (doc != null) {
      repo.deleteOne(doc);
      if (debugIt) System.out.println("  deleted: " + doc.toString());
    }
  }  
  
  private StockDateAndPrice stockDateAndPrice(Document doc) {
    System.out.println("In stockDateAndPrice: " + doc.get("_id").toString());
    return new StockDateAndPrice(
                    doc.get("_id").toString(),
                    doc.getString("ticker"),
                    doc.getString("stockDate"),
                    doc.getString("open"),
                    doc.getString("high"),
                    doc.getString("low"),
                    doc.getString("close"),
                    doc.getString("adjustedClose"),
                    doc.getString("volume"));
  }
  
}
