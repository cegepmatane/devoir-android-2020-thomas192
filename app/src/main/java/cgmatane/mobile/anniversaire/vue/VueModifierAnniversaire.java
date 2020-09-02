package cgmatane.mobile.anniversaire.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cgmatane.mobile.anniversaire.R;

public class VueModifierAnniversaire extends AppCompatActivity {

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
    }

    public void naviguerRetourVueAnniversaire() {
        this.finish();
    }

}