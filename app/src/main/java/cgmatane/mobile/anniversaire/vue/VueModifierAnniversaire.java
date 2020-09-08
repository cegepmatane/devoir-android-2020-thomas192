package cgmatane.mobile.anniversaire.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import cgmatane.mobile.anniversaire.R;
import cgmatane.mobile.anniversaire.donnee.AnniversaireDAO;
import cgmatane.mobile.anniversaire.modele.Anniversaire;

public class VueModifierAnniversaire extends AppCompatActivity {

    protected EditText vueModifierAnniversaireChampPrenomEtNom;
    protected EditText vueModifierAnniversaireChampDateDeNaissance;
    protected AnniversaireDAO anniversaireDAO;
    protected Anniversaire anniversaire;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_modifier_anniversaire);

        Button vueModifierAnniversaireActionAnnuler = (Button)findViewById(R.id.vueModifierAnniversaireActionAnnuler);

        vueModifierAnniversaireActionAnnuler.setOnClickListener(
                new View.OnClickListener() {

                    public void onClick(View arg0) {
                        naviguerRetourVueAnniversaire();
                    }
                }
        );

        Bundle parametres = this.getIntent().getExtras();
        String idParametre = (String) parametres.get("id");
        int id = Integer.parseInt(idParametre);
        anniversaireDAO = anniversaireDAO.getInstance();
        anniversaire = anniversaireDAO.chercherAnniversaireParId(id);

        vueModifierAnniversaireChampPrenomEtNom = (EditText)findViewById(R.id.vueModifierAnniversaireChampPrenomEtNom);
        vueModifierAnniversaireChampDateDeNaissance = (EditText)findViewById(R.id.vueModifierAnniversaireChampDateDeNaissance);
        vueModifierAnniversaireChampPrenomEtNom.setText(anniversaire.getPrenomEtNom());
        vueModifierAnniversaireChampDateDeNaissance.setText(anniversaire.getDateDeNaissance());

        Button vueModifierAnniversaireActionModifier = (Button)findViewById(R.id.vueModifierAnniversaireActionModifier);

        vueModifierAnniversaireActionModifier.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View arg0) {
                        enregistrerAnniversaire();
                        naviguerRetourVueAnniversaire();
                    }
                }
        );
    }

    private void enregistrerAnniversaire() {
        anniversaire.setPrenomEtNom(vueModifierAnniversaireChampPrenomEtNom.getText().toString());
        anniversaire.setDateDeNaissance(vueModifierAnniversaireChampDateDeNaissance.getText().toString());

        anniversaireDAO.modifierAnniversaire(anniversaire);
    }

    public void naviguerRetourVueAnniversaire() {
        this.finish();
    }

}