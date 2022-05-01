package com.example.taxcalculation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.slider.Slider;

public class MainActivity extends AppCompatActivity {

    TextView contribution,taxableIncome,totalTax,info;
    EditText income,RRSPLimit;
    Slider slider;
    Button calculate,refresh;
    SharedPreferences preferences;
    float percentage;
    double totalIncome,limitValue;
    String preferenceIncome,preferenceRRSP;
    float preferenceSlider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        income = findViewById(R.id.editIncome);
        RRSPLimit = findViewById(R.id.editRRSPLimit);
        slider = findViewById(R.id.continuousSlider);
        calculate = findViewById(R.id.btnCalculate);
        refresh=findViewById(R.id.btnRefresh);
        contribution = findViewById(R.id.txtRRSPContributionValue);
        taxableIncome = findViewById(R.id.txtTaxableIncomeValue);
        totalTax = findViewById(R.id.txtTotalTaxValue);
        info = findViewById(R.id.txtinfo);

        preferences = getSharedPreferences("MyPreference", Context.MODE_PRIVATE);

        Calculation cal = new Calculation();
        loadData();

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!income.getText().toString().isEmpty() && !RRSPLimit.getText().toString().isEmpty()) {
                    if(income.getText().toString().matches("[0-9]+") && RRSPLimit.getText().toString().matches("[0-9]+")) {
                        if(Double.parseDouble(RRSPLimit.getText().toString()) < 27231) {

                            totalIncome = Double.parseDouble(income.getText().toString());
                            limitValue = Double.parseDouble(RRSPLimit.getText().toString());
                            percentage = slider.getValue();
                            double contributionValue = cal.calculateRRSPContribution(limitValue, percentage);
                            double taxableIncomeValue = cal.calculateTaxableIncome(totalIncome, contributionValue);
                            contribution.setText(Double.toString(contributionValue));
                            taxableIncome.setText(Double.toString(taxableIncomeValue));
                            totalTax.setText(Double.toString(cal.calculateTotalTax(taxableIncomeValue)));

                            if(Double.parseDouble(totalTax.getText().toString()) < 0){
                                info.setText("You are Eligible for Tax Refund");
                            }else
                                info.setText("");
                        }else{
                            Toast.makeText(MainActivity.this,"RRSP Contribution should be between 0 to 27230", Toast.LENGTH_SHORT).show();
                        }
                    } else{
                        Toast.makeText(MainActivity.this,"Enter Valid Number", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(MainActivity.this,"Please fill all the fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("income",income.getText().toString());
                editor.putString("RRSPLimit",RRSPLimit.getText().toString());
                editor.putFloat("sliderValue",slider.getValue());
                editor.commit();
            }
        });
    }

    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences("MyPreference",Context.MODE_PRIVATE);
        income.setText(sharedPreferences.getString("income",""));
        RRSPLimit.setText(sharedPreferences.getString("RRSPLimit",""));
        slider.setValue(sharedPreferences.getFloat("sliderValue",0));
    }
}