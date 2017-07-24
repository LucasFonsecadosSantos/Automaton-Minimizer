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

        boolean[] control = new boolean[trueCounter];
        
        for(boolean b : control) {
            b = true;
        }
        
        for(int i=0; i < trueCounter; i++) {
            if(isFinalState(this.states.get(index_i.get(i))) && (isFinalState(this.states.get(index_j.get(i))))) {
                d.add(true);
                motivo.add("");
                control[i] = false;
            }else if(!isFinalState(this.states.get(index_i.get(i))) && (!isFinalState(this.states.get(index_j.get(i))))) {
                d.add(true);
                motivo.add("");
                control[i] = false;
            }else if((isFinalState(this.states.get(index_i.get(i))) && !isFinalState(this.states.get(index_j.get(i)))) || (!isFinalState(this.states.get(index_i.get(i))) && isFinalState(this.states.get(index_j.get(i))))){
                d.add(false);
                control[i] = true;
                motivo.add("Final/Nao Final");
                System.out.println(i);
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
                            if(control[i] ==  true){ // se for Final/ nao final, nao fazer nada
                                break;
                            }
                            else{
                                if(control[k] == false){   
                                    if ((this.states.get(index_i.get(i)).equals(str1) && this.states.get(index_j.get(i)).equals(str2)) ||  (this.states.get(index_i.get(i)).equals(str2) && this.states.get(index_j.get(i)).equals(str1))){
                                        break; // indo pro mesmo estado
                                    }              // se o motivo nao existir no k
                                    s[k]  += "["+index_i.get(i)+" , "+index_j.get(i)+"]"; // grava no dependencia
                                }
                                else{ // se nao estiver vazia, propaga ate a posicao
                                    if(control[i] == false){
                                        motivo.add(i,"prop" + String.valueOf(str1) + "," + String.valueOf(str2));
                                        System.out.println("propagou");
                                        control[i] = true;
                                        break;
                                    }
                                }    
                                break;
                            }
                        }
                    }
                }else if(!isFinalState(str1) && !isFinalState(str2)) {
                    for(int k=0; k < trueCounter; k++) {
                        if(this.states.get(index_i.get(k)).equals(str1) && this.states.get(index_j.get(k)).equals(str2)) {
                            if(control[i] ==  true){ // se for Final/ nao final, nao fazer nada
                                break;
                            }else{ // estado que voce esta nao estiver motivo
                                if(control[k] == false){ // se o motivo nao existir pode colocar em dependencia
                                    if ((this.states.get(index_i.get(i)).equals(str1) && this.states.get(index_j.get(i)).equals(str2)) ||  (this.states.get(index_i.get(i)).equals(str2) && this.states.get(index_j.get(i)).equals(str1))){
                                        break; // indo pro mesmo estado
                                    }
                                    s[k]  += "["+index_i.get(i)+" , "+index_j.get(i)+"]";
                                }else{ // se existir, propagar
                                    if(control[i] == false){
                                        motivo.add(i,"prop" + String.valueOf(str1) + "," + String.valueOf(str2));
                                        control[i] = true;
                                        break;
                                    }
                                }
                                break;
                            }
                        }
                    }
                }if((isFinalState(str1) && !isFinalState(str2)) || (!isFinalState(str1) && isFinalState(str2))) {
                    if(control[i] == true) { // se for Final/ nao final, nao fazer nada
                        break;
                    }
                    if(control[i] == false) {
                        motivo.add(i, String.valueOf(this.alphabet.charAt(j)) + "[" + str1 + "," + str2 + "]");
                        control[i] = true;
                        break;
                    }
                }

                if(isFinalState(this.states.get(index_i.get(i))) && (isFinalState(this.states.get(index_j.get(i))))) {
                    if(motivo.get(i).equals("Final/Nao Final")) {
                        motivo.add(i,"");
                    }
                    System.out.println("entrou aqui" + index_i.get(i) + " " + index_j.get(i));
                    control[i] = false;
                }else if(!isFinalState(this.states.get(index_i.get(i))) && (!isFinalState(this.states.get(index_j.get(i))))) {
                    if(motivo.get(i).equals("Final/Nao Final")) {
                        motivo.add(i,"");
                    }
                    control[i] = false;
                }else if((isFinalState(this.states.get(index_i.get(i))) && !isFinalState(this.states.get(index_j.get(i)))) || (!isFinalState(this.states.get(index_i.get(i))) && isFinalState(this.states.get(index_j.get(i))))){
                    motivo.add(i,"Final/Nao Final");
                    control[i] = true;
                }
            }
        }
        
        /**
         * This loop runs the whole depedency set and 
         */
        for(int ka=trueCounter-1; ka >= 0; ka--) {
             // eh invalido
             
                if(control[ka] == true){
                    System.out.println("ka");
                    String aux = dependencyPharser(s[ka]);
                    if(aux == null) {
                        continue;
                    }else {
                        //Aqui cai se existe o S para o index atual;
                            String x = aux; // recebendo o token pos i, para separar e propagar
                            int count_i = Character.getNumericValue(x.charAt(0));
                            int count_j = Character.getNumericValue(x.charAt(1));
                             for (int ks = 0; ks < trueCounter; ks++ ){
                                   if((index_i.get(ks) == count_i && index_j.get(ks) == count_j) || (index_i.get(ks) == count_j && index_j.get(ks) == count_i)){
                                        if(control[ks] ==  false){
                                            motivo.remove(ks);
                                            motivo.add(ks," prop [" +index_i.get(ka)+ " , " + index_j.get(ka) + " ]" );
                                            control[ks] = true;
                                            break;
                                        }   
                                    }
                            }
                        }
                    }
                
                }

            for(int i=0; i < trueCounter; i++) {
            
               System.out.println(motivo.get(i));
            }

            // juntar os estads iguais

            for (int cont = 0; cont < trueCounter; cont++){
                if (control[cont] ==  false){ // se for falso é pq nao tem motivo então, é igual
                    //junta os dois estado e transições
                    //procurar pelo indice_i e concatenar o indice_j nas transições
                    //procurar o indice_i e concatenar o indice_j e excluir o incidi_j
                    //printar no arquivo 

                }
            }
    }

    /* assim que estiver pronto printar no arquivo as tabelas*/








    /**
     * This private method breaks the current dependency data in some tokens.
     * It cleans the tokens generated by this method. It replaces undesirable
     *  characters
     *
     * @param
     * @return 
     */
    private String dependencyPharser(String s) {
            System.out.println("Pharser String: "+s);
                if(s == null) return null;
                String j = " ";
                if(s!=null && !s.trim().equals("")) {
                    if(!s.contains("[")) return null;
                    s = s.replace("null", "");
                    System.out.println(s + " s bosta");
                    String[] tokens = s.split("]");
                    
                    for(String str : tokens) {
                        str = str.replace("[", "");
                        str = str.replace("]", "");
                        str = str.replace(",", "");
                        str = str.replace(" ", "");
                        System.out.println(str);
                        j = str;
                    }
                    return j;
                }
        return j;
    }
            
    /**
     * This method is responsible for the return the next state of a current
     * state reads an alphabet letter. It receives an alphabet letter and the
     * current state and after that, creates a transitions tokens array by
     * transition pharser method.
     * Then, it verifys if tokens array contains the current state. If yes,
     * it also verify if the current token contains a alphabet letter. After
     * this, it returns the state and alphabet letter.
     * 
     * @param currentState A string current state that will be compared.
     * @param alphabetLetter A alphabet set that will be compared.
     * @return String A state string returned when the current 
     */
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

    /**
     * This method breaks the transition expression in tokens. This
     * tokens are two states and a alphabet letter. There is one current
     * state that reading a some alphabet element, goes to the next state.
     * 
     * @return String[][] A multidimensional array that contains the current transition tokens.
     */
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