
package Reclamation;

import Client.*;

import DecodeurJSON.FauxClient.FauxClient;
import DecodeurJSON.FauxClient.FauxReclamation;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CalculReclamationTest {

    Client client;
    Client clientAnterieur;
    Clients clients;
    CalculReclamation calculReclamation;
    FauxReclamation fauxReclamation;
    FauxClient fauxClient;


    @BeforeEach
    void setUp() {

        ArrayList<FauxReclamation> fauxReclamationsAL = new ArrayList<>();

        fauxReclamation = new FauxReclamation("50.00$", "2021-04-01", "400");
        fauxReclamationsAL.add(fauxReclamation);
        fauxReclamation = new FauxReclamation("65.00$", "2021-04-02", "200");
        fauxReclamationsAL.add(fauxReclamation);
        fauxReclamation = new FauxReclamation("75.00$", "2021-04-03", "200");
        fauxReclamationsAL.add(fauxReclamation);
        fauxReclamation = new FauxReclamation("75.00$", "2021-04-04", "200");
        fauxReclamationsAL.add(fauxReclamation);
        fauxReclamation = new FauxReclamation("75.00$", "2021-04-05", "200");
        fauxReclamationsAL.add(fauxReclamation);
        fauxReclamation = new FauxReclamation("75.00$", "2021-04-06", "200");
        fauxReclamationsAL.add(fauxReclamation);

        clients = new Clients();
        fauxClient = new FauxClient("000000", "2021-04", "B", fauxReclamationsAL);

        client = ClientFactory.obtenirClient(fauxClient);
        ReclamationFactory.ajouterReclamations(clients, client, fauxClient);
        CalculReclamation.obtenir(clients, client);
        clients.add(client);


    }

    @AfterEach
    void tearDown() {

        client  = null;
        clientAnterieur = null;
        clients = null;
        fauxClient = null;
        fauxReclamation = null;
        calculReclamation = null;

    }

    @Test
    @DisplayName("Verifie le constructeur")
    void verifieCalculReclamation() {
        CalculReclamation calculReclamation = new CalculReclamation();
        assertNotNull(calculReclamation);
    }

    @Test
    @DisplayName("Calcule une reclamation non couverte")
    void caculeReclamationNonCouverte() {
        assertEquals(0,
                client.getPremiereReclamationMensuelle().getReclamations().get(0).getMontantRemboursement().getCents());
    }

    @Test
    @DisplayName("Calcul remboursement avec Maximum mensuel et maximum par seance sans atteindre les maximums")
    void calculeRemboursementSansAtteindreMaximums () {
        clients = null;
        assertEquals(6500,
                client.getPremiereReclamationMensuelle().getReclamations().get(1).getMontantRemboursement().getCents());
    }

    @Test
    @DisplayName("Calcule un remboursement atteignant le maximum pour une reclamation")
    void calculeRemboursementMaximumReclamation() {
        assertEquals(7000,
                client.getPremiereReclamationMensuelle().getReclamations().get(2).getMontantRemboursement().getCents());

    }

    @Test
    @DisplayName("Calcule un remboursement ou le maximum mensuel n'etait pas atteint mais depasse avec ce rembousement")
    void calculeRemboursementDepassantMaximumMensuel() {
        int zero = 0;
        int remboursementSiMaximumNonAtteint =
                client.getPremiereReclamationMensuelle().getReclamations().get(3).getMontantRemboursement().getCents();
        int remboursementRecu =
                client.getPremiereReclamationMensuelle().getReclamations().get(4).getMontantRemboursement().getCents();
        assertTrue((zero  < remboursementRecu) && (remboursementRecu < remboursementSiMaximumNonAtteint));
    }

    @Test
    @DisplayName("Calcule un remboursement alors que la limite mensuelle est déjà atteinte")
    void calculeReclamationAvecMaximumDejaAtteint() {
        assertEquals(0,
                client.getPremiereReclamationMensuelle().getReclamations().get(5).getMontantRemboursement().getCents());
    }

    @Test
    @DisplayName("verifie si le total remboursement mensuel est augmente par les reclamations")
    void verificationAjoutRemboursementTotal() {
        assertTrue(0 < client.getReclamationsMensuelles().get(0).getRemboursementTotal().getCents());
    }

    @Test
    @DisplayName("Verifie si calcule le total des reclamations mensuelles d'une client qui a anterieurement fait" +
            "des reclamations pour le meme mois et pour la meme reclamation")
    void additionneReclamationAnterieurePareilleQueReclamationCourarante() {
        assertTrue(0 < CalculReclamation.calculRemboursementsReclamationsAnterieures("000000", clients,
                client.getReclamationsMensuelles().get(0).getReclamations().get(1)).getCents());
        ;
    }

    @Test
    @DisplayName("Verifie si le calcul des reclmations prends en compte des reclamations anterieures")
    void verifieSiReclamationsAnterieuresPrisEnCompte() {
        assertTrue(0 < CalculReclamation.calculRemboursementAnterieurs(client,
                clients, client.getPremiereReclamationMensuelle().getReclamations().get(1)).getCents());
    }
}