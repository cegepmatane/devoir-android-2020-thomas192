package cgmatane.mobile.anniversaire.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.HashMap;
import java.util.List;

import cgmatane.mobile.anniversaire.R;

public class VueAnniversaire extends AppCompatActivity {

    protected ListView VueListeAnniversaire;
    protected List<HashMap<String,String>> listeAnniversaire;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_anniversaire);
        VueListeAnniversaire = (ListView)findViewById(R.id.VueListeAnniversaire);
    }
}