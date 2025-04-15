package com.example.cashhelper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    private TextView nowMoney;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nowMoney = findViewById(R.id.nowMoney);
        showBalance();
    }
    protected void onResume(){
        super.onResume();
        showBalance();
    }
    private void showBalance() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(getFilesDir() + "/now_balances.txt"));
            StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = bufferedReader.readLine())!=null){
                stringBuffer.append(line);
            }
            bufferedReader.close();
            nowMoney.setText(stringBuffer.toString());
        } catch (IOException e) {
            e.printStackTrace();
            nowMoney.setText("0");
        }
    }

    public void goContacts(View view){
        Intent intent = new Intent(this, Contacts.class);
        startActivity(intent);
    }
    public void goAbout(View view){
        Intent intent = new Intent(this, AboutApplication.class);
        startActivity(intent);
    }
    public void goIncome(View view){
        Intent intent = new Intent(this, Income.class);
        startActivity(intent);
    }
    public void goOutcome(View view){
        Intent intent = new Intent(this, Outcome.class);
        startActivity(intent);
    }
    public void goHistory(View view){
        Intent intent = new Intent(this, History.class);
        startActivity(intent);
    }
}