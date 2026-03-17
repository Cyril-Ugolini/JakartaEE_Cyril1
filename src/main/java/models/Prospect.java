package models;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * Représente un prospect dans le CRM.
 *
 * @author Cyril
 * @version 1.0
 */
public final class Prospect extends Societe {

    /** Date de prospection. */
    @NotNull(message = "La date de prospection est obligatoire")
    private LocalDate dateProspection;

    /** État d'intérêt du prospect. */
    @NotNull(message = "L'état d'intérêt est obligatoire")
    private Interesse interesse;

    /**
     * Constructeur vide obligatoire pour un JavaBean.
     */
    public Prospect() {
        // Constructeur vide
    }

    /**
     * Retourne la date de prospection.
     * @return dateProspection
     */
    public LocalDate getDateProspection() {
        return dateProspection;
    }

    /**
     * Définit la date de prospection.
     * @param pDateProspection date de prospection
     */
    public void setDateProspection(final LocalDate pDateProspection) {
        this.dateProspection = pDateProspection;
    }

    /**
     * Retourne l'état d'intérêt.
     * @return interesse
     */
    public Interesse getInteresse() {
        return interesse;
    }

    /**
     * Définit l'état d'intérêt.
     * @param pInteresse état d'intérêt
     */
    public void setInteresse(final Interesse pInteresse) {
        this.interesse = pInteresse;
    }

    /**
     * Retourne une représentation textuelle du prospect.
     * @return chaîne de caractères
     */
    @Override
    public String toString() {
        return super.toString() + "\n"
                + "Date prospection : " + dateProspection + "\n"
                + "Intéressé : " + interesse;
    }
}
