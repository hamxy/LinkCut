package pl.linkcut.exception;

/**
 * Custom exception for invalid URL errors.
 * <p>
 * This exception is thrown when a user provides a string
 * that is not a valid URL according to the validation logic.
 * </p>
 */
public class InvalidUrlException extends RuntimeException {

    /**
     * Constructs a new InvalidUrlException with the specified detail message.
     *
     * @param message the detail message explaining the reason for the exception
     */
    public InvalidUrlException(String message) {
        super(message);
    }

    /**
     * Constructs a new InvalidUrlException with the specified detail message and cause.
     *
     * @param message the detail message explaining the reason for the exception
     * @param cause   the cause of the exception (can be another exception)
     */
    public InvalidUrlException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new InvalidUrlException with the specified cause.
     *
     * @param cause the cause of the exception (can be another exception)
     */
    public InvalidUrlException(Throwable cause) {
        super(cause);
    }
}