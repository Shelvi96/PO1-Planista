/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planista;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author konrad
 */
public class KontrolaWejścia {
    
    private File file;
    private Scanner sc;

    public KontrolaWejścia(String[] args) throws FileNotFoundException {
        
        this.sc = new Scanner(System.in);
        
        if (args.length > 0) {
            this.file = new File(args[0]);
            this.sc = new Scanner(file);
        }
        else
            System.err.println("Plik z danymi nie jest dostępny.");
        
    }
    
    private void wypiszBłąd (int numerLinii, String wiadomość) {
        
        System.err.println("Błąd w linii " + numerLinii + ": " + wiadomość);
        
    }
    
    public ArrayList<Proces> wczytaj () {
        
        ArrayList<Proces> wejście = new ArrayList<>();
        
        String l = sc.nextLine();
        Scanner scc = new Scanner(l);
        int n;
        
        if (scc.hasNextInt()) {
            
            n = scc.nextInt();
            if (scc.hasNext()) {
                wypiszBłąd(1, "Za dużo danych.");
                return new ArrayList<>();
            }
            
        }
        else {
            if (!scc.hasNext())
                wypiszBłąd(1, "Za mało danych.");
            else
                wypiszBłąd(1, "Zły typ danych.");
            return new ArrayList<>();
        }
        
        for (int i = 0; i < n; ++i) {
            
            l = sc.nextLine();
            scc = new Scanner(l);
            
            int x, y;
            if (scc.hasNextInt()) {
                
                x = scc.nextInt();
                
                if (x < 0) {
                    wypiszBłąd(i+2, "Zła wartość.");
                    return new ArrayList<>();
                }
                
                if (scc.hasNextInt()) {
                    
                    y = scc.nextInt();
                    
                    if (y < 1) {
                        wypiszBłąd(i+2, "Zła wartość.");
                        return new ArrayList<>();
                    }
                    
                    if (scc.hasNext()) {
                        wypiszBłąd(i+2, "Za dużo danych.");
                        return new ArrayList<>();
                    }
                    
                    wejście.add(new Proces(i+1, x, y));
                    
                }
                else if (scc.hasNext()) {
                    wypiszBłąd(i+2, "Zły typ danych.");
                    return new ArrayList<>();
                }
                else {
                    wypiszBłąd(i+2, "Za mało danych.");
                    return new ArrayList<>();
                }
                
            }
            else if (scc.hasNext()) {
                wypiszBłąd(i+2, "Zły typ danych.");
                return new ArrayList<>();
            }
            else {
                wypiszBłąd(i+2, "Za mało danych.");
                return new ArrayList<>();
            }
            
        }

        return wejście;
        
    }
    
    public ArrayList<Integer> wczytajRR (int numerLinii) {
        
        ArrayList<Integer> wejście = new ArrayList<>();
        
        String l = sc.nextLine();
        Scanner scc = new Scanner(l);
        int n;
        
        if (scc.hasNextInt()) {
            
            n = scc.nextInt();
            if (scc.hasNext()) {
                wypiszBłąd(numerLinii, "Za dużo danych.");
                return new ArrayList<Integer>();
            }
            
        }
        else {
            
            if (!scc.hasNext())
                wypiszBłąd(numerLinii, "Za mało danych.");
            else
                wypiszBłąd(numerLinii, "Zły typ danych.");
            
            return new ArrayList<Integer>();
            
        }
        
        l = sc.nextLine();
        scc = new Scanner(l);
        int q;
        
        for (int i = 0; i < n; ++i) {
            
            if (scc.hasNextInt()) {
                q = scc.nextInt();
                wejście.add(q);
            }
            else if (scc.hasNext()) {
                wypiszBłąd(3 + numerLinii, "Zły typ danych.");
                return new ArrayList<Integer>();
            }
            else { 
                wypiszBłąd(3 + numerLinii, "Za mało danych.");
                return new ArrayList<Integer>();
            }
            
        }
        
        if (scc.hasNext()) {
            wypiszBłąd(3 + numerLinii, "Za dużo danych.");
            return new ArrayList<Integer>();
        }
        
        return wejście;
                
    }
}
