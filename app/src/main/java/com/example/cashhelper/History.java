package com.example.cashhelper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class History extends AppCompatActivity {
    private ListView list;
    ArrayList<String> history = new ArrayList<>();
    ArrayList<String> empty = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_history);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        list = findViewById(R.id.list);
        readContact();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.design,R.id.user_name ,history);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(History.this,(String)list.getItemAtPosition(position),Toast.LENGTH_LONG).show();
            }
        });
    }
    public void readContact(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(getFilesDir() + "/user_money_story.txt"));
            String line = null;
            while ((line = reader.readLine()) != null) {
                history.add(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            empty.add("Добавьте первую запись в доходах");
        }
    }

    public void goMain(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void goAbout(View view){
        Intent intent = new Intent(this, AboutApplication.class);
        startActivity(intent);
    }
    public void goContacts(View view){
        Intent intent = new Intent(this, Contacts.class);
        startActivity(intent);
    }
}