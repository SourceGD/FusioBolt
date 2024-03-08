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


public class MainActivity extends AppCompatActivity {

    public ArrayList<Element> elements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        elements = Element.initData();

        for (Element element : elements) {
            System.out.println("Nom : " + element.getName());
            for (Map.Entry<Element, Element> entry : element.getPropriety().entrySet()) {
                Element key = entry.getKey();
                Element value = entry.getValue();
                System.out.println("Clé : " + key.getName() + ", Valeur : " + value.getName());
            }
        }
    }
}