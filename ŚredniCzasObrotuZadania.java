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
public class ŚredniCzasObrotuZadania extends Efektywność {
    
    public ŚredniCzasObrotuZadania() {
        this.nazwa = "Średni czas obrotu";
    }
    
    @Override
    public double zbadajEfektywność(ArrayList<Proces> procesy) {
        
        double suma = 0;
        
        for (Proces p: procesy) {
            suma += (p.getCzasZakończenia() - p.getCzasPojawienia());
        }
        
        return suma / procesy.size();
        
    }
    
}
