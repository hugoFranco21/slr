package project.exceptions;

public class IncorrectGrammarException extends Exception{
    public IncorrectGrammarException(String errorMessage){
        super(errorMessage);
    }
}
