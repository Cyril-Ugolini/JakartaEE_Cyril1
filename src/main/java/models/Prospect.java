package models;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class Prospect extends Societe {

    @NotNull(message = "La date de prospection est obligatoire")
    private LocalDate dateProspection;

    @NotNull(message = "L'état d'intérêt est obligatoire")
    private Interesse interesse;

    public Prospect() {
        // Constructeur vide obligatoire pour un JavaBean
    }

    public LocalDate getDateProspection() {
        return dateProspection;
    }

    public void setDateProspection(LocalDate dateProspection) {
        this.dateProspection = dateProspection;
    }

    public Interesse getInteresse() {
        return interesse;
    }

    public void setInteresse(Interesse interesse) {
        this.interesse = interesse;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
                "Date prospection : " + dateProspection + "\n" +
                "Intéressé : " + interesse;
    }
}