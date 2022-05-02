package com.example.currencyexchange;

public class Rate {

    private String name;
    private double spotRate;

    public String getName() {
        return name;
    }
    public double getSpotRate() {
        return spotRate;
    }

    public Rate(String name, double spotRate)
    {
        this.name = name;
        this.spotRate = spotRate;
    }
}
