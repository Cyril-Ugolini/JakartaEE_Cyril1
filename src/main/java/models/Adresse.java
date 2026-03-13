package models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Adresse {

    @NotNull(message = "L'identifiant de l'adresse est obligatoire")
    private Integer idAdresse;

    @NotBlank(message = "Le numéro de rue est obligatoire")
    @Size(max = 10, message = "Le numéro de rue ne doit pas dépasser 10 caractères")
    private String numeroRue;

    @NotBlank(message = "Le nom de rue est obligatoire")
    @Size(max = 100, message = "Le nom de rue ne doit pas dépasser 100 caractères")
    private String nomRue;

    @NotBlank(message = "Le code postal est obligatoire")
    @Pattern(regexp = "\\d{5}", message = "Le code postal doit contenir 5 chiffres")
    private String codePostal;

    @NotBlank(message = "La ville est obligatoire")
    @Size(max = 100, message = "La ville ne doit pas dépasser 100 caractères")
    private String ville;

    public Adresse() {
        // Constructeur vide obligatoire pour un JavaBean
    }

    public Integer getIdAdresse() {
        return idAdresse;
    }

    public void setIdAdresse(Integer idAdresse) {
        this.idAdresse = idAdresse;
    }

    public String getNumeroRue() {
        return numeroRue;
    }

    public void setNumeroRue(String numeroRue) {
        this.numeroRue = numeroRue;
    }

    public String getNomRue() {
        return nomRue;
    }

    public void setNomRue(String nomRue) {
        this.nomRue = nomRue;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    @Override
    public String toString() {
        return numeroRue + " " + nomRue + ", " + codePostal + " " + ville;
    }
}