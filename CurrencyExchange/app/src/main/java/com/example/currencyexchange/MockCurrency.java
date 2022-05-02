package com.example.currencyexchange;

import java.util.ArrayList;

public class MockCurrency implements CurrencyDAO {

    /**
     * Mock data
     * @param base - "base currency"
     * @return - "Return Mock data"
     */
    @Override
    public ArrayList<Rate> getRates(String base) {

        ArrayList<Rate> rateList = new ArrayList<Rate>(){};
        rateList.add(new Rate("GBP",0.84));
        rateList.add(new Rate("PLN",4.68));
        rateList.add(new Rate("DKK",7.44));
        rateList.add(new Rate("USD",1.05));
        rateList.add(new Rate("EUR",1));
        rateList.add(new Rate("RUB",75.23));

        return rateList;
    }
    public MockCurrency()
    {

    }
}