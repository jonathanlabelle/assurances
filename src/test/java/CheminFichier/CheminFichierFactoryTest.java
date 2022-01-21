
package CheminFichier;

import org.junit.Before;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class CheminFichierFactoryTest {

    CheminFichierFactory cheminFichierValide;
    CheminFichierFactory cheminFichierInvalide;
    ICheminFichier intrant;
    ICheminFichier extrant;
    ICheminFichier erreur;

    @BeforeEach
    void setUp() {
        String[] arrayCheminFichierValide = {"intrant.json", "extrant.json", "erreur.json"};
        String[] arrayCheminFichierInvalide = {};
        cheminFichierValide = new CheminFichierFactory(arrayCheminFichierValide);
        cheminFichierInvalide = new CheminFichierFactory(arrayCheminFichierInvalide);
    }

    @AfterEach
    void tearDown() {
        cheminFichierValide = null;
        cheminFichierInvalide = null;
        intrant = null;
        extrant = null;
        erreur = null;
    }

    @Test
    @DisplayName("Creation d'un chemin fichier intrant valide")
    void verificationCheminFichierIntrantValide() {
        intrant = cheminFichierValide.getIntrant();
        assertTrue(intrant.getChemin().endsWith(".json"));
    }

    @Test
    @DisplayName("Creation d'un chemin fichier extrant valide")
    void verificationCheminFichierExtrantValide() {
        extrant = cheminFichierValide.getExtrant();
        System.out.println(extrant);
        assertTrue(extrant.getChemin().endsWith(".json"));
    }

    @Test
    @DisplayName("Creation d'un chemin fichier erreur valide")
    void verificationCheminFichierErreurValide() {
        erreur = cheminFichierValide.getErreur();
        assertTrue(erreur.getChemin().contains(".erreur"));
    }

    @Test
    @DisplayName("Creation d'un chemin fichier intrant invalide")
    void verificationCheminFichierIntrantInvalide() {
        intrant = cheminFichierInvalide.getIntrant();
        assertNull(intrant);
    }

    @Test
    @DisplayName("Creation d'un chemin fichier extrant invalide")
    void verificationCheminFichierExtrantInvalide() {
        extrant = cheminFichierInvalide.getExtrant();
        assertNull(extrant);
    }

    @Test
    @DisplayName("Creation d'un chemin fichier intrant invalide mais quand meme cree")
    void verificationCheminFichierErreurInvalide() {
        erreur = cheminFichierInvalide.getErreur();
        assertTrue(erreur.getChemin().contains(".erreur"));
    }
}