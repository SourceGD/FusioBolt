package com.example.fusionbolt;

import java.util.ArrayList;
import java.util.HashMap;

public class Element {

    private final String name;
    private final HashMap<Element,Element> propriety;
    private final String logo; // chemin du logo

    public static Element feu = new Element("Feu", new HashMap<Element, Element>() {{
        put(eau,vapeur);
        put(terre,lave);
        put(vent,fume);
    }},"drawable/feu.png");
    public static Element eau = new Element("Eau",new HashMap<Element, Element>() {{
        put(feu,vapeur);
        put(terre,boue);
        put(vent,vague);
    }},"drawable/gouttes.png");
    public static Element terre = new Element("Terre",new HashMap<Element, Element>() {{
        put(feu,lave);
        put(eau,boue);
        put(vent,dust);
    }},"drawable/sol.png");
    public static Element vent = new Element("Vent",new HashMap<Element, Element>() {{
        put(feu,fume);
        put(eau,vague);
        put(terre,dust);
    }},"drawable/vent.png");
    public static Element vapeur = new Element("Vapeur", new HashMap<Element, Element>() {},"drawable/vapeur.png");
    public static Element lave = new Element("Lave", new HashMap<Element, Element>() {},"drawable/lave.png");;
    public static Element fume = new Element("Fume", new HashMap<Element, Element>() {},"drawable/fume.png");;
    public static Element boue = new Element("Boue", new HashMap<Element, Element>() {},"drawable/boue.png");;
    public static Element vague = new Element("Vague", new HashMap<Element, Element>() {},"drawable/vagues.png");;
    public static Element dust = new Element("Dust", new HashMap<Element, Element>() {},"drawable/dust.png");;


    public Element(String name, HashMap<Element,Element> propriety, String logo) {
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

    public HashMap<Element,Element> getPropriety() {
        return propriety;
    }

    public static ArrayList<Element> initData(){

        ArrayList<Element> elements = new ArrayList<>();

        elements.add(feu);
        elements.add(eau);
        elements.add(terre);
        elements.add(vent);
        elements.add(vapeur);
        elements.add(lave);
        elements.add(fume);
        elements.add(boue);
        elements.add(vague);
        elements.add(dust);

        return elements;

    }
    public static Element resFusion(Element e1, Element e2){return e1.propriety.getOrDefault(e2, e1);}
}
