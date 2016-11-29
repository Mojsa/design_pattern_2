/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mmojsino_zadaca_2.institucije;

import mmojsino_zadaca_2.Subject;

/**
 *
 * @author Mario
 */
public class HRS extends Institution {
    
    private String institutionName = "HRS";

    private Subject topic;

    @Override
    public void update() {
        //samo za trenutnog ronioca (this)
        String msg = (String) topic.getUpdate(this);
        if (msg == null) {
            System.out.println(this.institutionName + " no new messages");
        } else {
            System.out.println(this.institutionName + " consuming message: " + msg);
        }
    }

    @Override
    public void setSubject(Subject sub) {
        this.topic = sub;
    }

}
