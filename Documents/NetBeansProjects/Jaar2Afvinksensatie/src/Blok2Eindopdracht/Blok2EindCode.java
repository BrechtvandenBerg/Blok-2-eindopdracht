//package
package Blok2Eindopdracht;
//imports

import java.awt.*;
import java.io.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.JComponent;

/**
 *
 * @author Brecht
 */
public class Blok2EindCode {

    public static File file1 = new File("C:/Users/Brecht/Desktop/School/2e jaar/Informatica/Praktijk/Blok 2/Eind opdr/gene_info"); // De file die geninformatie bevat
    public static File file2 = new File("C:/Users/Brecht/Desktop/School/2e jaar/Informatica/Praktijk/Blok 2/Eind opdr/taxID_to_organism.txt");// De file die tax id's en organismen bevat
    public static File file3 = new File("C:/Users/Brecht/Desktop/School/2e jaar/Informatica/Praktijk/Blok 2/Eind opdr/gene2pub.txt");// De file met de tax id's en pubmed artikelen
    public static HashMap org = new HashMap<String, Integer>(); // Hashmap met organisme en tax id
    public static HashMap info = new HashMap<Integer, Blok2construct>(); // Hashmap met tax id, gene id, symbol en description
    public static HashMap<Integer, HashSet<Integer>> pubmed = new HashMap<Integer, HashSet<Integer>>(); // Hashmap met tax id en pubmed artikelen
    public static Blok2Eindopdr gui = new Blok2Eindopdr(); // aanroepen van Blok2Eindopdr zodat hij in Blok2EindCode kan gebruikt worden
    public static Graphics paper; // om de jPanel aan te kunnen passen
    public static String org1;
    public static int taxid1;
    public static String pubid1;
    public static String org2;
    public static int taxid2;
    public static String pubid2;
    public static int pubnum1;
    public static int pubnum2;
    public static int number1;
    public static int number2;
    public static int overlapaantal;
    public static int totaal;
    public static float uniek1;
    public static float uniek2;
    public static float uniek3;

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here

        //GeneInfo();   De file wilt hij wel openen maar het tabel is m
        Pubmed(); // Aanroepen van Pubmed anders begint die nooit

        OrgGene(); // Aanroepen van OrgGene anders begint die nooit

