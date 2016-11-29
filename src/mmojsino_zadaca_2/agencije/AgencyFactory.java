/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mmojsino_zadaca_2.agencije;

/**
 *
 * @author Mario
 */
public class AgencyFactory {

    public Agency getAgency(String name) {
        if (name == null) {
            System.out.println("Name is empty, please try again.");
            return null;
        }
        if (name.equalsIgnoreCase("SSI")) {
            return new SSI();
        } else if (name.equalsIgnoreCase("BSAC")) {
            return new BSAC();
        } else if (name.equalsIgnoreCase("NAUI")) {
            return new NAUI();
        } else if (name.equalsIgnoreCase("CMAS")) {
            return new CMAS();
        }
        return null;
    }

}
