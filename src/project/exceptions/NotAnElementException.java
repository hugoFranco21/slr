package project.exceptions;

public class NotAnElementException extends Exception{
    /**
     * This exception is called when an element is searched for in the
     * terminals and non terminals and it doesn't appear in either.
     * This exception should never come up.
     * @param errorMessage
     */
    public NotAnElementException(String errorMessage){
        super(errorMessage);
    }
}
