/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mmojsino_zadaca_2;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import mmojsino_zadaca_2.agencije.Agency;
import mmojsino_zadaca_2.algorithms.AlgorithmScuba;

/**
 *
 * @author Mario
 */
public class FileManager {

    File writeFile = null;
    private static RonilackiKlubSingleton instance = RonilackiKlubSingleton.getInstance();

    public void openFile(String path) throws IOException {
        Path filePath = Paths.get(path);
        Scanner scanner = new Scanner(filePath);
        String[] parts;
        if (path.contains("ronioci")) {
            while (scanner.hasNext()) {
                if (scanner.hasNext()) {
                    parts = scanner.next().split(";");
                    if (parts.length == 4) {
                        instance.dodajRonioca(new Ronioc.RoniocBuilder(parts[0], Integer.parseInt(parts[3]))
                                .setAgencija(parts[1])
                                .setAgencijaNorm(RankingNorm.getAgencije()[0])
                                .setRang(parts[2])
                                .setRangNorm()
                                .setCertifikat()
                                .build());
                    } else {
                        System.out.println("Neispravan zapis, preskacem liniju.");
                    }

                } else {
                    scanner.next();
                }
            }
        } else if (path.contains("uroni")) {
            while (scanner.hasNext()) {
                if (scanner.hasNext()) {
                    parts = scanner.next().split(";");
                    if (parts.length == 4) {
                        instance.dodajUron(new Uron.UronBuilder(parts[0], parts[1], Integer.parseInt(parts[2]), Integer.parseInt(parts[3])).build());
                    } else {
                        System.out.println("Neispravan zapis, preskacem liniju.");
                    }

                } else {
                    scanner.next();
                }
            }
        }
        scanner.close();
    }

    public void writeToFile(List<String> data) {
        FileWriter fr = null;
        BufferedWriter br = null;
        try {
            fr = new FileWriter(writeFile, true);
            br = new BufferedWriter(fr);
            for (String s : data) {
                br.append(s);
                br.append(System.lineSeparator());
            }
        } catch (IOException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                br.close();
                fr.close();
            } catch (IOException ex) {
                Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void writeToFile(String data){
        FileWriter fr = null;
        BufferedWriter br = null;
        try {
            fr = new FileWriter(writeFile, true);
            br = new BufferedWriter(fr);
            br.append(data);
            br.append(System.lineSeparator());
        } catch (IOException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                br.close();
                fr.close();
            } catch (IOException ex) {
                Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void openWriteFile(String path) {
        writeFile = new File(path);
        try {
            if (writeFile.exists()) {
                writeFile.delete();
                writeFile.createNewFile();
            } else {
                writeFile.createNewFile();
            }
        } catch (IOException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void podaciRoniocTablica() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps); 
        System.out.format("%-9s %15s %10s %-25s %20s %20s %20s",
                "Ime", "Godina rodjenja", "Agencija", "Certifikat", "Datum/Vrijeme", "Dubina", "Partneri");
        System.out.println("");
        instance.getListUroni().forEach((Uron u) -> {
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------");
            for (Sastav s : u.getUronGrupa()) {
                for (Ronioc r : s.getLista()) {
                    System.out.format("%-9s %15s %10s %-25s %23s %15s %25s",
                            r.getImeRonioca(), r.getGodinaRodjenja(), r.getAgencija(),
                            r.getCertifikat(), u.getDatum() + "/" + u.getVrijeme(), s.getDubina(), "");
                    System.out.println("");
                    for (Ronioc a : s.getLista()) {
                        if (!a.equals(r)) {
                            System.out.format("%-9s %15s %5s %23s %25s %15s %25s",
                                    "", "", "",
                                    "", "", "", a.getImeRonioca());
                            System.out.println("");
                        }
                    }
                    System.out.println(".....................................................................................................................................");
                }
            }
            u.getMjeraSigurnosti().forEach((k,v) -> {
                System.out.println( k + " : " + v);
            });
        });
        System.out.flush();
        System.setOut(old);
        writeToFile(baos.toString());
    }
    
    public void podaciUronStatistika() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps); 
        int ukupnaDubina = 0;
        int brojSastav = 0;
        for (Uron u : instance.getListUroni()) {
            for (Sastav s : u.getUronGrupa()) {
                brojSastav++;
                ukupnaDubina += s.getDubina();
            }
        }
        System.out.format("%-9s %15s %10s %-20s %20s",
                "Ukupno urona", "Prosječna dubina", "Ukupno po kategorijama", "Ukupno po agencijama", "Ukupno po savezu");
        System.out.println("");
        System.out.format("%-9s %15s %10s %-20s %20s",
                instance.getListUroni().size(), ukupnaDubina / brojSastav, "", "", "");
        System.out.println("");
        TreeMap tmKat = new TreeMap();
        TreeMap tmAg = new TreeMap();
        for (Uron u : instance.getListUroni()) {
            for (String a : AlgorithmScuba.razine) {
                for (Sastav s : u.getUronGrupa()) {
                    for (Ronioc r : s.getLista()) {
                        if (a.equals(r.getRang())) {
                            tmKat.put(a, (int) tmKat.getOrDefault(a, (int) 0) + 1);
                        }
                    }
                }
            }
            for (String b : Agency.getAgencies()) {
                for (Sastav s : u.getUronGrupa()) {
                    for (Ronioc r : s.getLista()) {
                        if (b.equals(r.getAgencija())) {
                            tmAg.put(b, (int) tmAg.getOrDefault(b, (int) 0) + 1);
                        }
                    }
                }
            }
        }
        for (String a : Agency.getAgencies()) {
            System.out.format("%-9s %15s %20s %20s %20s",
                    "", "", "", a + "- " + (tmAg.get(a) == null ? 0 : tmAg.get(a)), "");
            System.out.println("");
        }
        for (String a : AlgorithmScuba.razine) {
            System.out.format("%-9s %15s %20s %-20s %20s",
                    "", "", a + "- " + (tmKat.get(a) == null ? 0 : tmKat.get(a)), "", "");
            System.out.println("");
        }
        System.out.println("");
        System.out.flush();
        System.setOut(old);
        writeToFile(baos.toString());
    }
    

}
