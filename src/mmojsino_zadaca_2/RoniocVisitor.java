/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mmojsino_zadaca_2;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 *
 * @author Mario
 */
public class RoniocVisitor implements Visitor {

    private List<Integer> avgDiverAge;
    private LocalDateTime now;
    private TreeMap tmAgeRange;
    private List<Integer> avgNumDivers;

    //age range
    public RoniocVisitor() {
        this.avgDiverAge = new ArrayList<>();
        this.now = LocalDateTime.now();
        this.tmAgeRange = new TreeMap();
        this.avgNumDivers = new ArrayList<>();
    }

    @Override
    public void visit(Ronioc ronioc) {
        avgDiverAge.add(now.getYear() - ronioc.getGodinaRodjenja());
        switch ((int) (now.getYear() - ronioc.getGodinaRodjenja()) / 10) {
            case 1:
                tmAgeRange.put("10-19", (int) tmAgeRange.getOrDefault("10-19", 0)+1);
                break;
            case 2:
                tmAgeRange.put("20-29", (int) tmAgeRange.getOrDefault("20-29", 0)+1);
                break;
            case 3:
                tmAgeRange.put("30-39", (int) tmAgeRange.getOrDefault("30-39", 0)+1);
                break;
            case 4:
                tmAgeRange.put("40-49", (int) tmAgeRange.getOrDefault("40-49", 0)+1);
                break;
            case 5: 
                tmAgeRange.put("50-59", (int) tmAgeRange.getOrDefault("50-59", 0)+1);
                break;
            case 6:
                tmAgeRange.put("60+", (int) tmAgeRange.getOrDefault("60+", 0)+1);
                break;
            default:
                break;
        }
    }

    public double getAverageDiverAge() {
        int ukupno = 0;
        for (Integer i : avgDiverAge) {
            ukupno += i;
        }
        return (double) ukupno / this.avgDiverAge.size();
    }

    @Override
    public void visit(Uron uron) {
        avgNumDivers.add(uron.getBrojRonioca());
    }

    public double getAverageNumDivers() {
        int ukupno = 0;
        for (Integer i : avgNumDivers) {
            ukupno += i;
        }
        return (double) ukupno / this.avgNumDivers.size();
    }

    public TreeMap getTmAgeRange() {
        return tmAgeRange;
    }
    
}
