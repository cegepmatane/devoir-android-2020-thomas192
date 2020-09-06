package cgmatane.mobile.anniversaire.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cgmatane.mobile.anniversaire.R;
import cgmatane.mobile.anniversaire.donnee.AnniversaireDAO;

public class VueAnniversaire extends AppCompatActivity {

    protected ListView vueListeAnniversaire;
    protected List<HashMap<String, String>> listeAnniversaire;
    protected AnniversaireDAO anniversaireDAO;

    protected Intent intentionNaviguerAjouterAnniversaire;
    protected Intent intentionNaviguerModifierAnniversaire;

    static final public int ACTIVITE_AJOUTER_ANNIVERSAIRE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_anniversaire);
        vueListeAnniversaire = (ListView)findViewById(R.id.VueListeAnniversaire);

        anniversaireDAO = AnniversaireDAO.getInstance();

        afficherListeAnniversaire();

        Button vueActionAjouterAnniversaire = (Button)findViewById(R.id.vueActionAjouterAnniversaire);

        intentionNaviguerAjouterAnniversaire = new Intent(this, VueAjouterAnniversaire.class);

        vueActionAjouterAnniversaire.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View arg0) {
                        startActivityForResult(intentionNaviguerAjouterAnniversaire, ACTIVITE_AJOUTER_ANNIVERSAIRE);
                    }
                }
        );

        intentionNaviguerModifierAnniversaire = new Intent(this, VueModifierAnniversaire.class);

        vueListeAnniversaire.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {

                    public void onItemClick(AdapterView<?> parent, View vue,
                                            int positionDansAdapter, long positionItem) {

                        ListView vueListeAnniversaire = (ListView)vue.getParent();

                        @SuppressWarnings("unchecked")
                        HashMap<String,String> anniversaire =
                                (HashMap<String,String>)
                                        vueListeAnniversaire.getItemAtPosition((int)positionItem);

                        startActivity(intentionNaviguerModifierAnniversaire);
                    }
                }
        );
    }

    public void onActivityResult(int activite, int resultat, Intent donnees) {
        super.onActivityResult(activite, resultat, donnees);
        switch (activite) {
            case ACTIVITE_AJOUTER_ANNIVERSAIRE:
                afficherListeAnniversaire();
                break;
        }
    }

    public void afficherListeAnniversaire() {
        listeAnniversaire = anniversaireDAO.listerAnniversaire();

        SimpleAdapter adapter = new SimpleAdapter(
                this,
                listeAnniversaire,
                android.R.layout.two_line_list_item,
                new String[] {"prenomEtNom", "dateDeNaissance"},
                new int[] {android.R.id.text1, android.R.id.text2});

        vueListeAnniversaire.setAdapter(adapter);
    }
}