
package Outils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StingAnalyseTest {

    String stringTest;

    @BeforeEach
    void setUp() {
        stringTest = "alo45l78lo";
    }

    @AfterEach
    void tearDown() {
        stringTest = null;
    }

    @Test
    @DisplayName("Compte le nombre de fois qu'un caractere revient dans un string")
    void calculeNombreOccurenceCaractere() {
        assertEquals(3, StringAnalyse.contientNCar(stringTest, 'l'));
    }
}
