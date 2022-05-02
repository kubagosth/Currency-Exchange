package com.example.currencyexchange;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class FixerCurrency implements CurrencyDAO {
    private ArrayList<Rate> rates;
    @Override
    public ArrayList<Rate> getRates(String base) {
        return rates;
    }
    public void setRates(ArrayList<Rate> rates) {
        this.rates = rates;
    }

    /**
     * //FixerCurrency constructor on init send a volley request
     * @param view - "MainActivity view requested by volley"
     */
    public FixerCurrency(MainActivity view)
    {
        //Make a new request queue using volley
        RequestQueue queue = Volley.newRequestQueue(view);
        String API_KEY = "2dd15f590bb35c99ceb35bba25b9deab";
        String BASE = "EUR";
        String url = "http://data.fixer.io/api/latest?access_key=" + API_KEY + "&base=" + BASE;


        //Request object type JSON
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, response -> {
                    try {
                        setRates(extractList(response));
                        Log.d("Log_FixerCurrency_Volley","Response OK");
                    } catch (JSONException e)
                    {
                        Log.d("Log_FixerCurrency_Volley","JSON Extraction failed");
                    }
                }, error -> Log.d("Log_FixerCurrency_Volley","Response failed"));
        queue.add(jsonObjectRequest);
    }

    /**
     * // extract objects from Json file and save to array list
     * @param response - "Get json object from api response"
     * @return - "Return a array list with all objects"
     */
    public ArrayList<Rate> extractList(JSONObject response) throws JSONException {
        //Get the response array in json format
        JSONObject json = response.getJSONObject("rates");
        //Check for all elements with key value pairs
        Iterator<String> keys = json.keys();
        HashMap<String, String> map = new HashMap<String, String>();
        while(keys.hasNext()) {
            String key = keys.next();
            map.put(key, json.optString(key));
        }
        //Save all pairs to arraylist of rate object
        ArrayList<Rate> arrayList = new ArrayList<>();
        map.forEach(
                (key, value)
                        -> arrayList.add(new Rate(key,Double.parseDouble(value))));

        return arrayList;
    }
}
