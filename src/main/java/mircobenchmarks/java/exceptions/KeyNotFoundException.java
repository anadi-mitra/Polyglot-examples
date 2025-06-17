package mircobenchmarks.java.exceptions;

public class KeyNotFoundException extends RuntimeException{
    public KeyNotFoundException(String message) {
        super(message);
        System.err.println(message);
    }
}
