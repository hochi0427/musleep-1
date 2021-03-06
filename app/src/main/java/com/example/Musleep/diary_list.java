package com.example.Musleep;

public class diary_list {
    private String date;
    private long coffee;
    private long nap;

    private diary_list(){}
    private diary_list(long coffee,long nap){
        this.date = date;
        this.coffee = coffee;
        this.nap = nap;
    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getCoffee() {
        return coffee;
    }

    public void setCoffee(long coffee) {
        this.coffee = coffee;
    }

    public long getNap() {
        return nap;
    }

    public void setNap(long little_sleep) {
        this.nap = nap;
    }
}

