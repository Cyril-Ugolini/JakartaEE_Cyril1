package models;

/**
 * Enumération représentant l'intérêt d'un prospect.
 */
public enum Interesse {
    OUI,
    NON;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}