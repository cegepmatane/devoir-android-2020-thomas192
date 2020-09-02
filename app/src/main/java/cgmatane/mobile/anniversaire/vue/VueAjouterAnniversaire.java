package cgmatane.mobile.anniversaire.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cgmatane.mobile.anniversaire.R;

public class VueAjouterAnniversaire extends AppCompatActivity {

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
    }

    public void naviguerRetourVueAnniversaire() {
        this.finish();
    }
}