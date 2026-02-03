package spongebob.exceptions;

/**
 * Custom exception class for non-built-in errors.
 * Used to signal specific error conditions in the Spongebob application.
 */
public class SpongebobException extends Exception {
    public SpongebobException(String message) {
        super(message);
    }
}
