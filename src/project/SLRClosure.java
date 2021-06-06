package project;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;

public class SLRClosure {
    private Glc grammar;
    private Integer prodCount = -1;
    private Integer state = -1;
    private HashMap<Integer, ExtendedProduction> productions;
    private HashMap<Integer, State> states;
    private LinkedList<GoTo> goTo;
    private Deque<GoTo> remaining;

    /**
     * Constructor for the SLRClosure class, after creating the initial state this method calls the createSLR()
     * method, which generates every state and goTo object
     * @param g Glc - Context Free Grammar
     * @throws InterruptedException
     */
    public SLRClosure(Glc g) {
        this.grammar = g;
        productions = new HashMap<>();
        states = new HashMap<>();
        goTo = new LinkedList<>();
        remaining = new ArrayDeque<>();
        for(Production prod : this.grammar.getProductions().values()){
            Production aux = Production.copy(prod);
            this.insertExtendedProduction(new ExtendedProduction(aux));
        }
        State initial = new State();
        Kernel k0 = new Kernel();
        k0.addElement(productions.get(0));
        initial.setKernel(k0);
        createClosure(initial);
        insertState(initial);
        for(ExtendedProduction ex : states.get(0).getClosure().getProductions()){
            if(!ex.isPeriodLast()){
                GoTo go = new GoTo(0, ex.rightOfPeriod());
                goTo.add(go);
                remaining.add(go);
                states.get(0).setIsAcceptance(false);
            } else {
                states.get(0).setIsAcceptance(true);
            }
        }
        createSLR();
    }

    /**
     * This method inserts an extended production into the productions HashMap
     * @param prod ExtendedProduction
     */
    public void insertExtendedProduction(ExtendedProduction prod){
        this.productions.put(++this.prodCount, prod);
    }

    /**
     * This method inserts a new state into the states HashMap
     * @param s State
     */
    public void insertState(State s){
        this.states.put(++this.state, s);
        s.setNumber(this.state);
    }

    /**
     * This method creates the closure for a given state, to do this, it adds the elements from the Kernel, and then
     * checks one by one to see if there is a Non Terminal after a period, if there is the element is added to the closure and
     * the loop is ran again, it does this until the number of elements in the closure before the loop and after are the same
     * @param s State
     */
    public void createClosure(State s) {
        Kernel k = s.getKernel();
        Closure c = new Closure();
        Integer currentSize, prevSize;
        for(ExtendedProduction ex : k.getProductions()){
            c.insertProduction(ex);
        }
        currentSize = c.getProductions().size();
        prevSize = 0;
        while(prevSize != currentSize){
            prevSize = currentSize;
            HashSet<ExtendedProduction> aux = new HashSet<>();
            for(ExtendedProduction ex : c.getProductions()){
                if(!ex.isPeriodLast() && this.grammar.getNonTerminals().contains(ex.rightOfPeriod())){
                    for(Production p : this.grammar.getProductionStartingWith(ex.rightOfPeriod())){
                        Production cop = Production.copy(p);
                        aux.add(new ExtendedProduction(cop));
                    }
                }
            }
            for(ExtendedProduction ex : aux){
                c.insertProduction(ex);
            }
            currentSize = c.getProductions().size();
        }
        s.setClosure(c);
    }

    /**
     * This method does something similar to BFS, it reads the queue of GoTo's and advances
     * the symbol in the productions that are possible, it gets this information with the getAdvanceableProductions() method.
     * After that it sets that as the kernel, and checks the previous states to see if there is a match; if there is, it points the
     * GoTo to that state, and pops the first element in the queue; if there isn't, it creates the new state and calculates its closure,
     * it then adds the elements to the GoTo queue if the end of the production hasn't been reached; if it has it just sets the 
     * isAcceptance flag for the GoTo.
     */
    public void createSLR() {
        while(!remaining.isEmpty()){
            GoTo g = remaining.removeFirst();
            State newState = new State();
            State s = this.states.get(g.getFromState());
            Kernel k = new Kernel(s.getClosure().getAdvanceableProductions(g.getSymbol()));
            Kernel ke = new Kernel();
            for(ExtendedProduction ex : k.getProductions()){
                ke.addElement(ex.advancePeriod());
            }
            Integer pS = this.compareKernels(ke);
            if(pS != - 1){
                g.setDestinationState(pS);
                g.setPointsToPrevious(true);
                updateElementList(g, pS);
            } else {
                newState.setKernel(ke);
                createClosure(newState);
                insertState(newState);
                g.setDestinationState(this.state);
                updateElementList(g, this.state);
                Integer index = this.goTo.indexOf(g);
                this.goTo.get(index).setDestinationState(this.state);
                for(ExtendedProduction ex : states.get(this.state).getClosure().getProductions()){
                    if(!ex.isPeriodLast()){
                        GoTo go = new GoTo(this.state, ex.rightOfPeriod());
                        goTo.add(go);
                        remaining.add(go);
                    } else {
                        states.get(this.state).setIsAcceptance(true);
                    }
                }
            }
        }
    }

