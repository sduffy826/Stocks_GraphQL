package com.corti.stocks.graphql;

import com.coxautodev.graphql.tools.SchemaParser;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

import javax.servlet.annotation.WebServlet;
import graphql.servlet.SimpleGraphQLServlet;

import graphql.schema.GraphQLSchema;

@WebServlet(urlPatterns = "/graphql")
public class GraphQLEndpoint extends SimpleGraphQLServlet {
  private static final boolean debugIt = true;
  private static final StockAttributeRepository stockAttributeRepository;
  
  static {
    MongoDatabase mongoDb = new MongoClient().getDatabase("stock");
    stockAttributeRepository = new StockAttributeRepository(mongoDb.getCollection("stocks"));
  }
  
  public GraphQLEndpoint() {
    super(buildSchema());
    if (debugIt) System.out.println("In GraphQLEndpoint constructor.");
  }
    
  private static GraphQLSchema buildSchema() {
    if (debugIt) System.out.println("In GraphQLSchema->buildSchema()");
    return SchemaParser.newParser()
             .file("schema.graphqls")
             .resolvers(new Query(stockAttributeRepository), new Mutation(stockAttributeRepository))
             .build()
             .makeExecutableSchema();
  }

}
