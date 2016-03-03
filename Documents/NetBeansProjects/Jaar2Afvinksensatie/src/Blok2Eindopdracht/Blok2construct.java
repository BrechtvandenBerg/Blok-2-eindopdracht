/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Blok2Eindopdracht;

/**
 *
 * @author Brecht
 */
public class Blok2construct extends Blok2EindCode {
    private int taxId;
    private int geneId;
    private String symbol;
    private String descrip;

    public Blok2construct(int t, int g, String s, String d){
        setTaxId(t);
        setGeneId(g);
        setSymbol(s);
        setDescrip(d);
    }

    /**
     * @return the taxId
     */
    public int getTaxId() {
        return taxId;
    }

    /**
     * @param taxId the taxId to set
     */
    public void setTaxId(int taxId) {
        this.taxId = taxId;
    }

    /**
     * @return the geneId
     */
    public int getGeneId() {
        return geneId;
    }

    /**
     * @param geneId the geneId to set
     */
    public void setGeneId(int geneId) {
        this.geneId = geneId;
    }

    /**
     * @return the symbol
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * @param symbol the symbol to set
     */
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    /**
     * @return the descrip
     */
    public String getDescrip() {
        return descrip;
    }

    /**
     * @param descrip the descrip to set
     */
    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }
    }