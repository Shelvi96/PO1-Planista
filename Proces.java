/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planista;

/**
 *
 * @author konrad
 */
public class Proces {
    private final int id;
    private final int czasPojawienia;
    private final int zapotrzebowanie;
    private double czasZakończenia;
    private double postęp;

    public Proces(int id, int czasPojawienia, int zapotrzebowanie) {
        this.id = id;
        this.czasPojawienia = czasPojawienia;
        this.zapotrzebowanie = zapotrzebowanie;
        this.czasZakończenia = -1;
        this.postęp = 0;
    }
    
    public double pozostałyCzas() {
        return this.getZapotrzebowanie() - this.getPostęp();
    }
    
    public int getId() {
        return id;
    }

    public int getCzasPojawienia() {
        return czasPojawienia;
    }

    public int getZapotrzebowanie() {
        return zapotrzebowanie;
    }

    public double getCzasZakończenia() {
        return czasZakończenia;
    }

    public double getPostęp() {
        return postęp;
    }
    
    public void setCzasZakończenia(double czasZakończenia) {
        this.czasZakończenia = czasZakończenia;
    }

    public void setPostęp(double postęp) {
        this.postęp = postęp;
    }
    
}
