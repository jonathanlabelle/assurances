
package Fichier;

import CheminFichier.CheminFichierFactory;
import CheminFichier.ICheminFichier;
import Erreur.ErreurSortie;
import org.json.JSONObject;

import javax.sound.midi.Soundbank;
import java.io.*;

import static Erreur.ErreurSortie.terminer;

public class FichierTemps {
    private static final String bypass = "bypass";

    private static Long timeStampDatabase = 0L;
    private static Long timeStampStatistique = 0L;

    private long timeStamp;
    private File file;

    public FichierTemps(File file ) {
        this.file = file;
        this.timeStamp = file.lastModified();
    }


    //GET
    public long getTimeStamp() {
        return timeStamp;
    }


    //SET
    private static void setTimeStampDatabase(long timeStampDatabase) {
        FichierTemps.timeStampDatabase = timeStampDatabase;
    }

    private static void setTimeStampStatistique(long timeStampStatistique) {
        FichierTemps.timeStampStatistique = timeStampStatistique;
    }

    private static void setTimeStampStatistique(String timeStampStatistique) {
        try {
            setTimeStampStatistique( Long.parseLong(timeStampStatistique) );
        } catch (Exception e) {
            ErreurSortie.erreur();
        }
    }

    private static void setTimeStampDatabase(String timeStampDatabase) {
        try {
            setTimeStampDatabase( Long.parseLong(timeStampDatabase) );
        } catch (Exception e) {
            ErreurSortie.erreur();
        }
    }

    private static void setTimeStampDatabaseOrBypass(String database) {
        if (!database.equals(bypass)) {
            setTimeStampDatabase(database);
        } else {
            timeStampDatabase = null;
        }
    }

    private static void setTimeStampStatistiqueOrBypass(String stats) {
        if (stats.equals(bypass)) {
            setTimeStampStatistique(stats);
        } else {
            timeStampStatistique = null;
        }
    }

    //PUBLIC

    public static void ecrire() {
        String erreur = FichierEcrire.ecrire(jsonFormat(), CheminFichierFactory.getTimeStamp().getChemin());{
            if (erreur != null) terminer("fichier.ecrire");
        }
    }

    public static void lire() {
        String json = FichierLire.lire(CheminFichierFactory.getTimeStamp().getChemin());
        if (null != json && !json.isEmpty()) {

            try {
                JSONObject jsonObject = new JSONObject(json);
                setTimeStampDatabaseOrBypass( String.valueOf(jsonObject.get("timeStampDatabase")) );
                setTimeStampStatistiqueOrBypass( String.valueOf(jsonObject.get("timeStampStatistique")) );
            } catch (Exception e) {
                ErreurSortie.erreur();
            }

        }
    }

    public boolean estInchange() {
        return compareStatistique() && compareDatabase();
    }


    //PRIVATE

    private static String jsonFormat() {
        setTimeStampFin();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("timeStampDatabase", timeStampDatabase);
            jsonObject.put("timeStampStatistique", timeStampStatistique);
        } catch (Exception e) {
        }
        return jsonObject.toString(4);
    }

    private static void setTimeStampFin() {
        setTimeStampDatabase( timeStampDatabase() );
        setTimeStampStatistique( timeStampStatistique() );
    }

    private static long timeStampStatistique() {
        FichierTemps fichierTemps = new FichierTemps( new File(CheminFichierFactory.getStatistique().getChemin()) );
        return fichierTemps.getTimeStamp();
    }

    private static long timeStampDatabase() {
        FichierTemps fichierTemps = new FichierTemps( new File(CheminFichierFactory.getSource().getChemin()) );
        return fichierTemps.getTimeStamp();
    }

    private boolean compareStatistique() {
        if (null != timeStampStatistique) {
            if (this.file.getName().equals(CheminFichierFactory.getStatistique().getChemin().split("/")[1])) {
                if (this.timeStamp != timeStampStatistique) {
                    ErreurSortie.erreur("stats.lecture");
                    return false;
                }
            }
        }
        return true;
    }

    private boolean compareDatabase() {
        if (null != timeStampDatabase) {
            if (this.file.getName().equals(CheminFichierFactory.getSource().getChemin().split("/")[1])) {
                if (this.timeStamp != timeStampDatabase) {
                    ErreurSortie.erreur("database.lecture");
                    return false;
                }
            }
        }
        return true;
    }

}
