/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mmojsino_zadaca_2.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.TreeMap;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import mmojsino_zadaca_2.RankingNorm;
import mmojsino_zadaca_2.RonilackiKlubSingleton;
import mmojsino_zadaca_2.Ronioc;
import mmojsino_zadaca_2.Sastav;
import mmojsino_zadaca_2.Uron;

/**
 *
 * @author Mario
 */
public class AlgorithmLevel extends AlgorithmScuba {

    private List<Ronioc> listaOdabranih = null;

    //sto vise ronioca roni s partnerima iste razine
    @Override
    public TreeMap generirajPlan(Uron u) {
        TreeMap tm = new TreeMap();
        listaOdabranih = getSkupRonioca(u.getBrojRonioca());
        List<List<Ronioc>> filtriraniRonioci = new ArrayList<>();
        List<List<Ronioc>> filtriraniRoniociProfi = new ArrayList<>();
        List<Sastav> listSastav = new ArrayList<>();

        for (String a : RankingNorm.getRazineRekreacijske()) {
            filtriraniRonioci.add(listaOdabranih.stream()
                    .filter((x) -> a.equals(x.getRangNorm()))
                    .collect(Collectors.toList()));
        }
        for (String a : RankingNorm.getRazineProfi()) {
            filtriraniRoniociProfi.add(listaOdabranih.stream()
                    .filter((x) -> a.equals(x.getRangNorm()))
                    .collect(Collectors.toList()));
        }
        if (!filtriraniRonioci.isEmpty()) {
            listSastav = vratiSastavIsteRazine(filtriraniRonioci);
        }
        if (!filtriraniRoniociProfi.isEmpty()) {
            listSastav.addAll(vratiSastavIsteRazine(filtriraniRoniociProfi));
        }

        listSastav.addAll(vratiSastavOstali());

        float ukupno = 0;
        for (Sastav s : listSastav) {
            s.postaviDubinu(vratiDubinu(listSastav));
            List<Ronioc> list = s.getLista();
            list.sort((r1, r2) -> r1.getRang().compareTo(r2.getRang()));
            if (!list.isEmpty()) {
                ukupno += s.getDubina() / (float) pronadjiApsolutnuRazinu(list.get(0).getRang(), list.get(list.size() - 1).getRang());
            }
        }
        tm.put("Mjera", ukupno);
        tm.put("Lista", listSastav);
        return tm;
    }

    private List<Sastav> vratiSastavIsteRazine(List<List<Ronioc>> filtrirani) {
        List<Sastav> listSastav = new ArrayList<>();
        for (List<Ronioc> lista : filtrirani) {
            ListIterator<Ronioc> it = lista.listIterator();
            //provjera je li ronioc odredjene razine sam (tj. nema ronioca iste razine u skupu)
            if (lista.size() == 1) {
                continue;
            }
            for (Ronioc r : lista) {
                //brisanje iz odabranih kako bi ostali samo oni koji nemaju partnera po pitanju razine
                listaOdabranih.remove(r);
            }
            it.forEachRemaining(new Consumer<Ronioc>() {
                @Override
                public void accept(Ronioc n) {
                    while (it.hasNext()) {
                        if ((lista.size() % 2 == 0) && (lista.size() > 1)) {
                            //dodaj dva
                            final Sastav s = new Sastav();
                            s.postaviDubinu(pronadjiDubinu(Integer.parseInt(n.getRangNorm().substring(1, 2)), false));
                            Ronioc r = it.next();
                            s.dodajRoniocaSastav(r);
                            it.remove();
                            r = it.next();
                            s.dodajRoniocaSastav(r);
                            it.remove();
                            listSastav.add(s);
                        } else if ((lista.size() % 2 == 1) && (lista.size() > 1)) {
                            //dodaj 3
                            final Sastav s = new Sastav();
                            s.postaviDubinu(pronadjiDubinu(Integer.parseInt(n.getRangNorm().substring(1, 2)), false));
                            Ronioc r = it.next();
                            s.dodajRoniocaSastav(r);
                            it.remove();
                            r = it.next();
                            s.dodajRoniocaSastav(r);
                            it.remove();
                            r = it.next();
                            s.dodajRoniocaSastav(r);
                            it.remove();
                            listSastav.add(s);
                        }
                    }
                }
            });
        }
        return listSastav;
    }

    private List<Sastav> vratiSastavOstali() {
        ListIterator<Ronioc> listIt = listaOdabranih.listIterator();
        List<Sastav> list = new ArrayList<>();
        while (listIt.hasNext()) {
            if ((listaOdabranih.size() % 2 == 0) && (listaOdabranih.size() > 1)) {
                //dodaj dva
                //provjeriti ima li rekreacijskog u grupi, ako ima onda se prema njemu postaviti i dodati +10
                Sastav sas = new Sastav();
                Ronioc r = listIt.next();
                sas.dodajRoniocaSastav(r);
                r = listIt.next();
                sas.dodajRoniocaSastav(r);
                list.add(sas);

            } else if ((listaOdabranih.size() % 2 == 1) && (listaOdabranih.size() > 1)) {
                Sastav sas = new Sastav();
                Ronioc r = listIt.next();
                sas.dodajRoniocaSastav(r);
                r = listIt.next();
                sas.dodajRoniocaSastav(r);
                r = listIt.next();
                sas.dodajRoniocaSastav(r);
                list.add(sas);
            }
        }
        for (Sastav s : list) {
            s.postaviDubinu(vratiDubinu(list));
        }
        
        return list;
    }

    private int vratiDubinu(List<Sastav> sastav) {
        int indexRekr = 0;
        boolean rekreacijski = false;
        for (Sastav s : sastav) {
            List<Ronioc> lista = s.getLista();
            for (Ronioc r : lista) {
                if (Arrays.asList(RankingNorm.getRazineRekreacijske()).contains(r.getRangNorm())) {
                    if (lista.indexOf(r) > 0) {
                        if (indexRekr > -1 && (Integer.parseInt(lista.get(indexRekr).getRangNorm().substring(1, 2)))
                                < (Integer.parseInt(r.getRangNorm().substring(1, 2)))) {
                            rekreacijski = true;
                        } else {
                            indexRekr = lista.indexOf(r);
                            rekreacijski = true;

                        }
                    } else {
                        indexRekr = lista.indexOf(r);
                    }
                }
            }
        }
        if (!rekreacijski) {
            indexRekr = 3;
        }
        return pronadjiDubinu(indexRekr + 1, true);
    }
}
