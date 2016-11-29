/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mmojsino_zadaca_2.agencije;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import mmojsino_zadaca_2.Observer;

/**
 *
 * @author Mario
 */
public abstract class Agency implements Observer{

    protected int[] levelsRecreational;
    protected int[] levelsProfessional;

    protected String[] levelsCertifiedRecreational;
    protected String[] levelsCertifiedProffesional;

    private String[] levelRecreationalShort = {
        "R0", "R1", "R2", "R3", "R4", "R5"
    };

    private String[] levelProfShort = {
        "I0", "I1", "I2", "I3", "I4", "I5", "I6", "I7"
    };

    public Agency(int[] levelsRecreational, int[] levelsProfessional, String[] levelsCertifiedRecreational, String[] levelsCertifiedProffesional) {
        this.levelsRecreational = levelsRecreational;
        this.levelsProfessional = levelsProfessional;
        this.levelsCertifiedRecreational = levelsCertifiedRecreational;
        this.levelsCertifiedProffesional = levelsCertifiedProffesional;
    }
    
    public String getCertificate(String level) {
        int index;
        if (Arrays.asList(levelRecreationalShort).contains(level)) {
            //recreational
            index = Arrays.asList(levelRecreationalShort).indexOf(level);
            if (levelsRecreational[index] != 0) {
                return levelsCertifiedRecreational[index];
            } else {
                return "Not available";
            }
        }else if(Arrays.asList(levelProfShort).contains(level)){
            index = Arrays.asList(levelProfShort).indexOf(level);
            if (levelsProfessional[index] != 0) {
                return levelsCertifiedProffesional[index];
            } else {
                return "Not available";
            }
        }
        return "Not available";
    }

    public static List<String> getAgencies(){
        List<String> agencies = new ArrayList<>();
        agencies.add("SSI");
        agencies.add("BSAC");
        agencies.add("NAUI");
        agencies.add("CMAS");
        return agencies;
    }
}
