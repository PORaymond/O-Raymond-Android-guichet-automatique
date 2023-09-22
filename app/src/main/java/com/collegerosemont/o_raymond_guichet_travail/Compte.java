package com.collegerosemont.o_raymond_guichet_travail;

public abstract class Compte {
    private int numeroNIP, numeroCompte;

    private float soldeCompte;

    Compte(int nip, int numeroCompte, float solde){
        this.numeroNIP = nip;
        this.numeroCompte = numeroCompte;
        this.soldeCompte = solde;
    }
    public void retrait(float montant){
        soldeCompte -= montant;
    }
    public  void depot(float montant){
        soldeCompte += montant;
    }

    public int getNumeroCompte() {
        return numeroCompte;
    }

    public void setNumeroCompte(int numeroCompte) {
        this.numeroCompte = numeroCompte;
    }

    public int getNumeroNIP() {
        return numeroNIP;
    }

    public void setNumeroNIP(int numeroNIP) {
        numeroNIP = numeroNIP;
    }

    public float getSoldeCompte() {
        return soldeCompte;
    }

    public void setSoldeCompte(float soldeCompte) {
        this.soldeCompte = soldeCompte;
    }
}
