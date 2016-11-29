/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mmojsino_zadaca_2.algorithms;

/**
 *
 * @author Mario
 */
public class AlgorithmFactory {
    public static AlgorithmScuba getAlgorithm(String algorithmType){
        if(algorithmType == null){
            System.out.println("Algorithm type not set.");
            return null;
        }if(algorithmType.equalsIgnoreCase("AlgMaksUron")){
            return new AlgorithmMaxDepth();
        }else if(algorithmType.equalsIgnoreCase("AlgIstaKategUro")){
            return new AlgorithmLevel();
        }else if(algorithmType.equalsIgnoreCase("AlgSlucUron")){
            return new AlgorithmRandom();
        }
        return new AlgorithmMaxDepth();
    }
}
