package pl.linkcut.dto;

/**
 * DTO class representing the original URL request.
 * This is used to encapsulate the original URL passed from the client to the server.
 */
public class OriginalUrlRequest {

    // The original URL provided by the client
    private String originalUrl;

    /**
     * Default constructor.
     * Used for deserialization purposes (e.g. by frameworks like Jackson).
     */
    public OriginalUrlRequest() {
    }

    /**
     * Constructor with original URL parameter.
     *
     * @param originalUrl The original URL to be processed.
     */
    public OriginalUrlRequest(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    /**
     * Getter for the original URL.
     *
     * @return The original URL.
     */
    public String getOriginalUrl() {
        return originalUrl;
    }

    /**
     * Setter for the original URL.
     *
     * @param originalUrl The original URL to set.
     */
    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }
}