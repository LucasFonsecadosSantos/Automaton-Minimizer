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

import java.util.List;
import java.util.ArrayList;

public class State {

    private String currentState;
    
    private String[] alphabetElements;

    private String alphabet;

    /**
     * State object constructor. It receives a current state String
     * and alphabet String by parameters, after that, he creates a new
     * String alplhabet states array.
     *
     * @param currentState String with current state.
     * @param alplhabet String with all alphabet elements.
     */
    public State(String currentState, String alphabet) {
        alphabetElements = new String[alphabet.length()];
        this.currentState = currentState;
        this.alphabet = alphabet;
    }

    public void setNextState(String alphabetLetter, String nextState) {
        for(int i=0; i < alphabet.length(); i++) {
            if(alphabetLetter.equals(String.valueOf(alphabet.charAt(i)))) {
                alphabetElements[i] = nextState;
            }
        }
    }

    public String getNextState(String alphabetLetter) {
        for(int i=0; i < alphabet.length(); i++) {
            if(alphabetLetter.equals(String.valueOf(alphabet.charAt(i)))) {
                return alphabetElements[i];
            }
        }
        return null;
    }
}