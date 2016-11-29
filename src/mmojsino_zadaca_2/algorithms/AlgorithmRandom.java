/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mmojsino_zadaca_2.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;
import java.util.TreeMap;
import mmojsino_zadaca_2.RankingNorm;
import mmojsino_zadaca_2.Ronioc;
import mmojsino_zadaca_2.Sastav;
import mmojsino_zadaca_2.Uron;

/**
 *
 * @author Mario
 */
public class AlgorithmRandom extends AlgorithmScuba {

    //slucajno
    @Override
    public TreeMap generirajPlan(Uron u) {
        TreeMap tm = new TreeMap();
        List<Ronioc> listaOdabranih = getSkupRonioca(u.getBrojRonioca());
        List<Sastav> list = new ArrayList<>();
        Collections.shuffle(listaOdabranih);
        List<Ronioc> kopija = new ArrayList<>();
        kopija.addAll(listaOdabranih);
        ListIterator<Ronioc> listIt = kopija.listIterator();
        if (listaOdabranih.size() < 2) {
            return null;
        }
        Sastav sas = new Sastav();
        while (true) {
            if (kopija.size() == 0) {
                break;
            }
            if (kopija.size() % 2 == 0) {
                sas = new Sastav();
                sas.dodajRoniocaSastav(listIt.next());
                listIt.remove();
                sas.dodajRoniocaSastav(listIt.next());
                listIt.remove();
                list.add(sas);
            }
            if (kopija.size() % 2 == 1) {
                sas = new Sastav();
                sas.dodajRoniocaSastav(listIt.next());
                listIt.remove();
                sas.dodajRoniocaSastav(listIt.next());
                listIt.remove();
                sas.dodajRoniocaSastav(listIt.next());
                listIt.remove();
                list.add(sas);
            }
        }
        float ukupno = 0;
        for (Sastav s : list) {
            s.postaviDubinu(vratiDubinu(s));
            List<Ronioc> listR = s.getLista();
            listR.sort((r1, r2) -> r1.getRang().compareTo(r2.getRang()));
            if (!listR.isEmpty()) {
                ukupno += s.getDubina() / (float) pronadjiApsolutnuRazinu(listR.get(0).getRang(), listR.get(listR.size() - 1).getRang());
            }
        }
        tm.put("Mjera", ukupno);
        tm.put("Lista", list);
        return tm;
    }

    private int vratiDubinu(Sastav sastav) {
        int index = 10;
        String najmanjaRazina = "";
        ListIterator<Ronioc> it = sastav.getLista().listIterator();
        boolean imaVise = false;
        boolean imaProfi = false;
        while (it.hasNext()) {
            Ronioc r = it.next();
            if (Arrays.asList(RankingNorm.getRazineRekreacijske()).contains(r.getRangNorm())) {
                if (Integer.parseInt(r.getRangNorm().substring(1, 2)) < index) {
                    index = Integer.parseInt(r.getRangNorm().substring(1, 2));
                    najmanjaRazina = r.getRangNorm();
                    imaVise = true;

                } else {
                    imaVise = true;
                }
            } else if (Arrays.asList(RankingNorm.getRazineProfi()).contains(r.getRangNorm())) {
                imaProfi = true;
            }

        }
        if (najmanjaRazina.equals("") && imaProfi) {
            return 40;
        }
        return pronadjiDubinu(Integer.parseInt(najmanjaRazina.substring(1, 2)), imaVise);
    }

}
