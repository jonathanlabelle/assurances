package Reclamation;

import Contrat.*;
import Dollar.Dollar;
import Outils.DateConteneur;
import Dollar.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ReclamationTest {

    Reclamation reclamation;
    Dollar dollar1;
    Dollar dollar2;
    PoliceSoin policeSoin;
    TauxAssurable tauxAssurable;

    @BeforeEach
    void setUp() {
        dollar1 = DollarFactory.getDollar(500);
        dollar2 = DollarFactory.getDollar(5000);
        policeSoin = PoliceSoinFactory.obtenir(Contrat.A, "100");
        tauxAssurable = new TauxAssurable(50, 50, 100);
        reclamation = new Reclamation(new DateConteneur(), new DateConteneur(), dollar1, dollar2, policeSoin, tauxAssurable);
    }

    @AfterEach
    void tearDown() {
        dollar1 = null;
        dollar2 = null;
        policeSoin = null;
        tauxAssurable = null;
        reclamation = null;
    }

    @Test
    @DisplayName("Verification que la reclamation est bien construit")
    void verifieConstructionReclamation() {
        assertEquals(500 , reclamation.getMontantReclamation().getCents());
    }

    @Test
    @DisplayName("Verifie le toString")
    void verifieToString() {
        String resultat = reclamation.toString();
        String attendu = "Reclamation{" +
                "montantRemboursement=50.00$" +
                ", montantReclamation=5.00$" +
                ", dateCalcul=" + new DateConteneur() +
                ", dateReclamation=" + new DateConteneur() +
                ", policeSoin=A100" +
                ", contratSoin=A" +
                '}';
        assertEquals(attendu,resultat);
    }
}
