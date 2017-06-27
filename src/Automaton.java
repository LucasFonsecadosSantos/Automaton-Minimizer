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
public class Automaton {
    
    private List<String> states;
    private List<String> transitions;
    private List<String> finalStates;
    private String alphabet;
    private String initialState;

    public Automaton(List<String> automatonStates, String automatonAlphabet, List<String> automatonTransitions, String automatonInitialState, List<String> automatonFinalStates) {
        states = new ArrayList<String>();
        transitions = new ArrayList<String>();
        finalStates = new ArrayList<String>();
        this.states = automatonStates;
        this.transitions = automatonTransitions;
        this.finalStates = automatonFinalStates;
        this.alphabet = automatonAlphabet;
        this.initialState = automatonInitialState;
    }


}