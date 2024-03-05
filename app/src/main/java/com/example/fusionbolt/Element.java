package com.example.fusionbolt;

import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Element {

    private String name;
    private String[] propriety;
    private String logo; // chemin du logo

    public Element(String name, String[] propriety, String logo) {
        this.name = name;
        this.propriety = propriety;
        this.logo = logo;
    }

    public String getLogo() {
        return logo;
    }

    public String getName() {
        return name;
    }

    public String[] getPropriety() {
        return propriety;
    }

    public static ArrayList<Element> loadElementsFromAsset(AssetManager assetManager, String fileName) {
        ArrayList<Element> elements = new ArrayList<>();

        try {
            InputStream inputStream = assetManager.open(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(", ");
                if (data.length == 3) {
                    String name = data[0];
                    String propertiesString = data[1].replace("[", "").replace("]", "");
                    String[] properties = propertiesString.split(",");
                    String logo = data[2];

                    Element element = new Element(name, properties, logo);
                    elements.add(element);
                }
            }

            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return elements;
    }
}
