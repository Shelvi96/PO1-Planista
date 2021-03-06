/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planista;

import java.util.Comparator;

/**
 *
 * @author konrad
 */
public class SRTxtraComparator implements Comparator<Proces> {

    @Override
    public int compare(Proces p1, Proces p2) {
        if (p1.getZapotrzebowanie() - p1.getPostęp() < p2.getZapotrzebowanie() - p2.getPostęp())
            return -1;
        if (p1.getZapotrzebowanie() - p1.getPostęp() > p2.getZapotrzebowanie() - p2.getPostęp())
            return 1;
        if (p1.getId() < p2.getId())
            return -1;
        if (p1.getId() > p2.getId())
            return 1;
        return 0;
    }
    
}