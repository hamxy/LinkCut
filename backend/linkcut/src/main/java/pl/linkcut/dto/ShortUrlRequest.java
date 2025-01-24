package pl.linkcut.dto;

/**
 * DTO class representing the shortened URL request.
 * This is used to encapsulate the shortened URL passed from the client to the server.
 */
public class ShortUrlRequest {

    // The shortened URL provided by the client
    private String shortUrl;

    /**
     * Default constructor.
     * Used for deserialization purposes (e.g. by frameworks like Jackson).
     */
    public ShortUrlRequest() {
    }

    /**
     * Constructor with shortened URL parameter.
     *
     * @param shortUrl The shortened URL to be processed.
     */
    public ShortUrlRequest(String shortUrl) {
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