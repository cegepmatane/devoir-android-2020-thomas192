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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_anniversaire);

        anniversaireDAO = AnniversaireDAO.getInstance();
        listeAnniversaire = anniversaireDAO.listerAnniversaire();

        vueListeAnniversaire = (ListView)findViewById(R.id.VueListeAnniversaire);

        SimpleAdapter adapter = new SimpleAdapter(
                this,
                listeAnniversaire,
                android.R.layout.two_line_list_item,
                new String[] {"prenomEtNom", "dateDeNaissance"},
                new int[] {android.R.id.text1, android.R.id.text2});

        vueListeAnniversaire.setAdapter(adapter);

        Button vueActionAjouterAnniversaire = (Button)findViewById(R.id.vueActionAjouterAnniversaire);

        intentionNaviguerAjouterAnniversaire = new Intent(this, VueAjouterAnniversaire.class);

        vueActionAjouterAnniversaire.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View arg0) {
                        startActivity(intentionNaviguerAjouterAnniversaire);
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

}