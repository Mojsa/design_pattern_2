/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mmojsino_zadaca_2.agencije;

import mmojsino_zadaca_2.Subject;

/**
 *
 * @author Mario
 */
public class NAUI extends Agency {

    private Subject topic;
    
    private String agencyName = "NAUI";

    public static int[] levelsRecreational = {0, 1, 1, 1, 1, 0};

    public static int[] levelsProfessional = {1, 0, 1, 1, 0, 0, 0, 1};

    public static String[] levelsCertifiedRecreational = {"", "Scuba Diver", "Advanced Scuba Diver", "Scuba Rescue Diver",
        "Master Scuba Diver", ""};
    public static String[] levelsCertifiedProffesional = {"Assistant Instructor", "", "Divemaster", "Instructor",
        "", "", "", "Instructor Trainer"};

    public NAUI() {
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
