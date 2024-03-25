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
        dbHelper.addElement("Roche", "drawable/roche");
        dbHelper.addElement("Feu Follet", "drawable/feufollet");
        dbHelper.addElement("Pierre", "drawable/pierre");
        dbHelper.addElement("Océan", "drawable/ocean");
        dbHelper.addElement("Argile", "drawable/argile");
        dbHelper.addElement("Géysère", "drawable/geysere");
        dbHelper.addElement("Rivière", "drawable/riviere");
        dbHelper.addElement("Sable", "drawable/sable");
        dbHelper.addElement("Cendres", "drawable/cendres");
        dbHelper.addElement("Marais", "drawable/marais");
        dbHelper.addElement("Nuage", "drawable/nuage");
        dbHelper.addElement("Brume", "drawable/brume");
        dbHelper.addElement("Volcan", "drawable/volcan");
        dbHelper.addElement("Brouillard", "drawable/brouillard");
        dbHelper.addElement("Rochers Chauds", "drawable/rocherschauds");
        dbHelper.addElement("Plage", "drawable/plage");
        dbHelper.addElement("Éclair", "drawable/eclair");
        dbHelper.addElement("Île", "drawable/ile");
        dbHelper.addElement("Rosée", "drawable/rosee");
        dbHelper.addElement("Verre", "drawable/verre");
        dbHelper.addElement("Cascade", "drawable/cascade");
        dbHelper.addElement("Montagne", "drawable/montagne");
        dbHelper.addElement("Pierres Précieuses", "drawable/pierresprecieuses");
        dbHelper.addElement("Pluie", "drawable/pluie");
        dbHelper.addElement("Tourbière", "drawable/tourbiere");
        dbHelper.addElement("Sable Brûlant", "drawable/sablebrulant");
        dbHelper.addElement("Magma", "drawable/magma");
        dbHelper.addElement("Falaise", "drawable/falaise");
        dbHelper.addElement("Glace", "drawable/glace");
        dbHelper.addElement("Évaporation", "drawable/evaporation");
        dbHelper.addElement("Silex", "drawable/silex");
        dbHelper.addElement("Lit de Rivière", "drawable/litderiviere");
        dbHelper.addElement("Gouttes", "drawable/gouttesdeau");
        dbHelper.addElement("Côte", "drawable/cote");
        dbHelper.addElement("Gravier", "drawable/gravier");
        dbHelper.addElement("Glacière", "drawable/glaciere");
        dbHelper.addElement("Sel", "drawable/sel");
        dbHelper.addElement("Moulin à Eau", "drawable/moulinaeau");
        dbHelper.addElement("Source", "drawable/source");
        dbHelper.addElement("Obsidienne", "drawable/obsidienne");
        dbHelper.addElement("Scories", "drawable/scories");
        dbHelper.addElement("Torrent", "drawable/torrent");
        dbHelper.addElement("Sommet", "drawable/sommet");
        dbHelper.addElement("Rivage", "drawable/rivage");
        dbHelper.addElement("Ruisseau", "drawable/ruisseau");
        dbHelper.addElement("Gisement", "drawable/gisement");



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
