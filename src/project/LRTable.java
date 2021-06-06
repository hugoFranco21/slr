package project;

public class LRTable {
    private Glc grammar;
    private GoToTable goToTable;
    private ActionTable actionTable;

    public LRTable(Glc g, GoToTable goToTable, ActionTable actionTable){
        this.grammar = g;
        this.goToTable = goToTable;
        this.actionTable = actionTable;
    }

    public String toHTMl(){
        StringBuilder bobTheBuilder = new StringBuilder();
        bobTheBuilder.append("<table style=\"border: 1px solid black; background-color: WhiteSmoke;\">\n");
        bobTheBuilder.append("<thead style=\"text-align: center; font-weight: bold;\">\n");
        bobTheBuilder.append("<tr>\n");
        Integer size = this.grammar.getNonTerminals().size() + this.grammar.getTerminals().size();
        bobTheBuilder.append("<td colspan=" + "\"" + size.toString() + "\" style=\"border: 1px solid black;\" >LR Table</td>\n");
        bobTheBuilder.append("</tr>\n");
        bobTheBuilder.append("<tr>\n");
        bobTheBuilder.append("<td style=\"border: 1px solid black;\" rowspan=\"2\">State</td>\n");
        Integer actionSize = this.grammar.getTerminals().size();
        bobTheBuilder.append("<td colspan=" + "\"" + actionSize.toString() + "\" style=\"border: 1px solid black;\">ACTION</td>\n");
        Integer goToSize = this.grammar.getNonTerminals().size();
        bobTheBuilder.append("<td colspan=" + "\"" + actionSize.toString() + "\" style=\"border: 1px solid black;\">GOTO</td>\n");
        bobTheBuilder.append("</tr>\n");
        bobTheBuilder.append("<tr>\n");
        for(String s : this.grammar.getNonTerminals()){
            bobTheBuilder.append("<td style=\"border: 1px solid black;\">" + s + "</td>\n");
        }
        for(String s : this.grammar.getTerminals()){
            bobTheBuilder.append("<td style=\"border: 1px solid black;\">" + s + "</td>\n");
        }
        bobTheBuilder.append("</tr>\n");
        bobTheBuilder.append("</thead>\n");
        bobTheBuilder.append("<tbody>\n");
        bobTheBuilder.append("<tr>\n");
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
}