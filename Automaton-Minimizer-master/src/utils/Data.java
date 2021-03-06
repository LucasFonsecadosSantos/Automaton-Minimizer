/**
 * AUTOMATON-MINIMIZER 1.0 June 26, 2017
 * Formal Languages and Atomatons pratical project 2017.
 * GCCXXX - DCC - Federal University of Lavras.
 * 
 * This file the Data implementation class.
 * 
 * @author Lucas Fonseca dos Santos      (201712078)
 * @author Leonardo Carvalho de Oliveira (201420432)
 * @author Tulio de Oliveira Taveira     (201621232)
 */
package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;

/**
 * This class is responsible to manipulate the data files inputed by user.
 * He reads the file, recovery the information, returns it and after that,
 * in the next algorithm stage he writes the result in two others files.
 */
public class Data {

    /**
     * Java list string data structure attribute.
     */
    private ArrayList<String> descriptionArray;

    /**
     * Java BufferedReader object attribute.
     */
    private BufferedReader inFile;

    /**
     * Java BuffereWriter object attribute.
     */
    private BufferedWriter outFile_1;
    
    /**
     * Java BufferedWriter object attribute.
     */
    private BufferedWriter outFile_2;

    /**
     * Data object constructor. He receives 3 strings with name and path files.
     * He creates 3 new objects to read and write this files. If there is an exception,
     * the method will try with try catch clause.
     * 
     * @param descriptionFile Input text file String name/path.
     * @param tableFile Output text file with a algorithm minimizer table.
     * @param minimizedAutomaton Output text file with the result of algorithm execution.
     */
    public Data(String descriptionFile, String tableFile, String minimizedAutomaton) {
        descriptionArray = new ArrayList<String>();
        try {
            inFile = new BufferedReader(new FileReader(descriptionFile));
            //if(!new File(tableFile).exists()) {
             //   new File("../table.txt").createNewFile();
           // }
            outFile_1 = new BufferedWriter(new FileWriter(tableFile));
            //if(!new File(minimizedAutomaton).exists()) {
             //   new File("afd_minimized.txt").createNewFile();
           // }
            outFile_2 = new BufferedWriter(new FileWriter(minimizedAutomaton));
        }catch(IOException ioex) {

        }
    }

    
    public Data(String descriptionFile) {
        descriptionArray = new ArrayList<String>();
        try {
            inFile = new BufferedReader(new FileReader(descriptionFile));
        }catch(IOException ioex) {

        }
    }

    /**
     * This method is reponsible to get the automaton description information
     * input. He opens the file by BufferedReader object method and verifys the
     * file validation. After that, he reads line by line and store the lines in
     * an array list string data structure. This method returns that array.
     * 
     * @return ArrayList<String> Java ArrayList data structure that contains all automaton description.
     */
    public ArrayList<String> returnDescription() {
        boolean control = false;
        String tmpString;

        try {
            while((tmpString = inFile.readLine()) != null) {
                if(tmpString.equals("(")) {
                    control = !control;
                }
                if(control) {
                    tmpString = tmpString.trim();
                    descriptionArray.add(tmpString);
                }
            }
        }catch(IOException ioex) {

        }
        return descriptionArray;
    }

    /*public void printTable(List<Integer> index_i, List<Integer> index_j, List<Boolean> d, List<String> motivo) {
        for(int i=0; i < index_i.size(); i++) {
            
        }
    }*/
}