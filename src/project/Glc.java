package project;

import java.util.HashSet;

import project.exceptions.IncorrectGrammarException;

import java.util.HashMap;

public class Glc {

    private Integer count = 0;
    private HashMap<Integer, Production> productions;
    private HashSet<String> nonTerminals;
    private HashSet<String> terminals;
    private String startSymbol = null;

    /**
     * Cosntructor for the Glc class
     */
    public Glc(){
        this.productions = new HashMap<>();
        this.nonTerminals = new HashSet<>();
        this.terminals = new HashSet<>();
    }

    /**
     * Getter for the productions of the grammar
     * @return HashMap<Integer, Production>
     */
    public HashMap<Integer, Production> getProductions(){
        return this.productions;
    }

    /**
     * This method inserts a new production to the grammar
     * @param prod Production
     */
    public void insertProduction(Production prod){
        productions.put(++this.count, prod);
    }

    /**
     * This method inserts a new production with a defined key
     * @param key Integer
     * @param prod Production
     */
    public void insertProduction(Integer key, Production prod){
        productions.put(key, prod);
    }

    /**
     * This method inserts a new non terminal
     * @param nonTerminal String
     */
    public void insertNonTerminal(String nonTerminal){
        nonTerminals.add(nonTerminal);
    }

    /**
     * Getter for the Non terminals of the grammar
     * @return
     */
    public HashSet<String> getNonTerminals(){
        return this.nonTerminals;
    }

    /**
     * This method inserts a new terminal
     * @param terminal String
     */
    public void insertTerminal(String terminal){
        terminals.add(terminal);
    }

    /**
     * TGetter for the terminals of the grammar
     * @return
     */
    public HashSet<String> getTerminals(){
        return this.terminals;
    }

    /**
     * Setter for the Start Symbol of the grammar
     * @param symbol
     */
    public void setStartSymbol(String symbol){
        this.startSymbol = symbol;
    }

    /**
     * Getter for the Start Symbol of the grammar
     * @return
     */
    public String getStartSymbol(){
        return this.startSymbol;
    }

    /**
     * This method checks that the constructed grammar is valid
     * @throws IncorrectGrammarException If the grammar is not complete
     */
    public void checkValidity () throws IncorrectGrammarException {
        if(this.count == 0 ||
        this.productions.size() == 0 ||
        this.terminals.size() == 0 ||
        this.nonTerminals.size() == 0 ||
        this.startSymbol == null){
            throw new IncorrectGrammarException("La gramática ingresada presenta errores");
        }
    }

    /**
     * This methods converts the grammar to a String
     */
    @Override
    public String toString() {
        StringBuilder bobTheBuilder = new StringBuilder();
        bobTheBuilder.append("Starting symbol = " + this.startSymbol + "\n");
        bobTheBuilder.append("Productions: \n");
        for(Production prod : this.productions.values()){
            bobTheBuilder.append(prod.toString() + "\n");
        }
        bobTheBuilder.append("Terminals: \n");
        for(String s : this.terminals){
            bobTheBuilder.append(s + "\n");
        }
        bobTheBuilder.append("Non terminals: \n");
        for(String s : this.nonTerminals){
            bobTheBuilder.append(s + "\n");
        }
        return bobTheBuilder.toString();
    }
}