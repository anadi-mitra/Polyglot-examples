package mircobenchmarks.java.exceptions;

public class InvalidArgumentException extends RuntimeException {
    public InvalidArgumentException(String message) {
        super(message);
        System.err.println(message);
    }
}
