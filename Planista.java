/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planista;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 *
 * @author konrad
 */
public class Planista {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Testuj tester = new Testuj();
        
        ArrayList<Proces> procesy = new ArrayList<>();
        ArrayList<Integer> kwantyCzasu = new ArrayList<>();
        
        KontrolaWejścia wejście;
        try {
            wejście = new KontrolaWejścia(args);
            procesy = wejście.wczytaj();
            kwantyCzasu = wejście.wczytajRR(procesy.size() + 2);
        } catch (FileNotFoundException ex) {
            System.err.println("Plik z danymi nie jest dostępny.");
        }
        
        if (!procesy.isEmpty()) {
            tester.przeprowadzTest(procesy, new FCFS());
            tester.przeprowadzTest(procesy, new SJF());
            tester.przeprowadzTest(procesy, new SRT());
            tester.przeprowadzTest(procesy, new PS());

            for (int i = 0; i < kwantyCzasu.size(); ++i) {
                tester.przeprowadzTest(procesy, new RR(kwantyCzasu.get(i)));
            }
        }

    }
    
}
