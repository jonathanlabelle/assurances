
package CheminFichier;

import Fichier.Dossier;

public class CheminFichierFactory {

    private static final String CHEMIN_SOURCE = "database.json";
    private static final String CHEMIN_STATS = "stats.json";
    private static final String CHEMIN_STAMP = "fichiers.json";

    private String[] cheminFichiers;

    public CheminFichierFactory(String[] cheminFichiers) {
        setCheminFichiers(cheminFichiers);
    }

    private void setCheminFichiers(String[] cheminFichiers){
        this.cheminFichiers = cheminFichiers;
    }

    //GET

    public ICheminFichier getIntrant(){
        ICheminFichier cheminFichier;
        try {
            cheminFichier = new CheminFichierIntrant(this.cheminFichiers);
        } catch (Exception e) {
            cheminFichier = null;
        }
        return cheminFichier;
    }

    public ICheminFichier getExtrant(){
        ICheminFichier cheminFichier;
        try {
            cheminFichier = new CheminFichierExtrant(this.cheminFichiers, getIntrant());
        } catch (Exception e) {
            cheminFichier = null;
        }
        return cheminFichier;
    }

    public ICheminFichier getErreur(){
        ICheminFichier cheminFichier;
        try {
            cheminFichier = new CheminFichierErreur(getExtrant());
        } catch (Exception e) {
            cheminFichier = null;
        }
        return cheminFichier;
    }

    public static ICheminFichier getErreurGenerique(){
        return new CheminFichierErreur(null);
    }

    public static ICheminFichier getSource(){
        return new CheminFichier(Dossier.DATABASE + CHEMIN_SOURCE);
    }

    public static ICheminFichier getStatistique(){
        return new CheminFichier(Dossier.DATABASE + CHEMIN_STATS);
    }

    public static ICheminFichier getTimeStamp(){
        return new CheminFichier(Dossier.DATABASE + CHEMIN_STAMP);
    }

}