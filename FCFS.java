/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planista;

import static java.lang.Math.max;
import java.util.ArrayList;

/**
 *
 * @author konrad
 */
public class FCFS extends Strategia {
    
    public FCFS() {
        this.nazwa = "FCFS";
    }
    
    @Override
    public void przedstawSię () {
        System.out.println("Strategia: " + nazwa);
    }
    
    @Override
    void przeprowadzStrategie(ArrayList<Proces> procesy) {
        
        // Przygotowanie zmiennych i pojemników, porządkowanie danych
        ArrayList<Proces> OczekująceProcesy = new ArrayList<>();
        double czas = 0;
        
        for (Proces p: procesy) {
            p.setPostęp(0);
            p.setCzasZakończenia(-1);
        }
        
        for (int i = 0; i < procesy.size(); i++) {
            OczekująceProcesy.add(procesy.get(i));
        }
        
        OczekująceProcesy.sort(new FCFSComparator());
        
        
        
        // wykonuję kolejne procesy
        while(!OczekująceProcesy.isEmpty()) {
            Proces p = OczekująceProcesy.remove(0);
            czas = max(czas, p.getCzasPojawienia());
            czas += p.getZapotrzebowanie();
            p.setCzasZakończenia(czas);
        }
        
    }
    
}