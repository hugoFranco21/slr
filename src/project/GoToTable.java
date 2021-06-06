package project;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import project.exceptions.NotAnElementException;

public class GoToTable {
    private Glc grammar;
    private HashMap<Integer, HashMap<String, String>> goTos;
    private HashMap<Integer, State> states;
    private LinkedList<GoTo> goTo;
    
    public GoToTable (Glc g, HashMap<Integer, State> states, LinkedList<GoTo> goTo) {
        this.grammar = g;
        this.states = states;
        this.goTo = goTo;
        this.goTos = new HashMap<>();
        for(Map.Entry<Integer, State> s : this.states.entrySet()){
            goTos.put(s.getKey(), new HashMap<>());
        }
    }

    public void createGoToTable() throws NotAnElementException{
        for(GoTo g : goTo){
            if(grammar.getNonTerminals().contains(g.getSymbol())){
                if(!this.states.get(g.getFromState()).getIsAcceptance()){
                    this.insertInnerHashMap(g.getFromState(), g.getSymbol(), g.getDestinationState().toString());
                } 
            } else {
                continue;
            }
        }
    }

    public void insertInnerHashMap(Integer from, String nonTerminal, String toState){
        this.goTos.get(from).put(nonTerminal, toState);
    }

    public HashMap<Integer, HashMap<String, String>> getGoTos(){
        return this.goTos;
    }

    @Override
    public String toString(){
        StringBuilder bobTheBuilder = new StringBuilder();
        for(Map.Entry<Integer, HashMap<String, String>> h : this.getGoTos().entrySet()){
            Integer aux = h.getKey();
            for(Map.Entry<String, String> ss : h.getValue().entrySet()){
                bobTheBuilder.append(aux.toString() + ": " + ss.getKey() + " = " + ss.getValue() + "\n");
            }
        }
        return bobTheBuilder.toString();
    }

    public HashMap<Integer, State> getStates(){
        return this.states;
    }

    public String toHTML(Integer state){
        StringBuilder bobTheBuilder = new StringBuilder();
        for(String s: this.grammar.getNonTerminals()){
            if(this.goTos.get(state).containsKey(s)){
                bobTheBuilder.append("<td style=\"color: blue; border: 1px solid black;\">" + this.goTos.get(state).get(s) + "</td>");
            } else {
                bobTheBuilder.append("<td style=\"border: 1px solid black;\"></td>");
            }
        }
        bobTheBuilder.append("</tr>");
        return bobTheBuilder.toString();
    }

}
