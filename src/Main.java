/**
<<<<<<< HEAD
 * AUTOMATON-MINIMIZER 1.0 June 26, 2017
 * Formal Languages and Atomatons pratical project 2017.
 * GCCXXX - DCC - Federal University of Lavras.
 * 
 * This file the Main implementation class.
 * 
 * @author Lucas Fonseca dos Santos      (201712078)
 * @author Leonardo Carvalho de Oliveira (201420432)
 * @author Tulio de Oliveira Taveira     (201621232)
 */
import utils.Data;
import java.util.ArrayList;

/**
 * Main class
 * This class is responsible to execute the software.
 * He creates the algorithm objects as: Automaton, data,
 * tokenizer and others and execute the stages of algorithm.
 */
public class Main {

    private static ArrayList<String> data;
    private ArrayList<String> statesConjunction;
    private ArrayList<String> finalStatesConjunction;
    private ArrayList<String> transitionsConjunction;
    
    public static void main(String[] args) {
        Data dt = new Data(args[0], args[1], args[2]);
        
        data = new ArrayList<String>();
        data = dt.returnDescription();

        for(String s : data) {
            System.out.print(s);
        }

        /*Tokenizer tk = new Tokenizer();

        statesConjunction = tk.returnStates();
        finalStatesConjunction = tk.returnFinalStates();
        transitionsConjunctions = tk.returnTransitions();*/

    }
}