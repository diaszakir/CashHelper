package com.example.cashhelper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Income extends AppCompatActivity {
    private ImageButton work, business, acsia, present, freelance, sell, crypto, other;
    private TextView categorySelected, work_text, business_text, acsia_text, present_text, freelance_text, sell_text, crypto_text, other_text;
    private EditText writeSum, writeSmt;
    private int main_sum, sum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);
        work = findViewById(R.id.work);
        business = findViewById(R.id.business);
        acsia = findViewById(R.id.acsia);
        present = findViewById(R.id.present);
        freelance = findViewById(R.id.freelance);
        sell = findViewById(R.id.sell);
        crypto = findViewById(R.id.crypto);
        other = findViewById(R.id.other);
        categorySelected = findViewById(R.id.categorySelected);
        work_text = findViewById(R.id.work_text);
        business_text = findViewById(R.id.business_text);
        acsia_text = findViewById(R.id.acsia_text);
        present_text = findViewById(R.id.present_text);
        freelance_text = findViewById(R.id.freelance_text);
        sell_text = findViewById(R.id.sell_text);
        crypto_text = findViewById(R.id.crypto_text);
        other_text = findViewById(R.id.other_text);
        writeSum = findViewById(R.id.writeSum);
        writeSmt = findViewById(R.id.writeSmt);
        loadBalance();
        //addToSum();
        work.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categorySelected.clearComposingText();
                categorySelected.setText(work_text.getText());
                writeSmt.setEnabled(false);
                writeSmt.setText("");
            }
        });
        business.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categorySelected.clearComposingText();
                categorySelected.setText(business_text.getText());
                writeSmt.setEnabled(false);
                writeSmt.setText("");
            }
        });
        acsia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categorySelected.clearComposingText();
                categorySelected.setText(acsia_text.getText());
                writeSmt.setEnabled(false);
                writeSmt.setText("");
            }
        });
        present.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categorySelected.clearComposingText();
                categorySelected.setText(present_text.getText());
                writeSmt.setEnabled(false);
                writeSmt.setText("");
            }
        });
        freelance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categorySelected.clearComposingText();
                categorySelected.setText(freelance_text.getText());
                writeSmt.setEnabled(false);
                writeSmt.setText("");
            }
        });
        sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categorySelected.clearComposingText();
                categorySelected.setText(sell_text.getText());
                writeSmt.setEnabled(false);
                writeSmt.setText("");
            }
        });
        crypto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categorySelected.clearComposingText();
                categorySelected.setText(crypto_text.getText());
                writeSmt.setEnabled(false);
                writeSmt.requestFocus();
            }
        });
        other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categorySelected.clearComposingText();
                categorySelected.setText(other_text.getText());
                writeSmt.setEnabled(true);
                writeSmt.requestFocus();
            }
        });
    }

    private void loadBalance() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(openFileInput("now_balances.txt")));
            String line = bufferedReader.readLine();
            if(line!=null){
                main_sum = Integer.parseInt(line);
            }
            else{
                main_sum = 0;
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
            main_sum = 0;
        }
    }
    public void goMain(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void goContacts(View view){
        Intent intent = new Intent(this, Contacts.class);
        startActivity(intent);
    }
    public void goAbout(View view){
        Intent intent = new Intent(this, AboutApplication.class);
        startActivity(intent);
    }
    public void saveData(View view){
        String category = categorySelected.getText().toString();
        String sumString = writeSum.getText().toString();
        String otherCategory = writeSmt.getText().toString();
        String records;
        if(category.isEmpty() || sumString.isEmpty() || (category.equals(other_text.getText().toString()) && otherCategory.isEmpty())){
            Toast.makeText(this, "Заполните все поля", Toast.LENGTH_LONG).show();
            return;
        }
        else {
            try {
                int sum = Integer.parseInt(sumString);
                main_sum += sum;
                FileOutputStream fileOutputStream1 = openFileOutput("user_money_story.txt", Context.MODE_APPEND);
                FileOutputStream fileOutputStream2 = openFileOutput("now_balances.txt", Context.MODE_PRIVATE);
                if (category.equals(other_text.getText())) {
                    records = "Доход " + category + " " + otherCategory + " " + sum;
                } else {
                    records = "Доход " + category + " " + sum;
                }
                fileOutputStream1.write((records + "\n").getBytes());
                fileOutputStream2.write(String.valueOf(main_sum).getBytes());
                fileOutputStream1.close();
                fileOutputStream2.close();
                Toast.makeText(this, "Данные сохранены", Toast.LENGTH_LONG).show();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(this, "Не возможно обработать", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}