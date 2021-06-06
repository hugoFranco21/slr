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
    
    public ActionTable (Glc g, HashMap<Integer, State> states, LinkedList<GoTo> goTo) {
        this.grammar = g;
        this.states = states;
        this.goTo = goTo;
        this.actions = new HashMap<>();
        for(Map.Entry<Integer, State> s : this.states.entrySet()){
            actions.put(s.getKey(), new HashMap<>());
        }
    }

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
                            this.insertInnerHashMap(st.getNumb(), s, "R" + ruleNumber.toString());
                        }
                    }
                }
            } 
        }
        for(GoTo g : goTo){
            if(grammar.getTerminals().contains(g.getSymbol())){
                if(!this.states.get(g.getFromState()).getIsAcceptance()){
                    this.insertInnerHashMap(g.getFromState(), g.getSymbol(), "S" + g.getDestinationState().toString());
                } 
            } 
        }
    }

    public void insertInnerHashMap(Integer from, String symbol, String action){
        this.actions.get(from).put(symbol, action);
    }

    public HashMap<Integer, HashMap<String, String>> getActions(){
        return this.actions;
    }

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

    public String toHTML(Integer state){
        StringBuilder bobTheBuilder = new StringBuilder();
        bobTheBuilder.append("<tr>\n");
        bobTheBuilder.append("<td style=\"border: 1px solid black; color: blue\">");
        bobTheBuilder.append(state.toString());
        bobTheBuilder.append("</td>");
        for(Map.Entry<Integer, HashMap<String, String>> h : this.getActions().entrySet()){
            Integer aux = h.getKey();
            for(Map.Entry<String, String> ss : h.getValue().entrySet()){
                bobTheBuilder.append(aux.toString() + ": " + ss.getKey() + " = " + ss.getValue() + "\n");
            }
        }
        return bobTheBuilder.toString();
    }
}
