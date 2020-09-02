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

public class VueAnniversaire extends AppCompatActivity {

    protected ListView vueListeAnniversaire;
    protected List<HashMap<String, String>> listeAnniversaire;
    protected Intent intentionNaviguerAjouterAnniversaire;
    protected Intent intentionNaviguerModifierAnniversaire;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_anniversaire);
        vueListeAnniversaire = (ListView)findViewById(R.id.VueListeAnniversaire);

        listeAnniversaire = preparerListeAnniversaire();

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

    private List<HashMap<String, String>> preparerListeAnniversaire() {
        List<HashMap<String, String>> listeAnniversaire = new ArrayList<HashMap<String, String>>();

        HashMap<String, String> anniversaire;

        anniversaire = new HashMap<String, String>();
        anniversaire.put("prenomEtNom", "Elon Musk");
        anniversaire.put("dateDeNaissance", "1971-06-28");
        listeAnniversaire.add(anniversaire);

        anniversaire = new HashMap<String, String>();
        anniversaire.put("prenomEtNom", "Leonardo DiCaprio");
        anniversaire.put("dateDeNaissance", "1974-11-11");
        listeAnniversaire.add(anniversaire);

        anniversaire = new HashMap<String, String>();
        anniversaire.put("prenomEtNom", "Anthony Hopkins");
        anniversaire.put("dateDeNaissance", "1937-12-31");
        listeAnniversaire.add(anniversaire);

        return listeAnniversaire;
    }
}