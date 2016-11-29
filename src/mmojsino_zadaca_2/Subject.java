/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mmojsino_zadaca_2;

/**
 *
 * @author Mario
 */
public interface Subject {
    public void register (Observer obj);
    public void unregister(Observer obj);
    
    public void notifyObservers();
    
    public Object getUpdate(Observer obj);
}
