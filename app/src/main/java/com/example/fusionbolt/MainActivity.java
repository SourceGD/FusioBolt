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



public class MainActivity extends AppCompatActivity {

    public ArrayList<Element> elements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Accédez au fichier texte depuis le dossier 'assets'
        elements = Element.loadElementsFromAsset(getAssets(), "database.txt");

        // Affichez les objets dans la console ou faites ce que vous souhaitez avec eux
        for (Element element : elements) {
            System.out.println("Nom : " + element.getName());
        }
    }
    // ... (le reste du code inchangé)


    private ArrayList<Element> lireFichierTexte(String nomFichier) {
        ArrayList<Element> elements = new ArrayList<>();

        try {
            AssetManager assetManager = getAssets();


            InputStream inputStream = assetManager.open(nomFichier);


            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String ligne;

            while ((ligne = reader.readLine()) != null) {
                String[] donnees = ligne.split(", ");

                if (donnees.length == 3) {
                    String name = donnees[0];
                    String proprietiesString = donnees[1].replace("[", "").replace("]", "");
                    String[] proprieties = proprietiesString.split(",");
                    String logo = donnees[2];

                    Element element = new Element(name, proprieties, logo);
                    elements.add(element);
                }
            }

            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Erreur de lecture du fichier texte.", Toast.LENGTH_SHORT).show();
        }

        return elements;
    }
}