    /**
     * This method updates the destination attribute for the GoTo element in the LinkedList
     * where every transaction is being written to
     * @param g GoTo
     * @param destination Integer
     */
    public void updateElementList(GoTo g, Integer destination){
        Integer index = this.goTo.indexOf(g);
        this.goTo.get(index).setDestinationState(destination);
    }

    /**
     * This methods receives a kernel and compares it to every kernel present before, to see if there is a match,
     * if there is the number of the state is returned, if there isn't -1 is returned.
     * @param k Kernel
     * @return Integer
     */
    public Integer compareKernels(Kernel k){
        for(Map.Entry<Integer, State> s : this.states.entrySet()){
            if(s.getValue().getKernel().toString().equals(k.toString())){
                return s.getKey();
            }
        }
        return -1;
    }

    public HashMap<Integer, State> getStates(){
        return this.states;
    }

    public LinkedList<GoTo> getGoTos(){
        return this.goTo;
    }

    /**
     * Helper method to print the GoTo LinkedList
     * @return String
     */
    public String printGoTo(){
        StringBuilder bobTheBuilder = new StringBuilder();
        for(GoTo g : this.goTo){
            bobTheBuilder.append(g.toString());
        }
        return bobTheBuilder.toString();
    }

    /**
     * Helper method to print the queue where all the goto's await
     * @return String
     */
    public String printRemain(){
        StringBuilder bobTheBuilder = new StringBuilder();
        for(GoTo g : this.remaining){
            bobTheBuilder.append(g.toString());
        }
        return bobTheBuilder.toString();
    }

    /**
     * This method returns the SLRClosure as a Java String
     */
    @Override
    public String toString(){
        StringBuilder bobTheBuilder = new StringBuilder();
        for(Integer i = 0; i < this.state; i++){
            bobTheBuilder.append(this.states.get(i).toString());
            if(i > 0){
                bobTheBuilder.append(this.goTo.get(i - 1));
            }
        }
        bobTheBuilder.append(this.goTo.getLast());
        return bobTheBuilder.toString();
    }

    /**
     * This method creates the HTML table with every element and the styling necessary
     * @return String
     */
    public String toHTML(){
        StringBuilder bobTheBuilder = new StringBuilder();
        bobTheBuilder.append("<table style=\"border: 1px solid black; background-color: WhiteSmoke;\">\n");
        bobTheBuilder.append("<thead style=\"text-align: center; font-weight: bold;\">\n");
        bobTheBuilder.append("<tr>\n");
        bobTheBuilder.append("<td colspan=\"4\" style=\"border: 1px solid black;\" >SLR closure table</td>\n");
        bobTheBuilder.append("</tr>\n");
        bobTheBuilder.append("<tr>\n");
        bobTheBuilder.append("<td style=\"border: 1px solid black;\">Goto</td>\n");
        bobTheBuilder.append("<td style=\"border: 1px solid black;\">Kernel</td>\n");
        bobTheBuilder.append("<td style=\"border: 1px solid black;\">State</td>\n");
        bobTheBuilder.append("<td style=\"border: 1px solid black;\">Closure</td>\n");
        bobTheBuilder.append("</tr>\n");
        bobTheBuilder.append("</thead>\n");
        bobTheBuilder.append("<tbody>\n");
        bobTheBuilder.append("<tr>\n");
        bobTheBuilder.append("<td style=\"border: 1px solid black;\"></td>\n");
        bobTheBuilder.append(this.states.get(0).toHTML(false));
        bobTheBuilder.append("</tr>\n");
        Integer i = 1;
        for(i = 0; i < this.goTo.size(); i++){
            bobTheBuilder.append("<tr>\n");
            bobTheBuilder.append(this.goTo.get(i).toHTML());
            bobTheBuilder.append(this.states.get(this.goTo.get(i).getDestinationState()).toHTML(this.goTo.get(i).getPointsToPrevious()));
            bobTheBuilder.append("</tr>\n");
        }
        bobTheBuilder.append("</tbody>\n");
        bobTheBuilder.append("</table>\n");
        bobTheBuilder.append("<br/>\n");
        bobTheBuilder.append("<br/>\n");
        return bobTheBuilder.toString();
    }
}