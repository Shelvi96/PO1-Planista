/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planista;

import static java.lang.Math.abs;
import static java.lang.Math.min;
import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 *
 * @author konrad
 */
public class PS extends Strategia {
    
    public PS() {
        this.nazwa = "PS";
    }
    
    @Override
    public void przedstawSię () {
        System.out.println("Strategia: " + this.nazwa);
    }
    
    @Override
    void przeprowadzStrategie(ArrayList<Proces> procesy) {
        
        // Przygotowanie zmiennych i pojemników, porządkowanie danych
        PriorityQueue<Proces> OczekująceProcesy = new PriorityQueue<>(new PSComparator());
        ArrayList<Proces> AktywneProcesy = new ArrayList<>();
        double czas = 0;
        double eps = 1e-10;
        
        for (int i = 0; i < procesy.size(); i++) {
            OczekująceProcesy.add(procesy.get(i));
        }
        
        for (Proces p: OczekująceProcesy) {
            p.setPostęp(0);
            p.setCzasZakończenia(-1);
        }
        
        
        
        // Symulacja wykonywania zadań
        while(!OczekująceProcesy.isEmpty() || !AktywneProcesy.isEmpty()) {
            
            // Dodajemy zadania ktore sie pojawily
            while (!OczekująceProcesy.isEmpty() && OczekująceProcesy.peek().getCzasPojawienia() <= czas) {
                Proces p = OczekująceProcesy.poll();
                AktywneProcesy.add(p);
            }
            
            // Jesli jakies zadanie sie zakonczylo to usuwamy je
            for (int i = AktywneProcesy.size() - 1; i >= 0; --i) {
                Proces p = AktywneProcesy.get(i);
                if (abs(p.getPostęp() - p.getZapotrzebowanie()) < eps) {
                    p.setCzasZakończenia(czas);
                    AktywneProcesy.remove(i);
                }
            }
            
            // Po dodaniu i usunieciu sprawdzamy, ktore zadanie skonczy sie jako pierwsze
            double czasNastepnegoWydarzenia = Double.MAX_VALUE;
            for (int i = 0; i < AktywneProcesy.size(); ++i) {
                Proces p = AktywneProcesy.get(i);
                czasNastepnegoWydarzenia = min(czasNastepnegoWydarzenia, (p.pozostałyCzas()) * AktywneProcesy.size());
            }
            
            // Sprawdzamy kiedy pojawi sie nowe zadanie
            if (!OczekująceProcesy.isEmpty()) {
                czasNastepnegoWydarzenia = min(czasNastepnegoWydarzenia, OczekująceProcesy.peek().getCzasPojawienia() - czas);
            }
            
            // Wykonujemy procesy
            for (int i = 0; i < AktywneProcesy.size(); ++i) {
                Proces p = AktywneProcesy.get(i);
                p.setPostęp(p.getPostęp() + czasNastepnegoWydarzenia / AktywneProcesy.size());
            }
            
            czas += czasNastepnegoWydarzenia;
            
        }
        
    }
    
}
