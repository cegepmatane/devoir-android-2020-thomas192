package cgmatane.mobile.anniversaire.modele;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;

import javax.xml.datatype.Duration;

public class Anniversaire {
    protected String prenomEtNom;
    protected String dateDeNaissance;
    protected int id;

    public Anniversaire(String prenomEtNom, String dateDeNaissance, int id) {
        this.prenomEtNom = prenomEtNom;
        this.dateDeNaissance = dateDeNaissance;
        this.id = id;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public HashMap<String, String> obtenirAnniversairePourAfficher() {
        // Gestion de l'âge à la date de l'anniversaire
        // et décompte des jours avant le prochain anniversaire
        LocalDate dateDeNaissance = LocalDate.parse(this.dateDeNaissance);
        LocalDate dateActuelle = LocalDate.now();
        LocalDate dateAnniversaire = dateDeNaissance.withYear(dateActuelle.getYear());
        if (ChronoUnit.DAYS.between(dateActuelle, dateAnniversaire) < 0) {
            dateAnniversaire = dateDeNaissance.withYear(dateActuelle.getYear() + 1);
        }
        long joursRestants = ChronoUnit.DAYS.between(dateActuelle, dateAnniversaire);
        long age = ChronoUnit.YEARS.between(dateDeNaissance, dateActuelle);
        age++;

        HashMap<String, String> anniversairePourAfficher = new HashMap<String, String>();
        anniversairePourAfficher.put("info", this.prenomEtNom + " (" + this.dateDeNaissance + ")");
        anniversairePourAfficher.put("decompte",  age + " ans dans " + joursRestants + " jours");
        anniversairePourAfficher.put("id", "" + this.id);
        return anniversairePourAfficher;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrenomEtNom() {
        return prenomEtNom;
    }

    public void setPrenomEtNom(String prenomEtNom) {
        this.prenomEtNom = prenomEtNom;
    }

    public String getDateDeNaissance() {
        return dateDeNaissance;
    }

    public void setDateDeNaissance(String dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }
}
