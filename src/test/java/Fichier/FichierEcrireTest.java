
package Fichier;

import CheminFichier.CheminFichierFactory;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class FichierEcrireTest {

    FichierEcrire fichierEcrire;
    CheminFichierFactory cheminFichierFactory;

    @BeforeEach
    void setUp() {
        String[] stringListe = {"extrant.json"};
        cheminFichierFactory = new CheminFichierFactory(stringListe);
        fichierEcrire = new FichierEcrire("contenu fichier", cheminFichierFactory.getIntrant());
    }

    @AfterEach
    void tearDown() {
        cheminFichierFactory = null;
        fichierEcrire = null;
    }

    @Test
    @DisplayName("Verifie si le nom de fichier sorti est cree")
    void verifieNomFichierSorti() {
        assertEquals("extrant.json",
                fichierEcrire.getCheminFichier().getChemin());
    }
}