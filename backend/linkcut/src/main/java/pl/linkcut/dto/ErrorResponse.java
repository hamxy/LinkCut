package pl.linkcut.dto;

/**
 * A Data Transfer Object (DTO) for representing error responses in the API.
 * <p>
 * This class provides a structured way to return error details,
 * including the HTTP status code, a general error message, and a detailed message.
 * </p>
 */
public class ErrorResponse {

    private int status;  // HTTP status code
    private String error; // General error message (e.g., "Invalid URL")
    private String message; // Detailed error message (e.g., "Provided string is not a valid URL")

    /**
     * Default no-argument constructor.
     * <p>
     * Required for frameworks like Jackson to deserialize JSON into this object.
     * </p>
     */
    public ErrorResponse() {}

    /**
     * Constructs an ErrorResponse with the specified details.
     *
     * @param status  the HTTP status code (e.g., 400 for Bad Request)
     * @param error   a general error message (e.g., "Invalid URL")
     * @param message a detailed error description (e.g., "Provided string is not a valid URL")
     */
    public ErrorResponse(int status, String error, String message) {
        this.status = status;
        this.error = error;
        this.message = message;
    }

    // Getters and setters

    /**
     * Gets the HTTP status code.
     *
     * @return the HTTP status code
     */
    public int getStatus() {
        return status;
    }

    /**
     * Sets the HTTP status code.
     *
     * @param status the HTTP status code
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * Gets the general error message.
     *
     * @return the error message
     */
    public String getError() {
        return error;
    }

    /**
     * Sets the general error message.
     *
     * @param error the error message
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     * Gets the detailed error message.
     *
     * @return the detailed error message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the detailed error message.
     *
     * @param message the detailed error message
     */
    public void setMessage(String message) {
        this.message = message;
    }
}