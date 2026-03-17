package models;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * Représente un prospect dans le CRM.
 *
 * @author Cyril
 * @version 1.1
 */
public final class Prospect extends Societe {

    /** Identifiant unique du prospect. */
    @NotNull(message = "L'identifiant du prospect est obligatoire")
    private Integer idProspect;

    /** Date de prospection. */
    @NotNull(message = "La date de prospection est obligatoire")
    private LocalDate dateProspection;

    /** État d'intérêt du prospect. */
    @NotNull(message = "L'état d'intérêt est obligatoire")
    private Interesse interesse;

    /** Constructeur vide obligatoire pour un JavaBean. */
    public Prospect() {
        // Constructeur vide
    }

    // -------------------------
    // GETTERS / SETTERS
    // -------------------------

    public Integer getIdProspect() {
        return idProspect;
    }

    public void setIdProspect(final Integer pIdProspect) {
        this.idProspect = pIdProspect;
    }

    public LocalDate getDateProspection() {
        return dateProspection;
    }

    public void setDateProspection(final LocalDate pDateProspection) {
        this.dateProspection = pDateProspection;
    }

    public Interesse getInteresse() {
        return interesse;
    }

    public void setInteresse(final Interesse pInteresse) {
        this.interesse = pInteresse;
    }

    @Override
    public String toString() {
        return super.toString() + "\n"
                + "ID Prospect : " + idProspect + "\n"
                + "Date prospection : " + dateProspection + "\n"
                + "Intéressé : " + interesse;
    }
}
