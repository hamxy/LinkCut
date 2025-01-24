package pl.linkcut.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

/**
 * Entity class representing a link in the database.
 * <p>
 * This class maps to the "link" table in the database and stores details about
 * the original URL, its shortened version, and the expiration date of the link.
 * </p>
 */
@Entity
@Table(name = "link") // Specifies the table name in the database
public class Link {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generates unique IDs for each record
    private Long id;

    @Column(nullable = false) // Ensures this column cannot be null
    private String originalUrl;

    @Column(nullable = false, unique = true) // Ensures this column is unique and cannot be null
    private String shortenedLink;

    @Column(nullable = false) // Ensures this column cannot be null
    private LocalDate expirationDate;

    /**
     * Default no-argument constructor.
     * <p>
     * Required by JPA for entity instantiation during runtime.
     * </p>
     */
    public Link() {
    }

    /**
     * Constructor to initialize the Link entity with specific values.
     *
     * @param originalUrl    the original URL to be shortened
     * @param shortenedLink  the shortened version of the URL
     * @param expirationDate the date when the link will expire
     */
    public Link(String originalUrl, String shortenedLink, LocalDate expirationDate) {
        this.originalUrl = originalUrl;
        this.shortenedLink = shortenedLink;
        this.expirationDate = expirationDate;
    }

    // Getters and Setters

    /**
     * Gets the unique identifier for this link.
     *
     * @return the ID of the link
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier for this link.
     *
     * @param id the ID of the link
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the original URL of this link.
     *
     * @return the original URL
     */
    public String getOriginalUrl() {
        return originalUrl;
    }

    /**
     * Sets the original URL of this link.
     *
     * @param originalUrl the original URL
     */
    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    /**
     * Gets the shortened version of the URL.
     *
     * @return the shortened URL
     */
    public String getShortenedLink() {
        return shortenedLink;
    }

    /**
     * Sets the shortened version of the URL.
     *
     * @param shortenedLink the shortened URL
     */
    public void setShortenedLink(String shortenedLink) {
        this.shortenedLink = shortenedLink;
    }

    /**
     * Gets the expiration date of the link.
     *
     * @return the expiration date
     */
    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    /**
     * Sets the expiration date of the link.
     *
     * @param expirationDate the expiration date
     */
    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }
}