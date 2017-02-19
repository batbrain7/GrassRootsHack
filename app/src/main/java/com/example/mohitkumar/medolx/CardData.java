package com.example.mohitkumar.medolx;

/**
 * Created by mohitkumar on 19/02/17.
 */

public class CardData {

    String medname,meddose,medpersonname,expirydate,address;
    public CardData(String meddose,String medname,String medpersonname,String expirydate,String address) {
        this.meddose = meddose;
        this.medname = medname;
        this.medpersonname = medpersonname;
        this.expirydate = expirydate;
        this.address = address;
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

    public String getMedpersonname() {
        return medpersonname;
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

    public void setMedpersonname(String medpersonname) {
        this.medpersonname = medpersonname;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
