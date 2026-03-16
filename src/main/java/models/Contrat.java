package models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Contrat {

    @NotNull(message = "L'identifiant du contrat est obligatoire")
    private Integer identifiant;

    @NotNull(message = "L'identifiant du client est obligatoire")
    private Integer idClient;

    @NotBlank(message = "Le nom du contrat est obligatoire")
    private String nomContrat;

    @Min(value = 1, message = "Le montant doit être strictement positif")
    private double montant;

    public Contrat() {
        // Constructeur vide obligatoire pour un JavaBean
    }

    public Integer getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(Integer identifiant) {
        this.identifiant = identifiant;
    }

    public Integer getIdClient() {
        return idClient;
    }

    public void setIdClient(Integer idClient) {
        this.idClient = idClient;
    }

    public String getNomContrat() {
        return nomContrat;
    }

    public void setNomContrat(String nomContrat) {
        this.nomContrat = nomContrat;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

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