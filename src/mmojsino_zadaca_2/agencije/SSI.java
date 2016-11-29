/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mmojsino_zadaca_2.agencije;

import java.util.Arrays;
import mmojsino_zadaca_2.Subject;

/**
 *
 * @author Mario
 */
public class SSI extends Agency {

    private Subject topic;
    
    private String agencyName = "SSI";

    public static int[] levelsRecreational = {1, 1, 1, 1, 1, 1};

    public static int[] levelsProfessional = {1, 1, 1, 1, 1, 1, 1, 1};

    public static String[] levelsCertifiedRecreational = {"Scuba Diver", "Open Water Diver", "Advanced Adventure", "Diver Stress & Rescue",
        "Advanced Open Water Diver", "Master Diver"};
    public static String[] levelsCertifiedProffesional = {"Dive Guide", "Divemaster", "Dive Control Specialist", "Open Water Instructor",
        "Advanced Open Water Instructor", "Divemaster Instructor", "Dive Control Specialist Instructor", "Instructor Trainer"};

    public SSI() {
        super(levelsRecreational, levelsProfessional, levelsCertifiedRecreational, levelsCertifiedProffesional);
    }

    @Override
    public void update() {
        //samo za trenutnog ronioca (this)
        String msg = (String) topic.getUpdate(this);
        if(msg == null){
            System.out.println(this.agencyName + " no new messages");
        }else{
            System.out.println(this.agencyName + " consuming message: " + msg);
        }
    }

    @Override
    public void setSubject(Subject sub) {
        this.topic = sub;
    }
}
