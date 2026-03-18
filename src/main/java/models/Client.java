package models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/**
 * Représente un client dans le CRM.
 *
 * @author Cyril
 * @version 1.0
 */
public final class Client extends Societe {

    /** Identifiant unique du client. */
    @NotNull(message = "L'identifiant du client est obligatoire")
    private Integer idClient;

    /** Chiffre d'affaires minimum autorisé. */
    private static final int MIN_CHIFFRE_AFFAIRES = 200;

    /** Chiffre d'affaires du client. */
    @Min(
            value = MIN_CHIFFRE_AFFAIRES,

            message = "Le chiffre d'affaires doit être"
                    + " supérieur à 200 €"
    )
    private long chiffreAffaires;

    /** Nombre d'employés du client. */
    @Min(
            value = 1,
            message = "Le nombre d'employés doit être"
                    + " supérieur à 0"
    )
    private int nombreEmployes;

    /**
     * Constructeur vide obligatoire pour un JavaBean.
     */
    public Client() {
        // Constructeur vide
    }

    /**
     * Retourne l'identifiant du client.
     * @return idClient
     */
    public Integer getIdClient() {
        return idClient;
    }

    /**
     * Définit l'identifiant du client.
     * @param pIdClient identifiant du client
     */
    public void setIdClient(final Integer pIdClient) {
        this.idClient = pIdClient;
    }

    /**
     * Retourne le chiffre d'affaires.
     * @return chiffreAffaires
     */
    public long getChiffreAffaires() {
        return chiffreAffaires;
    }

    /**
     * Définit le chiffre d'affaires.
     * @param pChiffreAffaires chiffre d'affaires
     */
    public void setChiffreAffaires(final long pChiffreAffaires) {
        this.chiffreAffaires = pChiffreAffaires;
    }

    /**
     * Retourne le nombre d'employés.
     * @return nombreEmployes
     */
    public int getNombreEmployes() {
        return nombreEmployes;
    }

    /**
     * Définit le nombre d'employés.
     * @param pNombreEmployes nombre d'employés
     */
    public void setNombreEmployes(final int pNombreEmployes) {
        this.nombreEmployes = pNombreEmployes;
    }

    /**
     * Retourne une représentation textuelle du client.
     * @return chaîne de caractères
     */
    @Override
    public String toString() {
        return "Client ID : " + idClient + "\n"
                + "Chiffre d'affaires : " + chiffreAffaires + " €\n"
                + "Nombre d'employés : " + nombreEmployes;
    }
}
