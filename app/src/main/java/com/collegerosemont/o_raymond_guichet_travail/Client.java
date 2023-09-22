package com.collegerosemont.o_raymond_guichet_travail;

public class Client {
    private String nom, prenom, username;
    private int numeroNIP;

    public Client(Client nouveauClient) {
        this.nom = nouveauClient.nom;
        this.prenom = nouveauClient.prenom;
        this.username = nouveauClient.username;
        this.numeroNIP = nouveauClient.numeroNIP;

    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getNumeroNIP() {
        return numeroNIP;
    }

    public void setNumeroNIP(int numeroNIP) {
        this.numeroNIP = numeroNIP;
    }

    Client() {
        this("Olivier","Olivier","gert_rude", 112233);
    }
    Client(String nom, String prenom, String username, int numeroNIP){
        this.nom = nom;
        this.prenom = prenom;
        this.username = username;
        this.numeroNIP = numeroNIP;
    }
}
