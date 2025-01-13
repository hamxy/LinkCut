package pl.linkcut.service;

import org.springframework.stereotype.Service;
import pl.linkcut.repository.LinkRepository;

import java.time.LocalDate;

/**
 * Service responsible for cleaning up expired links from the database.
 * This service identifies links with expiration dates before the current date
 * and removes them from the database.
 */
@Service
public class LinkCleanupService {

    // Repository to interact with the Link database table
    private final LinkRepository linkRepository;

    /**
     * Constructor to inject the LinkRepository dependency.
     *
     * @param linkRepository the repository used for managing Link entities
     */
    public LinkCleanupService(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    /**
     * Deletes all expired links from the database.
     * A link is considered expired if its expiration date is strictly earlier than the current date.
     */
    public void deleteExpiredLinks() {
        LocalDate today = LocalDate.now(); // Get the current date

        // Delete all links with expirationDate before today
        linkRepository.deleteAllByExpirationDateBefore(today);

        // Log the success message to the console
        System.out.println("Expired links deleted successfully.");
    }
}