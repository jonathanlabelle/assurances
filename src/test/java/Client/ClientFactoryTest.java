
package Client;

import Contrat.Contrat;
import DecodeurJSON.FauxClient.FauxClient;
import DecodeurJSON.FauxClient.FauxReclamation;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class ClientFactoryTest {

    Client client1;
    Clients clients;
    FauxClient fauxClient;

    @BeforeEach
    void setUp() {

        clients = new Clients();
        fauxClient = new FauxClient();

    }

    @AfterEach
    void tearDown() {
        client1 = null;
        clients = null;
        fauxClient = null;
    }

    @Test
    @DisplayName("Constructeur verification")
    void verifieClientFactory() {
        ClientFactory clientFactory = new ClientFactory();
        assertNotNull(clientFactory);
    }

//    @Test
//    @DisplayName("Rejet de creation de client pour numero dossier trop court")
//    void rejetClientDossierTropCourt()  {
//        fauxClient.setDossier("11");
//        assertThrows(NullPointerException.class, () -> {
//            client1 = ClientFactory.obtenirClient(fauxClient);
//        });
//    }

//    @Test
//    @DisplayName("Rejet de creation de dossier pour caracteres invalides")
//    void rejetClientDossierCaracteresInvalides() {
//        fauxClient.setDossier("A!45613");
//        assertThrows(NullPointerException.class, () -> {
//            client1 = ClientFactory.obtenirClient(fauxClient);
//        });
//    }
//
//    @Test
//    @DisplayName("Rejet de creation de dossier pour caracteres invalides")
//    void rejetClientContratInvalide() {
//        fauxClient.setDossier("M111111");
//        assertThrows(NullPointerException.class, () -> {
//            client1 = ClientFactory.obtenirClient(fauxClient);
//        });
//    }


    @Test
    @DisplayName("Separation du numeroClient a partir du dossier fourni")
    void creationNumeroClient() {
        fauxClient.setDossier("A111111");
        fauxClient.setDateMois("2021-04");
        client1 = ClientFactory.obtenirClient(fauxClient);
        assertEquals("111111", client1.getNumeroClient());
    }

    @Test
    @DisplayName("Separation du contrat a partir du dossier fourni")
    void creationContratClient() {
        fauxClient.setDossier("A111111");
        fauxClient.setDateMois("2021-04");
        client1 = ClientFactory.obtenirClient(fauxClient);
        assertEquals(Contrat.A, client1.getContrat());
    }

}
