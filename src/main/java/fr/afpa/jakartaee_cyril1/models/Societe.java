package fr.afpa.jakartaee_cyril1.models;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

/**
 * Représente une société dans le CRM.
 *
 * @author Cyril
 * @version 1.0
 */
public class Societe {

    /** Identifiant unique de la société. */
    private Integer identifiant;

    /** Raison sociale de la société. */
    @NotBlank(message = "La raison sociale est obligatoire")
    private String raisonSociale;

    /** Adresse de la société. */
    @Valid
    @NotNull(message = "L'adresse est obligatoire")
    private Adresse adresse;

    /** Numéro de téléphone. */
    @NotBlank(message = "Le numéro de téléphone est obligatoire")
    @Pattern(
            regexp = "\\d{10}",
            message = "Le téléphone doit contenir 10 chiffres"
    )
    private String telephone;

    /** Adresse email. */
    @NotBlank(message = "L'adresse email est obligatoire")
    @Email(message = "Format d'email invalide")
    private String adresseMail;

    /** Commentaires libres. */
    private String commentaires;

    /**
     * Constructeur vide obligatoire pour un JavaBean.
     */
    public Societe() {
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
     * @param pIdentifiant identifiant
     */
    public void setIdentifiant(final Integer pIdentifiant) {
        this.identifiant = pIdentifiant;
    }

    /**
     * Retourne la raison sociale.
     * @return raisonSociale
     */
    public String getRaisonSociale() {
        return raisonSociale;
    }

    /**
     * Définit la raison sociale.
     * @param pRaisonSociale raison sociale
     */
    public void setRaisonSociale(final String pRaisonSociale) {
        this.raisonSociale = pRaisonSociale;
    }

    /**
     * Retourne l'adresse.
     * @return adresse
     */
    public Adresse getAdresse() {
        return adresse;
    }

    /**
     * Définit l'adresse.
     * @param pAdresse adresse
     */
    public void setAdresse(final Adresse pAdresse) {
        this.adresse = pAdresse;
    }

    /**
     * Retourne le téléphone.
     * @return telephone
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * Définit le téléphone.
     * @param pTelephone téléphone
     */
    public void setTelephone(final String pTelephone) {
        this.telephone = pTelephone;
    }

    /**
     * Retourne l'adresse mail.
     * @return adresseMail
     */
    public String getAdresseMail() {
        return adresseMail;
    }

    /**
     * Définit l'adresse mail.
     * @param pAdresseMail adresse mail
     */
    public void setAdresseMail(final String pAdresseMail) {
        this.adresseMail = pAdresseMail;
    }

    /**
     * Retourne les commentaires.
     * @return commentaires
     */
    public String getCommentaires() {
        return commentaires;
    }

    /**
     * Définit les commentaires.
     * @param pCommentaires commentaires
     */
    public void setCommentaires(final String pCommentaires) {
        this.commentaires = pCommentaires;
    }

    /**
     * Retourne une représentation textuelle de la société.
     * @return chaîne de caractères
     */
    @Override
    public String toString() {
        return "ID: " + (identifiant != null ? identifiant : "N/A")
                + " | " + raisonSociale
                + " | " + adresse;
    }
}
