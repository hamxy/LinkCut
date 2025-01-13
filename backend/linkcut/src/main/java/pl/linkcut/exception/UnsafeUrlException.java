package pl.linkcut.exception;

/**
 * Custom exception for unsafe URL errors.
 * <p>
 * This exception is thrown when the Google Safe Browsing API determines
 * that a provided URL is unsafe (e.g., due to malware, phishing, or other threats).
 * </p>
 */
public class UnsafeUrlException extends RuntimeException {

    /**
     * Constructs a new UnsafeUrlException with the specified detail message.
     *
     * @param message the detail message explaining why the URL is considered unsafe
     */
    public UnsafeUrlException(String message) {
        super(message);
    }

    /**
     * Constructs a new UnsafeUrlException with the specified detail message and cause.
     *
     * @param message the detail message explaining why the URL is considered unsafe
     * @param cause   the underlying cause of the exception (e.g., another exception)
     */
    public UnsafeUrlException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new UnsafeUrlException with the specified cause.
     *
     * @param cause the underlying cause of the exception (e.g., another exception)
     */
    public UnsafeUrlException(Throwable cause) {
        super(cause);
    }
}