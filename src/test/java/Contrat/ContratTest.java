
package Contrat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ContratTest {

    @BeforeEach
    void setUp() {

    }

    @Test
    @DisplayName("Tente de creer un contrat avec parametre vide, retourne null")
    void creationContratVide() {
        assertFalse(Contrat.existe(null));
    }

}