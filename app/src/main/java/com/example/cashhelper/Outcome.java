package com.example.cashhelper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.annotation.SuppressLint;
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

public class Outcome extends AppCompatActivity {
    private ImageButton health, grocery, hobby, home, internet, transport, travel, sport, entertainment, gift, education, other;
    private TextView categorySelected, health_text, other_text, grocery_text, hobby_text, home_text, internet_text, transport_text, travel_text, sport_text, entertainment_text, gift_text, education_text;
    private int main_sum, sum;
    private EditText writeSum, writeSmt;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outcome);
        categorySelected = findViewById(R.id.categorySelected);
        health_text = findViewById(R.id.health_text);
        other_text = findViewById(R.id.other_text);
        grocery_text = findViewById(R.id.grocery_text);
        hobby_text = findViewById(R.id.hobby_text);
        home_text = findViewById(R.id.home_text);
        internet_text = findViewById(R.id.internet_text);
        transport_text = findViewById(R.id.transport_text);
        travel_text = findViewById(R.id.travel_text);
        sport_text = findViewById(R.id.sport_text);
        entertainment_text = findViewById(R.id.entertainment_text);
        gift_text = findViewById(R.id.gift_text);
        education_text = findViewById(R.id.education_text);
        health = findViewById(R.id.health);
        grocery = findViewById(R.id.grocery);
        hobby = findViewById(R.id.hobby);
        home = findViewById(R.id.home);
        internet = findViewById(R.id.internet);
        transport = findViewById(R.id.transport);
        travel = findViewById(R.id.travel);
        sport = findViewById(R.id.sport);
        entertainment = findViewById(R.id.entertainment);
        gift = findViewById(R.id.gift);
        education = findViewById(R.id.education);
        other = findViewById(R.id.other);
        writeSum = findViewById(R.id.writeSum);
        writeSmt = findViewById(R.id.writeSmt);
        buttonsAction();
        loadBalance();
    }

    public void buttonsAction(){
        health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categorySelected.clearComposingText();
                categorySelected.setText(health_text.getText());
                writeSmt.setEnabled(false);
                writeSmt.setText("");
            }
        });
        grocery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categorySelected.clearComposingText();
                categorySelected.setText(grocery_text.getText());
                writeSmt.setEnabled(false);
                writeSmt.setText("");
            }
        });
        hobby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categorySelected.clearComposingText();
                categorySelected.setText(hobby_text.getText());
                writeSmt.setEnabled(false);
                writeSmt.setText("");
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categorySelected.clearComposingText();
                categorySelected.setText(home_text.getText());
                writeSmt.setEnabled(false);
                writeSmt.setText("");
            }
        });
        internet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categorySelected.clearComposingText();
                categorySelected.setText(internet_text.getText());
                writeSmt.setEnabled(false);
                writeSmt.setText("");
            }
        });
        transport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categorySelected.clearComposingText();
                categorySelected.setText(transport_text.getText());
                writeSmt.setEnabled(false);
                writeSmt.setText("");
            }
        });
        travel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categorySelected.clearComposingText();
                categorySelected.setText(travel_text.getText());
                writeSmt.setEnabled(false);
                writeSmt.setText("");
            }
        });
        sport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categorySelected.clearComposingText();
                categorySelected.setText(sport_text.getText());
                writeSmt.setEnabled(false);
                writeSmt.setText("");
            }
        });
        entertainment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categorySelected.clearComposingText();
                categorySelected.setText(entertainment_text.getText());
                writeSmt.setEnabled(false);
                writeSmt.setText("");
            }
        });
        gift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categorySelected.clearComposingText();
                categorySelected.setText(gift_text.getText());
                writeSmt.setEnabled(false);
                writeSmt.setText("");
            }
        });
        education.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categorySelected.clearComposingText();
                categorySelected.setText(education_text.getText());
                writeSmt.setEnabled(false);
                writeSmt.setText("");
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
    public void saveData(View view){
        String category = categorySelected.getText().toString();
        String sumString = writeSum.getText().toString();
        String otherCategory = writeSmt.getText().toString();
        String records = "";
        if(category.isEmpty() || sumString.isEmpty() || (category.equals(other_text.getText().toString()) && otherCategory.isEmpty())){
            Toast.makeText(this, "Заполните все поля", Toast.LENGTH_LONG).show();
            return;
        }
        int sum = Integer.parseInt(sumString);
        if(sum>main_sum){
            Toast.makeText(this,"У вас на балансе недостаточно средств",Toast.LENGTH_LONG).show();
        }
        else {
            main_sum -= sum;
            try {
                FileOutputStream fileOutputStream1 = openFileOutput("user_money_story.txt", Context.MODE_APPEND);
                FileOutputStream fileOutputStream2 = openFileOutput("now_balances.txt", Context.MODE_PRIVATE);
                if (sum < main_sum) {
                    if (category.equals(other_text.getText())) {
                        records = "Расход " + category + " " + otherCategory + " " + sum;
                    } else {
                        records = "Расход " + category + " " + sum;
                    }
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
    public void goMain(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void goAbout(View view) {
        Intent intent = new Intent(this, AboutApplication.class);
        startActivity(intent);
    }
    public void goContacts(View view){
        Intent intent = new Intent(this, Contacts.class);
        startActivity(intent);
    }
}