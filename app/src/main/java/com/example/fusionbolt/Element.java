package com.example.fusionbolt;

public class Element {

    private String name;
    private String[] propriety;

    private String logo; //path to logo

    public Element(String name, String[] propriety, String logo){
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
}
