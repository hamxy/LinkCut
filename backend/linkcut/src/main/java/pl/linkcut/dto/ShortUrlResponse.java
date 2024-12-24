package pl.linkcut.dto;

/**
 * DTO class representing the response with the shortened URL.
 * This class is used to return the shortened URL to the client.
 */
public class ShortUrlResponse {

    // The shortened URL to be returned in the response
    private String shortUrl;

    /**
     * Default constructor.
     * Used for deserialization purposes (e.g., by frameworks like Jackson).
     */
    public ShortUrlResponse() {
    }

    /**
     * Constructor with shortened URL parameter.
     *
     * @param shortUrl The shortened URL to be returned in the response.
     */
    public ShortUrlResponse(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    /**
     * Getter for the shortened URL.
     *
     * @return The shortened URL.
     */
    public String getShortUrl() {
        return shortUrl;
    }

    /**
     * Setter for the shortened URL.
     *
     * @param shortUrl The shortened URL to set.
     */
    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }
}