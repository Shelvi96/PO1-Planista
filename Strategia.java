/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planista;

import java.util.ArrayList;

/**
 *
 * @author konrad
 */
public abstract class Strategia {
    
    protected String nazwa;

    public String getNazwa() {
        return nazwa;
    }
    
    abstract void przedstawSię();
    
    abstract void przeprowadzStrategie(ArrayList<Proces> procesy);
    
}
