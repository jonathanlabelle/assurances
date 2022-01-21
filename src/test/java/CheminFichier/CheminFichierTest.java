
package CheminFichier;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CheminFichierTest {

    CheminFichier cheminFichier1;
    CheminFichier cheminFichier2;
    CheminFichier cheminFichier3;
    String nomDeCheminValide;
    String nomDeCheminInvalide;
    String[] tabDeCheminValide;
    String[] tabDeCheminInvalide;
    String[] tabDeCheminUnElement;

    @BeforeEach
    void setUp() {

        tabDeCheminValide = new String[] {"intrant.json", "extrant", "erreur.json"};
        tabDeCheminInvalide = new String[] {"intrant", "extrant", "erreur"};
        tabDeCheminUnElement = new String[] {"intrant.json"};
        nomDeCheminValide = "source.json";
    }

    @AfterEach
    void tearDown() {

        tabDeCheminValide = null;
        tabDeCheminInvalide = null;
        tabDeCheminUnElement = null;
        cheminFichier1 = null;
        cheminFichier2 = null;
        cheminFichier3 = null;
    }

    @Test
    @DisplayName("Assignation de chemin ficher intrant valide")
    void assignationCheminFichierIntrant() {

        cheminFichier1 = new CheminFichierIntrant(tabDeCheminValide);
        assertEquals("Chemin Fichier Intrant : intrant.json", cheminFichier1.toString());
    }

    @Test
    @DisplayName("Creation d'un fichier erreur json")
    void assignationNomDeFichierErreur() {

        cheminFichier1 = new CheminFichierIntrant(tabDeCheminValide);
        cheminFichier2 = new CheminFichierExtrant(tabDeCheminValide, cheminFichier1);
        cheminFichier3 = new CheminFichierErreur(cheminFichier2);
        assertTrue(cheminFichier3.toString().endsWith(".json"));
    }

    @Test
    @DisplayName("Assignation de nom de fichier d'entree non valide")
    void assignationCheminIntrantNonValide() {

        assertThrows(IllegalArgumentException.class, () -> {
            cheminFichier1 = new CheminFichierIntrant(tabDeCheminInvalide);
        });
    }

    @Test
    @DisplayName("Assignation de nom de fichier de sortie non valide")
    void assignationCheminExtrantSansExtensionJson() {

        cheminFichier1 = new CheminFichierIntrant(tabDeCheminValide);
        cheminFichier2 = new CheminFichierExtrant(tabDeCheminValide, cheminFichier1);
        assertTrue(cheminFichier2.toString().endsWith(".json"));
    }

    @Test
    @DisplayName ("Assignation d'un nom de fichier de sorti si non specifier dans le tableau de chemin")
    void creationCheminFichierExportLorsqueNonSpecifie() {

        cheminFichier1 = new CheminFichierIntrant(tabDeCheminUnElement);
        cheminFichier2 = new CheminFichierExtrant(tabDeCheminUnElement, cheminFichier1);
        assertTrue(cheminFichier2.toString().endsWith("export.json"));
    }

    @Test
    @DisplayName("Invalidation d'un chemin ne finissant pas par .json")
    void invalideCheminSansLaBonneExtension() {
        assertFalse(CheminFichier.estCheminValide(nomDeCheminInvalide));
    }

}