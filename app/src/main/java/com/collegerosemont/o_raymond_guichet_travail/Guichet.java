package com.collegerosemont.o_raymond_guichet_travail;

public class Guichet {


    public static int getProchainNoCheque() {
        return prochainNoCheque;
    }

    public static void incProchainNoCheque() {
        prochainNoCheque++;
    }

    public static int getProchainNoEpargne() {
        return prochainNoEpargne;
    }
    public static void incProchainNoEpargne() {
        prochainNoEpargne++;
    }

    static int prochainNoCheque = 100000;
    static int prochainNoEpargne = 200000;
    private final Client[] clients;
    private final Cheque[] comptesCheques;
    private final Epargne[] comptesEpargne;

    Guichet(int nbClients){
        clients = new Client[nbClients];
        comptesCheques = new Cheque[nbClients];
        comptesEpargne = new Epargne[nbClients];
        //FIXME section temp
        for (int i = 0; i < nbClients; i++) {
            clients[i] = new Client();
        }

    }
    public void ajouterClient(Client nouveauClient, int indice){
        clients[indice] = new Client(nouveauClient);
    }

    public boolean validerUtilisateur(String username, int nip){
        boolean clientTrouve = false;
        int indiceClientTrouve = -1;
        for (int i = 0; i < clients.length; i++) {
            if(this.clients[i].getUsername().equals(username)){
                clientTrouve = true;
                indiceClientTrouve = i;
                break;
            }
        }
        if (!clientTrouve){
            return false;
        }
        return this.clients[indiceClientTrouve].getNumeroNIP() == nip;

    }
    public float retraitCheque(int nip, float montant){
        int indice;
        float solde;

        indice = trouverCompte(nip, comptesCheques);
        solde = comptesCheques[indice].getSoldeCompte();
        if (indice > -1){
            if(montant > solde){
                return -solde;
            } else {
                this.comptesCheques[indice].retrait(montant);
                solde = comptesCheques[indice].getSoldeCompte();
                return solde;
            }
        }
        return -solde; //TODO
    }
    public float retraitEpargne(int nip, float montant){
        int indice;
        float solde;

        indice = trouverCompte(nip,comptesEpargne);
        solde = comptesCheques[indice].getSoldeCompte();
        if (indice > -1){
            if(montant > comptesCheques[indice].getSoldeCompte()){
                return -solde;
            } else {
                this.comptesEpargne[indice].retrait(montant);
                solde = comptesEpargne[indice].getSoldeCompte();
                return solde;
            }
        } else {
            return -solde;
        }


    }

    public float depotCheque(int nip, float montant){
        int indice;
        float solde;
        indice = trouverCompte(nip, comptesCheques);
        solde = comptesCheques[indice].getSoldeCompte();
        if (indice > -1){

                this.comptesCheques[indice].depot(montant);
                solde = comptesCheques[indice].getSoldeCompte();
                return solde;

        } else {
            return -solde;
        }

    }
    public float depotEpargne(int nip, float montant){
        int indice;
        float solde;
        indice = trouverCompte(nip, comptesEpargne);
        solde = comptesEpargne[indice].getSoldeCompte();
        if (indice > -1){
                this.comptesEpargne[indice].depot(montant);
                solde = comptesEpargne[indice].getSoldeCompte();
                return solde;

        } return -solde;
    }
    public void addCompteCheque(int nip, int solde, int indice){
        this.comptesCheques[indice] = new Cheque(nip, getProchainNoCheque(), solde);
        incProchainNoCheque();
    }

    public void addCompteEpargne(int nip, int solde, int indice, float tauxInteret){
        this.comptesEpargne[indice] = new Epargne(nip, getProchainNoEpargne(), solde, tauxInteret);
        incProchainNoEpargne();
    }
    public int trouverCompte(int nip, Compte[] leCompte) {
        int indice = -1;

        for (int i = 0; i < leCompte.length; i++) {
            if (leCompte[i].getNumeroNIP() == nip) {
                indice = i;

                break;
            }
        }

        return indice;
    }
    public float getSoldeEpargne(int leNip){
        int indice = trouverCompte(leNip, comptesEpargne);
        return this.comptesEpargne[indice].getSoldeCompte();
    }


    public float getSoldeCheque(int leNip) {
        int indice = trouverCompte(leNip, comptesCheques);
        return this.comptesCheques[indice].getSoldeCompte();
    }
}
