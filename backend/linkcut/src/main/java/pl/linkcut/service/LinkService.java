package pl.linkcut.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.linkcut.entity.Link;
import pl.linkcut.exception.UrlNotFoundException;
import pl.linkcut.helper.UrlValidator;
import pl.linkcut.repository.LinkRepository;
import java.time.LocalDate;
import java.util.Random;

/**
 * Service class to handle URL shortening and retrieval logic.
 * <p>
 * This service is responsible for validating URLs, generating shortened links,
 * and managing interactions with the database for storing and retrieving URLs.
 * </p>
 */
@Service
public class LinkService {

    private final LinkRepository linkRepository;
    private final UrlValidator urlValidator;

    /**
     * Constructor for dependency injection.
     *
     * @param linkRepository the repository for database interactions
     * @param urlValidator   the validator for URL validation and safety checks
     */
    @Autowired
    public LinkService(LinkRepository linkRepository, UrlValidator urlValidator) {
        this.linkRepository = linkRepository;
        this.urlValidator = urlValidator;
    }

    /**
     * Shortens a given URL.
     * <p>
     * This method validates the provided URL, generates a unique shortened link,
     * and stores the mapping in the database with an expiration date.
     * </p>
     *
     * @param originalUrl the full URL to be shortened
     * @return Link object
     */
    public Link shortenUrl(String originalUrl) {
        // Validate the original URL for format and safety
        urlValidator.validateUrl(originalUrl);

        // Generate a unique shortened link
        String shortenedLink = generateShortenedLink();

        // Create a new Link entity with a 30-day expiration date
        Link link = new Link(originalUrl, shortenedLink, LocalDate.now().plusDays(30));

        // Save the link entity in the database
        linkRepository.save(link);

        return link;
    }

    /**
     * Retrieves the original URL for a given shortened link.
     *
     * @param shortUrl the shortened URL
     * @return the original URL associated with the shortened link
     * @throws UrlNotFoundException if the shortened link does not exist in the database
     */
    public String getOriginalUrl(String shortUrl) {
        // Find the link entity in the database by its shortened link
        Link link = linkRepository.findByShortenedLink(shortUrl);

        if (link == null) {
            throw new UrlNotFoundException("Shortened link not found.");
        }

        return link.getOriginalUrl();
    }

    /**
     * Generates a unique 6-character shortened link.
     * <p>
     * The method uses a random combination of alphanumeric characters
     * and ensures the generated link is unique by checking the database.
     * </p>
     *
     * @return a unique shortened link
     */
    private String generateShortenedLink() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder shortened = new StringBuilder();
        Random random = new Random();

        // Generate a random 4-character string
        for (int i = 0; i < 4; i++) {
            shortened.append(characters.charAt(random.nextInt(characters.length())));
        }

        // Ensure the shortened link is unique
        String shortenedLink = shortened.toString();
        while (linkRepository.findByShortenedLink(shortenedLink) != null) {
            shortened.setCharAt(random.nextInt(6), characters.charAt(random.nextInt(characters.length())));
            shortenedLink = shortened.toString();
        }
        return shortenedLink;
    }
}