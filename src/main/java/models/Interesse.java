package models;

/**
 * Enumération représentant l'intérêt d'un prospect.
 */
public enum Interesse {

    /** Le prospect est intéressé. */
    OUI,

    /** Le prospect n'est pas intéressé. */
    NON;

    /**
     * Retourne une représentation textuelle en minuscules.
     * @return chaîne de caractères
     */
    @Override
    public String toString() {
        return name().toLowerCase();
    }
}