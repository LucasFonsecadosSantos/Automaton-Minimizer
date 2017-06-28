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

/**
 * This class describes the automaton object and implements his methods.
 * The automaton represents here, is a deterministic finite automaton, then
 * he is built by a quintuple, where the object constructor receives: a
 * automaton states string list, automaton alphabet string, automaton
 * transitions string list, automaton initial state string and automaton
 * final states string list.
 */
public class Automaton {
    
    /**
     * States set of automaton attribute;
     */
    private List<String> states;

    /**
     * States transitions set of automaton attribute;
     */
    private List<String> transitions;

    /**
     * Final states set of automaton attribute;
     */
    private List<String> finalStates;

    /**
     * Alphabet set accepted by automaton attribute;
     */
    private String alphabet;

    /**
     * Initial state of automaton attribute;
     */
    private String initialState;

    /**
     * Automaton object constructor. He receives states set,
     * alphabet set, transitions set, initial state and finals
     * states or states of acceptance of automaton.
     * 
     * @param automatoStates Java list data structure with automaton states.
     * @param automatonAlphabet String with the alphabet accept by automaton.
     * @param automatonTransitions Java list data structure with all automaton transitions.
     * @param automatonInitialState String with the automaton initial state.
     * @param automatonFinalState Java list data structure with all states of automaton acceptance.
     */
    public Automaton(
                        List<String> automatonStates,
                        String automatonAlphabet,
                        List<String> automatonTransitions,
                        String automatonInitialState,
                        List<String> automatonFinalStates
                    ) {
        states = new ArrayList<String>();
        transitions = new ArrayList<String>();
        finalStates = new ArrayList<String>();
        this.states = automatonStates;
        this.transitions = automatonTransitions;
        this.finalStates = automatonFinalStates;
        this.alphabet = automatonAlphabet;
        this.initialState = automatonInitialState;
    }

    public void minimize() {
        boolean[][] statesMatrix = new boolean[this.states.size()][this.states.size()];
        
        int count = 0;
        List<Integer> positionsToCheckOnMatrix = new ArrayList<Integer>();
        boolean control = false;
        String[] tokens = new String[2];
        for(int i=0; i < this.transitions.size(); i++) {
            count = 0;
            for(int j=0; j < this.states.size(); j++) {

               tokens = this.transitions.get(i).split(",");
               
               if(tokens[0].contains(this.states.get(j))) {
                   positionsToCheckOnMatrix.add(j);
                   System.out.println("PRIMEIRA: "+j + this.states.get(j));
               }
               if(tokens[1].contains(this.states.get(j))) {
                   if(tokens[0] != tokens[1]) {
                        positionsToCheckOnMatrix.add(j);
                        System.out.println("SEGUNDA: "+j + this.states.get(j));
                   }else {
                       positionsToCheckOnMatrix.remove(positionsToCheckOnMatrix.size()-1);
                       continue;
                   }
               }
               
               
               
            }
            if(count == 1) {
                //positionsToCheckOnMatrix.remove(positionsToCheckOnMatrix.get(positionsToCheckOnMatrix.size()));
            }
        }

        System.out.println(positionsToCheckOnMatrix);
    }

}