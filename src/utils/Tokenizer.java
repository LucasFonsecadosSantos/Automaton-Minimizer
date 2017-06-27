/**
 * AUTOMATON-MINIMIZER 1.0 June 26, 2017
 * Formal Languages and Atomatons pratical project 2017.
 * GCC122 - DCC - Federal University of Lavras.
 * 
 * This file the Main implementation class.
 * 
 * @author Lucas Fonseca dos Santos      (201712078)
 * @author Leonardo Carvalho de Oliveira (201420432)
 * @author Tulio de Oliveira Taveira     (201621232)
 */
package utils;

import java.util.List;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

/**
 * This class is responsible for the data strings treatment.
 * His function on the software is return the automaton states,
 * final states, initial states, transitions and other informations
 * formatted, ready to be processed.
 */
public class Tokenizer {
    
    /**
     * Java list string data structure attribute.
     */
    private List<String> contentData;
    
    /**
     * Java list string data structure attribute.
     */
    private List<String> finalStates;

    /**
     * Tokenizer object constructor. He receives a one list
     * data structure by parameter with all data load by file
     * at class Data.
     * 
     * @param content Java list data structure with automaton data information.
     */
    public Tokenizer(List content) {
        this.contentData = content;
        finalStates = new ArrayList<String>();
    }

    /**
     * This method is responsible for the string states conjunction treatment.
     * He removes all characters unwanted to the automaton processing. He replaces
     * that characters on string and stores in the states list to be returned
     * by method.
     * 
     * @return List Java list data struct with all automaton states.
     */
    public List returnStates() {
        String line = "";
        for(String s : this.contentData) {
            if(s.contains("{")) {
                line = s;
                break;
            }
        }
        String[] tokenzinhos = line.split(",");
        for(String s : tokenzinhos) {
            s = s.replace("{", "");
            s = s.replace("}", "");
            s = s.replace(";", "");
            s = s.replace(",", "");
            s = s.replace(" ", "");
            finalStates.add(s);
        }
        return finalStates;
    }

    /**
     * This method is responsible for load data file and returns
     * the alphabet that automaton will work.
     * He indentifies the last character of last line and after that,
     * decides if the current line is alphabet.
     * 
     * @return String Automaton alphabet.
     */
    public String returnAlphabet() {
        String line="";
        boolean control = false;
        for(String s : this.contentData) {
            if(control) {
                line = s;
                break;
            }
            if(s.contains("},")) {
                control = !control;
            }
        }
        line = line.replace("{", "");
        line = line.replace("}", "");
        line = line.replace(",", "");
        line = line.replace(";", "");
        return line;
    }

}