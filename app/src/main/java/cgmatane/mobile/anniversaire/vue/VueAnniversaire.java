package cgmatane.mobile.anniversaire.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cgmatane.mobile.anniversaire.R;

public class VueAnniversaire extends AppCompatActivity {

    protected ListView VueListeAnniversaire;
    protected List<HashMap<String, String>> listeAnniversaire;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_anniversaire);
        VueListeAnniversaire = (ListView)findViewById(R.id.VueListeAnniversaire);

        listeAnniversaire = prepareListeAnniversaire();
    }

    private List<HashMap<String, String>> prepareListeAnniversaire() {
        List<HashMap<String, String>> listeAnniversaire = new ArrayList<HashMap<String, String>>();

        HashMap<String, String> anniversaire;

        anniversaire = new HashMap<String, String>();
        anniversaire.put("nom", "Thomas Saudemont");
        anniversaire.put("date", "22/02/2001");
        listeAnniversaire.add(anniversaire);

        return listeAnniversaire;
    }
}