
package Outils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DateFactoryTest {

    DateConteneur dateConteneurMalFormate;
    DateConteneur dateConteneurBienFormate;

    @BeforeEach
    void setUp() {

        dateConteneurMalFormate = DateFactory.obtenir("2021-2-28");
        dateConteneurBienFormate = DateFactory.obtenir("2021-02-28");
    }

    @AfterEach
    void tearDown() {

        dateConteneurMalFormate = null;
        dateConteneurBienFormate = null;
    }

    @Test
    @DisplayName("Transformation d'une date a un chiffre pour le jour et le mois")
    void ajoutDe0AuMois() { assertEquals(dateConteneurBienFormate.getMois(), dateConteneurMalFormate.getMois()); }

    @Test
    @DisplayName("Transformation d'une date a un chiffre pour le jour")
    void ajoutDe0AuJour() { assertEquals(dateConteneurBienFormate.getJour(), dateConteneurMalFormate.getJour()); }
}