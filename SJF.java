/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planista;

import static java.lang.Math.max;
import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 *
 * @author konrad
 */
public class SJF extends Strategia {
    
    public SJF() {
        this.nazwa = "SJF";
    }
    
    @Override
    public void przedstawSię () {
        System.out.println("Strategia: " + this.nazwa);
    }
    
    @Override
    void przeprowadzStrategie(ArrayList<Proces> procesy) {
        
        // Przygotowanie zmiennych i pojemników, porządkowanie danych
        ArrayList<Proces> OczekująceProcesy = new ArrayList<>();
        PriorityQueue<Proces> AktywneProcesy = new PriorityQueue<>(new SJFxtraComparator());
        double czas = 0;
        
        for (Proces p: procesy) {
            p.setPostęp(0);
            p.setCzasZakończenia(-1);
        }
        
        for (int i = 0; i < procesy.size(); i++) {
            OczekująceProcesy.add(procesy.get(i));
        }
        
        OczekująceProcesy.sort(new SJFComparator());
        
        
        
        // Symulacja wykonywania zadań
        while(!OczekująceProcesy.isEmpty() || !AktywneProcesy.isEmpty()) {
            while (!OczekująceProcesy.isEmpty() && czas >= OczekująceProcesy.get(0).getCzasPojawienia()) {
                AktywneProcesy.add(OczekująceProcesy.remove(0));
            }
            if (!AktywneProcesy.isEmpty()) {
                Proces p = AktywneProcesy.poll();
                czas = max(czas, p.getCzasPojawienia());
                czas += p.getZapotrzebowanie();
                p.setCzasZakończenia(czas);
            }
        }
        
    }
    
}
