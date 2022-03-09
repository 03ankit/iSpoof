
package com.coretechies.ispoof;

public class Call_log_Model
{

            //private int id;
            private String Phname,number;
            private long date;

    public Call_log_Model(String name, String number, String date) {
        this.Phname = name;
        this.number = number;
        this.date = Long.parseLong(date);
    }

    public String getName() {
        return Phname;
    }
    public void setName(String name) {
        this.Phname = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Long getDate() {
        return Long.valueOf(String.valueOf(date));
    }

    public void setDate(long date) {
        this.date = date;
    }

        }


