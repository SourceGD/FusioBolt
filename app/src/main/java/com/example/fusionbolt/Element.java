package com.example.fusionbolt;

import java.util.ArrayList;
import java.util.HashMap;

public class Element {
    private String name;
    private String logo;
    private boolean used;

    // Constructeur
    public Element(String name, String logo, boolean used) {
        this.name = name;
        this.logo = logo;
        this.used = used;
    }

    // Getters et Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }
}
