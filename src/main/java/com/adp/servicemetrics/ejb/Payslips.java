/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adp.servicemetrics.ejb;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.bson.types.ObjectId;

/**
 *
 * @author pdusek
 */
public class Payslips {
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId pk;
    private String CUID;
    private String CID;
    private String Client;
    private String Country;
    private int eeFromBPM;
    private int eeCorrection;
    private int ee2Invoice;

    public Payslips(ObjectId pk, String CUID, String CID, String Client, String Country, int eeFromBPM, int eeCorrection, int ee2Invoice) {
        this.pk = pk;
        this.CUID = CUID;
        this.CID = CID;
        this.Client = Client;
        this.Country = Country;
        this.eeFromBPM = eeFromBPM;
        this.eeCorrection = eeCorrection;
        this.ee2Invoice = ee2Invoice;
    }

    @Override
    public String toString() {
        return "Payslips{" + "pk=" + pk + ", CUID=" + CUID + ", CID=" + CID + ", Client=" + Client + ", Country=" + Country + ", eeFromBPM=" + eeFromBPM + ", eeCorrection=" + eeCorrection + ", ee2Invoice=" + ee2Invoice + '}';
    }

    public Payslips() {
    }

   

    public ObjectId getPk() {
        return pk;
    }

    public void setPk(ObjectId pk) {
        this.pk = pk;
    }

    public String getCUID() {
        return CUID;
    }

    public void setCUID(String CUID) {
        this.CUID = CUID;
    }

    public String getCID() {
        return CID;
    }

    public void setCID(String CID) {
        this.CID = CID;
    }

    public String getClient() {
        return Client;
    }

    public void setClient(String Client) {
        this.Client = Client;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String Country) {
        this.Country = Country;
    }

    public int getEeFromBPM() {
        return eeFromBPM;
    }

    public void setEeFromBPM(int eeFromBPM) {
        this.eeFromBPM = eeFromBPM;
    }

    public int getEeCorrection() {
        return eeCorrection;
    }

    public void setEeCorrection(int eeCorrection) {
        this.eeCorrection = eeCorrection;
    }

    public int getEe2Invoice() {
        return ee2Invoice;
    }

    public void setEe2Invoice(int ee2Invoice) {
        this.ee2Invoice = ee2Invoice;
    }
    
    
    
    
    
}
