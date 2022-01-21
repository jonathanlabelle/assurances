
import Statistique.Statistiques;
import com.sun.jdi.connect.Connector;
import com.sun.tools.javac.Main;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArgumentTest {

    String[] tabDeCheminValide;
    String[] tabDeCheminInvalide;
    String[] argumentSupprimerStats;
    String[] argumentAfficherStats;


    @BeforeEach
    void setUp() {

        tabDeCheminValide = new String[] {"intrant.json", "extrant", "erreur.json"};
        tabDeCheminInvalide = new String[] {"intrant", "extrant", "erreur"};
        Statistiques.setReclamationsInvalides(1);
        Statistiques.setReclamationsValides(1);
        argumentSupprimerStats = new String[]{"-SR"};
        argumentAfficherStats = new String[]{"-S"};

    }

    @Test
    @DisplayName("Analyser non valide")
    void analysernonvalide() throws Exception {

        Argument argument1 = new Argument(argumentSupprimerStats);
        assertNotNull(argument1);

    }
    @Test
    @DisplayName("Analyser valide")
    void analyservalide() {
        assertTrue(Argument.afficherStatistique());
        assertTrue(Argument.supprimerStatistique());
        assertTrue(Argument.supprimerClients());
        assertTrue(Argument.afficherAide());

    }
}