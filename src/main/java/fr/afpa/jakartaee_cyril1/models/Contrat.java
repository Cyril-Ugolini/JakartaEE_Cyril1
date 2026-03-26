package fr.afpa.jakartaee_cyril1.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Représente un contrat dans le CRM.
 *
 * @author Cyril
 * @version 1.0
 */
public final class Contrat {

    /** Identifiant unique du contrat. */
    @NotNull(message = "L'identifiant du contrat est obligatoire")
    private Integer identifiant;

    /** Identifiant du client associé. */
    private Integer idClient;

    /** Nom du contrat. */
    @NotBlank(message = "Le nom du contrat est obligatoire")
    private String nomContrat;

    /** Montant du contrat. */
    @Min(value = 1, message = "Le montant doit être strictement positif")
    private double montant;

    /**
     * Constructeur vide obligatoire pour un JavaBean.
     */
    public Contrat() {
        // Constructeur vide
    }

    /**
     * Retourne l'identifiant.
     * @return identifiant
     */
    public Integer getIdentifiant() {
        return identifiant;
    }

    /**
     * Définit l'identifiant.
     * @param pIdentifiant identifiant du contrat
     */
    public void setIdentifiant(final Integer pIdentifiant) {
        this.identifiant = pIdentifiant;
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
     * Retourne le nom du contrat.
     * @return nomContrat
     */
    public String getNomContrat() {
        return nomContrat;
    }

    /**
     * Définit le nom du contrat.
     * @param pNomContrat nom du contrat
     */
    public void setNomContrat(final String pNomContrat) {
        this.nomContrat = pNomContrat;
    }

    /**
     * Retourne le montant.
     * @return montant
     */
    public double getMontant() {
        return montant;
    }

    /**
     * Définit le montant.
     * @param pMontant montant du contrat
     */
    public void setMontant(final double pMontant) {
        this.montant = pMontant;
    }

    /**
     * Retourne une représentation textuelle du contrat.
     * @return chaîne de caractères
     */
    @Override
    public String toString() {
        return String.format(
                "Contrat #%s - %s (%.2f €)",
                identifiant != null ? identifiant : "N/A",
                nomContrat,
                montant
        );
    }
}
