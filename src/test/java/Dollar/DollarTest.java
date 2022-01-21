
package Dollar;

import Dollar.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

public class DollarTest {

    Dollar dollar100;
    Dollar dollar500;
    Dollar dollar1000;

    @BeforeEach
    void setUp() {
        dollar100 = DollarFactory.getDollar(100);
        dollar500 = DollarFactory.getDollar(500);
        dollar1000 = DollarFactory.getDollar("1000$");
    }

    @AfterEach
    void tearDown() {
        dollar100 = null;
        dollar500 = null;
        dollar1000 = null;
    }

    @Test
    @DisplayName("Verifie si le get renvoie la valeur dollar en int")
    void verifieSiGetCentsRetourneInt() {
        assertEquals(100, dollar100.getCents());
    }

    @Test
    @DisplayName("Test de soustraction de deux Dollar")
    void calculeSoustractionDollar() {
        Dollar dollarSoustraction =  Dollar.Soustraction(dollar500, dollar100);
        assertEquals(400, dollarSoustraction.getCents());
    }

    @Test
    @DisplayName("Test d'addition de deux Dollar")
    void calculeAdditionDollar() {
        Dollar dollarAddition =  Dollar.Addition(dollar1000, dollar100);
        assertEquals(1100, dollarAddition.getCents());
    }

    @Test
    @DisplayName("Test de soustraction de deux Dollar")
    void calculeMultiplicationDollar() {
        Dollar dollarMultiplicaton =  Dollar.Multiplication(dollar500, 1000);
        assertEquals(5000, dollarMultiplicaton.getCents());
    }

//    @Test
//    @DisplayName("Test si formate bien une devise en String")
//    void chiffreAuDessusDuMaximum() {
//        assertEquals("5555$", DollarFactory.formaterDevise("55,55$"));
//    }

    @Test
    @DisplayName("Test si retourne le Dollar le plus grand dans une suite")
    void rechercheDollarLePlusGrand() {
        Dollar dollarMaximum = Dollar.Maximum(dollar100, dollar500, dollar1000);
        assertEquals(1000, dollarMaximum.getCents());
    }

    @Test
    @DisplayName("Test si retourne le Dollar le plus petit dans une suite")
    void rechercheDollarLePlusPetit() {
        dollar100.setCents(50);
        Dollar dollarMinimum = Dollar.Minimum(dollar500, dollar100, dollar1000);
        assertEquals(50, dollarMinimum.getCents());
    }

//    @Test
//    @DisplayName("Sortie du programme du a une monnaie trop grosse")
//    void rejetteMonnaieTropGrosse() {
//
//        assertThrows(NullPointerException.class,
//                () -> {Dollar dollarTropGros = DollarFactory.getDollar(555555555);});
//    }

}

