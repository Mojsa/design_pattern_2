/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mmojsino_zadaca_2.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

import mmojsino_zadaca_2.RankingNorm;
import mmojsino_zadaca_2.RonilackiKlubSingleton;
import mmojsino_zadaca_2.Ronioc;
import mmojsino_zadaca_2.Uron;


/**
 *
 * @author Mario
 */
public abstract class AlgorithmScuba {
    
    private static RonilackiKlubSingleton instance = RonilackiKlubSingleton.getInstance();
    
    public abstract TreeMap generirajPlan(Uron u);
    
    public static final String[] razine = {"R0", "R1", "R2", "R3", "R4", "R5", "I0", "I1", "I2", "I3", "I4", "I5", "I6", "I7"};

    public int pronadjiDubinu(int broj, boolean profiMix) {
        int dodatak = 0;
        if (profiMix) {
            dodatak = 10;
        }
        switch (broj) {
            case 0:
                return 0;
            case 1:
                return 10 + dodatak;
            case 2:
                return 30 + dodatak;
            default:
                return 40;
        }
    }

    public int pronadjiApsolutnuRazinu(String r1, String r2) {
        int apsolutnaRazina = 0;
        if (Arrays.asList(RankingNorm.getRazineRekreacijske()).contains(r1)
                && Arrays.asList(RankingNorm.getRazineRekreacijske()).contains(r2)) {
            //both recreational
            apsolutnaRazina = Math.abs(Arrays.asList(razine).indexOf(r1) - Arrays.asList(razine).indexOf(r2)) + 1;
        } else if (Arrays.asList(RankingNorm.getRazineProfi()).contains(r1)
                && Arrays.asList(RankingNorm.getRazineProfi()).contains(r2)) {
            //both professional
            apsolutnaRazina = Math.abs(Arrays.asList(razine).indexOf(r1) - Arrays.asList(razine).indexOf(r2)) + 1;
        } else if ((Arrays.asList(RankingNorm.getRazineRekreacijske()).contains(r1)
                && Arrays.asList(RankingNorm.getRazineProfi()).contains(r2))) {
            //1.rekr 2.profess
            apsolutnaRazina = Math.abs(Arrays.asList(razine).indexOf(r1) - Arrays.asList(razine).indexOf(r2)) + 1;
        } else {
            //1.prof 2.rekr
            apsolutnaRazina = Math.abs(Arrays.asList(razine).indexOf(r1) - Arrays.asList(razine).indexOf(r2)) + 1;
        }
        return apsolutnaRazina;
    }
    
    public static String getMinKey(TreeMap<String, Object> map) {
        String[] minKey = new String[1];
        minKey[0] = map.firstKey();
        map.forEach((k, v) -> {
            if ((float) map.get(k) < (float) map.get(minKey[0])) {
                minKey[0] = k;
            }
        });
        return minKey[0];
    }
    
    public List<Ronioc> getSkupRonioca(int brojRonioca) {
        List<Ronioc> skupRonioca = new ArrayList<>();
        if (instance.getListRonioca().size() < brojRonioca) {
            System.out.println("Nema dovoljno ronioca!");
            return null;
        }
        Ronioc random;
        while (brojRonioca > 0) {
            random = instance.getListRonioca().get(instance.getGenerator().nextInt(instance.getListRonioca().size()));
            while (skupRonioca.contains(random) && random.getRangNorm() != "-") {
                random = instance.getListRonioca().get(instance.getGenerator().nextInt(instance.getListRonioca().size()));
            }
            skupRonioca.add(random);
            brojRonioca -= 1;
        }
        return skupRonioca;
    }
}
