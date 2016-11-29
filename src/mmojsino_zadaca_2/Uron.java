/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mmojsino_zadaca_2;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 *
 * @author Mario
 */
public class Uron implements Subject, Visitable {

    //required
    private String datum;
    private String vrijeme;
    private int maxDubina;
    private int brojRonioca;
    //optional
    private List<Sastav> uronGrupa = new ArrayList<>();
    private TreeMap mjeraSigurnosti;

    private List<Observer> observers;
    private String message;
    private boolean changed;
    private final Object MUTEX = new Object();

    private Uron(UronBuilder b) {
        this.datum = b.datum;
        this.vrijeme = b.vrijeme;
        this.maxDubina = b.maxDubina;
        this.brojRonioca = b.brojRonioca;
        this.uronGrupa = b.uronGrupa;
        this.mjeraSigurnosti = b.mjeraSigurnosti;
        this.observers = new ArrayList<>();
    }

    public TreeMap getMjeraSigurnosti() {
        return mjeraSigurnosti;
    }

    public String getDatum() {
        return datum;
    }

    public String getVrijeme() {
        return vrijeme;
    }

    public int getMaxDubina() {
        return maxDubina;
    }

    public int getBrojRonioca() {
        return brojRonioca;
    }

    public List<Sastav> getUronGrupa() {
        return uronGrupa;
    }

    @Override
    public void register(Observer obj) {
        if (obj == null) {
            throw new NullPointerException("Null Observer");
        }
        synchronized (MUTEX) {
            if (!observers.contains(obj)) {
                observers.add(obj);
            }
        }
    }

    @Override
    public void unregister(Observer obj) {
        synchronized (MUTEX) {
            observers.remove(obj);
        }
    }

    @Override
    public void notifyObservers() {
        List<Observer> observersLocal = null;
        //synchronization is used to make sure any observer registered after message is received is not notified
        synchronized (MUTEX) {
            if (!changed) {
                return;
            }
            observersLocal = new ArrayList<>(this.observers);
            this.changed = false;
        }
        for (Observer obj : observersLocal) {
            obj.update();
        }
    }

    @Override
    public Object getUpdate(Observer obj) {
        return this.message;
    }
    
    //method to post the message - ronioc i njegovi parovi???
    public void postMessage(String msg){
        System.out.println("Message posted from Uron: "+ msg);
        this.message = msg;
        this.changed = true;
        notifyObservers();
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public static class UronBuilder {

        //required
        private String datum;
        private String vrijeme;
        private int maxDubina;
        private int brojRonioca;

        //optional
        private List<Sastav> uronGrupa = new ArrayList<>();
        private TreeMap mjeraSigurnosti;

        public UronBuilder(String datum, String vrijeme, int maxDubina, int brojRonioca) {
            this.datum = datum;
            this.vrijeme = vrijeme;
            this.maxDubina = maxDubina;
            this.brojRonioca = brojRonioca;
        }

        public UronBuilder postaviGrupu(List<Sastav> s) {
            this.uronGrupa = s;
            return this;
        }

        public UronBuilder postaviMjeruSigurnosti(TreeMap m) {
            this.mjeraSigurnosti = new TreeMap();
            this.mjeraSigurnosti = m;
            return this;
        }

        public Uron build() {
            return new Uron(this);
        }
    }
}
