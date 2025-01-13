package pl.linkcut.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.linkcut.dto.ErrorResponse;

/**
 * Global exception handler for the LinkCut application.
 * <p>
 * This class handles specific exceptions and converts them into
 * structured error responses using the {@link ErrorResponse} class.
 * </p>
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles exceptions of type {@link InvalidUrlException}.
     * <p>
     * This is triggered when a provided URL is invalid according to
     * validation logic (e.g., missing domain, incorrect format).
     * </p>
     *
     * @param e the exception being handled
     * @return a {@link ResponseEntity} containing the error details and HTTP status 400
     */
    @ExceptionHandler(InvalidUrlException.class)
    public ResponseEntity<ErrorResponse> handleInvalidUrlException(InvalidUrlException e) {
        // Create structured error response
        ErrorResponse response = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),  // HTTP status code
                "Invalid URL",                  // General error message
                e.getMessage()                  // Detailed error message
        );

        // Return response with 400 BAD REQUEST
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles exceptions of type {@link UnsafeUrlException}.
     * <p>
     * This is triggered when a URL is flagged as unsafe by external services
     * (e.g., Google Safe Browsing API).
     * </p>
     *
     * @param e the exception being handled
     * @return a {@link ResponseEntity} containing the error details and HTTP status 422
     */
    @ExceptionHandler(UnsafeUrlException.class)
    public ResponseEntity<ErrorResponse> handleUnsafeUrlException(UnsafeUrlException e) {
        // Create structured error response
        ErrorResponse response = new ErrorResponse(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),  // HTTP status code
                "Unsafe URL",                             // General error message
                e.getMessage()                            // Detailed error message
        );

        // Return response with 422 UNPROCESSABLE ENTITY
        return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(UrlNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUrlNotFoundException(UrlNotFoundException e) {
        // Create structured error response
        ErrorResponse response = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                "URL Not Found",
                e.getMessage()
        );
        // Return response with 404 NOT FOUND
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}