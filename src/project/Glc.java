package project;

import java.util.HashSet;
import java.util.Map;

import project.exceptions.IncorrectGrammarException;
import project.exceptions.NotAnElementException;

import java.util.HashMap;

public class Glc {

    private Integer count = 0;
    private HashMap<Integer, Production> productions;
    private HashSet<String> nonTerminals;
    private HashSet<String> terminals;
    private HashMap<String, HashSet<String>> first, follows;
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
     * This method returns every production that has the parameter s in the left side
     * @param s String
     * @return HashSet<Production>
     */
    public HashSet<Production> getProductionStartingWith(String s){
        HashSet<Production> hs = new HashSet<>();
        for(Production p : this.getProductions().values()){
            if(p.getLeftSide().equals(s)){
                hs.add(p);
            }
        }
        return hs;
    }

    /**
     * This method returns every production that contain the parameter s in 
     * the right side of the production
     * @param s String
     * @return HashSet<Production>
     */
    public HashSet<Production> getProductionsWithSymbolOnRight(String s){
        HashSet<Production> hs = new HashSet<>();
        for(Production p : this.getProductions().values()){
            if(p.getRightSide().contains(s)){
                hs.add(p);
            }
        }
        return hs;
    }

    /**
     * This function computes the first of every non terminal, it calls the helper function 
     * getFirst()
     * @throws NotAnElementException
     */
    public void computeFirst() throws NotAnElementException{
        this.first = new HashMap<>();
        for(String s : this.nonTerminals){
            this.first.put(s, new HashSet<>());
        }
        for(String s : this.nonTerminals){
            HashSet<String> aux = new HashSet<>();
            for(Production p : this.getProductionStartingWith(s)){
                aux.addAll(this.getFirst(p.getRightSide().getFirst()));
            }
            this.first.put(s, aux);
        }
    }
    
    /**
     * This method receives a String s, and returns its first, if the element is a terminal
     * it returns itself in a HashSet, if it's a non terminal it checks for its own first, 
     * and if it has another nonterminal then it checks for the first of that element recursively
     * @param s String
     * @return HashSet<String>
     * @throws NotAnElementException When it searches for an element not in the grammar
     */
    public HashSet<String> getFirst(String s) throws NotAnElementException {
        if(this.nonTerminals.contains(s)){
            if(this.first.get(s).size() != 0){
                return this.first.get(s);
            } else {
                HashSet<String> aux = new HashSet<>();
                for(Production p : this.getProductionStartingWith(s)){
                    aux.addAll(this.getFirst(p.getRightSide().getFirst()));
                }
                return aux;
            }
        } else if(this.terminals.contains(s)){
            HashSet<String> hs = new HashSet<String>();
            hs.add(s);
            return hs;
        } else {
            throw new NotAnElementException("El string " + s + " no es parte de la gramática");
        }
    }

    /**
     * This function computes the follows of every non terminal, it calls the helper function 
     * getFollows()
     * @throws NotAnElementException
     */
    public void computeFollows() throws NotAnElementException{
        this.follows = new HashMap<>();
        for(String s : this.nonTerminals){
            this.follows.put(s, null);
        }
        HashSet<String> hs = new HashSet<>();
        hs.add("$");
        this.follows.put(this.startSymbol, hs);
        for(String s : this.nonTerminals){
            HashSet<String> aux;
            if(this.follows.get(s) == null){
                aux = new HashSet<>();
            } else {
                aux = this.follows.get(s);
            }
            for(Production p : this.getProductionsWithSymbolOnRight(s)){
                if(p.getElementRightOfSymbol(s) == null){
                    aux.addAll(this.getFollows(p.getLeftSide()));
                } else {
                    aux.addAll(this.getFirst(p.getElementRightOfSymbol(s)));
                }
            }
            this.follows.put(s, aux);
        }
    }

    /**
     * This method receives a String s, and returns its follows. If it exists already it is returned, if not
     * it first checks to see if there are productions where the String s is on the left side of another element 
     * within the right side, if there is it adds the first of the next element, if there isn't it gets the follow
     * of the left side element of the production
     * @param s String
     * @return HashSet<String>
     * @throws NotAnElementException When it searches for an element not in the grammar
     */
    public HashSet<String> getFollows(String s) throws NotAnElementException {
        if(this.nonTerminals.contains(s)){
            if(this.follows.get(s) != null){
                return this.follows.get(s);
            } else {
                HashSet<String> aux = new HashSet<>();
                for(Production p : this.getProductionsWithSymbolOnRight(s)){
                    if(p.getElementRightOfSymbol(s) == null){
                        aux.addAll(this.getFollows(p.getLeftSide()));
                    } else {
                        aux.addAll(this.getFirst(p.getElementRightOfSymbol(s)));
                    }
                }
                return aux;
            }
        } else {
            throw new NotAnElementException("El string " + s + " no es parte de la gramática");
        }
    }

    /**
     * This method searches for the number of the production in the hashmap and returns the index
     * @param prod Production
     * @return Integer
     */
    public Integer productionNumber(Production prod){
        for(Map.Entry<Integer, Production> p : this.productions.entrySet()){
            if(p.getValue().toString().equals(prod.toString())){
                return p.getKey();
            }
        }
        return -1;
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
        bobTheBuilder.append("First: \n");
        for(String s : this.first.keySet()){
            bobTheBuilder.append(s + " = { ");
            for(String aux : this.first.get(s)){
                bobTheBuilder.append(aux + ", ");
            }
            bobTheBuilder.append(" } \n");
        }
        bobTheBuilder.append("Follows: \n");
        for(String s : this.follows.keySet()){
            bobTheBuilder.append(s + " = { ");
            for(String aux : this.follows.get(s)){
                bobTheBuilder.append(aux + ", ");
            }
            bobTheBuilder.append(" } \n");
        }
        return bobTheBuilder.toString();
    }
}