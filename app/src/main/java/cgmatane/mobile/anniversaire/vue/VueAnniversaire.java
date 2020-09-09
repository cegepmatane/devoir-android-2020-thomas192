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
import cgmatane.mobile.anniversaire.donnee.BaseDeDonnees;
import cgmatane.mobile.anniversaire.modele.Anniversaire;

public class VueAnniversaire extends AppCompatActivity {

    protected ListView vueListeAnniversaire;
    protected List<Anniversaire> listeAnniversaire;
    protected AnniversaireDAO anniversaireDAO;

    protected Intent intentionNaviguerAjouterAnniversaire;
    protected Intent intentionNaviguerModifierAnniversaire;

    static final public int ACTIVITE_AJOUTER_ANNIVERSAIRE = 1;
    static final public int ACTIVITE_MODIFIER_ANNIVERSAIRE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_anniversaire);
        vueListeAnniversaire = (ListView)findViewById(R.id.VueListeAnniversaire);

        // Ce getInstance() doit se faire ici avant AnniversaireDAO.getInstance()
        BaseDeDonnees.getInstance(getApplicationContext());

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

                    @Override
                    public void onItemClick(AdapterView<?> parent, View vue,
                                            int positionDansAdapter, long positionItem) {

                        ListView vueListeAnniversaire = (ListView)vue.getParent();

                        @SuppressWarnings("unchecked")
                        HashMap<String,String> anniversaire =
                                (HashMap<String,String>)
                                        vueListeAnniversaire.getItemAtPosition((int)positionItem);

                        //startActivity(intentionNaviguerModifierAnniversaire);
                        intentionNaviguerModifierAnniversaire.putExtra("id", anniversaire.get("id"));
                        startActivityForResult(intentionNaviguerModifierAnniversaire, ACTIVITE_MODIFIER_ANNIVERSAIRE);
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
            case ACTIVITE_MODIFIER_ANNIVERSAIRE:
                afficherListeAnniversaire();
                break;
        }
    }

    public void afficherListeAnniversaire() {
        listeAnniversaire = anniversaireDAO.listerAnniversaire();

        List<HashMap<String, String>>listeAnniversairePourAfficher =
                new ArrayList<HashMap<String, String>>();

        for (Anniversaire a : listeAnniversaire) {
            listeAnniversairePourAfficher.add(a.obtenirAnniversairePourAfficher());
        }

        SimpleAdapter adapteur = new SimpleAdapter(
                this,
                listeAnniversairePourAfficher,
                android.R.layout.two_line_list_item,
                new String[] {"info", "decompte"},
                new int[] {android.R.id.text1, android.R.id.text2});

        vueListeAnniversaire.setAdapter(adapteur);
    }
}