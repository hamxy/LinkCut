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

@RestController
@RequestMapping("/api")
public class LinkController {

    private LinkService linkService;

    @Autowired
    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }

    @GetMapping("/")
    public String helloWorld() {
        return "Hello world";
    }

    @PostMapping("/shorten")
    public ResponseEntity<ShortUrlResponse> shortenUrl(@RequestBody OriginalUrlRequest originalUrlRequest) {
        // Create shortened URL with linkService class
        String shortenedUrl = linkService.shortenUrl(originalUrlRequest.getOriginalUrl());
        // Create response dto
        ShortUrlResponse response = new ShortUrlResponse(shortenedUrl);
        // return response
        return ResponseEntity.ok(response);
    }

    @PostMapping("/original")
    public ResponseEntity<OriginalUrlResponse> getOriginalUrl(@RequestBody ShortUrlRequest shortUrlRequest) {
        // Retrieve originalUrl
        String originalUrl = linkService.getOriginalUrl(shortUrlRequest.getShortUrl());
        // Create response dto
        OriginalUrlResponse response = new OriginalUrlResponse(originalUrl);
        // return response
        return ResponseEntity.ok(response);

    }
}
