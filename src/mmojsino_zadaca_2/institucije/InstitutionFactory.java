/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mmojsino_zadaca_2.institucije;

/**
 *
 * @author Mario
 */
public class InstitutionFactory {
    public static Institution getInstitution(String institutionType){
        if(institutionType == null){
            System.out.println("Algorithm type not set.");
            return null;
        }if(institutionType.equalsIgnoreCase("HRS")){
            return new HRS();
        }
        return new HRS();
    }
}
