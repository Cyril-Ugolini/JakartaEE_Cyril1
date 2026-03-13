package models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;


public class Client {

    @NotNull
    private Integer idClient;

    @Min(200)
    private long chiffreAffaires;

    @Min(1)
    private int nombreEmployes;

    public Client() {
        // Constructeur vide obligatoire pour un JavaBean
    }

    public Integer getIdClient() {
        return idClient;
    }

    public void setIdClient(Integer idClient) {
        this.idClient = idClient;
    }

    public long getChiffreAffaires() {
        return chiffreAffaires;
    }

    public void setChiffreAffaires(long chiffreAffaires) {
        this.chiffreAffaires = chiffreAffaires;
    }

    public int getNombreEmployes() {
        return nombreEmployes;
    }

    public void setNombreEmployes(int nombreEmployes) {
        this.nombreEmployes = nombreEmployes;
    }

    @Override
    public String toString() {
        return "Client ID : " + idClient + "\n" +
                "Chiffre d'affaires : " + chiffreAffaires + " €\n" +
                "Nombre d'employés : " + nombreEmployes;
    }
}