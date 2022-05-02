package com.example.currencyexchange;

import java.util.List;

public interface CurrencyDAO {

    List<Rate> getRates(String base);
}
