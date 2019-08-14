package pl.comp.models.exceptions;

public class NoFileSelectedException extends Exception {
    public NoFileSelectedException(String message, Throwable cause){
        super(message, cause);
    }
    public NoFileSelectedException(String message){
        super(message);
    }
}
