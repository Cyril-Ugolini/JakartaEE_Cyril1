package models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * Représente une adresse dans le CRM.
 *
 * @author Cyril
 * @version 1.0
 */
public final class Adresse {

    /** Taille maximale du numéro de rue. */
    private static final int MAX_NUMERO_RUE = 10;

    /** Taille maximale du nom de rue et de la ville. */
    private static final int MAX_NOM = 100;

    /** Identifiant unique de l'adresse. */
    private Integer idAdresse;

    /** Numéro de rue. */
    @NotBlank(message = "Le numéro de rue est obligatoire")
    @Size(
            max = MAX_NUMERO_RUE,
            message = "Le numéro de rue ne doit pas dépasser 10 caractères"
    )
    private String numeroRue;

    /** Nom de la rue. */
    @NotBlank(message = "Le nom de rue est obligatoire")
    @Size(
            max = MAX_NOM,
            message = "Le nom de rue ne doit pas dépasser 100 caractères"
    )
    private String nomRue;

    /** Code postal. */
    @NotBlank(message = "Le code postal est obligatoire")
    @Pattern(
            regexp = "\\d{5}",
            message = "Le code postal doit contenir 5 chiffres"
    )
    private String codePostal;

    /** Ville. */
    @NotBlank(message = "La ville est obligatoire")
    @Size(
            max = MAX_NOM,
            message = "La ville ne doit pas dépasser 100 caractères"
    )
    private String ville;

    /**
     * Constructeur vide obligatoire pour un JavaBean.
     */
    public Adresse() {
        // Constructeur vide
    }

    /**
     * Retourne l'identifiant de l'adresse.
     * @return idAdresse
     */
    public Integer getIdAdresse() {
        return idAdresse;
    }

    /**
     * Définit l'identifiant de l'adresse.
     * @param pIdAdresse identifiant de l'adresse
     */
    public void setIdAdresse(final Integer pIdAdresse) {
        this.idAdresse = pIdAdresse;
    }

    /**
     * Retourne le numéro de rue.
     * @return numeroRue
     */
    public String getNumeroRue() {
        return numeroRue;
    }

    /**
     * Définit le numéro de rue.
     * @param pNumeroRue numéro de rue
     */
    public void setNumeroRue(final String pNumeroRue) {
        this.numeroRue = pNumeroRue;
    }

    /**
     * Retourne le nom de la rue.
     * @return nomRue
     */
    public String getNomRue() {
        return nomRue;
    }

    /**
     * Définit le nom de la rue.
     * @param pNomRue nom de la rue
     */
    public void setNomRue(final String pNomRue) {
        this.nomRue = pNomRue;
    }

    /**
     * Retourne le code postal.
     * @return codePostal
     */
    public String getCodePostal() {
        return codePostal;
    }

    /**
     * Définit le code postal.
     * @param pCodePostal code postal
     */
    public void setCodePostal(final String pCodePostal) {
        this.codePostal = pCodePostal;
    }

    /**
     * Retourne la ville.
     * @return ville
     */
    public String getVille() {
        return ville;
    }

    /**
     * Définit la ville.
     * @param pVille ville
     */
    public void setVille(final String pVille) {
        this.ville = pVille;
    }

    /**
     * Retourne une représentation textuelle de l'adresse.
     * @return chaîne de caractères
     */
    @Override
    public String toString() {
        return numeroRue + " " + nomRue
                + ", " + codePostal + " " + ville;
    }
}
