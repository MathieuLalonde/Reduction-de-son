import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;


/**
 * Décrit le son d'un groupe de consonne en français.
 * <p>
 * Cette description permet de contenir une ou deux consonnes afin de décrire le son des consonnes dans une syllabe.
 *
 * @see API_Consonne
 * @see SyllabeFrancais
 * @see <a href="https://fr.wiktionary.org/wiki/Annexe:Prononciation/fran%C3%A7ais">référence</a>
 */
public class ConsonneFrancais {
    /**
     * La consonne de base du groupe de consonne.
     * Ne doit pas être {@code null}.
     */
    protected API_Consonne consonne1;

    /**
     * La consonne secondaire du groupe de consonne.
     * La valeur {@code null} est utilisé pour indiquer qu'elle n'est pas présente dans le groupe.
     */
    protected API_Consonne consonne2 = null;


    /**
     * Construit un groupe avec une seule consonne.
     *
     * @param consonne1 La consonne du groupe.  Elle est placé comme consonne de base.  Ne doit pas être {@code null}.
     */
    public ConsonneFrancais(API_Consonne consonne1) {
        this.consonne1 = consonne1;
    }

    /**
     * Construit un groupe avec deux consonnes.
     *
     * @param consonne1 La consonne de base du groupe.  Ne doit pas être {@code null}.
     * @param consonne2 La consonne secondaire du groupe.
     */
    public ConsonneFrancais(API_Consonne consonne1, API_Consonne consonne2) {
        this.consonne1 = consonne1;
        this.consonne2 = consonne2;
    }


    /**
     * Lit un groupe de consonnes dans le {@code Scanner}.
     * <p>
     * Vérifie si le prochain caractère du {@code scanner} représente une consonne.  Si oui, alors cette consonne
     * deviendra la consonne de base du groupe retourné.
     * Ensuite, vérifie si le prochain caractère représente une consonne.  Si oui, alors cette consonne deviendra la
     * consonne secondaire du groupe retourné.
     *
     * @param scanner le {@code Scanner} dans lequel la lecture est effectué.
     * @return le groupe de consonne lu.
     * @throws NoSuchElementException s'il n'y a pas de {@code API_Consonne} valide.
     * @throws IllegalStateException  si le {@code Scanner} est fermé.
     */
    public static ConsonneFrancais lire(Scanner scanner) {
        ConsonneFrancais resultat = null;
        API_Consonne consonne1 = API_Consonne.lire(scanner);
        API_Consonne consonne2;

        try {
            consonne2 = API_Consonne.lire(scanner);
            resultat = new ConsonneFrancais(consonne1, consonne2);
        } catch (NoSuchElementException e) {
            resultat = new ConsonneFrancais(consonne1);
        }

        return resultat;
    }


    /**
     * retourne une chaîne de caractère composée des consonnes du groupe.
     *
     * @return la chaîne contenant les symboles des consonnes du groupe.
     */
    @Override
    public String toString() {
        return "" + consonne1 + (null == consonne2 ? "" : consonne2);
    }


    /**
     * Compare les valeurs de deux {@code ConsonneFrancais}.
     *
     * @param o l'objet auquel le {@code ConsonneFrancais} est comparé.
     * @return une valeur booléenne {@code true} si les deux consonnes comparées ont les mêmes caractéristiques.
     */
    @Override
    public boolean equals(Object o) {
        boolean resultat1;
        boolean resultat2 = false;

        ConsonneFrancais other = (ConsonneFrancais) o;

        resultat1 = this.consonne1.equals(other.consonne1);

        if (this.consonne2 == null || other.consonne2 == null) {
            if (this.consonne2 == other.consonne2) {
                resultat2 = true;
            }
        } else {
            resultat2 = this.consonne2.equals(other.consonne2);
        }
        return (resultat1 && resultat2);
    }

    /**
     * Crée un {@code hashCode} à des fins de comparaison d'objets de la classe.
     *
     * @return un {@code hashCode} calculé à partir des valeurs {@code consonne1} et {@code consonne2}.
     */
    @Override
    public int hashCode() {
        return Objects.hash(consonne1, consonne2);
    }


    /**
     * Comptabilise les distances entre deux {@code ConsonneFrancais}.
     *
     * @param autre le {@code ConsonneFrancais} auquel on compare {@code this}.
     * @return une valeur numérique totalisant la distance entre les deux paires de {@code ConsonneFrancais}.
     */
    public int distanceEntre(ConsonneFrancais autre) {
        int distance = this.consonne1.distanceEntre(autre.consonne1);

        if (this.consonne2 == null || autre.consonne2 == null) {
            if (this.consonne2 != autre.consonne2) {
                distance += 6;
            }
        } else {
            distance += this.consonne2.distanceEntre(autre.consonne2);
        }
        return distance;
    }
}
