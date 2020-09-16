package cgmatane.mobile.anniversaire.vue;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.CamcorderProfile;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import cgmatane.mobile.anniversaire.R;
import cgmatane.mobile.anniversaire.donnee.AnniversaireDAO;
import cgmatane.mobile.anniversaire.modele.Anniversaire;

import static android.app.AlarmManager.RTC;
import static cgmatane.mobile.anniversaire.vue.VueAnniversaire.ACTIVITE_ALARME_ANNIVERSAIRE;

public class VueAlarmeAnniversaire extends AppCompatActivity {

    protected TextView vueAlarmeAnniversaireTitre;
    protected CheckBox vueAlarmeAnniversaireCheckBoxRappel;
    protected EditText vueAlarmeAnniversaireChampMinutes;

    protected AnniversaireDAO anniversaireDAO;
    protected Anniversaire anniversaire;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_alarme_anniversaire);

        Bundle parametres = this.getIntent().getExtras();
        int id = (int)parametres.get("id");
        anniversaireDAO = AnniversaireDAO.getInstance();
        anniversaire = anniversaireDAO.chercherAnniversaireParId(id);

        vueAlarmeAnniversaireTitre = (TextView)findViewById(R.id.vueAlarmeAnniversaireTitre);
        vueAlarmeAnniversaireTitre.setText("C'est l'anniversaire de "+anniversaire.getPrenomEtNom()+" aujourd'hui !");
        vueAlarmeAnniversaireCheckBoxRappel = (CheckBox)findViewById(R.id.vueAlarmeAnniversaireCheckBoxRappel);
        vueAlarmeAnniversaireChampMinutes = (EditText)findViewById(R.id.vueAlarmeAnniversaireChampMinutes);

        Button vueAlarmeAnniversaireActionConfirmer = (Button)findViewById(R.id.vueAlarmeAnniversaireActionConfirmer);

        vueAlarmeAnniversaireActionConfirmer.setOnClickListener(
                new View.OnClickListener() {

                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                    public void onClick(View arg0) {
                        reportAlarme();
                        naviguerRetourVueAnniversaire();
                    }
                }
        );
}

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void reportAlarme() {
        if (vueAlarmeAnniversaireCheckBoxRappel.isChecked()) {
            int minutesAvantProchaineAlarme = Integer.parseInt(vueAlarmeAnniversaireChampMinutes.getText().toString());

            Intent intentionNaviguerAlarmeAnniversaire = new Intent(this, VueAlarmeAnniversaire.class);
            intentionNaviguerAlarmeAnniversaire.putExtra("id", anniversaire.getId());

            PendingIntent intentionEnAttente = PendingIntent.getActivity(this, ACTIVITE_ALARME_ANNIVERSAIRE,
                    intentionNaviguerAlarmeAnniversaire, 0);

            AlarmManager alarmeManager = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
            Calendar calendrier = Calendar.getInstance();
            calendrier.setTimeInMillis(System.currentTimeMillis());
            calendrier.add(Calendar.MINUTE, minutesAvantProchaineAlarme);

            alarmeManager.set(AlarmManager.RTC_WAKEUP,
                    calendrier.getTimeInMillis(),
                    intentionEnAttente);

            System.out.println(alarmeManager);
        }
    }

    public void naviguerRetourVueAnniversaire() {
        this.finish();
    }
}
