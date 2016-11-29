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
public class CMAS extends Agency {

    private Subject topic;

    private String agencyName = "CMAS";

    public static int[] levelsRecreational = {0, 1, 1, 1, 1, 0};

    public static int[] levelsProfessional = {1, 0, 1, 1, 0, 0, 0, 0};

    public static String[] levelsCertifiedRecreational = {"", "One Star Diver", "One Star Diver", "Two Star Diver",
        "Two Star Diver", ""};
    public static String[] levelsCertifiedProffesional = {"Three Star Diver", "", "One Star Instructor", "Two Star Instructor",
        "", "", "", ""};

    public CMAS() {
        super(levelsRecreational, levelsProfessional, levelsCertifiedRecreational, levelsCertifiedProffesional);
    }

    @Override
    public void update() {
        //samo za trenutnog ronioca (this)
        String msg = (String) topic.getUpdate(this);
        if (msg == null) {
            System.out.println(this.agencyName + " no new messages");
        } else {
            System.out.println(this.agencyName + " consuming message: " + msg);
        }
    }

    @Override
    public void setSubject(Subject sub) {
        this.topic = sub;
    }
}
