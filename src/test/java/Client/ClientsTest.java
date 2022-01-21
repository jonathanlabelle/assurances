
package Client;

import DecodeurJSON.FauxClient.FauxClient;
import DecodeurJSON.FauxClient.FauxReclamation;
import Reclamation.CalculReclamation;
import Reclamation.ReclamationFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class ClientsTest {

    Client client1;
    Client client2;
    Client client3;
    Clients clients;
    FauxReclamation fauxReclamation;
    FauxClient fauxClient;
    ArrayList<FauxReclamation> fauxReclamationsAL;

    @BeforeEach
    void setUp() {

        clients = new Clients();

        fauxReclamationsAL = new ArrayList<>();
        fauxClient = new FauxClient("000000", "2021-04", "B", fauxReclamationsAL);
        fauxReclamation = new FauxReclamation("500.00$", "2021-04-22", "400");
        fauxReclamationsAL.add(fauxReclamation);

        client1 = ClientFactory.obtenirClient(fauxClient);
//        ReclamationFactory.ajouterReclamations(client1, fauxClient);
        CalculReclamation.obtenir(clients, client1);
        clients.add(client1);

        fauxClient = new FauxClient("000001", "2021-04", "B", fauxReclamationsAL);
        fauxReclamation = new FauxReclamation("500.00$", "2021-04-22", "400");
        fauxReclamationsAL.add(fauxReclamation);

        client2 = ClientFactory.obtenirClient(fauxClient);
//        ReclamationFactory.ajouterReclamations(client2, fauxClient);
        CalculReclamation.obtenir(clients, client2);
        clients.add(client2);

    }

    @AfterEach
    void tearDown() {
        clients = null;
        client1 = null;
        client2 = null;
        client3 = null;
        fauxClient = null;
        fauxReclamation = null;
        fauxReclamationsAL = null;
    }

    @Test
    @DisplayName("Rejet de creation d'un client identique a un client existant deja avec les memes reclamations ")
    void ajoutClientEtReclamationDejaDansClients() {
        fauxClient = new FauxClient("000000", "2021-04", "B", fauxReclamationsAL);
        fauxReclamation = new FauxReclamation("500.00$", "2021-04-22", "400");
        fauxReclamationsAL.add(fauxReclamation);

        client3 = ClientFactory.obtenirClient(fauxClient);
//        ReclamationFactory.ajouterReclamations(client3, fauxClient);
        CalculReclamation.obtenir(clients, client3);
        clients.add(client3);

        assertThrows(IndexOutOfBoundsException.class, () -> {clients.get(2); });
    }

    @Test
    @DisplayName("Verifie si la date de la la reclamation mensuelle est ajoutee")
    void retrouverDateReclamation() {
        assertNotNull(client1.getDateMois());
    }
}

