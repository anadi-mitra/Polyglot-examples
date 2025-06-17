package mircobenchmarks.java.exceptions;

public class UnsupportedOperationException extends RuntimeException {
    public UnsupportedOperationException(String message) {
        super(message);
        System.err.println(message);
    }
}
