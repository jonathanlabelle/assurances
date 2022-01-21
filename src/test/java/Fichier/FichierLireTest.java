
package Fichier;

import CheminFichier.ICheminFichier;
import CheminFichier.CheminFichierFactory;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.*;


class FichierLireTest {

    CheminFichierFactory cheminFichiers;
    ICheminFichier cheminFichierIntrant;
    String[] tabCheminFichier;
    FichierLire fichierALire;
    String contenu;

    @BeforeEach
    void setUp() {

        tabCheminFichier = new String[]{"intrant.json", "extrant", "erreur.json"};
        cheminFichiers = new CheminFichierFactory(tabCheminFichier);
        cheminFichierIntrant = cheminFichiers.getIntrant();
        fichierALire = new FichierLire(cheminFichierIntrant);
        contenu = FichierLire.lire(fichierALire.toString());
    }

    @AfterEach
    void tearDown() {

        tabCheminFichier = null;
        cheminFichiers = null;
        cheminFichierIntrant = null;
        fichierALire = null;
    }

    @Test
    @DisplayName("Verifie le constructeur et si les valeurs sont attribué convenablement")
    void verifieFichierLire() {
        assertNotNull(fichierALire.getCheminFichier());
        assertNull(fichierALire.getContenuFichier());
    }

}
