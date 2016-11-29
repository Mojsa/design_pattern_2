/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mmojsino_zadaca_2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import mmojsino_zadaca_2.agencije.Agency;
import mmojsino_zadaca_2.agencije.AgencyFactory;
import mmojsino_zadaca_2.institucije.Institution;
import mmojsino_zadaca_2.institucije.InstitutionFactory;

/**
 *
 * @author Mario
 */
public class RonilackiKlubSingleton {

    private List<Ronioc> listRonioca = new ArrayList<>();
    private List<Uron> listUroni = new ArrayList<>();
    private Random generator;

    private RonilackiKlubSingleton() {

    }

    public void setGenerator(int seed) {
        generator = new Random(seed);
    }

    public Random getGenerator() {
        return generator;
    }

    public List<Ronioc> getListRonioca() {
        return listRonioca;
    }

    public List<Uron> getListUroni() {
        return listUroni;
    }

    public void dodajRonioca(Ronioc r) {
        listRonioca.add(r);
    }

    public void dodajUron(Uron u) {
        listUroni.add(u);
    }

    public void setListUroni(List<Uron> listUroni) {
        this.listUroni = listUroni;
    }

    private static class SingletonHelper {

        private static final RonilackiKlubSingleton instance = new RonilackiKlubSingleton();

    }

    public static RonilackiKlubSingleton getInstance() {
        return SingletonHelper.instance;
    }

    public void notifyObservers(Uron u, Sastav s, Ronioc ronioc) {
        Institution institution = InstitutionFactory.getInstitution("HRS");
        Agency a = new AgencyFactory().getAgency(ronioc.getAgencija());
        u.register(ronioc);
        ronioc.setSubject(u);
        String poruka = ronioc.getImeRonioca() + " roni dana: " + u.getDatum()
                + ", u vrijeme: " + u.getVrijeme() + " maks. dubina: "
                + u.getMaxDubina() + " zajedno s: ";
        String poruka2 = "Ime -> " + ronioc.getImeRonioca() + ", Rang -> " + ronioc.getRang() + "/" + ronioc.getCertifikat()
                + ", Datum -> " + u.getDatum() + "/" + u.getVrijeme() + ", Dubina -> " + s.getDubina();
        ;
        for (Ronioc partner : s.getLista()) {
            if (!ronioc.equals(partner)) {
                poruka += partner.getImeRonioca() + " ";
            }
        }
        u.postMessage(poruka);
        u.unregister(ronioc);//samo Ronioc dobiva tu poruku;
        a.setSubject(u);
        u.register(a);
        institution.setSubject(u);
        u.register(institution);
        u.postMessage(poruka2);
        // ne ispisuju se sve poruke nakon sto dodje nova
        u.unregister(a); 
        u.unregister(institution);
    }

}
