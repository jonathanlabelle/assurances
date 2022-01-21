
package Contrat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TauxAssurableTest {

    TauxAssurable tauxAssurable;
    TauxAssurable tauxAssurablePourcentageSeulement;

    @BeforeEach
    void setUp() {
        tauxAssurable = new TauxAssurable(50, 100);
        tauxAssurablePourcentageSeulement = new TauxAssurable(50);
    }

    @AfterEach
    void tearDown() {
        tauxAssurable = null;
    }

    @Test
    @DisplayName("Verifie que le montantMaximumRencontre est applique avec constructeur a 2 params")
    void verificationMontantMaximumRencontre() {
        assertEquals(10000, tauxAssurable.getMontantMaximumRencontre().getCents());
    }

    @Test
    @DisplayName("Verifie que le constructeur avec juste un pourcentage est bien contruit")
    void verificationTauxPourcentageMaximum() {
        assertEquals(50, tauxAssurablePourcentageSeulement.getPourcentageMaximum());
    }

}