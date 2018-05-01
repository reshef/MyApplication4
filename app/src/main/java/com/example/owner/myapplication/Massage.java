package com.example.owner.myapplication;


public class Massage {

    private String massage;
    private String date;

    public Massage (String massage,String date) {
        this.massage = massage;
        this.date = date;
    }

    public String getMassageText() {
        return this.massage;
    }
    public String getDate() {
        return this.date;
    }
    public void setMassageText(String massage) {

        this.massage = massage;
    }

    @Override
    public String toString() {
            return this.massage;
    }

}