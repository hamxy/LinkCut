package pl.linkcut.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import pl.linkcut.dto.OriginalUrlRequest;
import pl.linkcut.dto.OriginalUrlResponse;
import pl.linkcut.dto.ShortUrlResponse;
import pl.linkcut.entity.Link;
import pl.linkcut.service.LinkService;
import java.util.HashMap;
import java.util.Map;

/**
 * REST Controller for managing URL shortening and retrieval operations.
 * <p>
 * Provides endpoints to shorten a URL and retrieve the original URL
 * from a shortened one. All endpoints are prefixed with "/api".
 * </p>
 */
@RestController
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
    public Map<String, String> helloWorld() {
        // Creating a map to return
        Map<String, String> response = new HashMap<>();
        response.put("message", "Hello, World!");
        return response;
    }

    /**
     * Endpoint to shorten a given original URL.
     *
     * @param originalUrlRequest the request DTO containing the original URL
     * @return a ResponseEntity containing the shortened URL wrapped in a DTO
     */
    @PostMapping("/link")
    public ResponseEntity<ShortUrlResponse> createLink(@RequestBody OriginalUrlRequest originalUrlRequest) {
        // Shorten the provided URL using the LinkService
        Link link = linkService.shortenUrl(originalUrlRequest.getOriginalUrl());

        // Create a response DTO
        ShortUrlResponse response = new ShortUrlResponse(link.getShortenedLink(), link.getExpirationDate());

        // Return the response with HTTP status 200 (OK)
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint to retrieve the original URL for a given shortened URL.
     *
     * @param shortUrl the request String containing the shortened URL
     * @return a ResponseEntity containing the original URL wrapped in a DTO
     */
    @GetMapping("/link/{shortUrl}")
    public ResponseEntity<OriginalUrlResponse> getOriginalUrl(@PathVariable String shortUrl) {
        // Retrieve the original URL using the LinkService
        String originalUrl = linkService.getOriginalUrl(shortUrl);

        // Create a response DTO with the original URL
        OriginalUrlResponse response = new OriginalUrlResponse(originalUrl);

        // Return the response with HTTP status 200 (OK)
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint to redirect to the original URL.
     *
     * @param shortUrl String containing the shortened URL
     * @return RedirectView redirecting to the original URL.
     */
    @GetMapping("/{shortUrl}")
    public RedirectView redirectToOriginalUrl(@PathVariable String shortUrl) {
        // Retrieve the original URL using the LinkService
        String originalUrl = linkService.getOriginalUrl(shortUrl);
        return new RedirectView(originalUrl);
    }
}