package com.example.currencyexchange;

import android.util.Log;

import java.util.ArrayList;

public class CurrencyCalculator {

    public CurrencyCalculator()
    {

    }

    /**
     * //Convert currentAmount to Euro
     * @param base - "Base currency chosen by user
     * @param currencyAmount - "Amount of currency chosen by user"
     * @param rates - "ArrayList with Rates in Euro
     * @return - "a double
     */
    public double convertBaseCurrencyToEur(String base,double currencyAmount,ArrayList<Rate> rates)
    {
        try {
            //currentAmount divided by base currency value to get the currencyAmount to be in Euro
            for (Rate var : rates)
                if (base.equals(var.getName()))
                    currencyAmount = currencyAmount / var.getSpotRate();
        }catch (NullPointerException exception)
        {
            Log.d("Log_CurrencyCalculator_convertBaseCurrencyToEur","Array is empty!");
        }
        return currencyAmount;
    }
    /**
     * Convert currentAmount in Euro to all currencies
     * @param base - "Base currency chosen by user
     * @param currencyAmount - "Amount of currency chosen by user"
     * @param rates - "ArrayList with Rates in Euro
     * @return - "ArrayList with all currencies converted
     */
    public ArrayList<Rate> convertToCurrency(String base,double currencyAmount,ArrayList<Rate> rates)
    {
        ArrayList<Rate> calculatedRates = new ArrayList<>();
        try {
        for (Rate var : rates)
            calculatedRates.add(new Rate(var.getName(),currencyAmount * var.getSpotRate()));
        }catch (NullPointerException exception)
        {
            Log.d("Log_CurrencyCalculator_convertToCurrency","Array is empty!");
        }
        return calculatedRates;
    }

}
