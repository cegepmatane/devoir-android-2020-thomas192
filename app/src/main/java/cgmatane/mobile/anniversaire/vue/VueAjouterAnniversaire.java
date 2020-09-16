package cgmatane.mobile.anniversaire.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import cgmatane.mobile.anniversaire.R;
import cgmatane.mobile.anniversaire.donnee.AnniversaireDAO;
import cgmatane.mobile.anniversaire.modele.Anniversaire;

public class VueAjouterAnniversaire extends AppCompatActivity {

    protected EditText vueAjouterAnniversaireChampPrenomEtNom;
    protected EditText vueAjouterAnniversaireChampDateDeNaissance;
    protected AnniversaireDAO anniversaireDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_ajouter_anniversaire);

        Button vueAjouterAnniversaireActionAnnuler = (Button)findViewById(R.id.vueAjouterAnniversaireActionAnnuler);

        vueAjouterAnniversaireActionAnnuler.setOnClickListener(
                new View.OnClickListener() {

                    public void onClick(View arg0) {
                        naviguerRetourVueAnniversaire();
                    }
                }
        );

        vueAjouterAnniversaireChampPrenomEtNom = (EditText)findViewById(R.id.vueModifierAnniversaireChampPrenomEtNom);
        vueAjouterAnniversaireChampDateDeNaissance = (EditText)findViewById(R.id.vueModifierAnniversaireChampDateDeNaissance);

        Button vueAjouterAnniversaireActionAjouter = (Button)findViewById(R.id.vueModifierAnniversaireActionModifier);

        vueAjouterAnniversaireActionAjouter.setOnClickListener(
                new View.OnClickListener() {

                    public void onClick(View arg0) {
                        enregistrerAnniversaire();
                        naviguerRetourVueAnniversaire();
                    }
                }
        );
    }

    public void enregistrerAnniversaire() {
        /*
        HashMap<String, String> a;

        a = new HashMap<String, String>();
        a.put("prenomEtNom", vueAjouterAnniversaireChampPrenomEtNom.getText().toString());
        a.put("dateDeNaissance", vueAjouterAnniversaireChampDateDeNaissance.getText().toString());

        anniversaireDAO = anniversaireDAO.getInstance();
        anniversaireDAO.ajouterAnniversaire(a);
         */

        Anniversaire anniversaire = new Anniversaire(vueAjouterAnniversaireChampPrenomEtNom.getText().toString(),
                vueAjouterAnniversaireChampDateDeNaissance.getText().toString(), 0);

        anniversaireDAO = new AnniversaireDAO().getInstance();
        anniversaireDAO.ajouterAnniversaire(anniversaire);
    }

    public void naviguerRetourVueAnniversaire() {
        this.finish();
    }
}