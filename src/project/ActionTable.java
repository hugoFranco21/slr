package project;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import project.exceptions.NotAnElementException;

public class ActionTable {
    private Glc grammar;
    private HashMap<Integer, HashMap<String, String>> actions;
    private HashMap<Integer, State> states;
    private LinkedList<GoTo> goTo;
    
    /**
     * Constructor for the ActionTable class, it receives the grammar, the states, and the goTo list, and 
     * initializes the actions HashMap with empty HashMaps
     * @param g
     * @param states
     * @param goTo
     */
    public ActionTable (Glc g, HashMap<Integer, State> states, LinkedList<GoTo> goTo) {
        this.grammar = g;
        this.states = states;
        this.goTo = goTo;
        this.actions = new HashMap<>();
        for(Map.Entry<Integer, State> s : this.states.entrySet()){
            actions.put(s.getKey(), new HashMap<>());
        }
    }

    /**
     * This method checks every state to see if it is an acceptance state (a state where a production is accepted), if it 
     * is, it adds the reduce action and the production to every element in the follows of the element on the left.
     * If it is not an acceptance state, it checks that you move with a terminal and not a nonTerminal, and
     * adds the Shift rule for those elements
     */
    public void createActionTable() throws NotAnElementException{
        for(State st : this.states.values()){
            if(st.getIsAcceptance()){
                for(ExtendedProduction ex : st.getKernel().getProductions()){
                    Production p = Production.copyFromExtended(ex);
                    Integer ruleNumber = this.grammar.productionNumber(p);
                    if(ruleNumber == 0){
                        this.insertInnerHashMap(st.getNumb(), "$", "acc");
                    } else {
                        for(String s : this.grammar.getFollows(this.grammar.getProductions().get(ruleNumber).getLeftSide())){
                            this.insertInnerHashMap(st.getNumb(), s, "r" + ruleNumber.toString());
                        }
                    }
                }
            } 
        }
        for(GoTo g : goTo){
            if(grammar.getTerminals().contains(g.getSymbol())){
                if(!this.states.get(g.getFromState()).getIsAcceptance()){
                    this.insertInnerHashMap(g.getFromState(), g.getSymbol(), "s" + g.getDestinationState().toString());
                } 
            } 
        }
    }

    /**
     * This method adds an action to a given state in a given symbol
     * @param from Integer - The state it is added to
     * @param symbol String - The symbol that produces the action
     * @param action String - The action to be performed
     */
    public void insertInnerHashMap(Integer from, String symbol, String action){
        this.actions.get(from).put(symbol, action);
    }

    /**
     * Getter for the actions HashMap
     * @return HashMap<Integer, HashMap<String, String>>
     */
    public HashMap<Integer, HashMap<String, String>> getActions(){
        return this.actions;
    }

    /**
     * This method returns the Action Table as a Java String
     * @return String
     */
    @Override
    public String toString(){
        StringBuilder bobTheBuilder = new StringBuilder();
        for(Map.Entry<Integer, HashMap<String, String>> h : this.getActions().entrySet()){
            Integer aux = h.getKey();
            for(Map.Entry<String, String> ss : h.getValue().entrySet()){
                bobTheBuilder.append(aux.toString() + ": " + ss.getKey() + " = " + ss.getValue() + "\n");
            }
        }
        return bobTheBuilder.toString();
    }

    /**
     * This method creates the HTML of the action table of a given state
     * @return String
     */
    public String toHTML(Integer state){
        StringBuilder bobTheBuilder = new StringBuilder();
        bobTheBuilder.append("<tr>\n");
        bobTheBuilder.append("<td style=\"border: 1px solid black; color: blue\">");
        bobTheBuilder.append(state.toString());
        bobTheBuilder.append("</td>");
        for(String s: this.grammar.getTerminals()){
            if(this.actions.get(state).containsKey(s)){
                if(this.actions.get(state).get(s).startsWith("s")){
                    bobTheBuilder.append("<td style=\"border: 1px solid black;\">");
                    bobTheBuilder.append("<span style=\"color: black\">s</span>");
                    bobTheBuilder.append("<span style=\"color: blue\">" + this.actions.get(state).get(s).charAt(this.actions.get(state).get(s).length() - 1) + "</span>");
                    bobTheBuilder.append("</td>");
                } else if(this.actions.get(state).get(s).startsWith("r")){
                    bobTheBuilder.append("<td style=\"border: 1px solid black;\">");
                    bobTheBuilder.append("<span style=\"color: black\">r</span>");
                    bobTheBuilder.append("<sub style=\"color: green\">" + this.actions.get(state).get(s).charAt(this.actions.get(state).get(s).length() - 1) + "</sub>");
                    bobTheBuilder.append("</td>");
                } else {
                    bobTheBuilder.append("<td style=\"border: 1px solid black; color: green;\">acc</td>");
                }
            } else {
                bobTheBuilder.append("<td style=\"border: 1px solid black;\"></td>");
            }
        }
        return bobTheBuilder.toString();
    }
}
