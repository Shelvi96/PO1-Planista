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
public class RRComparator implements Comparator<Proces> {

    @Override
    public int compare(Proces p1, Proces p2) {
        if (p1.getCzasPojawienia() < p2.getCzasPojawienia())
            return -1;
        if (p1.getCzasPojawienia() > p2.getCzasPojawienia())
            return 1;
        if (p1.getId() < p2.getId())
            return -1;
        if (p1.getId() > p2.getId())
            return 1;
        return 0;
    }
    
}
