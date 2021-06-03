package project;

import java.util.HashSet;
import java.util.HashMap;

public class Glc {

    private Integer count = -1;
    private HashMap<Integer, Production> productions;
    private HashSet<String> nonTerminals;
    private HashSet<String> terminals;
    private String startSymbol = null;

    public Glc(){
        this.productions = new HashMap<>();
        this.nonTerminals = new HashSet<>();
        this.terminals = new HashSet<>();
    }

    public HashMap<Integer, Production> getProductions(){
        return this.productions;
    }

    public void insertProduction(Production prod){
        productions.put(++this.count, prod);
    }

    public void insertNonTerminal(String nonTerminal){
        nonTerminals.add(nonTerminal);
    }

    public HashSet<String> getNonTerminals(){
        return this.nonTerminals;
    }

    public void insertTerminal(String terminal){
        nonTerminals.add(terminal);
    }

    public HashSet<String> getTerminals(){
        return this.terminals;
    }

    public void setStartSymbol(String symbol){
        this.startSymbol = symbol;
    }
}