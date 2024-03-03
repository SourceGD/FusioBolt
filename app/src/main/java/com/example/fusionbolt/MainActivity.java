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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Accédez au fichier texte depuis le dossier 'assets'
        ArrayList<Element> elements = lireFichierTexte("databasee.txt");

        // Affichez les objets dans la console ou faites ce que vous souhaitez avec eux
        for (Element element : elements) {
            System.out.println("Nom : " + element.getName() );
        }
    }

    private ArrayList<Element> lireFichierTexte(String nomFichier) {
        ArrayList<Element> elements = new ArrayList<>();

        try {
            // Obtenez l'AssetManager pour accéder aux fichiers dans le dossier 'assets'
            AssetManager assetManager = getAssets();

            // Ouvrez le fichier texte en tant que flux d'entrée
            InputStream inputStream = assetManager.open(nomFichier);

            // Lisez le fichier ligne par ligne
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String ligne;

            while ((ligne = reader.readLine()) != null) {
                // Divisez les données en fonction du séparateur (par exemple, la virgule)
                String[] donnees = ligne.split(", ");

                // Créez un objet et ajoutez-le à la liste
                if (donnees.length == 3) {
                    String name = donnees[0];
                    String proprietiesString = donnees[1].replace("[", "").replace("]", ""); // Supprimez les crochets
                    String[] proprieties = proprietiesString.split(",");
                    String logo = donnees[2];

                    Element element = new Element(name, proprieties, logo);
                    elements.add(element);
                }
            }

            // Fermez le flux
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Erreur de lecture du fichier texte.", Toast.LENGTH_SHORT).show();
        }

        return elements;
    }
}