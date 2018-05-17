/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planista;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 *
 * @author konrad
 */
public class Testuj {
    
    private static final DecimalFormat df = new DecimalFormat("0.00");
    
    void przeprowadzTest(ArrayList<Proces> procesy, Strategia s) {
        
        s.przedstawSię();
        s.przeprowadzStrategie(procesy);
        procesy.sort(new TestujComparator());
        
        for(int i = 0; i < procesy.size(); ++i) {
            System.out.print("[" + procesy.get(i).getId() + " " + procesy.get(i).getCzasPojawienia() + " " + df.format(procesy.get(i).getCzasZakończenia()) + "]");
        }
        System.out.println("");
        
        Efektywność e1 = new ŚredniCzasObrotuZadania();
        Efektywność e2 = new ŚredniCzasOczekiwania();
        
        System.out.println(e1.getNazwa() + ": " + df.format(e1.zbadajEfektywność(procesy)));
        if (s.getNazwa().equals("PS"))
            System.out.println(e2.getNazwa() + ": 0.00");
        else
            System.out.println(e2.getNazwa() + ": " + df.format(e2.zbadajEfektywność(procesy)));
        
        System.out.println("");
        
    }
    
}
