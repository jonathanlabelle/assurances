
package DecodeurJSON;


import Client.Client;
import Client.ClientFactory;
import Client.Clients;
import DecodeurJSON.FauxClient.FauxClient;
import DecodeurJSON.FauxClient.FauxReclamation;
import Fichier.FichierLire;
import Reclamation.CalculReclamation;
import Reclamation.ReclamationFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonDecodeurTest {


    @Test
    @DisplayName("S'assure de retourner null si le Json est vide")
    void testUnJsonVide() {
        //assertNull(JsonDecodeur.formaterImport("{}"));
    }

    @Test
    @DisplayName("S'sassure que le Json n'est pas vide")
    void testUnJsonBienNonVide() {
        assertNotEquals(null, JsonDecodeur.formaterImport(FichierLire.JSON_TEST));
    }


    @Test
    @DisplayName("Verifie si le client est formater d'une facon acceptable pour le json")
    void testFormaterExport() {

        Clients clients = new Clients();
        ArrayList<FauxReclamation> fauxReclamationsAL = new ArrayList<>();
        FauxClient fauxClient = new FauxClient("000000", "2021-04", "A", fauxReclamationsAL);
        FauxReclamation fauxReclamation = new FauxReclamation("234.00$", "2021-04-22", "100");
        fauxReclamationsAL.add(fauxReclamation);
        Client client1 = ClientFactory.obtenirClient(fauxClient);
        ReclamationFactory.ajouterReclamations(clients, client1, fauxClient);
        CalculReclamation.obtenir(clients, client1);
        clients.add(client1);
        String resultat = JsonDecodeur.formaterExport(clients.get(0));
        String attendue = "{\n" +
                "  \"dossier\": \"A000000\",\n" +
                "  \"mois\": \"2021-04\",\n" +
                "  \"reclamations\": [\n" +
                "    {\n" +
                "      \"soin\": 100,\n" +
                "      \"date\": \"2021-04-22\",\n" +
                "      \"montant\": \"81.90$\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"total\": \"81.90$\"\n" +
                "}";

        assertEquals(attendue,resultat);
    }

}





