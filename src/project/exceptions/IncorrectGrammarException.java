package project.exceptions;

public class IncorrectGrammarException extends Exception{
    /**
     * Exception called when the grammar does not have the elements
     * to be considered correct
     * @param errorMessage
     */
    public IncorrectGrammarException(String errorMessage){
        super(errorMessage);
    }
}
