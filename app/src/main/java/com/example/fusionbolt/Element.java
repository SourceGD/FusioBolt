package com.example.fusionbolt;

import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Element {

    private String name;
    private HashMap<String,String> propriety;
    private String logo; // chemin du logo

    public Element(String name, HashMap<String,String> propriety, String logo) {
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

    public HashMap<String,String> getPropriety() {
        return propriety;
    }

    public static ArrayList<Element> initData(){

        ArrayList<Element> elements = new ArrayList<>();

        elements.add(new Element("Feu", new HashMap<String, String>() {{
            put("Eau","Vapeur");
            put("Terre","Lave");
            put("Vent","Fume");
        }},"drawable/feu.png"));
        elements.add(new Element("Eau",new HashMap<String, String>() {{
            put("Feu","Vapeur");
            put("Terre","Boue");
            put("Vent","Vague");
        }},"drawable/gouttes.png"));
        elements.add(new Element("Terre",new HashMap<String, String>() {{
            put("Feu","Lave");
            put("Eau","Boue");
            put("Vent","Dust");
        }},"drawable/sol.png"));
        elements.add(new Element("Vent",new HashMap<String, String>() {{
            put("Feu","Fume");
            put("Eau","Vague");
            put("Terre","Dust");
        }},"drawable/vent.png"));
        elements.add(new Element("Vapeur", new HashMap<String, String>() {},"drawable/vapeur.png"));
        elements.add(new Element("Lave", new HashMap<String, String>() {},"drawable/lave.png"));
        elements.add(new Element("Fume", new HashMap<String, String>() {},"drawable/fume.png"));
        elements.add(new Element("Boue", new HashMap<String, String>() {},"drawable/boue.png"));
        elements.add(new Element("Vague", new HashMap<String, String>() {},"drawable/vagues.png"));
        elements.add(new Element("Dust", new HashMap<String, String>() {},"drawable/dust.png"));

        return elements;

    }
}
