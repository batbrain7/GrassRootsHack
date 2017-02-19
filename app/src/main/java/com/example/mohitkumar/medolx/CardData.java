package com.example.mohitkumar.medolx;

/**
 * Created by mohitkumar on 19/02/17.
 */

public class CardData {

    String medname,meddose,expirydate,address,quantity,usrname;
    public CardData(String meddose,String medname,String expirydate,String address,String quantity,String usrname) {
        this.meddose = meddose;
        this.medname = medname;
        this.expirydate = expirydate;
        this.address = address;
        this.quantity = quantity;
        this.usrname = usrname;
    }

    public String getUsrname() {
        return usrname;
    }

    public void setUsrname(String usrname) {
        this.usrname = usrname;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getExpirydate() {
        return expirydate;
    }

    public String getMeddose() {
        return meddose;
    }

    public String getMedname() {
        return medname;
    }

    public String getAddress() {
        return address;
    }

    public void setExpirydate(String expirydate) {
        this.expirydate = expirydate;
    }

    public void setMeddose(String meddose) {
        this.meddose = meddose;
    }

    public void setMedname(String medname) {
        this.medname = medname;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
