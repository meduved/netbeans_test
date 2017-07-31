/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adp.servicemetrics.db;

import com.adp.servicemetrics.ejb.Payslips;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.inject.Inject;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

/**
 *
 * @author pdusek
 */
@Singleton
@LocalBean
public class PayslipsManagerDB {

    @Inject
    private DatabaseConnectionFactory databaseConnectionFactory;

    /* mapping */
    private final String COLLECTIONNAME = "PAYSLIPS";

    private static Document Object2Doc(Payslips p) {
        return new Document()
                .append("Country", p.getCountry())
                .append("CID", p.getCID())
                .append("CUID", p.getCUID())
                .append("Client", p.getClient())
                .append("eeFromBPM", p.getEeFromBPM())
                .append("eeCorrection", p.getEeCorrection())
                .append("ee2Invoice", p.getEe2Invoice()
                );
    }

    private Payslips doc2Object(Document v) {
        Payslips p = new Payslips();
        p.setPk(v.getObjectId("_id"));
        p.setCUID(v.getString("CUID"));
        p.setCID(v.getString("CID"));
        p.setClient(v.getString("Client"));
        p.setCountry(v.getString("Country"));
        p.setEeFromBPM(v.getInteger("eeFromBPM"));
        p.setEeCorrection(v.getInteger("eeCorrection"));
        p.setEe2Invoice(v.getInteger("ee2Invoice"));
        return p;
    }
    /* mapping */

    public List<Payslips> getAll() {
        MongoDatabase db = databaseConnectionFactory.getDefaultDatabase();
        return db.getCollection(COLLECTIONNAME)
                .find()
                .map(this::doc2Object)
                .into(new ArrayList<>());
    }

    public ObjectId add(Payslips p) {
        MongoDatabase db = databaseConnectionFactory.getDefaultDatabase();
        ObjectId id = new ObjectId();
        db.getCollection(COLLECTIONNAME)
                .insertOne(Object2Doc(p)
                        .append("_id", id)
                );
        return id;
    }

    public void remove(ObjectId id) {
        MongoDatabase db = databaseConnectionFactory.getDefaultDatabase();
        db.getCollection(COLLECTIONNAME)
                .deleteOne(Filters.eq("_id", id));

    }

    public void update(Payslips p) {
        System.out.println(p.toString());
        System.out.println(Object2Doc(p));
        MongoDatabase db = databaseConnectionFactory.getDefaultDatabase();
        db.getCollection(COLLECTIONNAME)
                .updateOne(Filters.eq("_id", p.getPk()), new Document("$set",Object2Doc(p)));
                //.updateOne(Filters.eq("_id", new ObjectId("596c4c1c17350924c4777465")), 
                //            new Document("$set", new Document("Country", "New")));

    }

    public Payslips findByID(ObjectId id) {
        MongoDatabase db = databaseConnectionFactory.getDefaultDatabase();
        return db.getCollection(COLLECTIONNAME)
                .find(Filters.eq("_id", id))
                .map(this::doc2Object)
                .first();
    }

}
