
package Outils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DateConteneurTest {

    DateConteneur date201901;
    DateConteneur date20200101;
    DateConteneur date20200102;
    DateConteneur date20200201;
    DateConteneur date202012;

    @BeforeEach
    void setUp() {
        date201901 = new DateConteneur(2019, 01);
        date20200101 = new DateConteneur(2020, 01, 01);
        date20200102 = new DateConteneur(2020, 01, 02);
        date20200201 = new DateConteneur(2020, 02, 01);
        date202012 = new DateConteneur(2020, 12);
    }

    @AfterEach
    void tearDown() {
        date201901 = null;
        date20200101 = null;
        date20200102 = null;
        date20200201 = null;
        date202012 = null;
    }

    @Test
    @DisplayName("Verifie si une annee plus petite precede une plus grande")
    void verifieSiUneAnneePlusGrandeDePrecedePas() {
        assertTrue(date201901.precede(date20200101));
    }

    @Test
    @DisplayName("Verifie si le mois de janvier precede le mois de decembre dans une annee")
    void verifieSiUnMoisAnterieurPrecedeLAutre() {
        assertTrue(date20200101.precede(date202012));
    }

    @Test
    @DisplayName("Verifie si le jour 1 precede le jour deux d'un meme mois et meme annee")
    void verifieSiJourAnterieurPrecedeLAutre() {
        assertTrue(date20200101.precede(date20200102));
    }

    @Test
    @DisplayName("Verifie si une date est du meme mois ou mois anterieur")
    void verifieSiMemeMoisOuPrecede() {
        assertTrue(date20200101.precedeOuMemeMois(date20200201));
    }

    @Test
    @DisplayName("Verifie si deux dates du meme mois mais pas le meme jour")
    void verifieSiMemeMoisOuPrecedeMemeMois() {
        assertFalse((date20200102.precedeOuMemeMois(date20200101)));
    }

    @Test
    @DisplayName("Verifie que le mois avant est bien le mois avant")
    void verifieMoisPasse() {
        assertTrue(date20200101.precedeOuMemeMoisPasse(date20200201));
    }

    @Test
    @DisplayName("Verifie si le toString annee, mois, jour")
    void verifieFormatStringAnneeMoisJour() {
        assertEquals("2020-01-01", date20200101.toString());
    }

    @Test
    @DisplayName("Verifie que le toString formatte bien la date avec une annee et un mois")
    void verifieFormatStringDateAnneeMois() {
        assertEquals("2020-01", date20200101.toStringClient());
    }


}