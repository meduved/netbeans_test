/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adp.servicemetrics.res;


import com.adp.servicemetrics.db.PayslipsManagerDB;
import com.adp.servicemetrics.ejb.Payslips;
import java.util.Collection;
import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.bson.types.ObjectId;

/**
 * REST Web Service
 *
 * @author pdusek
 */
@Path("payslips")
@Produces(MediaType.APPLICATION_JSON)
public class PayslipsResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of PayslipsResource
     */
    public PayslipsResource() {
    }

    @Inject
    private PayslipsManagerDB pm;
    
    @GET
    public Collection<Payslips> rsGetAll() {
    return pm.getAll();
    }
    
    @PUT
    public Payslips rsAddPayslip(Payslips p) {
    System.out.println(p.toString()); 
    ObjectId id = pm.add(p);
    return pm.findByID(id);
    }
    
    @POST
    public void update(Payslips p){
    pm.update(p);
    }
    
    
    @Path("{id}")
    @GET
    public Payslips rsFindById(@PathParam("id") String id) {
        Payslips p = pm.findByID (new ObjectId(id));
        if (p == null) {
            throw new WebApplicationException(Response.status(400).entity("Payslips " +id + " not not found").build());
        };
        return p;
    }
    
    
}
