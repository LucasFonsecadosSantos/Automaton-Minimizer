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
import utils.Tokenizer;
import utils.Data;
import java.util.List;
import java.util.ArrayList;

/**
 * Main class
 * This class is responsible to execute the software.
 * He creates the algorithm objects as: Automaton, data,
 * tokenizer and others and execute the stages of algorithm.
 */
public class Main {
    
    public static void main(String[] args) {
        Data dt = new Data(args[0]);
        
        List<String>data = dt.returnDescription();

        Tokenizer tk = new Tokenizer(data);
        Automaton m1 = new Automaton(tk.returnStates(), tk.returnAlphabet(), tk.returnTransitions(), tk.returnInitialState(), tk.returnFinalStates());

        m1.minimize();

    }
}