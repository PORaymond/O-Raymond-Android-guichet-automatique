package com.collegerosemont.o_raymond_guichet_travail;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ATM extends AppCompatActivity {
    boolean period = false;
    int nbDigits = 0;
    private static final int RESULT_LOGIN_FAILED = 1;
    String username;
    String nip;

    private final Guichet monGuichet = new Guichet(5);

    private final Client socrate  = new Client("Socrate", "Socrate","Socrate1", 123451);
    private final Client platon  = new Client("Platon", "Platon","Platon1", 123452);
    private final Client aristote  = new Client("Aristote", "Aristote","Aristote1", 123453);
    private final Client seneque  = new Client("Seneque", "Seneque","Seneque1", 123454);
    private final Client epicure  = new Client("Epicure", "Epicure","Epicure1", 123455);





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        monGuichet.ajouterClient(socrate, 0);
        monGuichet.addCompteCheque(123451, 10000, 0);
        monGuichet.addCompteEpargne(123451,10000, 0, 0.12f);

        monGuichet.ajouterClient(platon, 1);
        monGuichet.addCompteCheque(123452, 20000, 1);
        monGuichet.addCompteEpargne(123452,20000, 1, 0.12f);

        monGuichet.ajouterClient(aristote, 2);
        monGuichet.addCompteCheque(123453, 10000, 2);
        monGuichet.addCompteEpargne(123453,10000, 2, 0.12f);

        monGuichet.ajouterClient(seneque, 3);
        monGuichet.addCompteCheque(123454, 10000, 3);
        monGuichet.addCompteEpargne(123454,10000, 3, 0.12f);

        monGuichet.ajouterClient(epicure, 4);
        monGuichet.addCompteCheque(123455, 10000, 4);
        monGuichet.addCompteEpargne(123455,10000, 4, 0.12f);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            username = extras.getString("username");
            nip = extras.getString("nip");
            int intNip = Integer.parseInt(nip);

            if(monGuichet.validerUtilisateur(username, intNip)){
                Toast.makeText(this, username, Toast.LENGTH_LONG ).show();
            }else{
                setResult(RESULT_LOGIN_FAILED);
                finish();
            }

        }

    }
    public void onClickLogout(View view) {
        finish();
    }
    public void onClick1(View view) { appendDigit("1"); }
    public void onClick2(View view) {
        appendDigit("2");
    }
    public void onClick3(View view) {
        appendDigit("3");
    }
    public void onClick4(View view) {
        appendDigit("4");
    }
    public void onClick5(View view) {
        appendDigit("5");
    }
    public void onClick6(View view) {
        appendDigit("6");
    }
    public void onClick7(View view) {
        appendDigit("7");
    }
    public void onClick8(View view) {
        appendDigit("8");
    }
    public void onClick9(View view) {
        appendDigit("9");
    }
    public void onClick0(View view) {
        appendDigit("0");
    }
    public void onClickC(View view) {
        EditText montant = findViewById(R.id.editTextMontant);
        montant.getText().clear();
        period = false;
        nbDigits = 0;
    }
    public void onClick_(View view) {
        EditText montant = findViewById(R.id.editTextMontant);
        if(!period){
            montant.append(".");
            period = true;
        }
    }
    public void appendDigit(String number){
        EditText montant = findViewById(R.id.editTextMontant);
        if(period) {
            if (nbDigits < 2) {
                montant.append(number);
                nbDigits++;
            }
        } else {
            montant.append(number);
        }
    }

    public void onClickSoumettre(View view) {
        String choixAction ="";
        String choixCompte = "";
        int leNip = Integer.parseInt(nip);
        RadioGroup radioGroupAction = findViewById(R.id.radioGroupAction);
        RadioGroup radioGroupCompte = findViewById(R.id.radioGroupCompte);
        RadioButton radioButtonChoixCompte = findViewById(radioGroupCompte.getCheckedRadioButtonId());
        RadioButton radioButtonChoixAction = findViewById(radioGroupAction.getCheckedRadioButtonId());

        EditText montant = findViewById(R.id.editTextMontant);
        float leMontant;
        float leSolde;
        if(!montant.getText().toString().equals("")){
            leMontant = Float.parseFloat(montant.getText().toString());
        } else {
            leMontant = 0;
        }



        if(radioButtonChoixAction != null){
            choixAction = radioButtonChoixAction.getText().toString();
        } else {
            Toast.makeText(ATM.this,"choisir une transaction", Toast.LENGTH_SHORT).show();
        }

        if(radioButtonChoixCompte != null ){

            choixCompte = radioButtonChoixCompte.getText().toString();

        } else {
            Toast.makeText(ATM.this,"choisir un compte", Toast.LENGTH_SHORT).show();
        }
        switch (choixAction) {
            case "Dépôt":
                if (choixCompte.equals("Épargne")) {
                    leSolde = monGuichet.depotEpargne(leNip, leMontant);
                } else {
                    leSolde =monGuichet.depotCheque(leNip, leMontant);
                }
                validerTransaction(leMontant, leSolde, choixCompte,"Dépôt");
                break;
            case "Retrait":
                if (choixCompte.equals("Épargne")) {
                    leSolde =monGuichet.retraitEpargne(leNip, leMontant);
                } else {
                    leSolde = monGuichet.retraitCheque(leNip, leMontant);
                }
                validerTransaction(leMontant, leSolde, choixCompte, "Retrait");

                break;
            case "Virement":
                if (choixCompte.equals("Épargne")) {
                    leSolde = monGuichet.retraitCheque(leNip, leMontant);
                    if( leSolde >= 0)
                    {
                        monGuichet.depotEpargne(leNip, leMontant);
                        Toast.makeText(ATM.this, "Virement de " + leMontant + " de votre compte Épargne vers votre compte Chèque", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(ATM.this, "Virement annulé, fond insuffisant", Toast.LENGTH_LONG).show();
                    }



                } else {
                    leSolde = monGuichet.retraitEpargne(leNip, leMontant);
                    if( leSolde >= 0)
                    {
                        monGuichet.depotCheque(leNip, leMontant);
                        Toast.makeText(ATM.this, "Virement de " + leMontant + " de votre compte Chèque vers votre compte Épargne", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(ATM.this, "Virement annulé, fond insuffisant", Toast.LENGTH_LONG).show();
                    }
                }
                break;
        }
    }
    public void validerTransaction(float montantTransaction, float solde, String compte, String transaction) {
        if (solde >= 0) {
            Toast.makeText(ATM.this, transaction + " de " + montantTransaction + " sur votre compte " + compte, Toast.LENGTH_SHORT).show();
            Toast.makeText(ATM.this, "Solde du compte " + compte + " : " + solde, Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(ATM.this, "Aucune opération n’a été porté a votre compte", Toast.LENGTH_SHORT).show();
            Toast.makeText(ATM.this, "Solde du compte " + compte + " : " +(-solde), Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickEtatComptes(View view) {
        TextView etatCheque = findViewById(R.id.txtEtatChequeID);
        TextView etatEpargne = findViewById(R.id.txtEtatEpargneID);
        String message = "Solde compte chèque :";
        int leNip = Integer.parseInt(nip);
        float solde = monGuichet.getSoldeEpargne(leNip);
        message = message + solde;
        etatEpargne.setText(message);

        solde = monGuichet.getSoldeCheque(leNip);
        message = "Solde compte épargne :" + solde;
        etatCheque.setText(message);
    }
}