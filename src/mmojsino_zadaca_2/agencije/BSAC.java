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
public class BSAC extends Agency {

    private Subject topic;

    private String agencyName = "BSAC";

    public static int[] levelsRecreational = {0, 1, 1, 1, 1, 0};

    public static int[] levelsProfessional = {1, 0, 1, 1, 1, 0, 0, 0};

    public static String[] levelsCertifiedRecreational = {"", "Ocean Diver", "Ocean Diver", "Sports Diver",
        "Sports Diver", ""};
    public static String[] levelsCertifiedProffesional = {"Dive Leader", "", "Assistant Open Water Instructor", "Open Water Instructor",
        "Advanced Instructor", "", "", ""};

    public BSAC() {
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
