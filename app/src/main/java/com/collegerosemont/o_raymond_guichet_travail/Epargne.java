package com.collegerosemont.o_raymond_guichet_travail;

public class Epargne extends Compte{
    final private float tauxInteret;



    Epargne(int nip, int numeroCompte, float solde, float tauxInteret) {
        super(nip, numeroCompte, solde);
        this.tauxInteret = tauxInteret;
    }

    public void paiementInterets(){
        super.setSoldeCompte( this.getSoldeCompte() * (1 + tauxInteret));
    }
}
