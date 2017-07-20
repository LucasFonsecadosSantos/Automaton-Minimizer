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
package app;

import java.util.List;

import javax.sound.midi.Soundbank;

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

    /**
     * 
     */
    public void minimize() {

        boolean[][] statesMatrix = new boolean[this.states.size()][this.states.size()];
        List<Integer> index_i = new ArrayList<Integer>();
        List<Integer> index_j = new ArrayList<Integer>();
        List<Boolean> d = new ArrayList<Boolean>();
        List<String> motivo = new ArrayList<String>();

        int trueCounter = 0;
        for(int i=0; i < this.states.size(); i++) {
            for(int j=0; j < this.states.size(); j++) {
                if((i == j) || (i > j)) {
                    statesMatrix[i][j] = false;
                }else {
                    statesMatrix[i][j] = true;
                    trueCounter++;
                    index_i.add(i);
                    index_j.add(j);
                }
            } 
        }
        
        String[] s = new String[trueCounter];
        for(String st : s ) {
            st = " ";
        }

        for(int i=0; i < trueCounter; i++) {
            if(isFinalState(this.states.get(index_i.get(i))) && (isFinalState(this.states.get(index_j.get(i))))) {
                d.add(true);
                motivo.add("");
            }else if(!isFinalState(this.states.get(index_i.get(i))) && (!isFinalState(this.states.get(index_j.get(i))))) {
                d.add(true);
                motivo.add("");
            }else if((isFinalState(this.states.get(index_i.get(i))) && !isFinalState(this.states.get(index_j.get(i)))) || (!isFinalState(this.states.get(index_i.get(i))) && isFinalState(this.states.get(index_j.get(i))))){
                d.add(false);
                motivo.add("Final/Nao Final");
            }
        }

        String[][] tokens = transitionPharser();
        String str1 = "";
        String str2 = "";
        
        for(int i=0; i < trueCounter; i++) {
            for(int j=0; j < this.alphabet.length(); j++) {
                
                str1 = returnNextState(this.states.get(index_i.get(i)), String.valueOf(this.alphabet.charAt(j)));
                str2 = returnNextState(this.states.get(index_j.get(i)), String.valueOf(this.alphabet.charAt(j)));
        
                if(isFinalState(str1) && isFinalState(str2)) {
                    for(int k=0; k < trueCounter; k++) {
                        if(this.states.get(index_i.get(k)).equals(str1) && this.states.get(index_j.get(k)).equals(str2)) { //quando acha o estado certo 
                            if(motivo.get(k).equals("")){                   // se o motivo nao existir
                                s[k]  += "["+index_i.get(i)+" , "+index_j.get(i)+"] "; // grava no dependencia
                             }
                            else{ // se nao estiver vazia, propaga ate a posicao
                                if(motivo.get(i).equals("")){
                                    motivo.add(i,"prop" + String.valueOf(str1) + "," + String.valueOf(str2));
                                    break;
                                }
                            }    
                            break;
                        }
                    }
                }else if(!isFinalState(str1) && !isFinalState(str2)) {
                    for(int k=0; k < trueCounter; k++) {
                        if(this.states.get(index_i.get(k)).equals(str1) && this.states.get(index_j.get(k)).equals(str2)) {
                            if(motivo.get(k).equals("")){ // se o motivo nao existir pode colocar em dependencia
                                s[k]  += "["+index_i.get(i)+" , "+index_j.get(i)+"] ";
                            }else{ // se existir, propagar
                                if(motivo.get(i).equals("")){
                                    motivo.add(i,"prop" + String.valueOf(str1) + "," + String.valueOf(str2));
                                    break;
                                }
                            }
                            break;
                        }
                    }
                }if((isFinalState(str1) && !isFinalState(str2)) || (!isFinalState(str1) && isFinalState(str2))){
                    if(motivo.get(i).equals("Final/Nao Final")){ // se for Final/ nao final, nao fazer nada
                        break;
                    }else{
                        if(motivo.get(i).equals("")){
                            motivo.add(i, String.valueOf(this.alphabet.charAt(j)) + "[" + str1 + "," + str2 + "]");
                             break;
                        }
                    }
                }
                
            }
        }

        for(int i=0; i < trueCounter; i++) {
            
                System.out.println(s[i]);
        
        }
        
        for(int i=0; i < trueCounter; i++) {
            
                System.out.println(motivo.get(i));
        
        }
    }

    private String returnNextState(String currentState, String alphabetLetter) {
        String[][] tokens = transitionPharser();
        for(int i=0; i < this.transitions.size(); i++) {
            if(tokens[i][0].equals(currentState)) {
                if(tokens[i][1].equals(alphabetLetter)) {
                    return tokens[i][2];
                }
            }else {
                continue;
            }
        }
        return null;
    }

    private String[][] transitionPharser() {
        String[][] tokens = new String[this.transitions.size()][3];
        String[] aux_1 = new String[2];
        String[] aux_2 = new String[2];
        
        for(int k = 0; k < this.transitions.size(); k ++){
            for(int i=0; i < this.transitions.size(); i++) {
                aux_1 = this.transitions.get(i).split(",");
                aux_1[0] = aux_1[0].replace("(", "");
                aux_1[0] = aux_1[0].trim();
                aux_2 = aux_1[1].split("->");
                aux_2[0] = aux_2[0].trim();
                aux_2[1] = aux_2[1].replace(")", "");
                aux_2[1] = aux_2[1].trim();
                    tokens[i][0] = aux_1[0];
                    tokens[i][1] = aux_2[0];
                    tokens[i][2] = aux_2[1]; 
            }
        }
        return tokens;
    }    

    /**
     * This private method is responsible for verifys if
     * the state received by parameter is a final state.
     * It returns true if yes.
     * 
     * @param state State String to compare.
     * @return boolean Result of comparable.
     */
    private boolean isFinalState(String state) {
        for(String s : this.finalStates) {
            if(s.equals(state)) {
                return true;
            }
        }
        return false;
    }

}