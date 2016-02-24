/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jaar2afvinksensatie;

/**
 *
 * @author Brecht
 */
import java.util.Collections;
import java.util.ArrayList;
public class Test2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    Jaar2Afvinksensatie g1 = new Jaar2Afvinksensatie("1234","p41","12p56.9");
    Jaar2Afvinksensatie g2 = new Jaar2Afvinksensatie("423","p10","12p6.9");
    Jaar2Afvinksensatie g3 = new Jaar2Afvinksensatie("14","p32","12p56.9");
    Jaar2Afvinksensatie g4 = new Jaar2Afvinksensatie("16244","p15","12p56.9");
        System.out.println(g1);
        System.out.println(g2);
        System.out.println(g3);
        System.out.println(g4);
        
        ArrayList<Jaar2Afvinksensatie> arrgen = new ArrayList<Jaar2Afvinksensatie>();
        
        arrgen.add(g1);
        arrgen.add(g2);
        arrgen.add(g3);
        arrgen.add(g4);
    
        System.out.println(arrgen);
        Collections.sort(arrgen,null);
        System.out.println("Na sorTERING:");
        System.out.println(arrgen);
    }
    
}
