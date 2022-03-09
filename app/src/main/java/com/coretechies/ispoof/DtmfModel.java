package com.coretechies.ispoof;

public class DtmfModel {

    private String ID,number1;
    private long date;

    public DtmfModel(String ID,String number, String date) {
        this.ID=ID;
        this.number1 = number;
        this.date = Long.parseLong(date);
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNumber() {
        return number1;
    }

    public void setNumber(String number) {
        this.number1 = number;
    }

    public Long getDate() {
        return Long.valueOf(String.valueOf(date));
    }

    public void setDate(long date) {
        this.date = date;
    }
}
