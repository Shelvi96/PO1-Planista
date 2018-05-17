/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planista;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 *
 * @author konrad
 */
public class SRT extends Strategia {
    
    public SRT() {
        this.nazwa = "SRT";
    }
    
    @Override
    public void przedstawSię () {
        System.out.println("Strategia: " + this.nazwa);
    }
    
    @Override
    void przeprowadzStrategie(ArrayList<Proces> procesy) {
        
        // Przygotowanie zmiennych i pojemników, porządkowanie danych
        PriorityQueue<Proces> OczekująceProcesy = new PriorityQueue<>(new SRTComparator());
        PriorityQueue<Proces> AktywneProcesy = new PriorityQueue<>(new SRTxtraComparator());
        double czas = 0;
        
        for (Proces p: procesy) {
            p.setPostęp(0);
            p.setCzasZakończenia(-1);
        }
        
        for (Proces p: procesy) {
            OczekująceProcesy.add(p);
        }

        
        
        // Symulacja wykonywania zadań
        Proces bieżącyProces = OczekująceProcesy.poll(); // zbiór procesów jest niepusty
//        System.out.println("Czas: " + czas + " ,nowy proces: " + bieżącyProces.getId());
        while(!OczekująceProcesy.isEmpty() || !AktywneProcesy.isEmpty()) {
            
            // Jeśli bieżący proces wykonał się, to kończymy go
            if (bieżącyProces != null && bieżącyProces.getZapotrzebowanie() == bieżącyProces.getPostęp()) {
                
                bieżącyProces.setCzasZakończenia(czas);
//                System.out.println("Czas: " + czas + " ,kończę: " + bieżącyProces.getId());
                // gdzieś na pewno jest jeszcze zadanie
                if (!AktywneProcesy.isEmpty() && !OczekująceProcesy.isEmpty()) {
                    Proces p = OczekująceProcesy.peek();
                    Proces a = AktywneProcesy.peek();
                    if (p.getCzasPojawienia() == czas && p.getZapotrzebowanie() <= a.getZapotrzebowanie() && p.getId() < a.getId()) {
                        bieżącyProces = OczekująceProcesy.poll();
                    }
                    else {
                        bieżącyProces = AktywneProcesy.poll();
                    }
                }
                else if (!AktywneProcesy.isEmpty()) {
                    bieżącyProces = AktywneProcesy.poll();
                }
                else
                    bieżącyProces = OczekująceProcesy.poll();
//                System.out.println("Czas: " + czas + " ,nowy proces x: " + bieżącyProces.getId());
            }
            
            // Jeśli pojawiły się nowe procesy, to bierzemy je
            while (!OczekująceProcesy.isEmpty() && czas == OczekująceProcesy.peek().getCzasPojawienia()) {
                
                Proces p = OczekująceProcesy.poll();
                
                // Jeśli nowy proces wykona się szybciej, to bierzemy się za niego
                if (p.getZapotrzebowanie() < bieżącyProces.pozostałyCzas()) {
//                    System.out.println("Czas: " + czas + " ,przerywam bieżący proces: " + bieżącyProces.getId());
                    AktywneProcesy.add(bieżącyProces);
                    bieżącyProces = p;
//                    System.out.println("Czas: " + czas + " ,biorę nowy proces: " + bieżącyProces.getId());
                }
                // W przeciwnym wypadku odkładamy proces na później
                else {
                    AktywneProcesy.add(p);
//                    System.out.println("Czas: " + czas + " ,odkładam na później: " + p.getId());
                }
                
            }
            
            // Wykonujemy bieżący proces do momentu pojawienia się nowego procesu lub do momentu zakończenia tego procesu
            // bieżącyProces na pewno nie jest tutaj nullem
            if (!OczekująceProcesy.isEmpty()) {
                
                // Proces kończy się przed pojawieniem się kolejnego zadania
                if( bieżącyProces.pozostałyCzas() < OczekująceProcesy.peek().getCzasPojawienia() - czas) {
                    czas += bieżącyProces.pozostałyCzas();
                    bieżącyProces.setPostęp(bieżącyProces.getZapotrzebowanie());
//                    System.out.println("essa");
                }
                // Proces nie zdąży się wykonać w całości
                else{
                    
                    bieżącyProces.setPostęp(bieżącyProces.getPostęp() + OczekująceProcesy.peek().getCzasPojawienia() - czas);
                    czas = OczekująceProcesy.peek().getCzasPojawienia();
                }
                
            }
            // Jeśli nie ma więcej nowych procesów, to po prostu robimy bieżący proces
            else {
                
                czas += bieżącyProces.pozostałyCzas();
                bieżącyProces.setPostęp(bieżącyProces.getZapotrzebowanie());
                
            }
//            System.out.println("Ustawiono czas na: " + czas);
            
        }
        bieżącyProces.setCzasZakończenia(czas);
    }
    
}
