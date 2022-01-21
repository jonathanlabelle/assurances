
package Fichier;

import CheminFichier.ICheminFichier;

import java.io.File;
import java.util.Scanner;


public class FichierLire {

    public static final String JSON_TEST = "{\n" +
            "\t\"client\": \"100223\",\n" +
            "\t\"contrat\": \"A\",\n" +
            "\t\"mois\": \"2021-02\",\n" +
            "\t\"reclamations\": [\n" +
            "\t\t{\n" +
            "\t\t\t\"soin\": 100,\n" +
            "\t\t\t\"date\": \"2021-01-11\",\n" +
            "\t\t\t\"montant\": \"234.00$\"\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"soin\": 200,\n" +
            "\t\t\t\"date\": \"2021-01-13\",\n" +
            "\t\t\t\"montant\": \"90.00$\"\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"soin\": 334,\n" +
            "\t\t\t\"date\": \"2021-01-23\",\n" +
            "\t\t\t\"montant\": \"125.00$\"\n" +
            "\t\t}\n" +
            "\t]\n" +
            "}\n";
    private String contenuFichier;
    private ICheminFichier cheminFichier;

    //CONSTRUCTEUR
    public FichierLire(ICheminFichier cheminFichier) {
        this.contenuFichier = lire(cheminFichier.getChemin());
        this.cheminFichier = cheminFichier;
    }

    //STATIC
    public static String lire(String cheminFichier) {

        StringBuilder stringBuilder = new StringBuilder();

        try {
            File file = new File(cheminFichier);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                stringBuilder.append(scanner.nextLine());
            }
            scanner.close();
        } catch (Exception e) {
            stringBuilder = null;
        }

        if (stringBuilder != null) {
            return stringBuilder.toString();
        }

        return null;
    }

    public static boolean fichierExiste(String cheminFichier) {
        return null != lire(cheminFichier);
    }

    //GET
    public String getContenuFichier() {
        return contenuFichier;
    }

    public ICheminFichier getCheminFichier() {
        return cheminFichier;
    }

}
