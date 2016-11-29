/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mmojsino_zadaca_2;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mario
 */
public class Sastav {

    private List<Ronioc> lista = new ArrayList<>();
    private int dubina;

    public void dodajRoniocaSastav(Ronioc r) {
        if (lista.size() > 3) {
            System.out.println("Sastav moze imati najvise 3 ronioca.");
            return;
        }
        lista.add(r);
    }

    public List<Ronioc> getLista() {
        return lista;
    }

    public void postaviDubinu(int d) {
        this.dubina = d;
    }

    public int getDubina() {
        return dubina;
    }

    public boolean mozeJosRonioca() {
        if (lista.size() < 3) {
            return true;
        } else {
            return false;
        }
    }

}
