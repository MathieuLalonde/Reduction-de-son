import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;


/**
 * Représente l'unité de base pour la prononciation en français.
 * <p>
 * Une syllabe est composé d'un groupe de syllabe (le noyau).
 * optionnelement, elle peu avoir deux groupes de consonne.
 * Le groupe de consonne avant le noyau est l'attaque de la syllabe.
 * Le groupe de consonne après le noyau est le coda de la syllabe.
 *
 * @see ConsonneFrancais
 * @see VoyelleFrancais
 * @see <a href="https://fr.wiktionary.org/wiki/Annexe:Prononciation/fran%C3%A7ais">référence</a>
 */
public class SyllabeFrancais {
    /**
     * Le groupe de consonne pour l'attaque de la syllabe.  S'il n'est pas présent, alors la valeur est à {@code null}.
     */
    protected ConsonneFrancais attaque = null;

    /**
     * Le groupe de voyelle pour la syllabe.  Ne doit pas être {@code null}.
     */
    protected VoyelleFrancais noyau;

    /**
     * Le groupe de consonne pour le code de la syllabe.  S'il n'est pas présent, alors la valeur est à {@code null}.
     */
    protected ConsonneFrancais coda = null;


    /**
     * Construit une syllabe avec un noyau seulement.
     *
     * @param noyau le groupe de voyelle utilisé pour la syllabe.  Ne doit pas être {@code null}.
     */
    public SyllabeFrancais(VoyelleFrancais noyau) {
        this.noyau = noyau;
    }

    /**
     * Construit une syllabe avec une attaque et un noyau.
     *
     * @param attaque le groupe de consonne utilisé pour la syllabe.
     * @param noyau   le groupe de voyelle utilisé pour la syllabe.  Ne doit pas être {@code null}.
     */
    public SyllabeFrancais(ConsonneFrancais attaque, VoyelleFrancais noyau) {
        this.attaque = attaque;
        this.noyau = noyau;
    }

    /**
     * Construit une syllabe avec une attaque, un noyau et un coda.
     *
     * @param attaque le groupe de consonne utilisé pour la syllabe.
     * @param noyau   le groupe de voyelle utilisé pour la syllabe.  Ne doit pas être {@code null}.
     * @param coda    le groupe de consonne utilisé pour la syllabe.
     */
    public SyllabeFrancais(ConsonneFrancais attaque, VoyelleFrancais noyau, ConsonneFrancais coda) {
        this.attaque = attaque;
        this.noyau = noyau;
        this.coda = coda;
    }

    /**
     * Construit une syllabe avec un noyau et un coda.
     *
     * @param noyau le groupe de voyelle utilisé pour la syllabe.  Ne doit pas être {@code null}.
     * @param coda  le groupe de consonne utilisé pour la syllabe.
     */
    public SyllabeFrancais(VoyelleFrancais noyau, ConsonneFrancais coda) {
        this.noyau = noyau;
        this.coda = coda;
    }


    /**
     * Lit une syllabe dans le {@code Scanner}.
     * <p>
     * Cherche possiblement un groupe de consonne qui servira d'attaque, ensuite un groupe de voyelle qui
     * servira de noyau et finalement un autre groupe de consonne pour le coda.
     *
     * @param scanner le {@code Scanner} dans lequel la lecture est effectué.
     * @return la voyelle lu.
     * @throws NoSuchElementException s'il n'y a pas de {@code SyllabeFrancais} valide.
     * @throws IllegalStateException  si le {@code Scanner} est fermé.
     */
    public static SyllabeFrancais lire(Scanner scanner) {
        ConsonneFrancais attaque = null;
        VoyelleFrancais noyau;
        ConsonneFrancais coda = null;

        try {
            attaque = ConsonneFrancais.lire(scanner);
        } catch (NoSuchElementException e) {
        }

        noyau = VoyelleFrancais.lire(scanner);

        try {
            coda = ConsonneFrancais.lire(scanner);
        } catch (NoSuchElementException e) {
        }


        return new SyllabeFrancais(attaque, noyau, coda);
    }


