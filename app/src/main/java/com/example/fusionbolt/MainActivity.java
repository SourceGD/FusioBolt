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
        dbHelper.addElement("Danse", "drawable/danse");
        dbHelper.addElement("Feuille", "drawable/feuille");
        dbHelper.addElement("Ile", "drawable/ile");
        dbHelper.addElement("Agriculture", "drawable/agriculture");
        dbHelper.addElement("Société", "drawable/societe");
        dbHelper.addElement("Dune", "drawable/dune");
        dbHelper.addElement("Poterie", "drawable/poterie");
        dbHelper.addElement("Sable", "drawable/sable");
        dbHelper.addElement("Forêt", "drawable/foret");
        dbHelper.addElement("Rivière", "drawable/riviere");
        dbHelper.addElement("Culture", "drawable/culture");
        dbHelper.addElement("Homme", "drawable/homme");
        dbHelper.addElement("Passion", "drawable/passion");
        dbHelper.addElement("Cendres", "drawable/cendres");
        dbHelper.addElement("Oasis", "drawable/oasis");
        dbHelper.addElement("Musique", "drawable/musique");
        dbHelper.addElement("Eléphant", "drawable/elephant");
        dbHelper.addElement("Archipel", "drawable/archipel");
        dbHelper.addElement("Famille", "drawable/famille");
        dbHelper.addElement("Océan", "drawable/ocean");
        dbHelper.addElement("Plante", "drawable/plante");
        dbHelper.addElement("Pluie", "drawable/pluie");
        dbHelper.addElement("Planète", "drawable/planete2");
        dbHelper.addElement("Pégase", "drawable/pegase");
        dbHelper.addElement("Incendie", "drawable/incendie");
        dbHelper.addElement("Obsidienne", "drawable/obsidienne");
        dbHelper.addElement("Marais", "drawable/marais");
        dbHelper.addElement("Charbon", "drawable/charbon");
        dbHelper.addElement("Sexe", "drawable/sexe");
        dbHelper.addElement("Système solaire", "drawable/solaire");
        dbHelper.addElement("Tempête", "drawable/tempete");
        dbHelper.addElement("Angleterre", "drawable/angleterre");
        dbHelper.addElement("Canyon", "drawable/canyon");
        dbHelper.addElement("Tornade", "drawable/tornade");
        dbHelper.addElement("Galaxie", "drawable/galaxie");
        dbHelper.addElement("Dieu", "drawable/dieumignon");
        dbHelper.addElement("Cérémonie", "drawable/ceremonie");
        dbHelper.addElement("Céramique", "drawable/ceramique");
        dbHelper.addElement("Désert", "drawable/desert");
        dbHelper.addElement("Art", "drawable/art");
        dbHelper.addElement("Orage", "drawable/orage");
        dbHelper.addElement("Civilisation", "drawable/civilisation");
        dbHelper.addElement("Grêle", "drawable/grele");
        dbHelper.addElement("Spectacle", "drawable/spectacle");
        dbHelper.addElement("Soleil", "drawable/soleil");
        dbHelper.addElement("Bol", "drawable/bol");
        dbHelper.addElement("Licorne", "drawable/licorne");
        dbHelper.addElement("Fleuve", "drawable/fleuve");
        dbHelper.addElement("Verre", "drawable/verre");
        dbHelper.addElement("Thé", "drawable/the");
        dbHelper.addElement("Poisson", "drawable/poisson");
        dbHelper.addElement("Mars", "drawable/mars");
        dbHelper.addElement("Tempête de sable", "drawable/sandstorm");
        dbHelper.addElement("Brouillard", "drawable/brouillard");
        dbHelper.addElement("Cyclone", "drawable/cyclone");
        dbHelper.addElement("Univers", "drawable/univers");
        dbHelper.addElement("Arbre", "drawable/arbre");
        dbHelper.addElement("Amour", "drawable/amour");
        dbHelper.addElement("Volcan", "drawable/volcan");
        dbHelper.addElement("Vase", "drawable/vase");
        dbHelper.addElement("Arc-en-ciel", "drawable/rainbow");
        dbHelper.addElement("Théâtre", "drawable/theatre");
        dbHelper.addElement("Continent", "drawable/continent");
        dbHelper.addElement("Roche", "drawable/roche");
        dbHelper.addElement("Neptune", "drawable/neptune");
        dbHelper.addElement("Terre", "drawable/laterre");
        dbHelper.addElement("Neige", "drawable/neige2");
        dbHelper.addElement("Peinture", "drawable/peinture");
        dbHelper.addElement("Gel", "drawable/gel");
        dbHelper.addElement("Mangrove", "drawable/mangrove");
        dbHelper.addElement("Oiseau", "drawable/oiseau");
        dbHelper.addElement("Nuage", "drawable/nuage2");
        dbHelper.addElement("Tasse", "drawable/tasse");
        dbHelper.addElement("Plaine", "drawable/plaine");
        dbHelper.addElement("Vie", "drawable/vie");
        dbHelper.addElement("Enfant", "drawable/enfant");





        dbHelper.addRelation("Feu", "Eau", "Vapeur");
        dbHelper.addRelation("Feu", "Vent", "Fumée");
        dbHelper.addRelation("Feu", "Terre", "Lave");
        dbHelper.addRelation("Eau", "Terre", "Boue");
        dbHelper.addRelation("Eau", "Vent", "Vague");
        dbHelper.addRelation("Terre", "Terre", "Roche");
        dbHelper.addRelation("Terre", "Vent", "Poussière");
        dbHelper.addRelation("Vapeur", "Vent", "Nuage");
        dbHelper.addRelation("Vapeur", "Terre", "Pluie");
        dbHelper.addRelation("Vapeur", "Eau", "Pluie");
        dbHelper.addRelation("Pluie", "Pluie", "Orage");
        dbHelper.addRelation("Fumée", "Eau", "Nuage");
        dbHelper.addRelation("Fumée", "Terre", "Poussière");
        dbHelper.addRelation("Lave", "Eau", "Obsidienne");
        dbHelper.addRelation("Lave", "Vent", "Cendres");
        dbHelper.addRelation("Boue", "Vent", "Terre");
        dbHelper.addRelation("Boue", "Eau", "Marais");
        dbHelper.addRelation("Vague", "Tornade", "Cyclone");
        dbHelper.addRelation("Océan", "Tornade", "Cyclone");
        dbHelper.addRelation("Eau", "Vague", "Océan");
        dbHelper.addRelation("Eau", "Eau", "Océan");
        dbHelper.addRelation("Poussière", "Tornade", "Tempête");
        dbHelper.addRelation("Eau", "Roche", "Sable");
        dbHelper.addRelation("Eau", "Fumée", "Brouillard");
        dbHelper.addRelation("Sable", "Sable", "Désert");
        dbHelper.addRelation("Sable", "Vent", "Dune");
        dbHelper.addRelation("Dune", "Tornade", "Tempête de sable");
        dbHelper.addRelation("Désert", "Tornade", "Tempête de sable");
        dbHelper.addRelation("Désert", "Sable", "Dune");
        dbHelper.addRelation("Désert", "Eau", "Oasis");
        dbHelper.addRelation("Désert", "Roche", "Canyon");
        dbHelper.addRelation("Vent", "Vent", "Tornade");
        dbHelper.addRelation("Feu", "Feu", "Soleil");
        dbHelper.addRelation("Soleil", "Pluie", "Arc-en-ciel");
        dbHelper.addRelation("Soleil", "Arc-en-ciel", "Licorne");
        dbHelper.addRelation("Neige", "Terre", "Gel");
        dbHelper.addRelation("Gel", "Feu", "Eau");
        dbHelper.addRelation("Gel", "Pluie", "Grêle");
        dbHelper.addRelation("Licorne", "Vent", "Pégase");
        dbHelper.addRelation("Sable", "Feu", "Verre");
        dbHelper.addRelation("Verre", "Soleil", "Arc-en-ciel");
        dbHelper.addRelation("Roche", "Terre", "Plaine");
        dbHelper.addRelation("Plaine", "Eau", "Plante");
        dbHelper.addRelation("Plante", "Feu", "Cendres");
        dbHelper.addRelation("Plante", "Plante", "Arbre");
        dbHelper.addRelation("Arbre", "Feu", "Charbon");
        dbHelper.addRelation("Arbre", "Vent", "Feuille");
        dbHelper.addRelation("Feuille", "Feu", "Cendres");
        dbHelper.addRelation("Arbre", "Arbre", "Forêt");
        dbHelper.addRelation("Forêt", "Feu", "Incendie");
        dbHelper.addRelation("Forêt", "Vent", "Feuille");
        dbHelper.addRelation("Forêt", "Eau", "Rivière");
        dbHelper.addRelation("Rivière", "Rivière", "Fleuve");
        dbHelper.addRelation("Fleuve", "Fleuve", "Océan");
        dbHelper.addRelation("Forêt", "Boue", "Mangrove");
        dbHelper.addRelation("Mangrove", "Eau", "Marais");
        dbHelper.addRelation("Forêt", "Marais", "Mangrove");
        dbHelper.addRelation("Plante", "Licorne", "Vie");
        dbHelper.addRelation("Arbre", "Licorne", "Vie");
        dbHelper.addRelation("Vie", "Vent", "Oiseau");
        dbHelper.addRelation("Vie", "Eau", "Poisson");
        dbHelper.addRelation("Vie", "Désert", "Eléphant");
        dbHelper.addRelation("Vie", "Feu", "Homme");
        dbHelper.addRelation("Vie", "Vie", "Amour");
        dbHelper.addRelation("Amour", "Feu", "Passion");
        dbHelper.addRelation("Amour", "Eau", "Vie");
        dbHelper.addRelation("Amour", "Passion", "Sexe");
        dbHelper.addRelation("Sexe", "Vie", "Enfant");
        dbHelper.addRelation("Enfant", "Enfant", "Famille");
        dbHelper.addRelation("Homme", "Plaine", "Agriculture");
        dbHelper.addRelation("Famille", "Agriculture", "Société");
        dbHelper.addRelation("Société", "Société", "Civilisation");
        dbHelper.addRelation("Civilisation", "Civilisation", "Culture");
        dbHelper.addRelation("Culture", "Culture", "Art");
        dbHelper.addRelation("Art", "Vent", "Musique");
        dbHelper.addRelation("Art", "Eau", "Peinture");
        dbHelper.addRelation("Boue", "Plante", "Vase");
        dbHelper.addRelation("Art", "Vase", "Céramique");
        dbHelper.addRelation("Céramique", "Feu", "Poterie");
        dbHelper.addRelation("Poterie", "Eau", "Bol");
        dbHelper.addRelation("Bol", "Bol", "Tasse");
        dbHelper.addRelation("Tasse", "Tasse", "Thé");
        dbHelper.addRelation("Thé", "Société", "Angleterre");
        dbHelper.addRelation("Thé", "Culture", "Cérémonie");
        dbHelper.addRelation("Cérémonie", "Art", "Danse");
        dbHelper.addRelation("Danse", "Musique", "Spectacle");
        dbHelper.addRelation("Spectacle", "Spectacle", "Théâtre");
        dbHelper.addRelation("Lave", "Plaine", "Volcan");
        dbHelper.addRelation("Volcan", "Océan", "Ile");
        dbHelper.addRelation("Ile", "Ile", "Archipel");
        dbHelper.addRelation("Archipel", "Archipel", "Continent");
        dbHelper.addRelation("Continent", "Océan", "Planète");
        dbHelper.addRelation("Planète", "Soleil", "Système solaire");
        dbHelper.addRelation("Système solaire", "Système solaire", "Galaxie");
        dbHelper.addRelation("Galaxie", "Galaxie", "Univers");
        dbHelper.addRelation("Univers", "Univers", "Dieu");
        dbHelper.addRelation("Dieu", "Vie", "Homme");
        dbHelper.addRelation("Planète", "Désert", "Mars");
        dbHelper.addRelation("Planète", "Eau", "Neptune");
        dbHelper.addRelation("Neptune", "Pluie", "Neige");



















    }
}
