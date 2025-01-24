package pl.linkcut.dto;

/**
 * DTO class representing the response with the original URL.
 * This class is used to return the original URL to the client.
 */
public class OriginalUrlResponse {

    // The original URL to be returned in the response
    private String originalUrl;

    /**
     * Default constructor.
     * Used for deserialization purposes (e.g., by frameworks like Jackson).
     */
    public OriginalUrlResponse() {
    }

    /**
     * Constructor with original URL parameter.
     *
     * @param originalUrl The original URL to be returned in the response.
     */
    public OriginalUrlResponse(String originalUrl) {
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