    /**
     * retourne une chaîne de caractère composée des phonèmes de la syllabe.
     *
     * @return la chaîne contenant les symboles des phonèmes de la syllabe.
     */
    @Override
    public String toString() {
        return "" + (null == attaque ? "" : attaque)
                + noyau
                + (null == coda ? "" : coda);
    }


    /**
     * Compare les valeurs du noyau, de l'attaque et du coda des deux {@code SyllabeFrancais}.
     *
     * @param o l'objet auquel le {@code SyllabeFrancais} est comparé. Un objet n'étant pas lui-même un
     *          {@code SyllabeFrancais} retourne une valeur {@code null}
     * @return une valeur booléenne {@code true} si le noyau, l'attaque et le coda des deux valeurs comparées sont
     * pareils.
     */
    @Override
    public boolean equals(Object o) {
        boolean resultat = false;

        if (this == o) {
            resultat = true;
        } else if (o == null || getClass() != o.getClass()) {
            resultat = false;
        } else {
            SyllabeFrancais that = (SyllabeFrancais) o;
            resultat = (this.noyeauEgaleNoyeayDe(that)
                    && this.attaqueEgaleAttaqueDe(that)
                    && this.codaEgaleCodaDe(that));
        }

        return resultat;
    }

    /**
     * Crée un {@code hashCode} à des fins de comparaison d'objets de la classe.
     *
     * @return un {@code hashCode} calculé à partir des valeurs {@code attaque}, {@code noyau} et {@code coda}.
     */
    @Override
    public int hashCode() {
        return Objects.hash(attaque, noyau, coda);
    }


    /**
     * Compare le {@code noyau} de deux {@code SyllabeFrancais} pour déterminer s'ils sont égaux.
     *
     * @param autre le {@code SyllabeFrancais} auquel on compare {@code this}.
     * @return valeur booléenne {@code true} si les deux {@code noyau} sont égaux.
     */
    private boolean noyeauEgaleNoyeayDe(SyllabeFrancais autre) {
        return noyau.equals(autre.noyau);
    }

    /**
     * Compare l'{@code attaque} de deux {@code SyllabeFrancais} pour déterminer si elles sont égales.
     *
     * @param autre le {@code SyllabeFrancais} auquel on compare {@code this}.
     * @return valeur booléenne {@code true} si les deux {@code attaque} sont égales.
     */
    private boolean attaqueEgaleAttaqueDe(SyllabeFrancais autre) {
        boolean resultat = false;

        if (attaque == null || autre.attaque == null) {
            if (attaque == autre.attaque) {
                resultat = true;
            }
        } else {
            resultat = attaque.equals(autre.attaque);
        }
        return resultat;
    }

    /**
     * Compare le {@code coda} de deux {@code SyllabeFrancais} pour déterminer s'ils sont égaux.
     *
     * @param autre le {@code SyllabeFrancais} auquel on compare {@code this}.
     * @return valeur booléenne {@code true} si les deux {@code coda} sont égales.
     */
    private boolean codaEgaleCodaDe(SyllabeFrancais autre) {
        boolean resultat = false;

        if (coda == null || autre.coda == null) {
            if (coda == autre.coda) {
                resultat = true;
            }
        } else {
            resultat = coda.equals(autre.coda);
        }
        return resultat;
    }


    /**
     * Comptabilise le nombre total des distances entre deux {@code SyllabeFrancais}.
     *
     * @param autre le {@code SyllabeFrancais} auquel on compare {@code this}.
     * @return une valeur numérique totalisant la distance entre les deux {@code SyllabeFrancais}.
     */
    public int distanceA(SyllabeFrancais autre) {
        int distance = noyau.distanceEntre(autre.noyau) * 2;

        if (this.attaque == null || autre.attaque == null) {
            if (this.attaque != autre.attaque) {
                distance += 12;
            }
        } else {
            distance += this.attaque.distanceEntre(autre.attaque);
        }
        if (this.coda == null || autre.coda == null) {
            if (this.coda != autre.coda) {
                distance += 12;
            }
        } else {
            distance += this.coda.distanceEntre(autre.coda);
        }
        return distance;
    }
}
