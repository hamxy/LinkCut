package pl.linkcut.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

/**
 * DTO class representing the response with the shortened URL.
 * This class is used to return the shortened URL to the client.
 */
public class ShortUrlResponse {

    // The shortened URL to be returned in the response
    private String shortUrl;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private LocalDate expirationDate;

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
    public ShortUrlResponse(String shortUrl, LocalDate expirationDate) {
        this.shortUrl = shortUrl;
        this.expirationDate = expirationDate;
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

    /**
     * Getter for the expiration date.
     *
     * @return The expiration date.
     */
    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    /**
     * Setter for the expiration date.
     *
     * @param expirationDate The expiration date to set.
     */
    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }
}