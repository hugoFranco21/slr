package project.exceptions;

public class NotCorrectFormatException extends Exception{
    /**
     * This Exception is called when the input file is not in the previously defined format
     */
    public NotCorrectFormatException(String errorMessage){
        super(errorMessage);
    }
}
