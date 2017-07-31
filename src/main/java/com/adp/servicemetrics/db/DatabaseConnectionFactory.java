/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adp.servicemetrics.db;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;

/**
 *
 * @author PDusek
 */
@Singleton
@LocalBean
public class DatabaseConnectionFactory {
private MongoClient client;
    public MongoClient createClient() {
        MongoClientURI uri = new MongoClientURI("mongodb://127.0.0.1:27017");
        MongoClient client = new MongoClient(uri);
        return client;
    }

    public MongoDatabase getDefaultDatabase()
    {
    return this.client.getDatabase("SERVICEMETRICS");
    }
    
    @PostConstruct
    public void init(){
    this.client = createClient();
    }
    
}
