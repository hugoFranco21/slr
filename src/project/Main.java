package project;

public class Main {
    public static void main(String[] args){
        try {
            FileReader input = new FileReader(args[0]);
            Glc grammar = input.readFile();
            System.out.println(grammar.toString());
            SLRClosure slrc = new SLRClosure(grammar);
            System.out.println(slrc.toString());
            grammar.getNonTerminals().add("$");
            ActionTable action = new ActionTable(grammar, slrc.getStates(), slrc.getGoTos());
            action.createActionTable();
            GoToTable goTo = new GoToTable(grammar, slrc.getStates(), slrc.getGoTos());
            goTo.createGoToTable();
            LRTable lrTable = new LRTable(g, goTo, action);
            FileWriter fileWriter = new FileWriter(slrc.toHTML(), lrTable.toHTMl());
            fileWriter.writeFile();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }
}