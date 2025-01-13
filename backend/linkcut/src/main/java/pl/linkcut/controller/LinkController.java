package pl.linkcut.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.linkcut.dto.OriginalUrlRequest;
import pl.linkcut.dto.OriginalUrlResponse;
import pl.linkcut.dto.ShortUrlRequest;
import pl.linkcut.dto.ShortUrlResponse;
import pl.linkcut.service.LinkService;

/**
 * REST Controller for managing URL shortening and retrieval operations.
 * <p>
 * Provides endpoints to shorten a URL and retrieve the original URL
 * from a shortened one. All endpoints are prefixed with "/api".
 * </p>
 */
@RestController
@RequestMapping("/api")
public class LinkController {

    private final LinkService linkService;

    /**
     * Constructor for dependency injection of LinkService.
     *
     * @param linkService the service handling URL shortening and retrieval logic
     */
    @Autowired
    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }

    /**
     * A simple endpoint to verify the service is running.
     *
     * @return a "Hello world" message
     */
    @GetMapping("/")
    public String helloWorld() {
        return "Hello world";
    }

    /**
     * Endpoint to shorten a given original URL.
     *
     * @param originalUrlRequest the request DTO containing the original URL
     * @return a ResponseEntity containing the shortened URL wrapped in a DTO
     */
    @PostMapping("/shorten")
    public ResponseEntity<ShortUrlResponse> shortenUrl(@RequestBody OriginalUrlRequest originalUrlRequest) {
        // Shorten the provided URL using the LinkService
        String shortenedUrl = linkService.shortenUrl(originalUrlRequest.getOriginalUrl());

        // Create a response DTO with the shortened URL
        ShortUrlResponse response = new ShortUrlResponse(shortenedUrl);

        // Return the response with HTTP status 200 (OK)
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint to retrieve the original URL for a given shortened URL.
     *
     * @param shortUrlRequest the request DTO containing the shortened URL
     * @return a ResponseEntity containing the original URL wrapped in a DTO
     */
    @PostMapping("/original")
    public ResponseEntity<OriginalUrlResponse> getOriginalUrl(@RequestBody ShortUrlRequest shortUrlRequest) {
        // Retrieve the original URL using the LinkService
        String originalUrl = linkService.getOriginalUrl(shortUrlRequest.getShortUrl());

        // Create a response DTO with the original URL
        OriginalUrlResponse response = new OriginalUrlResponse(originalUrl);

        // Return the response with HTTP status 200 (OK)
        return ResponseEntity.ok(response);
    }
}