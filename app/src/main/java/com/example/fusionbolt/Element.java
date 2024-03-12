package com.example.fusionbolt;

import java.util.ArrayList;
import java.util.HashMap;

public class Element {

    private final String name;
    private final HashMap<Element, Element> propriety;
    private final String logo; // chemin du logo

    // Éléments statiques privés
    private static Element feu;
    private static Element eau;
    private static Element terre;
    private static Element vent;
    private static Element vapeur;
    private static Element lave;
    private static Element fume;
    private static Element boue;
    private static Element vague;
    private static Element dust;

    private Element(String name, HashMap<Element, Element> propriety, String logo) {
        this.name = name;
        this.propriety = propriety;
        this.logo = logo;
    }

    // Méthodes statiques publiques pour l'accès aux éléments
    public static Element getFeu() {
        if (feu == null) {
            feu = new Element("Feu", new HashMap<>(), "drawable/feu.png");
            feu.propriety.put(getEau(), getVapeur());
            feu.propriety.put(getTerre(), getLave());
            feu.propriety.put(getVent(), getFume());
        }
        return feu;
    }

    public static Element getEau() {
        if (eau == null) {
            eau = new Element("Eau", new HashMap<>(), "drawable/gouttes.png");
            eau.propriety.put(getFeu(), getVapeur());
            eau.propriety.put(getTerre(), getBoue());
            eau.propriety.put(getVent(), getVague());
        }
        return eau;
    }

    public static Element getTerre() {
        if (terre == null) {
            terre = new Element("Terre", new HashMap<>(), "drawable/sol.png");
            terre.propriety.put(getFeu(), getLave());
            terre.propriety.put(getEau(), getBoue());
            terre.propriety.put(getVent(), getDust());
        }
        return terre;
    }

    public static Element getVent() {
        if (vent == null) {
            vent = new Element("Vent", new HashMap<>(), "drawable/vent.png");
            vent.propriety.put(getFeu(), getFume());
            vent.propriety.put(getEau(), getVague());
            vent.propriety.put(getTerre(), getDust());
        }
        return vent;
    }

    public static Element getVapeur() {
        if (vapeur == null) {
            vapeur = new Element("Vapeur", new HashMap<>(), "drawable/vapeur.png");
        }
        return vapeur;
    }

    public static Element getLave() {
        if (lave == null) {
            lave = new Element("Lave", new HashMap<>(), "drawable/lave.png");
        }
        return lave;
    }

    public static Element getFume() {
        if (fume == null) {
            fume = new Element("Fume", new HashMap<>(), "drawable/fume.png");
        }
        return fume;
    }

    public static Element getBoue() {
        if (boue == null) {
            boue = new Element("Boue", new HashMap<>(), "drawable/boue.png");
        }
        return boue;
    }

    public static Element getVague() {
        if (vague == null) {
            vague = new Element("Vague", new HashMap<>(), "drawable/vague.png");
        }
        return vague;
    }

    public static Element getDust() {
        if (dust == null) {
            dust = new Element("Dust", new HashMap<>(), "drawable/dust.png");
        }
        return dust;
    }

    // Getters
    public String getName() {
        return name;
    }

    public HashMap<Element, Element> getPropriety() {
        return propriety;
    }

    public String getLogo() {
        return logo;
    }
    public String getDrawableName() {
        if (logo != null && logo.contains("/")) {
            String[] parts = logo.split("/");
            String fileName = parts[parts.length - 1];
            int dotIndex = fileName.lastIndexOf('.');
            if (dotIndex > 0) {
                return fileName.substring(0, dotIndex);
            }
        }
        return null;
    }

    public static ArrayList<Element> initData(){

        ArrayList<Element> elements = new ArrayList<>();

        elements.add(getFeu());
        elements.add(getEau());
        elements.add(getTerre());
        elements.add(getVent());
        elements.add(getVapeur());
        elements.add(getLave());
        elements.add(getFume());
        elements.add(getBoue());
        elements.add(getVague());
        elements.add(getDust());

        return elements;

    }
    public static Element resFusion(Element e1, Element e2){return e1.propriety.getOrDefault(e2, e1);}
}
