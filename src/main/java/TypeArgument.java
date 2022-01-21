public enum TypeArgument {

    afficherStatistique     ( "-S",     Argument::afficherStatistique,  Borne.unique ),
    supprimerStatistique    ( "-SR",    Argument::supprimerStatistique, Borne.unique ),
    supprimerClients        ( "-CR",    Argument::supprimerClients,     Borne.unique ),
    afficherAide            ( "-help",  Argument::afficherAide,         Borne.unique ),
    modePrediction          ( "-p",     Argument::modePrediction,       Borne.prediction),
    calculReclamation       ( "",       Argument::calculReclamation,    Borne.calcul),
    aucunArgument           ( "",       Argument::aucunArgument,        Borne.aucun),
    invalideArgument        ( "-",      Argument::invalideArgument,     Borne.invalide),
    ;

    public String argumentClef;
    public Action action;
    public Borne borne;

    TypeArgument(String argumentClef, Action action, Borne borne) {
        this.argumentClef = argumentClef;
        this.action = action;
        this.borne = borne;
    }

    public static TypeArgument obtenir(String argumentClef) {
        for (TypeArgument typeArgument : TypeArgument.values()) {
            if (typeArgument.argumentClef.equals(argumentClef)) return typeArgument;
        }
        if (argumentClef.length() > 0 && argumentClef.charAt(0) == '-') return TypeArgument.invalideArgument;
        else return TypeArgument.calculReclamation;
    }

    public enum Borne {
        aucun(0,0),
        unique(1,1),
        calcul(1,2),
        prediction(2,3),
        invalide(0,4),
        ;

        public int inferieur;
        public int superieur;

        Borne(int inferieur, int superieur) {
            this.inferieur = inferieur;
            this.superieur = superieur;
        }
    }
}
