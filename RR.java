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
public class RR extends Strategia {
    
    private final int kwantCzasu;
    
    public RR(int q) {
        this.nazwa = "RR";
        this.kwantCzasu = q;
    }

    public double getKwantCzasu() {
        return kwantCzasu;
    }
    
    
    @Override
    public void przedstawSię () {
         System.out.println("Strategia: " + nazwa + "-" + kwantCzasu);
    }

    @Override
    public void przeprowadzStrategie(ArrayList<Proces> procesy) {
        
        // Przygotowanie zmiennych i pojemników, porządkowanie danych
        ArrayList<Proces> OczekująceProcesy = new ArrayList<>();
        ArrayList<Proces> AktywneProcesy = new ArrayList<>();
        double czas = 0;
        
        for (Proces p: procesy) {
            p.setPostęp(0);
            p.setCzasZakończenia(-1);
        }
        
        for (Proces p: procesy) {
            OczekująceProcesy.add(p);
        }
        
        OczekująceProcesy.sort(new RRComparator());
        
        
        
        // Symulacja wykonywania zadań
        while (!OczekująceProcesy.isEmpty() || !AktywneProcesy.isEmpty()) {
            
            if (!AktywneProcesy.isEmpty()) {
                
                // wykonuję kwant czasu dla pierwszego procesu w kolejce
                Proces p = AktywneProcesy.remove(0);
                p.setPostęp(p.getPostęp() + kwantCzasu);
                czas += kwantCzasu;

                // sprawdzam, czy nie spowodowało to jego zakończenia
                if(p.getPostęp() >= p.getZapotrzebowanie()) {
                    czas -= p.getPostęp() - p.getZapotrzebowanie();
                    p.setCzasZakończenia(czas);
                }
                
                // dodaję procesy które rozpoczęły się w międzyczasie
                while (!OczekująceProcesy.isEmpty()) {
                    if (OczekująceProcesy.get(0).getCzasPojawienia() < czas) {
                        AktywneProcesy.add(OczekująceProcesy.remove(0));
                    }
                    else
                        break;
                }

                // jeśli aktualnie rozważany proces się nie skończył, to dodaję go
                if (p.getCzasZakończenia() == -1)
                    AktywneProcesy.add(p);
                
                // dodaję procesy, które rozpoczęły się w momencie zakończenia rozważanego obecnie procesu
                while (!OczekująceProcesy.isEmpty()) {
                    if (OczekująceProcesy.get(0).getCzasPojawienia() == czas) {
                        AktywneProcesy.add(OczekująceProcesy.remove(0));
                    }
                    else
                        break;
                }
                
            }
            else {
                
                czas = OczekująceProcesy.get(0).getCzasPojawienia();
                AktywneProcesy.add(OczekująceProcesy.remove(0));
                
            }
        }
    }
}
