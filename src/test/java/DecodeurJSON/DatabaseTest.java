package DecodeurJSON;
import Client.Client;
import Client.Clients;
import DecodeurJSON.FauxClient.FauxClient;
import DecodeurJSON.FauxClient.FauxReclamation;
import Dollar.DollarFactory;
import Erreur.ErreurSortie;
import Fichier.FichierEcrire;
import Fichier.FichierLire;
import Outils.DateConteneur;
import Reclamation.CalculReclamation;
import Reclamation.Reclamation;
import Reclamation.ReclamationsMensuelle;
//import jdk.internal.access.JavaSecurityAccess;
import Contrat.*;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
//import org.skyscreamer.jsonassert.JSONAssert;

import java.util.ArrayList;

//import static DecodeurJSON.Database.getJSONArrayClients;
import static org.junit.jupiter.api.Assertions.*;

class DatabaseTest {

    private static final String CLIENTS = "clients";

    Client client;
    Client clientAnterieur;
    Clients clients;
    CalculReclamation calculReclamation;
    FauxReclamation fauxReclamation;
    FauxClient fauxClient;



    @BeforeEach
    void setUp() {

        clients = new Clients();

        Reclamation reclamationA = new Reclamation(new DateConteneur(), new DateConteneur(),
                DollarFactory.getDollar(15000), DollarFactory.getDollar(8500),
                new PoliceSoin(Contrat.A, 100),
                DictionnaireSoin.getTauxAssurable(new PoliceSoin(Contrat.A, 100)) );
        Reclamation reclamationB = new Reclamation(new DateConteneur(), new DateConteneur(),
                DollarFactory.getDollar(16000), DollarFactory.getDollar(9500),
                new PoliceSoin(Contrat.B, 200),
                DictionnaireSoin.getTauxAssurable(new PoliceSoin(Contrat.B, 200)) );
        Reclamation reclamationC = new Reclamation(new DateConteneur(), new DateConteneur(),
                DollarFactory.getDollar(17000), DollarFactory.getDollar(10500),
                new PoliceSoin(Contrat.C, 300),
                DictionnaireSoin.getTauxAssurable(new PoliceSoin(Contrat.C, 300)) );

        ReclamationsMensuelle reclamationsMensuelleA = new ReclamationsMensuelle(new DateConteneur(), new ArrayList<>());
        ReclamationsMensuelle reclamationsMensuelleB = new ReclamationsMensuelle(new DateConteneur(), new ArrayList<>());
        ReclamationsMensuelle reclamationsMensuelleC = new ReclamationsMensuelle(new DateConteneur(), new ArrayList<>());

        reclamationsMensuelleA.ajouterReclamation(reclamationA);
        reclamationsMensuelleA.ajouterReclamation(reclamationB);

        reclamationsMensuelleB.ajouterReclamation(reclamationB);
        reclamationsMensuelleB.ajouterReclamation(reclamationC);

        reclamationsMensuelleC.ajouterReclamation(reclamationC);
        reclamationsMensuelleC.ajouterReclamation(reclamationA);


        Client clientA = new Client("111111", Contrat.A, new ArrayList<ReclamationsMensuelle>());
        Client clientB = new Client("222222", Contrat.B, new ArrayList<ReclamationsMensuelle>());
        Client clientC = new Client("333333", Contrat.C, new ArrayList<ReclamationsMensuelle>());
        Client clientD = new Client("444444", Contrat.D, new ArrayList<ReclamationsMensuelle>());

        clientA.ajouterReclamationsMensuelle(reclamationsMensuelleA);
        clientA.ajouterReclamationsMensuelle(reclamationsMensuelleB);

        clientB.ajouterReclamationsMensuelle(reclamationsMensuelleB);
        clientB.ajouterReclamationsMensuelle(reclamationsMensuelleC);

        clientC.ajouterReclamationsMensuelle(reclamationsMensuelleC);
        clientC.ajouterReclamationsMensuelle(reclamationsMensuelleA);

        clientD.ajouterReclamationsMensuelle(reclamationsMensuelleA);
        clientD.ajouterReclamationsMensuelle(reclamationsMensuelleC);
        clientD.ajouterReclamationsMensuelle(reclamationsMensuelleB);

        clients.add(clientA);
        clients.add(clientB);
        clients.add(clientC);






    }



    @Test
    @DisplayName("Formater l'import d'un fichier pour voir si il est null")
    void formaterImport() {
        String JSON = FichierLire.lire("clients.json");
        clients = Database.formaterImport(JSON);
        assertNull(JSON);

    }

    @Test
    @DisplayName("Formater un fichier si il est vide")
    void formaterExport() {
        assertTrue( !Database.formaterExport(clients).isEmpty() );
    }
    @Test
    @DisplayName("Retour de null a la sortie de formater clients")
    void formaterEcrire(){

        String retournull = FichierEcrire.ecrire(Database.formaterExport(clients), "clients2.json");
        assertNull(retournull);

    }
    @Test
    @DisplayName("Retour de null a la sortie de formater clients avec le message d'erreur")
    void formaterImportErreur(){
        String JSON = FichierLire.lire("clients.json");
        clients = Database.formaterImport(JSON);
        assertNull(JSON);

        ErreurSortie.erreur("database.nouveau");

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

}