        gui.setVisible(true); // De visibiliteit aanzetten anders komt de GUI niet tevoorschijn

    }

    public static void GeneInfo() {
        BufferedReader lezen = null;
        try {
            lezen = new BufferedReader(new FileReader(file1));
            String line;
            int teller = 0;
            while ((line = lezen.readLine()) != null) { //De zinnen met code hiervoor zijn allemaal voor het inlezen van de gen_info file
                if (teller != 0) { // Zorgen dat de eerste zin niet mee word genomen
                    String[] splitted = line.split("\t"); // splitten op tabs
                    int TaxId = Integer.parseInt(splitted[0]);
                    int GeneId = Integer.parseInt(splitted[1]);
                    String Symbol = splitted[2];
                    String Descrip = splitted[8];
                    info.put(TaxId, new Blok2construct(TaxId, GeneId, Symbol, Descrip));//tax id, gene id, symbol en description worden in de hashmap gestopt
                }
                teller += 1;
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (IOException i) {
            System.out.println("Something went wrong.");
        }
    }

    public static void Pubmed() {
        BufferedReader lezen = null;
        try {
            lezen = new BufferedReader(new FileReader(file3));
            String line;
            int teller = 0;
            while ((line = lezen.readLine()) != null) { //De zinnen met code hiervoor zijn allemaal voor het inlezen van de gene2pubmed file
                if (teller != 0) { // Zorgen dat de eerste zin niet mee word genomen
                    String[] splitted = line.split("\t"); // splitten op tabs
                    int TaxId = Integer.parseInt(splitted[0]);
                    int Pubmed = Integer.parseInt(splitted[2]);
                    if (!pubmed.containsKey(TaxId)) { // zorgt ervoor dat de pubmed id niet meerdere keren voor een tax id op worden genomen
                        pubmed.put(TaxId, new HashSet<Integer>());// stopt de tax id en bijbehorende pubmed id in de hashmap
                    }
                    pubmed.get(TaxId).add(Pubmed);
                }
                teller += 1;
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (IOException i) {
            System.out.println("Something went wrong.");
        }
    }

    public static void OrgGene() {
        BufferedReader lezen = null;
        try {
            lezen = new BufferedReader(new FileReader(file2));
            String line;

            while ((line = lezen.readLine()) != null) {//De zinnen met code hiervoor zijn allemaal voor het inlezen van de taxID_to_organism.txt file
                String[] splitted = line.split("\t");// splitten op tabs
                int TaxId = Integer.parseInt(splitted[0]);
                if (pubmed.containsKey(TaxId)) {
                    org.put(splitted[1], TaxId);
                    Blok2Eindopdr.choice1.add(splitted[1]);// De organismen worden aan de eerste choice box toegevoegd
                    Blok2Eindopdr.choice3.add(splitted[1]);// De organismen worden aan de tweede choice box toegevoegd
                }

            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (IOException i) {
            System.out.println("Something went wrong.");
        }
        Choicebox();
    }

    public static void Choicebox() {
        Blok2Eindopdr.choice1.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent event1) {
                try {
                    org1 = Blok2Eindopdr.choice1.getSelectedItem();//haalt het met de eerste geselecteerde choicebox organisme op
                    String orgtax1 = org.get(org1).toString();//zet org1 om naar een string
                    taxid1 = Integer.parseInt(orgtax1);//zet orgtax1 om in een integer
                    pubid1 = pubmed.get(taxid1).toString();// zoekt in de pubmed id de corresponderende tax id 

                } catch (NullPointerException ee) {
                    System.out.println("The chosen organism does not have an article on Pubmed.");
                }

            }
        });

        Blok2Eindopdr.choice3.addItemListener(new ItemListener() {//heet choice3 omdat de tweede choice box per ongelijk verwijderd was
            public void itemStateChanged(ItemEvent event2) {
                try {
                    org2 = Blok2Eindopdr.choice3.getSelectedItem();//haalt het met de tweede geselecteerde choicebox organisme op
                    String orgtax1 = org.get(org2).toString();//zet org2 om naar een string
                    taxid2 = Integer.parseInt(orgtax1);//zet orgtax2 om in een integer
                    pubid2 = pubmed.get(taxid2).toString();// zoekt in de pubmed id de corresponderende tax id 

                } catch (NullPointerException ece) {
                    System.out.println("The chosen organism does not have an article on Pubmed.");
                }

            }
        });
        Calculate();
    }

    public static void Calculate() {
        Blok2Eindopdr.jButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event3) {

                number1 = pubid1.split("\\w+").length - 1;// er word om de een of andere reden in de stap hiervoor er 1 bij opgeteld en word er hier weer afgetrokken
                number2 = pubid2.split("\\w+").length - 1;
                ArrayList<String> overlap = new ArrayList<String>();
                String pubstring = pubid1.replaceAll("[.,]", "");
                String[] tokens = pubstring.split(" ");
                for (int i = 0; i < tokens.length; i++) {
                    if (pubid2.indexOf(tokens[i]) >= 0) {
                        overlap.add(tokens[i]);
                    }
                }
                overlapaantal = overlap.toString().split("\\w+").length - 1;
                
                Blok2Eindopdr.jTextArea1.setText(//tekst word in de text area gezet
                        "Number of genes referenced in Pubmed:" + "\n" + org1 + ": " + number1 + "\n" + org2 + ": " + number2 + "\n" + "Overlap: " + overlapaantal);
                
                totaal = number1 + number2 + overlapaantal;// de percentages worden hier berekend
                uniek1 = number1*100 / totaal;
                uniek2 = number2*100 / totaal;
                uniek3 = overlapaantal*100 / totaal;
                
                paper = Blok2Eindopdr.jPanel1.getGraphics();// hier en hieronder word het panel gevuld met het venn diagram
                paper.clearRect(0, 0, 200, 200);
                paper.drawOval(0, 0, 160, 160);
                paper.drawOval(80, 0, 160, 160);
                paper.setColor(Color.BLUE);
                paper.drawString(org1 + " has " + uniek1 + "% uniques", 0, 10);
                paper.setColor(Color.GREEN);
                paper.drawString(org2 + " has " + uniek2 + "% uniques", 50, 20);
                paper.setColor(Color.RED);
                paper.drawString("Overlap: " + uniek3 + "%", 95, 80);

            }

        }
        );
    }
}