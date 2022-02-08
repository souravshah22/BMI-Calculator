package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.text.DecimalFormat;


public class MainActivity extends AppCompatActivity {

    private TextView resultText;
    private Button calculatebmi;
    private RadioButton male;
    private RadioButton female;
    private EditText age;
    private EditText feet;
    private EditText inches;
    private EditText weight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        SetupButtonClickLIstener();
    }

    private void findViews() {
        resultText = findViewById(R.id.text_view_result);

        male = findViewById(R.id.radio_button_male);
        female = findViewById(R.id.radio_button_female);
        age = findViewById(R.id.edit_text_age);
        feet = findViewById(R.id.edit_text_feet);
        inches = findViewById(R.id.edit_text_inches);
        weight = findViewById(R.id.edit_text_Weight);
        calculatebmi = findViewById(R.id.button_Calculate);
    }

    private void SetupButtonClickLIstener() {
        calculatebmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double bmiresult = calculatebmi();

                String ageText = age.getText().toString();
                int age = Integer.parseInt(ageText);

                if (age >= 18) {
                    displayResult(bmiresult);
                } else {
                    displayguidance(bmiresult);
                }
            }
        });
    }


    private double calculatebmi() {
        String ageText = age.getText().toString();
        String feetText = feet.getText().toString();
        String inchesText = inches.getText().toString();
        String weightText = weight.getText().toString();

        //converting no string into int variables
        int age = Integer.parseInt(ageText);
        int feet = Integer.parseInt(feetText);
        int inches = Integer.parseInt(inchesText);
        int weight = Integer.parseInt(weightText);

        int totalinches = (feet * 12) + inches;

        //height in meters is the incehs mutipled by 0.0254
        double heightInMeters = totalinches * 0.0254;

        return weight / (heightInMeters * heightInMeters);
    }

    private void displayResult(double bmi) {

        DecimalFormat mydeciamlformater = new DecimalFormat("0.00"
        );
        String bmiTextResult = mydeciamlformater.format(bmi);

        String fullresultstring;
        if (bmi < 18.5) {
            //display underweight
            fullresultstring = bmiTextResult + " -you are underweight";
        } else if (bmi > 25) {
            fullresultstring = bmiTextResult + " -you are overweight";
            //display overweight
        } else {
            fullresultstring = bmiTextResult + " -you are healthy";
            //display healthy
        }
        resultText.setText(fullresultstring);
    }

    private void displayguidance(double bmi) {
        DecimalFormat mydeciamlformater = new DecimalFormat("0.00");
        String bmiTextResult = mydeciamlformater.format(bmi);
        String fullresultString = new String();
        if (female.isChecked()) {
            fullresultString = bmiTextResult + "- as you are under 18 pls refer to your doc girl";
        } else if (male.isChecked()) {
            fullresultString = bmiTextResult + "- as you are under 18 pls refer to you doc boy";
        }
        resultText.setText(fullresultString);

    }

}


