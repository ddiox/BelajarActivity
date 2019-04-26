package com.example.belajaractivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edtNilai1, edtNilai2, edtNilai3;
    Button btnCalculate, btnReset;
    TextView tvAverage, tvMedian, tvMin, tvMax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtNilai1 = findViewById(R.id.edt_nilai1);
        edtNilai2 = findViewById(R.id.edt_nilai2);
        edtNilai3= findViewById(R.id.edt_nilai3);
        btnCalculate = findViewById(R.id.btn_calculate);
        btnReset = findViewById(R.id.btn_reset);
        tvAverage = findViewById(R.id.txt_average);
        tvMedian = findViewById(R.id.txt_median);
        tvMin = findViewById(R.id.txt_min);
        tvMax = findViewById(R.id.txt_max);

        btnCalculate.setOnClickListener(this);
        btnReset.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_calculate) {
            String number1 = edtNilai1.getText().toString();
            String number2 = edtNilai2.getText().toString();
            String number3 = edtNilai3.getText().toString();

            boolean isEmptyFields = false;
            boolean isInvalidDouble = false;

            if (TextUtils.isEmpty(number1)) {
                isEmptyFields = true;
                edtNilai1.setError("Field ini tidak boleh kosong");
            }

            if (TextUtils.isEmpty(number2)) {
                isEmptyFields = true;
                edtNilai2.setError("Field ini tidak boleh kosong");
            }

            if (TextUtils.isEmpty(number3)) {
                isEmptyFields = true;
                edtNilai3.setError("Field ini tidak boleh kosong");
            }

            Double input1 = toDouble(number1);
            Double input2 = toDouble(number2);
            Double input3 = toDouble(number3);


            if (input1 == null) {
                isInvalidDouble = true;
                edtNilai1.setError("Field ini harus berupa nomor yang valid");
            }

            if (input2 == null) {
                isInvalidDouble = true;
                edtNilai2.setError("Field ini harus berupa nomor yang valid");
            }

            if (input3 == null) {
                isInvalidDouble = true;
                edtNilai3.setError("Field ini harus berupa nomor yang valid");
            }

            if (!isEmptyFields && !isInvalidDouble) {
                average(input1, input2, input3);
                median(input1, input2, input3);
                maximum(input1, input2, input3);
                minimum(input1, input2, input3);
            }
        }
        if (v.getId() == R.id.btn_reset){
            startActivity(new Intent(MainActivity.this, MainActivity.class));
        }
    }

    public void average(Double i1, Double i2, Double i3){
        double av = (i1+i2+i3)/3;
        tvAverage.setText(String.valueOf(av));
    }

    public void median(Double i1, Double i2, Double i3){
        double ar[] = {i1,i2,i3};
        Arrays.sort(ar);
        double middle = (ar[1]/2)+1;
        tvMedian.setText(String.valueOf(middle));
    }

    public void maximum(Double i1, Double i2, Double i3){
        if(i1 >= i2 && i1 >= i3){
            tvMax.setText(String.valueOf(i1));
        }
        else if(i2 >= i1 && i2 >= i3){
            tvMax.setText(String.valueOf(i2));
        }
        else if(i3 >= i1 && i3 >= i2){
            tvMax.setText(String.valueOf(i3));
        }

    }

    public void minimum(Double i1, Double i2, Double i3){
        if(i1 <= i2 && i1 <= i3){
            tvMin.setText(String.valueOf(i1));
        }
        else if(i2 <= i1 && i2 <= i3){
            tvMin.setText(String.valueOf(i2));
        }
        else if(i3 <= i1 && i3 <= i2){
            tvMin.setText(String.valueOf(i3));
        }

    }

    Double toDouble(String str) {
        try {
            return Double.valueOf(str);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
