/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package singleton;

/**
 *
 * @author Florian
 */
public final class Singleton {
    
    private static Singleton s = new Singleton(666);
    private int i;
    
    //NB: provate constructor
    private Singleton(int x) { i = x; }
    
    public static Singleton getReference() {
        return s;
    }
    
    public int getValue() { return i; }
    
    public void setValue(int x) { i = x; }
}
