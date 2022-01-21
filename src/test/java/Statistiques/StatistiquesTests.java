
package Statistiques;

import CheminFichier.CheminFichierFactory;
import CheminFichier.ICheminFichier;
import Client.Client;
import Client.ClientFactory;
import Client.Clients;
import DecodeurJSON.FauxClient.FauxClient;
import DecodeurJSON.FauxClient.FauxReclamation;
import Erreur.ErreurSortie;
import Fichier.FichierTemps;
import Reclamation.CalculReclamation;
import Reclamation.ReclamationFactory;
import Statistique.Statistiques;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class StatistiquesTests {

    Statistiques statistiques;
    FichierTemps fichierTempsStat;
    Clients clients;
    FauxReclamation fauxReclamation;
    FauxClient fauxClient;
    ArrayList<FauxReclamation> fauxReclamationsAL;
    Client client1;
    CheminFichierFactory cheminFichierFactory;
    Object obj;
    JSONObject jsonObject;
    JSONParser jsonParser;
    FileReader reader;

    @BeforeEach
    void setUp() {
        Statistiques.commencer();
        clients = new Clients();
        fauxReclamationsAL = new ArrayList<>();
        fauxClient = new FauxClient("000000", "2021-04", "B", fauxReclamationsAL);
        fauxReclamation = new FauxReclamation("50.00$", "2021-04-22", "400");
        fauxReclamationsAL.add(fauxReclamation);
        client1 = ClientFactory.obtenirClient(fauxClient);
        ReclamationFactory.ajouterReclamations(clients, client1, fauxClient);
        CalculReclamation.obtenir(clients, client1);
        clients.add(client1);
        fichierTempsStat = new FichierTemps(new File(CheminFichierFactory.getStatistique().getChemin()));
        Statistiques.ajoutReclamationsInconnues(client1.getPremiereReclamationMensuelle().getReclamations().size());
        Statistiques.compter(client1);

    }

    @AfterEach
    void tearDown() {
        Statistiques.recommencerStats();
        Statistiques.terminer();
        fichierTempsStat = null;
        clients = null;
        fauxReclamation = null;
        fauxClient = null;
        fauxReclamationsAL = null;
        client1 = null;
    }

    @Test
    @DisplayName("Verifie si une reclamation a bien ete ajoutee aux statistiques")
    void verifieSiReclamationAjoutee() {
        assertEquals(1, Statistiques.getStatistiqueSoin400().getNombreSoin());
    }

    @Test
    @DisplayName("Verification qu'une reclamation entre 300 et 399 est ajoutee aux statistiques du soin 300")
    void verifieSiFichierTemporaireCree() {
        fauxReclamation = new FauxReclamation("50.00$", "2021-04-22", "333");
        fauxReclamationsAL.add(fauxReclamation);
        client1 = ClientFactory.obtenirClient(fauxClient);
        ReclamationFactory.ajouterReclamations(clients, client1, fauxClient);
        CalculReclamation.obtenir(clients, client1);
        Statistiques.compter(client1);
        assertEquals(1, Statistiques.getStatistiqueSoin300().getNombreSoin());
    }

    @Test
    @DisplayName("Verification si une reiniatialisation des statistiques apportent les chiffres a 0")
    void verifieSiRecommencerStatsMetStatsAZero() {
        Statistiques.recommencerStats();
        assertEquals(0, Statistiques.getStatistiqueSoin400().getNombreSoin());
    }

//    @Test
//    @DisplayName("Verifie si la méthode afficher affiche la bonne chose")
//    void verifieAfficher() {
//        Statistiques.setReclamationsInconnues(true);
//        String attendu = "STATISTIQUES\n" +
//                "\tNombre de réclamations traitées : 1" +
//                "\n\tNombre de demandes rejetées : 0" +
//                "\n" +
//                "\n\tNombre de soin 0   : 0" +
//                "\n\tNombre de soin 100 : 0" +
//                "\n\tNombre de soin 150 : 0" +
//                "\n\tNombre de soin 175 : 0" +
//                "\n\tNombre de soin 200 : 0" +
//                "\n\tNombre de soin [300..399] : 0" +
//                "\n\tNombre de soin 400 : 1" +
//                "\n\tNombre de soin 500 : 0" +
//                "\n\tNombre de soin 600 : 0" +
//                "\n\tNombre de soin 700 : 0";
//        assertEquals(attendu, Statistiques.afficher());
//
//    }

    @Test
    @DisplayName("Verifie si la valeur est entre x et y")
    void verifieEstEntre() {
        assertTrue(Statistiques.estEntre(10, 5, 15));
        assertFalse(Statistiques.estEntre(10, 15, 20));

    }

    @Test
    @DisplayName("Verifie la méthode compter pour voir si les valeurs changent")
    void verifieCompter() {
        ArrayList<Integer> attendu = new ArrayList<>() {{
            add(Statistiques.getStatistiqueSoin000().getNombreSoin() + 1);
            add(Statistiques.getStatistiqueSoin100().getNombreSoin() + 1);
            add(Statistiques.getStatistiqueSoin150().getNombreSoin() + 1);
            add(Statistiques.getStatistiqueSoin175().getNombreSoin() + 1);
            add(Statistiques.getStatistiqueSoin200().getNombreSoin() + 1);
            add(Statistiques.getStatistiqueSoin300().getNombreSoin() + 1);
            add(Statistiques.getStatistiqueSoin400().getNombreSoin() + 2);
            add(Statistiques.getStatistiqueSoin500().getNombreSoin() + 1);
            add(Statistiques.getStatistiqueSoin600().getNombreSoin() + 1);
            add(Statistiques.getStatistiqueSoin700().getNombreSoin() + 1);
        }};

        fauxReclamationsAL.add(new FauxReclamation("10.00$", "2021-04-01", "000"));
        fauxReclamationsAL.add(new FauxReclamation("10.00$", "2021-04-02", "100"));
        fauxReclamationsAL.add(new FauxReclamation("10.00$", "2021-04-03", "150"));
        fauxReclamationsAL.add(new FauxReclamation("10.00$", "2021-04-04", "175"));
        fauxReclamationsAL.add(new FauxReclamation("10.00$", "2021-04-05", "200"));
        fauxReclamationsAL.add(new FauxReclamation("10.00$", "2021-04-06", "333"));
        fauxReclamationsAL.add(new FauxReclamation("10.00$", "2021-04-07", "400"));
        fauxReclamationsAL.add(new FauxReclamation("10.00$", "2021-04-08", "500"));
        fauxReclamationsAL.add(new FauxReclamation("10.00$", "2021-04-09", "600"));
        fauxReclamationsAL.add(new FauxReclamation("10.00$", "2021-04-10", "700"));

        client1 = ClientFactory.obtenirClient(fauxClient);
        ReclamationFactory.ajouterReclamations(clients, client1, fauxClient);
        CalculReclamation.obtenir(clients, client1);

        Statistiques.compter(client1);
        ArrayList<Integer> list = new ArrayList<>() {{
            add(Statistiques.getStatistiqueSoin000().getNombreSoin());
            add(Statistiques.getStatistiqueSoin100().getNombreSoin());
            add(Statistiques.getStatistiqueSoin150().getNombreSoin());
            add(Statistiques.getStatistiqueSoin175().getNombreSoin());
            add(Statistiques.getStatistiqueSoin200().getNombreSoin());
            add(Statistiques.getStatistiqueSoin300().getNombreSoin());
            add(Statistiques.getStatistiqueSoin400().getNombreSoin());
            add(Statistiques.getStatistiqueSoin500().getNombreSoin());
            add(Statistiques.getStatistiqueSoin600().getNombreSoin());
            add(Statistiques.getStatistiqueSoin700().getNombreSoin());
        }};
        assertEquals(attendu, list);
    }


}

