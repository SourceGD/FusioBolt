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


        //new ajout
        dbHelper.addElement("Roche", "drawable/Roche");
        dbHelper.addElement("Feu Follet", "drawable/Feu Follet");
        dbHelper.addElement("Pierre", "drawable/Pierre");
        dbHelper.addElement("Océan", "drawable/Océan");
        dbHelper.addElement("Argile", "drawable/Argile");
        dbHelper.addElement("Géysère", "drawable/Géysère");
        dbHelper.addElement("Rivière", "drawable/Rivière");
        dbHelper.addElement("Sable", "drawable/Sable");
        dbHelper.addElement("Cendres", "drawable/Cendres");
        dbHelper.addElement("Marais", "drawable/Marais");
        dbHelper.addElement("Nuage", "drawable/Nuage");
        dbHelper.addElement("Brume", "drawable/Brume");
        dbHelper.addElement("Volcan", "drawable/Volcan");
        dbHelper.addElement("Brouillard", "drawable/Brouillard");
        dbHelper.addElement("Rochers Chauds", "drawable/Rochers Chauds");
        dbHelper.addElement("Plage", "drawable/Plage");
        dbHelper.addElement("Éclair", "drawable/Éclair");
        dbHelper.addElement("Île", "drawable/Île");
        dbHelper.addElement("Rosée", "drawable/Rosée");
        dbHelper.addElement("Verre", "drawable/Verre");
        dbHelper.addElement("Cascade", "drawable/Cascade");
        dbHelper.addElement("Montagne", "drawable/Montagne");
        dbHelper.addElement("Pierres Précieuses", "drawable/Pierres Précieuses");
        dbHelper.addElement("Pluie", "drawable/Pluie");
        dbHelper.addElement("Tourbière", "drawable/Tourbière");
        dbHelper.addElement("Sable Brûlant", "drawable/Sable Brûlant");
        dbHelper.addElement("Magma", "drawable/Magma");
        dbHelper.addElement("Falaise", "drawable/Falaise");
        dbHelper.addElement("Glace", "drawable/Glace");
        dbHelper.addElement("Évaporation", "drawable/Évaporation");
        dbHelper.addElement("Silex", "drawable/Silex");
        dbHelper.addElement("Lit de Rivière", "drawable/Lit de Rivière");
        dbHelper.addElement("Gouttes", "drawable/Gouttes d'eau");
        dbHelper.addElement("Côte", "drawable/Côte");
        dbHelper.addElement("Gravier", "drawable/Gravier");
        dbHelper.addElement("Glacière", "drawable/Glacière");
        dbHelper.addElement("Sel", "drawable/Sel");
        dbHelper.addElement("Moulin à Eau", "drawable/Moulin à Eau");
        dbHelper.addElement("Source", "drawable/Source");
        dbHelper.addElement("Obsidienne", "drawable/Obsidienne");
        dbHelper.addElement("Scories", "drawable/Scories");
        dbHelper.addElement("Torrent", "drawable/Torrent");
        dbHelper.addElement("Sommet", "drawable/Sommet");
        dbHelper.addElement("Rivage", "drawable/Rivage");
        dbHelper.addElement("Ruisseau", "drawable/Ruisseau");
        dbHelper.addElement("Gisement", "drawable/Gisement");



        dbHelper.addRelation("Feu", "Eau", "Vapeur");
        dbHelper.addRelation("Feu", "Vent", "Fumée");
        dbHelper.addRelation("Eau", "Terre", "Boue");
        dbHelper.addRelation("Eau", "Vent", "Vague");
        dbHelper.addRelation("Terre", "Vent", "Poussière");



        //new ajout
        dbHelper.addRelation("Feu", "Terre", "Roche");
        dbHelper.addRelation("Feu", "Feu", "Feu Follet");
        dbHelper.addRelation("Terre", "Terre", "Pierre");
        dbHelper.addRelation("Eau", "Eau", "Océan");
        dbHelper.addRelation("Eau", "Roche", "Argile");
        dbHelper.addRelation("Feu", "Boue", "Fumée");
        dbHelper.addRelation("Terre", "Vapeur", "Géysère");
        dbHelper.addRelation("Roche", "Eau", "Rivière");
        dbHelper.addRelation("Eau", "Pierre", "Sable");
        dbHelper.addRelation("Terre", "Fumée", "Cendres");
        dbHelper.addRelation("Roche", "Feu", "Lave");
        dbHelper.addRelation("Boue", "Eau", "Marais");
        dbHelper.addRelation("Vapeur", "Terre", "Nuage");
        dbHelper.addRelation("Feu", "Océan", "Brume");
        dbHelper.addRelation("Terre", "Lave", "Volcan");
        dbHelper.addRelation("Eau", "Brume", "Brouillard");
        dbHelper.addRelation("Lave", "Eau", "Rochers Chauds");
        dbHelper.addRelation("Terre", "Océan", "Plage");
        dbHelper.addRelation("Feu", "Nuage", "Éclair");
        dbHelper.addRelation("Roche", "Océan", "Île");
        dbHelper.addRelation("Terre", "Brume", "Rosée");
        dbHelper.addRelation("Feu", "Sable", "Verre");
        dbHelper.addRelation("Eau", "Géysère", "Cascade");
        dbHelper.addRelation("Terre", "Île", "Montagne");
        dbHelper.addRelation("Roche", "Cendres", "Pierres Précieuses");
        dbHelper.addRelation("Eau", "Nuage", "Pluie");
        dbHelper.addRelation("Terre", "Marais", "Tourbière");
        dbHelper.addRelation("Feu", "Plage", "Sable Brûlant");
        dbHelper.addRelation("Boue", "Géysère", "Magma");
        dbHelper.addRelation("Roche", "Brume", "Falaise");
        dbHelper.addRelation("Eau", "Verre", "Glace");
        dbHelper.addRelation("Feu", "Cascade", "Évaporation");
        dbHelper.addRelation("Terre", "Éclair", "Silex");
        dbHelper.addRelation("Roche", "Rivière", "Lit de Rivière");
        dbHelper.addRelation("Eau", "Rosée", "Gouttes");
        dbHelper.addRelation("Boue", "Plage", "Côte");
        dbHelper.addRelation("Roche", "Sable", "Gravier");
        dbHelper.addRelation("Terre", "Glace", "Glacière");
        dbHelper.addRelation("Roche", "Évaporation", "Sel");
        dbHelper.addRelation("Eau", "Silex", "Moulin à Eau");
        dbHelper.addRelation("Terre", "Gouttes", "Source");
        dbHelper.addRelation("Roche", "Volcan", "Obsidienne");
        dbHelper.addRelation("Feu", "Gravier", "Scories");
        dbHelper.addRelation("Terre", "Cascade", "Torrent");
        dbHelper.addRelation("Roche", "Montagne", "Sommet");
        dbHelper.addRelation("Eau", "Plage", "Rivage");
        dbHelper.addRelation("Terre", "Source", "Ruisseau");
        dbHelper.addRelation("Roche", "Lit de Rivière", "Gisement");
    }
}
