package cgmatane.mobile.anniversaire.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cgmatane.mobile.anniversaire.R;

public class VueAnniversaire extends AppCompatActivity {

    protected ListView vueListeAnniversaire;
    protected List<HashMap<String, String>> listeAnniversaire;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_anniversaire);
        vueListeAnniversaire = (ListView)findViewById(R.id.VueListeAnniversaire);

        listeAnniversaire = prepareListeAnniversaire();

        SimpleAdapter adapter = new SimpleAdapter(
                this,
                listeAnniversaire,
                android.R.layout.two_line_list_item,
                new String[] {"nom", "date"},
                new int[] {android.R.id.text1, android.R.id.text2});

        vueListeAnniversaire.setAdapter(adapter);
    }

    private List<HashMap<String, String>> prepareListeAnniversaire() {
        List<HashMap<String, String>> listeAnniversaire = new ArrayList<HashMap<String, String>>();

        HashMap<String, String> anniversaire;

        anniversaire = new HashMap<String, String>();
        anniversaire.put("nom", "Thomas Saudemont");
        anniversaire.put("date", "22-02-2001");
        listeAnniversaire.add(anniversaire);

        return listeAnniversaire;
    }
}