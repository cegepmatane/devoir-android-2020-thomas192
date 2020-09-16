package cgmatane.mobile.anniversaire.modele;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;

public class Anniversaire implements Comparable<Anniversaire> {
    protected String prenomEtNom;
    protected String dateDeNaissance;
    protected int id;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public int compareTo(Anniversaire anniversaire) {
        // On trie les anniversaires selon le nombre de jours restants avant la date de
        // l'anniversaire dans l'ordre croissant
        LocalDate dateDeNaissance1 = LocalDate.parse(this.getDateDeNaissance());
        LocalDate dateDeNaissance2 = LocalDate.parse(anniversaire.getDateDeNaissance());
        LocalDate dateActuelle = LocalDate.now();
        LocalDate dateAnniversaire1 = dateDeNaissance1.withYear(dateActuelle.getYear());
        LocalDate dateAnniversaire2 = dateDeNaissance2.withYear(dateActuelle.getYear());
        if (ChronoUnit.DAYS.between(dateActuelle, dateAnniversaire1) < 0) {
            dateAnniversaire1 = dateDeNaissance1.withYear(dateActuelle.getYear() + 1);
        }
        if (ChronoUnit.DAYS.between(dateActuelle, dateAnniversaire2) < 0) {
            dateAnniversaire2 = dateDeNaissance2.withYear(dateActuelle.getYear() + 1);
        }

        return (int)ChronoUnit.DAYS.between(dateAnniversaire2, dateAnniversaire1);
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
        anniversairePourAfficher.put("information", this.prenomEtNom + " (" + this.dateDeNaissance + ")");
        anniversairePourAfficher.put("decompte",  age + " ans dans " + joursRestants + " jours");
        anniversairePourAfficher.put("id", "" + this.id);
        return anniversairePourAfficher;
    }

    public Anniversaire(String prenomEtNom, String dateDeNaissance, int id) {
        this.prenomEtNom = prenomEtNom;
        this.dateDeNaissance = dateDeNaissance;
        this.id = id;
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
