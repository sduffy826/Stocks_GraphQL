package com.corti.stocks.graphql;


import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.regex;

// Purpose is to save and load links from storage
public class StockAttributeRepository {
  private final MongoCollection<Document> repo;
  private static final boolean debugIt = true; 
    
  
  public StockAttributeRepository(MongoCollection<Document> repo) {
    this.repo = repo;
  }

  // Return document with this id
  private Document getById(String id) {
    return repo.find(eq("_id", new ObjectId(id))).first();
  }

  // Return document for the ticker symbol
  private Document getByTicker(String ticker) {
    return repo.find(eq("ticker", ticker.trim().toUpperCase())).first();
  }
  
  public StockAttribute findById(String id) {
    if (debugIt) System.out.println("In StockAttributeRepository->findById()");    
    return stockAttribute(getById(id));
  }
  
  public StockAttribute findByTicker(String ticker) {
    if (debugIt) System.out.println("In StockAttributeRepository->findByTicker()");    
    return stockAttribute(getByTicker(ticker));
  }
  
  public List<StockAttribute> getAllRecs() {
    if (debugIt) System.out.println("In StockAttributeRepository->getAllRecs()");
    List<StockAttribute> allRecs = new ArrayList<>();
    for (Document doc : repo.find()) {
      allRecs.add(stockAttribute(doc));
    }
    return allRecs;
  }
  
  public void modifyRec(StockAttribute stockAttribute) {
    if (debugIt) System.out.println("In StockAttributeRepository->modifyRec(stockAttribute)");
    Document doc = getByTicker(stockAttribute.getTicker());
    if (doc == null) {
      doc = new Document();
      doc.append("ticker", stockAttribute.getTicker());
      doc.append("name", stockAttribute.getName());
      repo.insertOne(doc);
      if (debugIt) System.out.println("In StockAttributeRepository->modifyRec-insertOne");
    }
    else {
      // It's an update, create document with fields to update, a document with the operation
      Document docWithUpdatedFields = new Document("name",stockAttribute.getName());
      Document updateOperation = new Document("$set", docWithUpdatedFields);
      // Perform update, the 'doc' is the filter doc (i.e. search)
      repo.updateOne(doc, updateOperation);
      if (debugIt) System.out.println("In StockAttributeRepository->modifyRec-updateOne");
    }
  }
  
  public void deleteRec(String ticker) {
    if (debugIt) System.out.println("In StockAttributeRepository->deleteRec(ticker)");
    Document doc = getByTicker(ticker);
    if (doc != null) {
      repo.deleteOne(doc);
      if (debugIt) System.out.println("  deleted: " + doc.toString());
    }
  }
  
  
  private StockAttribute stockAttribute(Document doc) {
    return new StockAttribute(
                    doc.get("_id").toString(),
                    doc.getString("ticker"),
                    doc.getString("name"));
  }
  
}
