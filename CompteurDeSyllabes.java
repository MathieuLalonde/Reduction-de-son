import java.util.ArrayList;
import java.util.LinkedHashMap;


/**
 * Une classe pour comptabiliser et stocker le nombre de {@code SyllabeFrancais} distincts dans un {@code TexteSonore}.
 * <p>
 * Cette classe permet de sélectionner la paire de syllabes la plus semblable selon leur distance et leur fréquence.
 *
 * @see TexteSonore
 * @see PaireDeSyllabes
 */
public class CompteurDeSyllabes extends LinkedHashMap<SyllabeFrancais, Integer> {
    /**
     * Constructeur par défaut. Crée un {@code CompteurDeSyllabes} vide sans l'initialiser.
     */
    public CompteurDeSyllabes() {
        super();
    }

    /**
     * Construit et initialise un {@code CompteurDeSyllabes} à partir d'un {@code TexteSonore} fourni.
     *
     * @param texteSonore le {@code TexteSonore} à partir duquel créer le compteur.
     */
    public CompteurDeSyllabes(TexteSonore texteSonore) {
        super();
        initialiseValeurs(texteSonore);
    }


    /**
     * Initialise (ou ré-initialise) un {@code CompteurDeSyllabes} à partir d'un {@code TexteSonore} fourni.
     * <p>
     * Cette opération comptabilise le nombre de {@code SyllabeFrancais} distincts dans un {@code TexteSonore} et
     * enregistre ces données dans le compteur.
     *
     * @param texteSonore le {@code TexteSonore} à partir duquel initialiser le compteur.
     */
    public void initialiseValeurs(TexteSonore texteSonore) {
        this.clear();
        for (SyllabeFrancais a : texteSonore) {
            if (this.containsKey(a)) {
                int compte = this.get(a);
                this.put(a, compte + 1);
            } else {
                this.put(a, 1);
            }
        }
    }


    /**
     * Sélectionne les deux {@code SyllabeFrancais} les plus similaires dans le {@code CompteurDeSyllabes}
     * <p>
     * Selon leur fréquence dans le compteur, on détermine laquelle des deux devra être remplacée
     * par l'autre à l'intérieur du {@code TexteSonore}.
     *
     * @return une {@code PaireDeSyllabes} à partir de laquelle on peut effectuer une réduction de syllabes.
     */
    public PaireDeSyllabes choisirSyllabesARemplacer() {
        int distance;
        int plusPetiteDistance = Integer.MAX_VALUE;
        SyllabeFrancais choixSyllabe1 = null;
        SyllabeFrancais choixSyllabe2 = null;
        PaireDeSyllabes paire;

        ArrayList<SyllabeFrancais> listeDesSyllabes = new ArrayList<>(this.keySet());

        for (int i = 0; i < listeDesSyllabes.size(); i++) {
            for (int j = i + 1; j < listeDesSyllabes.size(); j++) {

                SyllabeFrancais candidat1 = listeDesSyllabes.get(i);
                SyllabeFrancais candidat2 = listeDesSyllabes.get(j);

                distance = candidat1.distanceA(candidat2);

                if (distance < plusPetiteDistance) {
                    plusPetiteDistance = distance;
                    choixSyllabe1 = candidat1;
                    choixSyllabe2 = candidat2;
                }
            }
        }

        if (get(choixSyllabe1) < get(choixSyllabe2)) {
            paire = new PaireDeSyllabes(choixSyllabe1, choixSyllabe2);
        } else {
            paire = new PaireDeSyllabes(choixSyllabe2, choixSyllabe1);
        }
        return paire;
    }
}
