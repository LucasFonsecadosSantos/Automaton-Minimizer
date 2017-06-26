/**
 *  @(#) Main.java 1.0 June 26, 2017.
 *  AUTOMATON MINIMAZER SOFTWARE
 *  Formal Languages and Automatons Pratical Project.
 *  GCCXXX - Federal University of Lavras.
 *
 *
 *  @author Lucas Fonseca dos Santos  (201712078)
 *  @author Leonardo 
 *  @author Tulio de Oliveira Taveira (201621232)
 */
package utils;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *  This class is responsible to manipulate the input and output data
 *  file.
 */
public class Data {

    /**
     *  Java BufferedReader object declaration to input file.
     */
    private BufferedReader inFile;

    private ArrayList<String> descriptionArray;
    /**
     *  Data object constructor. He receives by parameter a string name
     *  input file to after that, read this file.
     *
     *  @param inputFileName String name of input text file.
     */
    /*public Data() {
        try {
            inFile = new BufferedReader(new FileReader("fire.txt"));
        }catch(Exception ex) {

        }
        descriptionArray = new ArrayList<String>();
    }*/

    /**
     *  This method is responsible to return all description of automaton
     *  to the caller method. He reads the input text file, line by line and
     *  save it in Java Array List data structure.
     *
     *  @return ArrayList<String> Array list data structure that contains the automaton description.
     */
    public ArrayList<String> returnAutomatonDescription() {
        
        try{
            while(inFile.readLine() != null) {
                try {
                    descriptionArray.add(inFile.readLine());
                }catch(IOException ex) {
                
                }
            }
        }catch(Exception ex) {

        }

        return descriptionArray;
    }

}