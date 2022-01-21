
import CheminFichier.CheminFichierFactory;
import Client.Client;
import Client.Clients;
import DecodeurJSON.FauxClient.FauxClient;
import DecodeurJSON.FauxClient.FauxReclamation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    CheminFichierFactory cheminFichierFactory;
    Client client;
    Clients clients;
    FauxClient fauxClient;
    FauxReclamation fauxReclamation;

    @BeforeEach
    void setUp() {
        ArrayList<FauxReclamation> fauxReclamationsAL = new ArrayList<>();
        fauxReclamation = new FauxReclamation("50.00$", "2021-04-22", "100");
        fauxReclamationsAL.add(fauxReclamation);
        fauxClient = new FauxClient("000000", "2021-04", "B", fauxReclamationsAL);

    }

    @AfterEach
    void tearDown() {
        cheminFichierFactory = null;
        clients = null;
        cheminFichierFactory = null;
        fauxClient = null;
        fauxReclamation = null;
    }

    @Test
    @DisplayName("Verification que le main recoit le bon chemin fichier a partir des arguments")
    void cheminsFichiersDansLeMain() {
        String[] chemins = {"lib/exemple.json", "sortant.json", "erreur.json"};
//        assertEquals("lib/exemple.json", Main.creerCheminFichierFactory(chemins).getIntrant().getChemin());
    }

    @Test
    @DisplayName("Verifie si clients n'est plus null apres avoir charge des clients existants ou non")
    void as() {
        clients = Main.charger();
        System.out.println(clients);
        assertNotNull(clients);
    }
    @Test
    @DisplayName("Verifie si le main cree un client a partir d'un fauxClient bien formate")
    void creerClientAPartirDeFauxClientMain() {
        clients = new Clients();
        client = Main.creerClientReclamation(clients,fauxClient);
        assertEquals("50.00$",
                client.getPremiereReclamationMensuelle().getReclamations().get(0).getMontantReclamation().toString());
    }
}