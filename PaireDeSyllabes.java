/**
 * Une classe pour contenir une paire de syllabes.
 *
 * @see CompteurDeSyllabes
 * @see TexteSonore
 */
public class PaireDeSyllabes {
    private SyllabeFrancais syllabeARemplacer;
    private SyllabeFrancais nouvelleValeur;


    /**
     * Constructeur par défaut. Crée une {@code PaireDeSyllabes} sans lui donner de valeurs.
     */
    public PaireDeSyllabes() {
        this.syllabeARemplacer = null;
        this.nouvelleValeur = null;
    }

    /**
     * Construit une {@code PaireDeSyllabes} en lui assignant deux {@code SyllabeFrancais}.
     *
     * @param syllabeARemplacer le {@code SyllabeFrancais} qui doit éventuellement être éliminé et remplacé.
     * @param nouvelleValeur    le {@code SyllabeFrancais} qui prendre la place du {@code syllabeARemplacer}.
     */
    public PaireDeSyllabes(SyllabeFrancais syllabeARemplacer, SyllabeFrancais nouvelleValeur) {
        this.syllabeARemplacer = syllabeARemplacer;
        this.nouvelleValeur = nouvelleValeur;
    }


    public SyllabeFrancais getSyllabeARemplacer() { return syllabeARemplacer; }

    public void setSyllabeARemplacer(SyllabeFrancais syllabeARemplacer) { this.syllabeARemplacer = syllabeARemplacer; }

    public SyllabeFrancais getNouvelleValeur() { return nouvelleValeur; }

    public void setNouvelleValeur(SyllabeFrancais nouvelleValeur) { this.nouvelleValeur = nouvelleValeur; }
}
