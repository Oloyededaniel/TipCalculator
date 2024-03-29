package com.example.tipcalculator;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private EditText baseAmountEditText;
    private SeekBar tipSeekBar;
    private TextView tipPercentageTextView;
    private TextView tipMessageTextView;
    private TextView tipAmountTextView;
    private TextView totalAmountTextView;
    private TextView madeByTextView;

    private double baseAmount = 0.0;
    private int tipPercentage = 15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        baseAmountEditText = findViewById(R.id.base_amount);
        tipSeekBar = findViewById(R.id.tip_percentage);
        tipPercentageTextView = findViewById(R.id.tip_percentage_text);
        tipMessageTextView = findViewById(R.id.tip_message);
        tipAmountTextView = findViewById(R.id.tip_amount);
        totalAmountTextView = findViewById(R.id.total_amount);
        madeByTextView = findViewById(R.id.made_by);
        
        tipPercentageTextView.setText(tipPercentage + "%");
        tipSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tipPercentage = progress;
                tipPercentageTextView.setText(tipPercentage + "%");
                updateTipAndTotalAmounts();
                updateTipMessage();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        baseAmountEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String input = s.toString().trim();
                if (input.isEmpty()) {
                    baseAmount = 0.0;
                } else {
                    baseAmount = Double.parseDouble(input);
                }
                updateTipAndTotalAmounts();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void updateTipAndTotalAmounts() {
        double tipAmount = baseAmount * (tipPercentage / 100.0);
        double totalAmount = baseAmount + tipAmount;


        DecimalFormat df = new DecimalFormat("#.##");
        tipAmount = Double.parseDouble(df.format(tipAmount));
        totalAmount = Double.parseDouble(df.format(totalAmount));

        tipAmountTextView.setText("Tip: $" + tipAmount);
        totalAmountTextView.setText("Total: $" + totalAmount);
    }

    private void updateTipMessage() {
        if (tipPercentage < 10) {
            tipMessageTextView.setText("Poor");
            tipMessageTextView.setTextColor(Color.RED);
        } else if (tipPercentage >= 10 && tipPercentage <= 15) {
            tipMessageTextView.setText("Acceptable");
            tipMessageTextView.setTextColor(Color.parseColor("#FFA500"));
        } else if (tipPercentage > 15 && tipPercentage <= 20) {
            tipMessageTextView.setText("Good");
            tipMessageTextView.setTextColor(Color.parseColor("#90EE90"));
        } else {
            tipMessageTextView.setText("Amazing");
            tipMessageTextView.setTextColor(Color.parseColor("#013220"));
        }
    }
}

