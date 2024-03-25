package com.example.fusionbolt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;


import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHelper(this);
        dbHelper.clearDatabase();
        insertInitialData();
    }

    private void insertInitialData() {
        // Ajouter des éléments

        dbHelper.addElement("Feu", "drawable/feu");
        dbHelper.setElementUsed("Feu");
        dbHelper.addElement("Eau", "drawable/gouttes");
        dbHelper.setElementUsed("Eau");
        dbHelper.addElement("Terre", "drawable/sol");
        dbHelper.setElementUsed("Terre");
        dbHelper.addElement("Vent", "drawable/vent");
        dbHelper.setElementUsed("Vent");
        dbHelper.addElement("Vapeur", "drawable/vapeur");
        dbHelper.addElement("Lave", "drawable/lave");
        dbHelper.addElement("Fumée", "drawable/fume");
        dbHelper.addElement("Boue", "drawable/boue");
        dbHelper.addElement("Vague", "drawable/vague");
        dbHelper.addElement("Poussière", "drawable/dust2");



        dbHelper.addRelation("Feu", "Eau", "Vapeur");
        dbHelper.addRelation("Feu", "Terre", "Lave");
        dbHelper.addRelation("Feu", "Vent", "Fumée");
        dbHelper.addRelation("Eau", "Terre", "Boue");
        dbHelper.addRelation("Eau", "Vent", "Vague");
        dbHelper.addRelation("Terre", "Vent", "Poussière");

    }
}
