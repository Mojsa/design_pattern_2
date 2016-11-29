/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mmojsino_zadaca_2;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import mmojsino_zadaca_2.algorithms.*;

/**
 *
 * @author Mario
 */
public class Mmojsino_zadaca_2 {

    private static RonilackiKlubSingleton instance = RonilackiKlubSingleton.getInstance();
    private static FileManager fr = new FileManager();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            AlgorithmScuba algorithm1, algorithm2, algorithm3;
            if (args.length == 7) {
                if (Integer.parseInt(args[0]) < 100) {
                    System.out.println("Sjeme za generator mora imati minimalno 3 znamenke.");
                    return;
                }
                instance.setGenerator(Integer.parseInt(args[0]));
                if (args[3].equals(args[4]) || args[3].equals(args[5]) || args[4].equals(args[5])) {
                    System.out.println("2-3 ista algoritma, nije ispravan unos.");
                    return;
                }
                algorithm1 = AlgorithmFactory.getAlgorithm(args[3]);
                algorithm2 = AlgorithmFactory.getAlgorithm(args[4]);
                algorithm3 = AlgorithmFactory.getAlgorithm(args[5]);

            } else {
                System.out.println("Provjerite upisane argumente!");
                return;
            }
            String workingDirectory = Paths.get(".").toAbsolutePath().normalize().toString();
            fr.openFile(workingDirectory + "\\" + args[1]);
            fr.openWriteFile(workingDirectory + "\\" + args[6]);
            fr.openFile(workingDirectory + "\\" + args[2]);

            List<Uron> listaUrona2 = new ArrayList<>();
            TreeMap tm1 = new TreeMap();
            TreeMap tm2 = new TreeMap();
            TreeMap tm3 = new TreeMap();
            TreeMap tm;
            String lowestSecurity = "";
            List<Sastav> sastav = new ArrayList<>();
            RoniocVisitor visitor = new RoniocVisitor();
            for (Uron u : instance.getListUroni()) {
                if (u.getBrojRonioca() > 1) {
                    u.accept(visitor);
                    //visitor.visit(u);
                    tm = new TreeMap();
                    tm1 = algorithm1.generirajPlan(u);
                    tm2 = algorithm2.generirajPlan(u);
                    tm3 = algorithm3.generirajPlan(u);
                    tm.put(args[3], tm1.get("Mjera"));
                    tm.put(args[4], tm2.get("Mjera"));
                    tm.put(args[5], tm3.get("Mjera"));
                    lowestSecurity = algorithm1.getMinKey(tm);
                    if (lowestSecurity.equalsIgnoreCase(args[3])) {
                        sastav = (List<Sastav>) tm1.get("Lista");
                    } else if (lowestSecurity.equalsIgnoreCase(args[4])) {
                        sastav = (List<Sastav>) tm2.get("Lista");
                    } else if (lowestSecurity.equalsIgnoreCase(args[5])) {
                        sastav = (List<Sastav>) tm3.get("Lista");
                    }
                    listaUrona2.add(new Uron.UronBuilder(u.getDatum(), u.getVrijeme(), u.getMaxDubina(), u.getBrojRonioca())
                            .postaviGrupu(sastav)
                            .postaviMjeruSigurnosti(tm)
                            .build());
                    for (Sastav s : sastav) {
                        for (Ronioc ronioc : s.getLista()) {
                            instance.notifyObservers(u, s, ronioc);
                            ronioc.accept(visitor);
                            //visitor.visit(ronioc);
                        }
                    }
                    System.out.println("");
                } else {
                    System.out.println("Broj ronioca urona nije veci od 1!");
                }
            }
            instance.setListUroni(listaUrona2);
            fr.podaciRoniocTablica();
            fr.podaciUronStatistika();
            System.out.println("Prosjecne godine: " + visitor.getAverageDiverAge());
            System.out.println("Prosjecan broj ronioca: " + visitor.getAverageNumDivers());
            visitor.getTmAgeRange().forEach((k, v) -> {
                System.out.println("Age range: " + k + "-> " + v);
            });

        } catch (IOException ex) {
            Logger.getLogger(Mmojsino_zadaca_2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
