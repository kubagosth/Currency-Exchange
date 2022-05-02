package com.example.currencyexchange;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private FixerCurrency fixerCurrency;
    private Spinner spinner;
    private String base;
    private String[] spinnerArray;
    private ArrayList<Rate> arrayOfRates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //CurrencyPresenter currencyPresenter = new CurrencyPresenter();

        fixerCurrency = new FixerCurrency(this);
        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        //Temporary
        spinnerArray = new String[]{"EUR", "USD", "GBP", "DKK", "PLN"};
        populateListDropDown();
         //Find button in current activity and create on click listener
        Button exchangeButton = findViewById(R.id.buttonCalculate);
        exchangeButton.setOnClickListener(v -> {
            base = spinner.getSelectedItem().toString();
            populateListView();

            //spinnerArray = fillSpinner();
            //populateListDropDown();
        });
    }

    /**
     * // UNFINISHED
     * @return - "String array for spinner drop down"
     */

    public String[] fillSpinner()
    {
        ArrayList<String> arrayList = new ArrayList<>();

        for (int i = 0; i < arrayOfRates.size(); i++) {
            arrayList.add(arrayOfRates.get(i).getName());
        }
        return arrayList.toArray(new String[0]);
    }

    /**
     * Populate drop down menu
     */
    public void populateListDropDown()
    {
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,spinnerArray);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

    }
    /**
     * Populate list view
     */
    public void populateListView()
    {
        // Construct the data source
        CurrencyCalculator calculator = new CurrencyCalculator();
        TextView textView = findViewById(R.id.editTextInput);
        double amount = Double.parseDouble(textView.getText().toString());

        //Get the rates
        arrayOfRates = fixerCurrency.getRates(base);
        //Convert the currency to eur
        amount = calculator.convertBaseCurrencyToEur(base,amount,arrayOfRates);
        //Calculate all rates
        arrayOfRates = calculator.convertToCurrency(base,amount,arrayOfRates);
        // Create the adapter to convert the array to views
        RatesAdapter adapter = new RatesAdapter(this, arrayOfRates);
        // Attach the adapter to a ListView
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Log.d("Log_Main_onItemSelected",adapterView.getSelectedItem().toString());

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}